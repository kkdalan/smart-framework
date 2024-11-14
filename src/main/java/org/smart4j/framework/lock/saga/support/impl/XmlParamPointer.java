package org.smart4j.framework.lock.saga.support.impl;

import java.io.StringWriter;
import java.util.Objects;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.smart4j.framework.lock.saga.support.ParamPointer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XmlParamPointer implements ParamPointer {

	private Document xml;
	private final String xpath;

	public XmlParamPointer(Document xml, String xpath) {
		Objects.requireNonNull(xml, "xml cannot be null.");
		Objects.requireNonNull(xpath, "xpath cannot be null.");

		this.xml = xml;
		this.xpath = xpath;
	}

	@Override
	public String resolveValue() {
		try {
			XPath xPath = XPathFactory.newInstance().newXPath();
			Node node = (Node) xPath.evaluate(xpath, xml, XPathConstants.NODE);
			return node != null ? node.getTextContent() : null;
		} catch (Exception e) {
			throw new RuntimeException("Error resolving value from XML", e);
		}
	}

	@Override
	public String injectValue(String value) {
		try {
			XPath xPath = XPathFactory.newInstance().newXPath();
			Node parentNode = (Node) xPath.evaluate(xpath.substring(0, xpath.lastIndexOf('/')), xml,
					XPathConstants.NODE);
			String lastKey = xpath.substring(xpath.lastIndexOf('/') + 1);

			if (parentNode == null || parentNode.getNodeType() != Node.ELEMENT_NODE) {
				throw new RuntimeException("Invalid path: " + xpath);
			}

			// 查找或創建目標節點
			Element element = (Element) ((Element) parentNode).getElementsByTagName(lastKey).item(0);
			if (element == null) {
				element = xml.createElement(lastKey);
				parentNode.appendChild(element);
			}

			// 設置節點值
			element.setTextContent(value);

			// 返回更新後的 XML 作為字串
			return convertDocumentToString(xml);
		} catch (Exception e) {
			throw new RuntimeException("Error injecting value into XML", e);
		}
	}

	private String convertDocumentToString(Document doc) {
		try {
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			return writer.getBuffer().toString();
		} catch (Exception e) {
			throw new RuntimeException("Error converting XML Document to String", e);
		}
	}

	@Override
	public Object getData() {
		return xml;
	}
}

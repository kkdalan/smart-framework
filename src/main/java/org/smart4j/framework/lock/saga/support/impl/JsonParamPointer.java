package org.smart4j.framework.lock.saga.support.impl;

import java.util.Objects;

import org.smart4j.framework.lock.saga.support.ParamPointer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonParamPointer implements ParamPointer {

	private JsonNode json;
	private final String jsonPointer;

	public JsonParamPointer(JsonNode json, String jsonPointer) {
		Objects.requireNonNull(json, "json cannot be null.");
		Objects.requireNonNull(jsonPointer, "jsonPointer cannot be null.");

		this.json = json;
		this.jsonPointer = jsonPointer;
	}

	@Override
	public String resolveValue() {
		try {
			JsonNode node = json.at(jsonPointer);
			return node.isMissingNode() ? null : node.asText();
		} catch (Exception e) {
			throw new RuntimeException("Error resolving value from JSON", e);
		}
	}

	@Override
	public String injectValue(String value) {
		try {
			String parentPath = jsonPointer.substring(0, jsonPointer.lastIndexOf('/'));
			String lastKey = jsonPointer.substring(jsonPointer.lastIndexOf('/') + 1);

			// 確保父節點存在並且是 ObjectNode
			JsonNode parentNode = json.at(parentPath);
			if (parentNode.isMissingNode() || !parentNode.isObject()) {
				throw new RuntimeException("Invalid jsonPointer: " + jsonPointer);
			}

			// 將父節點轉換為 ObjectNode 以進行修改
			ObjectNode mutableParentNode = (ObjectNode) parentNode;
			mutableParentNode.put(lastKey, value);

			// 如果需要，你可以返回更新後的 JSON 資料
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(json);
		} catch (Exception e) {
			throw new RuntimeException("Error injecting value into JSON", e);
		}
	}
	
	@Override
	public Object getData() {
		return json;
	}
}

package org.smart4j.framework.util.nlp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.semgraph.SemanticGraphEdge;
import edu.stanford.nlp.util.CoreMap;

public class SubjectVerbObjectExtractor {

	public static void main(String[] args) {

		// 初始化 StanfordCoreNLP，設定需要的分析器
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize,ssplit,pos,parse,lemma");
//            props.setProperty("tokenize.language", "zh"); // 設定為中文
//            props.setProperty("segment.model", "edu/stanford/nlp/models/segmenter/chinese/ctb.gz"); // 中文分詞模型
//            props.setProperty("pos.model", "edu/stanford/nlp/models/pos-tagger/chinese-distsim/chinese-distsim.tagger"); // 中文詞性標註
//            props.setProperty("parse.model", "edu/stanford/nlp/models/srparser/chineseSR.ser.gz"); // 中文依存句法分析

		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		// 輸入中文文字
		Scanner scanner = new Scanner(System.in);
		System.out.println("-------------------------");
		System.out.println("Please enter a sentences：");
		String text = scanner.nextLine();
		text = text.toLowerCase();
		
		// 建立一個 Annotation，包含輸入的文字
		Annotation document = new Annotation(text);

		// 透過管線進行分析
		pipeline.annotate(document);
		
		
		List<String> allSvoPairs = new ArrayList<>();

		// 解析句子中的詞性與句法依賢
		List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
		for (CoreMap sentence : sentences) {
			// 取得標註的單詞與詞性
			List<CoreLabel> tokens = sentence.get(CoreAnnotations.TokensAnnotation.class);
			List<String> mergedNouns = new ArrayList<>(); // 儲存合併的名詞

			StringBuilder currentNounPhrase = new StringBuilder();
			for (int i = 0; i < tokens.size(); i++) {
				CoreLabel token = tokens.get(i);
				String word = token.word();
				String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
				String lemma = token.get(CoreAnnotations.LemmaAnnotation.class); // 詞性還原為單數
				
				// 判斷是否為名詞詞性 (可以根據具體的標註情況擴展)
				if (pos.startsWith("NN")) {
					// 如果當前名詞短語不為空，將詞加到名詞短語中
					if (currentNounPhrase.length() > 0) {
						currentNounPhrase.append(" ").append(lemma);
					} else {
						currentNounPhrase.append(lemma);
					}
				} else {
					// 如果當前名詞短語不為空，儲存當前的名詞短語
					if (currentNounPhrase.length() > 0) {
						mergedNouns.add(currentNounPhrase.toString());
						currentNounPhrase.setLength(0); // 清空名詞短語
					}
				}
			}
			// 處理最後的名詞短語
			if (currentNounPhrase.length() > 0) {
				mergedNouns.add(currentNounPhrase.toString());
			}

			// 輸出合併後的名詞
			System.out.println("合併的名詞： " + mergedNouns);

			// 取得依存解析 (Dependency Parse)
			SemanticGraph dependencies = sentence.get(SemanticGraphCoreAnnotations.EnhancedDependenciesAnnotation.class);
//			System.out.println("依存解析：");
//			System.out.println(dependencies.toString(SemanticGraph.OutputFormat.LIST));

			// 存儲主詞 + 動詞 + 受詞的組合
			Map<String, String> subjects = new HashMap<>();
			Map<String, String> objects = new HashMap<>();
			Map<String, String> verbs = new HashMap<>(); // 儲存動詞
			List<String> svoPairs = new ArrayList<>();

			// 提取主詞 (nsubj)、受詞 (dobj) 和動詞
			for (SemanticGraphEdge edge : dependencies.edgeListSorted()) {
				
				String relation = edge.getRelation().toString();
				String verb = edge.getSource().lemma(); // 動詞

				// 主詞關係
				if (relation.equals("nsubj")) {
					String subject = edge.getTarget().lemma(); // 主詞
					subjects.put(verb, subject); // 存儲動詞和對應的主詞
				}
				// 受詞關係
				if (relation.equals("obj")) {
					String object = edge.getTarget().lemma(); // 受詞
					objects.put(verb, object); // 存儲動詞和對應的受詞
				}
				// 儲存所有動詞
				if (relation.equals("xcomp") || relation.equals("dep")) {
					// 遇到兩個動詞連在一起時，使用後一個動詞
					String dependentVerb = edge.getTarget().lemma(); // 依賴的動詞
					verbs.put(dependentVerb, verb); // 儲存兩個動詞間的依賴關係
				}
			}

			// 匹配主詞和受詞，並只使用後面的動詞來生成 SVO 組合
			for (String verb : subjects.keySet()) {
				// 若動詞是前後相連，使用後面的動詞
				if (verbs.containsKey(verb)) {
					verb = verbs.get(verb); // 使用後面的動詞
				}

				String subject = subjects.get(verb);
				String object = objects.get(verb); // 可能有或沒有受詞
				
				if (object != null) {
					// 動詞有主詞和受詞時
					svoPairs.add(subject + " + " + verb + " + " + object);
				} else {
					// 只有主詞和動詞時
//					svoPairs.add(subject + " + " + verb);
				}
			}

//			if (svoPairs.isEmpty()) {
//				System.out.println("未找到 [主詞 + 動詞 + 受詞] 的組合");
//			} else {
//				System.out.println("[主詞 + 動詞 + 受詞] 組合: " + svoPairs);
//			}
			
			allSvoPairs.addAll(svoPairs);
		}
		
		// 輸出主詞 + 動詞 + 受詞的組合
		System.out.println("所有 [主詞 + 動詞 + 受詞] 的組合:");
		allSvoPairs.forEach(svo -> System.out.println(svo));
	}
}

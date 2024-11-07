package org.smart4j.framework.util.nlp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class NounVerbExtractor {
	
    public static void main(String[] args) {
    	
        // 初始化 StanfordCoreNLP，設定我們需要的分析器
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma");
        
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // 輸入文字
        Scanner scanner = new Scanner(System.in);
        System.out.println("請輸入一段文字：");
        String text = scanner.nextLine();

        // 建立一個Annotation，包含輸入的文字
        Annotation document = new Annotation(text);

        // 透過管線進行分析
        pipeline.annotate(document);

        // 存儲名詞和動詞
        List<String> nouns = new ArrayList<>();
        List<String> verbs = new ArrayList<>();
        
        // 解析句子中的詞性
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap sentence : sentences) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                String lemma = token.get(CoreAnnotations.LemmaAnnotation.class); // 詞性還原為單數
                
                // 判斷詞性，NN 開頭的是名詞，VB 開頭的是動詞
                if (pos.startsWith("NN")) {
                    nouns.add(lemma);
                } else if (pos.startsWith("VB")) {
                    verbs.add(lemma);
                }
            }
        }
        
        Map<String,Integer> nounsStat = WordCountUtils.statWords(nouns);
        Map<String,Integer> verbsStat = WordCountUtils.statWords(verbs);
        
        // 顯示結果
        System.out.println("名詞: " + nouns);
		nounsStat.entrySet().forEach(e -> System.out.println(e.getKey() + ":" + e.getValue()));
		
		System.out.println("動詞: " + verbs);
//		verbsStat.entrySet().forEach(e -> System.out.println(e.getKey() + ":" + e.getValue()));
    }
    
    
}

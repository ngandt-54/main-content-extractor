package parser;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class JSoupParser {
	private Document doc;
	private static float textLength;
	private static float htmlLength;
	private static float domLevel;
	private static float maxHeight;
	private static float totalLink;
	private static float totalDiv;
	private static float totalInteractive;
	private static float totalImg;
	private static float totalForm;
	private static float totalTable;
	private static float totalP;
	
	public JSoupParser() {
		textLength = 0;
		htmlLength = 0;
		domLevel = 1;
		maxHeight = 0;
		totalLink = 0;
		totalDiv = 0;
		totalInteractive = 0;
		totalImg = 0;
		totalForm = 0;
		totalTable = 0;
		totalP = 0;
	}
	
	public void parseHTML(String html){
		doc = Jsoup.parse(html);
	}

	public void parseURL(String url) throws IOException {
		doc = Jsoup.connect(url)
				.data("query", "Java")
				.userAgent("Mozilla")
				.cookie("auth", "token")
				.timeout(30000)
				.get();
	}
	
	public void parseFile(String fname) throws IOException {
		doc = Jsoup.parse(new File(fname), "UTF-8");
	}
	
	public Document getDocument() { 
		return doc; 
	}
	
	public String getTitle() { 
		return doc.title(); 
	}
	
	public Element getBody() {
		return doc.body();
	} 
	
	public Element getElementById(String id) { 
		return doc.getElementById(id); 
	}
	
	public Elements getElementsByTag(String tag) { 
		return doc.getElementsByTag(tag); 
	}
	
	@SuppressWarnings("unused")
	private void count() {
		for(Element select : getElementsByTag("select")) { totalInteractive++; }
		for(Element input : getElementsByTag("input")) { totalInteractive++; }
		for(Element img : getElementsByTag("img")) { totalImg++; }
		for(Element form : getElementsByTag("form")) { totalForm++; }
		for(Element table : getElementsByTag("table")) { totalTable++; }
		for(Element div : getElementsByTag("div")) { totalDiv++; }
		for(Element link : getElementsByTag("link")) { totalLink++; }
		for(Element a : getElementsByTag("a")) { totalLink++; }
		for(Element p : getElementsByTag("p")) { totalP++; }
	}
	
	@SuppressWarnings("unused")
	private static void traverse(Element element) {
		float currentHeight = domLevel; 
		element.attr("domHeight", Float.toString(domLevel));
		
		List<Element> es = element.children();
		if(es.isEmpty() && domLevel > maxHeight)
			maxHeight = domLevel;
		else 
			domLevel++;

		for(int i = 0; i < es.size(); i++) {
			Element e = es.get(i);
			
			float eHtmlLength = e.html().length();
			float innerHtml = eHtmlLength/htmlLength;
			e.attr("innerHTML", Float.toString(innerHtml));
			
			float eTextLength = e.text().length();
			float innerText = eTextLength/textLength;
			e.attr("innerText", Float.toString(innerText));
			
			float interactiveNum = 0;
			float imgNum = 0;
			float formNum = 0;
			float tableNum = 0;
			float divNum = 0;
			float linkNum = 0;
			float linkTextLength = 0;
			float pNum = 0;
			
			for(Element select : e.getElementsByTag("select")) { interactiveNum++; }
			for(Element input : e.getElementsByTag("input")) { interactiveNum++; }
			for(Element img : e.getElementsByTag("img")) { imgNum++; }
			for(Element form : e.getElementsByTag("form")) { formNum++; }
			for(Element table : e.getElementsByTag("table")) { tableNum++; }
			for(Element div : e.getElementsByTag("div")) { divNum++; }
			for(Element link : e.getElementsByTag("link")) { 
				linkNum++;
				linkTextLength += link.text().length();
			}
			for(Element a : e.getElementsByTag("a")) { 
				linkNum++;
				linkTextLength += a.text().length();
			}
			for(Element p : e.getElementsByTag("p")) { pNum++; }

			interactiveNum = interactiveNum > 0 ? interactiveNum/totalInteractive : 0;
			imgNum = imgNum > 0 ? imgNum/totalImg : 0;
			formNum = formNum > 0 ? formNum/totalForm : 0;
			tableNum = tableNum > 0 ? tableNum/totalTable : 0;
			divNum = divNum > 0 ? divNum/totalDiv : 0;
			linkNum = linkNum > 0 ? linkNum/totalLink : 0;
			float linkToText = e.text().length() > 0 ? linkTextLength/e.text().length() : 0;
			pNum = pNum > 0 ? pNum/totalP : 0;
			
			e.attr("interactiveNum", Float.toString(interactiveNum));
			e.attr("imgNum", Float.toString(imgNum));
			e.attr("formNum", Float.toString(formNum));
			e.attr("tableNum", Float.toString(tableNum));
			e.attr("divNum", Float.toString(divNum));
			e.attr("linkNum", Float.toString(linkNum));
			e.attr("linkToText", Float.toString(linkToText));
			e.attr("pNum", Float.toString(pNum));
			
			traverse(e);
		}
		
		domLevel = currentHeight;
	}
	
	public String process(String url) throws IOException {
		parseURL(url);
		Element body = getBody();
		textLength = body.text().length();
		htmlLength = body.html().length();
	
		count();
		traverse(body);		
		for(Element e : body.getAllElements()) {
			float eDomLevel = Float.parseFloat(e.attr("domHeight"));
			e.attr("domHeight", Float.toString(eDomLevel/maxHeight));
		}
		return body.outerHtml();
	}
	
//	public static void main(String[] args) {
//		JSoupParser parser = new JSoupParser();
//		String url ="http://vnexpress.net/gl/xa-hoi/2012/04/giay-phut-lua-bao-trum-tiem-vang-lam-4-nguoi-chet/"; 
//		try {
//			FileOutputStream fout = new FileOutputStream("input.html");
//			fout.write(parser.process(url).getBytes());
//			fout.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
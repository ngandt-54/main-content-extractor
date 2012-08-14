import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;


public class JSoupParser {
	private Document doc;
	private static int textLength;
	private static int htmlLength;
	
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
	
	private static void traverse(Element element) {
		List<Element> es = element.children();
		for(int i = 0; i < es.size(); i++) {
			Element e = es.get(i);
			
			float eHtmlLength = e.html().length();
			float innerHtml = eHtmlLength/htmlLength;
			e.attr("innerHTML", Float.toString(innerHtml));
			
			float eTextLength = e.text().length();
			float innerText = eTextLength/textLength;
			e.attr("innerText", Float.toString(innerText));
			
			int interactiveNum = 0;
			for(Element select : e.getElementsByTag("select"))
				interactiveNum++;
			for(Element input : e.getElementsByTag("input"))
				interactiveNum++;
			e.attr("interactiveNum", Integer.toString(interactiveNum));
			
			int imgNum = 0;
			for(Element img : e.getElementsByTag("img")) 
				imgNum++;
			e.attr("imgNum", Integer.toString(imgNum));
			
			int formNum = 0;
			for(Element form : e.getElementsByTag("form"))
				formNum++;
			e.attr("formNum", Integer.toString(formNum));
			
			int optionNum = 0;
			for(Element option : e.getElementsByTag("option"))
				optionNum++;
			e.attr("optionNum", Integer.toString(optionNum));
			
			int tableNum = 0;
			for(Element option : e.getElementsByTag("table"))
				tableNum++;
			e.attr("tableNum", Integer.toString(tableNum));
			
			int divNum = 0;
			for(Element option : e.getElementsByTag("div"))
				tableNum++;
			e.attr("divNum", Integer.toString(divNum));
			
			int linkNum = 0;
			for(Element option : e.getElementsByTag("link"))
				tableNum++;
			e.attr("linkNum", Integer.toString(linkNum));
			
			int pNum = 0;
			for(Element option : e.getElementsByTag("p"))
				tableNum++;
			e.attr("pNum", Integer.toString(pNum));
			
			traverse(e);
		}
	}
	
	public static void main(String[] args) throws IOException{
		JSoupParser parser = new JSoupParser();
		parser.parseURL("http://vnexpress.net/gl/xa-hoi/2012/04/giay-phut-lua-bao-trum-tiem-vang-lam-4-nguoi-chet/");
		Element body = parser.getBody();
		textLength = body.text().length();
		htmlLength = body.html().length();
//		System.out.println("Body length: " + textLength + "\n" + "Html length: " + htmlLength);
		traverse(body);
		
		System.out.println(body.outerHtml());
	}
}

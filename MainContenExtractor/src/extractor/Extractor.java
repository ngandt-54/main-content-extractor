package extractor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import parser.JSoupParser;

public class Extractor {
//	private Float getFloatAttr(Element e, String attr) {
//		return Float.parseFloat(e.attr(attr));
//	}
	
	public void extract(Element e) {
		List<Element> es = e.children();
		if(es.isEmpty()) {
			String tag = e.attr("tagname");
			float domHeight = Float.parseFloat(e.attr("domheight"));
			float innerHtml = Float.parseFloat(e.attr("innerhtml"));
			float innerText = Float.parseFloat(e.attr("innertext"));
			float interNum = Float.parseFloat(e.attr("interactivenum"));
			float imgNum = Float.parseFloat(e.attr("imgnum"));
			float formNum = Float.parseFloat(e.attr("formnum"));
			float tableNum = Float.parseFloat(e.attr("tablenum"));
			float divNum = Float.parseFloat(e.attr("divnum"));
			float linkNum = Float.parseFloat(e.attr("linknum"));
			float pNum = Float.parseFloat(e.attr("pnum"));
			float linkToText = Float.parseFloat(e.attr("linktotext"));
	
			if(pNum <= 0) {
				if(domHeight <= 0.4375) e.empty();
				
				else {
					if(linkToText <= 0.078197) {
						if(innerText <= 0.000205) {
							if(imgNum <= 0.015152) e.empty();
							else {
								if(linkNum <= 0.00823) e.attr("species", "main");
								else e.empty();
							}
							
						} else {
							if(divNum <= 0.014124) {
								if(tag.equals("div")) {
									if(domHeight <= 0.55) e.empty(); 
									else {
										if(innerText <= 0.00438) {
											if(domHeight > 0.666667) e.empty();
											else e.attr("species", "main");
										} else e.attr("species", "main");
									}
									
								} else if(tag.equals("td") || tag.equals("h1") || tag.equals("center") ||
										tag.equals("script") || tag.equals("noscript") || tag.equals("p") ||
										tag.equals("a") || tag.equals("ul") || tag.equals("style") ||
										tag.equals("em") || tag.equals("span") || tag.equals("strong")) {
									e.attr("species", "main");
									
								} else if(tag.equals("table")) {
									if(tableNum <= 0.095238) e.empty();
									else e.attr("species", "main");
									
								} else if(tag.equals("h2") || tag.equals("h3") || tag.equals("form")) {
									e.empty();
								} 
								
							} else e.empty();
						}
						
					} else e.empty();
				}
			} else {
				if(linkToText > 0.163636)
					e.empty();
				
				else {
					if(domHeight <= 0.411765) {
						if(pNum > 0.010989) e.empty();
						else {
							if(domHeight <= 0.325) e.empty();
							else e.attr("species", "main");
						}
						
					} else {
						if(tag.equals("div") || tag.equals("td") || tag.equals("h1") ||
								tag.equals("center") || tag.equals("script") || tag.equals("noscript") ||
								tag.equals("p") || tag.equals("h2") || tag.equals("h3") || 
								tag.equals("ul") || tag.equals("style") || tag.equals("form") ||
								tag.equals("em") || tag.equals("span") || tag.equals("strong")) {
							e.attr("species", "main");
							
						} else if(tag.equals("table")) {
							if(imgNum <= 0) e.remove();
							else e.attr("species", "main");
							
						} else if(e.attr("tagname").equals("a")) e.remove();
					}
				}
			}
		} else for(Element element : es) 
			extract(element);
		
		// remove empty element
//		if(e.children().isEmpty())
//			e.remove();
	}
	
	
	public static void main(String[] args) throws IOException {
		JSoupParser parser = new JSoupParser();
		String url ="http://hn.24h.com.vn/tin-tuc-trong-ngay/bau-kien-bi-bat-de-dieu-tra-c46a477685.html";
			parser.process(url);
			Element body = parser.getBody();
			Extractor extractor = new Extractor();
			extractor.extract(body);
		
			FileOutputStream fout = new FileOutputStream("input.html");
			fout.write(body.outerHtml().getBytes());
			fout.close();
	}
}

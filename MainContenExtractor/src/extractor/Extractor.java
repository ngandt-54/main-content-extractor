package extractor;

import java.io.FileOutputStream;
import java.io.IOException;

import org.jsoup.nodes.Element;

import parser.JSoupParser;
import weka.classifiers.WekaClassifier;

public class Extractor {
	public void extract(Element element) throws Exception  {
		for(Element e : element.children()) {
			
			// tuong ung voi danh sach Attributes trong file model. 
			String[] attrs = {e.tagName(), e.attr("domheight"), e.attr("innerhtml"), 
					e.attr("innertext"), e.attr("interactivenum"), e.attr("imgnum"), 
					e.attr("formnum"), e.attr("tablenum"), e.attr("divnum"), e.attr("linknum"),
					e.attr("linknum"), e.attr("pnum"), e.attr("linktotext")};
			
			double p = WekaClassifier.classify(attrs);
			if(p == 1) e.attr("species", "noisy");
			else if(p == 0) extract(e); // undefined
			else if(p == 2) e.attr("species", "main");
		}
	}
	
	public void clean(Element e) {
		System.out.println(e.tagName());
		System.out.println(e.attr("domHeight"));
		System.out.println("species: " + e.attr("species"));
		if(e.attr("species").equals("noisy")) {
			e.empty();
			System.out.println(e.outerHtml());
		}
		for(Element element : e.children()) 
			clean(element);
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < 15; i++)
			for(int j = 0; j < 100; j++) {
				try {
					JSoupParser parser = new JSoupParser();
					String infile ="data/crawl/html/page" + i + "/url" + j + ".html";
						parser.process(infile);
					Element body = parser.getBody();
					
					Extractor extractor = new Extractor();
					try {
						extractor.extract(body);
					} catch (Exception e) {
						e.printStackTrace();
					}
					extractor.clean(body);
					String outfile = "data/mloutput/page" + i + "/text" + j;
					FileOutputStream fout = new FileOutputStream(outfile);
					fout.write(body.text().getBytes());
					fout.close();
				} catch (IOException e) {
//					e.printStackTrace();
					continue;
				}
				
			}
	}
}

/**
 *  Cac buoc edit file WekaClassifier.java:
 *  	1. Xoa toan bo noi dung trong file WekaClassifier.java
 *  	2. Copy phan code tu file model, paste vao file WekaClassifier.java
 *  	3. Xoa class WekaWrapper
 *  	4. Chuyen class WekaClassifier ve dang public
 *  	5. Sua cac tu Object trong file thanh tu String (dung` chuc' nang find and replace bang cach nhan ctrl + F)
 *  	6. Sua "((Double) " thanh  "Double.parseDouble(" (ctrl + F)
 *  	7. Sua ".doubleValue() " thanh " " (ctrl + F)
 *  	8. Chua nghi ra
 */

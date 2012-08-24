package parser;

import java.io.IOException;

import org.jsoup.nodes.Element;

public class autoTag {

	
	public static void main(String[] args) throws IOException {
		JSoupParser parser = new JSoupParser();
		String url ="http://hn.24h.com.vn/tin-tuc-trong-ngay/bau-kien-bi-bat-de-dieu-tra-c46a477685.html";
		parser.process(url);
		
		Element body = parser.getBody();
		String domHeight = "";
		for(Element e : body.getAllElements())
			if(e.attr("species").equals("main")) {
				domHeight = e.attr("domheight");
			}
		
		for(Element e : body.getAllElements())
			if(e.attr("domheight").equals(domHeight) && e.attr("species").equals(""))
				e.attr("species", "noisy");
	}

}

package parser;

import java.io.FileOutputStream;
import java.io.IOException;

import org.jsoup.nodes.Element;

public class autoTag {
	
	public static void main(String[] args) throws IOException {
		
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 100; j++) {
				String fname = "data/trainingset/raw/page" + i + "/url" + j + ".html";
				String outfile = "data/trainingset/noisyadded/page" + i + "/url" + j + ".html";

				try {
					JSoupParser parser = new JSoupParser();
					parser.parseFile(fname);

					Element body = parser.getBody();
					String domHeight = "";
					for(Element e : body.getAllElements())
						if(e.attr("species").equals("main")) {
							domHeight = e.attr("domheight");
						}
					
					for(Element e : body.getAllElements())
						if(e.attr("domheight").equals(domHeight) && e.attr("species").equals(""))
							e.attr("species", "noisy");

					FileOutputStream buf = new FileOutputStream(outfile);
					buf.write(body.outerHtml().getBytes());
					buf.close();
					
				} catch (IOException e) {
//					e.printStackTrace();
					continue;
				} 
			}
		}
	}
}

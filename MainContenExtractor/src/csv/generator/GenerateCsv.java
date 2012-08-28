package csv.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
 
public class GenerateCsv {
	private static void addTag(Element e) {
		String attr = e.attr("species"); 
		if(!attr.equals("")) {
			for(Element child : e.children()) {
				child.attr("species", attr);
			}
		}
		
		for(Element child : e.children())
			addTag(child);
	}
	
	public static void main(String[] args) {
		
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 100; j++) {
				String fname = "data/trainingset/noisyadded/page" + i + "/url" + j + ".html";
				String fname2 = "data/addedtag/page" + i + "/url" + j + ".html" ;
				String outfile = "outvtv3.csv";
				try {
					Document doc = Jsoup.parse(new File(fname), "UTF-8");
					Element body = doc.body();

					System.out.println("start adding tag");
					addTag(body);
					
					File file2 = new File(fname2);
					FileOutputStream buf = new FileOutputStream(file2);
					buf.write(body.outerHtml().getBytes());
					buf.close();
					

					Document doc2 = Jsoup.parse(new File(fname2), "UTF-8");
					File file = new File(outfile);
					FileOutputStream fout = new FileOutputStream(file,true);

					String header = "TagName,type,domHeight,innerHtml,innerText,interactiveNum," +
							"ImgNum,FormNum,TableNum,DivNum,linkNum,Pnum,linkToText,species" + 
							"\n";
					if(file.length()==0){
						fout.write(header.getBytes());
					}
					for(Element e : doc.getAllElements()) {
						if(!e.attr("Species").equals("")) {
							// ghi cac thuoc tinh tuong ung ra file csv
							String attr =  e.tagName() + "," + e.attr("domheight") + "," 
									+ e.attr("type") + "," + e.attr("innerhtml") + "," 
									+ e.attr("innertext") + ","	+ e.attr("interactivenum") + ","
									+ e.attr("imgnum") + "," + e.attr("formnum") + ","
									+ e.attr("tablenum") + "," + e.attr("divnum") + "," 
									+ e.attr("linknum") + "," + e.attr("pnum") + "," 
									+ e.attr("linktotext") + "," + e.attr("species") + "\n";
							fout.write(attr.getBytes());
						}
					}
					
					for(Element e : doc2.getAllElements()) {
						if(e.attr("Species").equals("")) {
							String attr =  e.tagName() + "," + e.attr("domheight") + "," 
									+ e.attr("type") + "," + e.attr("innerhtml") + "," 
									+ e.attr("innertext") + ","	+ e.attr("interactivenum") + ","
									+ e.attr("imgnum") + "," + e.attr("formnum") + ","
									+ e.attr("tablenum") + "," + e.attr("divnum") + "," 
									+ e.attr("linknum") + "," + e.attr("pnum") + "," 
									+ e.attr("linktotext") + "," + "undefined" + "\n";
							fout.write(attr.getBytes());
						}
					}
					
					fout.close();

				} catch (IOException e) {
//					e.printStackTrace();
					continue;
				} 
			}
		}
	}
}
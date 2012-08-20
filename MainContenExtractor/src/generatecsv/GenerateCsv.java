package generatecsv;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
 
public class GenerateCsv {
	public static void main(String[] args) {
		String fname = "input.html";
		try {
			Document doc = Jsoup.parse(new File(fname), "UTF-8");
			FileOutputStream fout = new FileOutputStream("output.csv");
			
			String header = "TagName,domHeight,innerHtml,innerText,interactiveNum," +
					"ImgNum,FormNum,TableNum,DivNum,linkNum,Pnum,linkToText,species" + 
					"\n";
			fout.write(header.getBytes());

			for(Element e : doc.getAllElements()) {
				if(!e.attr("Species").equals("")) {
					// TODO: ghi cac thuoc tinh tuong ung ra file csv
					String attr =  e.tagName() + "," + e.attr("domheight") + ","
							+ e.attr("innerhtml") + "," + e.attr("innertext") + ","
							+ e.attr("interactivenum") + "," + e.attr("imgnum") + ","
							+ e.attr("formnum") + "," + e.attr("tablenum") + ","
							+ e.attr("divnum") + "," + e.attr("linknum") + ","
							+ e.attr("pnum") + "," + e.attr("linktotext") + ","
							+ e.attr("species") + "\n";
					fout.write(attr.getBytes());
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
}
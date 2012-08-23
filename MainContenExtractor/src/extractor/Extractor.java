package extractor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.jsoup.nodes.Element;

import parser.JSoupParser;

public class Extractor {
//	private Float getFloatAttr(Element e, String attr) {
//		return Float.parseFloat(e.attr(attr));
//	}
	
	public void extract(Element e) {
		List<Element> es = e.children();
		if(es.isEmpty()) {
			if(Float.parseFloat(e.attr("pnum")) <= 0) {
				if(Float.parseFloat(e.attr("domheight")) <= 0.4375) 
					e.remove();
				else {
					if(Float.parseFloat(e.attr("linktotext")) <= 0.078197) {
						if(Float.parseFloat(e.attr("innertext")) <= 0.000205) {
							if(Float.parseFloat(e.attr("imgnum")) <= 0.015152)
								e.remove();
							else if(Float.parseFloat(e.attr("linknum")) > 0.00823)
								e.remove();
						} else {
							if(Float.parseFloat(e.attr("divnum")) <= 0.014124) {
								if(e.attr("tagname").equals("div")) {
									if(Float.parseFloat(e.attr("domheight")) <= 0.55) 
										e.remove();
									else if(Float.parseFloat(e.attr("innertext")) <= 0.00438) {
										if(Float.parseFloat(e.attr("domheight")) > 0.666667)
											e.remove();
									}
								} else if(e.attr("tagname").equals("table")) {
									if(Float.parseFloat(e.attr("tablenum")) <= 0.095238) 
										e.remove();
								} else if(e.attr("tagname").equals("h2")) {
									e.remove();
								} else if(e.attr("tagname").equals("h3")) {
									e.remove();
								} else if(e.absUrl("tagname").equals("form")) {
									e.remove();
								} 
							} else
								e.remove();
						}
					} else
						e.remove();
				}
			} else {
				if(Float.parseFloat(e.attr("linktotext")) > 0.163636)
					e.remove();
				else {
					if(Float.parseFloat(e.attr("domheight")) <= 0.411765) {
						if(Float.parseFloat(e.attr("pnum")) > 0.010989)
							e.remove();
						else if(Float.parseFloat(e.attr("domheight")) <= 0.325)
								e.remove();
					} else {
						if(e.attr("tagname").equals("table")) {
							if(Float.parseFloat(e.attr("imgnum")) <= 0)
								e.remove();
						} else if(e.attr("tagname").equals("a")) 
							e.remove();
					}
				}
			}
		} else for(Element element : es) 
			extract(element);
		
		// remove empty element
//		if(e.children().isEmpty())
//			e.remove();
	}
//	public void extract(Element e) {
//
//		Float domHeight = getFloatAttr(e, "domheight");
//		Float innerHtml = getFloatAttr(e, "innerhtml");
//		Float innerText = getFloatAttr(e, "innertext");
//		Float interactive = getFloatAttr(e, "interactivenum");
//		Float img = getFloatAttr(e, "imgnum");
//		Float divNum = getFloatAttr(e, "divnum");
//		Float tableNum = getFloatAttr(e, "tablenum");
//		Float linkNum = getFloatAttr(e, "linknum");
//		Float pnum = getFloatAttr(e, "pnum");
//		Float linkToText = getFloatAttr(e, "linktotext");
//
//		String tag = e.attr("tagname");
//		
//		if(getFloatAttr(e, "innertext") <= 0.366505) {
//
//			if(getFloatAttr(e, "domheight") <= 0.083333) {
//				String tagName = e.attr("tagname");
//				if(tagName.equals("#root") || tagName.equals("html") || tagName.equals("head")
//						|| tagName.equals("body") || tagName.equals("a") || tagName.equals("input")) { // undefined
//					for(Element child : e.children())
//						extract(child);
//				} else if (tagName.equals("div") || tagName.equals("img") || tagName.equals("noscript")) { // noisy
//					e.remove(); 
//				} else if(tagName.equals("script")) {
//					if(innerHtml > 0.004112)
//						e.remove();
//					else for(Element child : e.children())
//						extract(child);
//				}
//				
//			} else if(getFloatAttr(e, "domheight") <= 0.230769) {
//				if(innerHtml <= 0.000126) {
//					if(getFloatAttr(e, "interactivenum") <= 0.034483) {
//						if(tag.equals("#root") || tag.equals("html") || tag.equals("head") || tag.equals("body") 
//								|| tag.equals("a") || tag.equals("iframe") || tag.equals("link")) {
//							for(Element child : e.children())
//								extract(child);
//							
//						} else if (tag.equals("img") || tag.equals("input") || tag.equals("tbody") || tag.equals("tr")) {
//							e.remove();
//							
//						} else if(tag.equals("div")) {
//							if(getFloatAttr(e, "divnum") <= 0.004) {
//								if(getFloatAttr(e, "domheight") <= 0.111111) {
//									if(getFloatAttr(e, "divnum") <= 0.003484) e.remove();
//									else for(Element child : e.children())
//										extract(child);
//								} else e.remove();
//								
//							} else {
//								if( domHeight <= 0.181818) {
//									for(Element child : e.children())
//										extract(child);
//								} else if(domHeight <= 0.2) e.remove();
//								else for(Element child : e.children())
//									extract(child);
//							}
//							
//						} else if(tag.equals("script")) {
//							if(getFloatAttr(e, "domheight") <= 0.1)
//								e.remove();
//							else for(Element child : e.children())
//								extract(child);
//						} 
//					} else for(Element child : e.children())
//						extract(child);
//					
//				} else {
//					if(tag.equals("#root") || tag.equals("html") || tag.equals("head") || tag.equals("body")) {
//						for(Element child : e.children())
//							extract(child);
//						
//					} else if(tag.equals("div") || tag.equals("center") || tag.equals("a") || 
//							tag.equals("ul") || tag.equals("p") || tag.equals("table") || 
//							tag.equals("tbody") || tag.equals("tr") || tag.equals("noscript") || tag.equals("style")) {
//						e.remove();
//						
//					} else if(tag.equals("script")) {
//						if(innerHtml <= 0.001628) {
//							if(domHeight <= 0.119048) e.remove();
//							else if(domHeight <= 0.130435) {
//								for(Element child : e.children())
//									extract(child);
//							} else{
//								if(innerHtml <= 0.000936) e.remove();
//								else {
//									if(domHeight <= 0.176471) e.remove();
//									else if (domHeight <= 0.2) {
//										for(Element child : e.children())
//											extract(child);
//									} else {
//										if(innerHtml <= 0.001347) e.remove();
//										else for(Element child : e.children())
//											extract(child);
//									}
//								}
//							}
//							
//						} else e.remove();
//					}
//				}
//				
//			} else {
//				if(getFloatAttr(e, "pnum") <= 0) {
//					if(getFloatAttr(e, "linknum") <= 0) {
//						if(getFloatAttr(e, "innertext") <= 0.003174) {
//							if(tag.equals("#root") || tag.equals("html") || tag.equals("head")) {
//								for(Element child : e.children())
//									extract(child);
//								
//							} else if(tag.equals("div")) {
//								if(domHeight <= 0.764706) {
//									if(innerHtml <= 0.000136) {
//										if(getFloatAttr(e, "divnum") <= 0.007813) {
//											if(innerHtml <= 0.000007) {
//												
//												if(domHeight <= 0.23913) e.remove();
//												else if(domHeight <= 0.275) {
//													if(getFloatAttr(e, "divnum") > 0.006879) e.remove();
//													else for(Element child : e.children())
//														extract(child);
//													
//												} else if(domHeight <= 0.347826) e.remove();
//												else if(domHeight <= 0.4375) {
//													if(getFloatAttr(e, "divnum") <= 0.003268) e.remove();
//													else if(getFloatAttr(e, "divnum") <= 0.006993) {
//														for(Element child : e.children())
//															extract(child);
//													} else e.remove();
//												} else e.remove();
//												
//											} else e.remove();
//											
//										} else {
//											if(innerHtml <= 0.000058) e.remove();
//											else {
//												if(domHeight <= 0.565217) e.remove();
//												else for(Element child : e.children())
//													extract(child);
//											}
//										}
//										
//									} else if(innerHtml <= 0.000269) {
//										if(domHeight <= 0.555556) e.remove();
//										else if(domHeight <= 0.575) { 
//											for(Element child : e.children())
//												extract(child);
//										} else if(domHeight <= 588235) {
//											if(getFloatAttr(e, "divnum") <= 0.008333) e.remove();
//											else e.attr("species", "main");
//										} else e.remove();
//										
//									} else {
//										if(img <= 0.025) e.remove();
//										else {
//											if(domHeight <= 0.555556) e.remove();
//											else {
//												if(innerHtml <= 0.001639) e.remove();
//												else if(getFloatAttr(e, "innertext") <= 0) {
//													if(img <= 0.035714) e.attr("species", "main");
//													else e.remove();
//												} else e.remove();
//											}
//										}
//									}
//									
//								} else {
//									if(getFloatAttr(e, "divnum") <= 0.008772) e.remove();
//									else {
//										if(getFloatAttr(e, "interactivenum") <= 0.025641) {
//											if(innerHtml  <= 0.000558) e.attr("species", "main");
//											else if(innerHtml <= 0.004244) {
//												if(getFloatAttr(e, "innertext") <= 0.00106) e.remove();
//												else e.attr("species", "main");
//											} else e.attr("species", "main");
//										} else e.remove();
//									}
//								}
//								
//							} else if(tag.equals("img")) {
//								if(img <= 0.037037) {
//									if(domHeight <= 0.615385) e.remove();
//									else if(domHeight <= 0.695652) {
//										if(img <= 0.033333) e.remove();
//										else {
//											if(domHeight <= 0.630435) e.attr("species", "main");
//											else if(domHeight <= 0.652174) e.remove();
//											else e.attr("species", "main");
//										}
//									} else if(domHeight <= 0.705882) e.attr("species", "main");
//									else e.remove();
//									
//								} else if(img <= 0.039216) {
//									if(domHeight <= 0.826087) e.remove();
//									else if(domHeight <= 0.85) {
//										for(Element child : e.children())
//											extract(child);
//									} else if(domHeight <= 0.913044) e.remove();
//									else if(domHeight <= 0.925) e.attr("species", "main");
//									else e.remove();
//								} else e.remove();
//								
//							} else if(tag.equals("object") || tag.equals("embed") || tag.equals("ul") || 
//									tag.equals("li") || tag.equals("label") || tag.equals("form") ||
//									tag.equals("b") || tag.equals("textarea") || tag.equals("noscript") ||
//									tag.equals("select") || tag.equals("option") || tag.equals("h1") ||
//									tag.equals("g:plusone") || tag.equals("h2") || tag.equals("font") ||
//									tag.equals("h3") || tag.equals("map") || tag.equals("area") ||
//									tag.equals("style") || tag.equals("bb:sp-like") || tag.equals("u") ||
//									tag.equals("menu") || tag.equals("ie:menuitem") || tag.equals("meta") ||
//									tag.equals("title") || tag.equals("h4")) {
//								e.remove();
//								
//							} else if(tag.equals("param")) {
//								if(domHeight < 0.825) e.remove();
//								else e.attr("species", "main");
//								
//							} else if(tag.equals("span")) {
//								if(innerHtml <= 0.000435) {
//									if(domHeight <= 0.833333) e.remove();
//									else if(domHeight <= 0.857143) {
//										if(innerHtml <= 0.00001) {
//											if(domHeight <= 0.847826)  e.attr("species", "main");
//											else e.remove();
//										} else if(innerHtml <= 0.000115) e.remove();
//										else e.attr("species", "main");
//									} else e.remove();
//								} else {
//									if(domHeight <= 0.595238) e.remove();
//									else if(domHeight <= 0.611111 )  e.attr("species", "main");
//									else if(domHeight <= 0.642857) e.remove();
//									else if(domHeight <= 0.666667)  e.attr("species", "main");
//									else if(domHeight <= 0.804348) e.remove();
//									else e.attr("species", "main");
//								}
//								
//							} else if(tag.equals("input")) {
//								if(interactive <= 0.055556) e.remove();
//								
//								else if(interactive <= 0.060606) {
//									if(domHeight <= 0.25) e.remove();
//									else if(domHeight <= 0.285714) {
//										for(Element child : e.children())
//											extract(child);
//									} else e.remove();
//								
//								} else {
//									if(domHeight <= 0.529412) e.remove();
//									else if(domHeight <= 0.55) {
//										for(Element child : e.children())
//											extract(child);
//									} else e.remove();
//								}
//								
//							} else if(tag.equals("script")) {
//								if(domHeight <= 0.461538) {
//									if(innerHtml <= 0.000201) {
//										if(domHeight <= 0.275) {
//											for(Element child : e.children())
//												extract(child);
//										} else if(domHeight <= 0.277778) e.remove();
//										else if(domHeight <= 0.285714) {
//											for(Element child : e.children())
//												extract(child);
//										} else if(domHeight <= 0.357143) e.remove();
//										else if(domHeight <= 0.369565) {
//											for(Element child : e.children())
//												extract(child);
//										} else if(domHeight <= 0.4) e.remove();
//										else for(Element child : e.children())
//											extract(child);
//									} else e.remove();
//								} else e.remove();
//								
//							} else if(tag.equals("table") || tag.equals("tbody")) {
//								if(img <= 0.015152) e.remove();
//								else e.attr("species", "main");
//								
//							} else if(tag.equals("tr")) {
//								if(img <= 0) {
//									if(innerText <= 0.001637) e.remove();
//									else {
//										if(innerHtml <= 0.002363) e.attr("species", "main");
//										else e.remove();
//									}
//									
//								} else {
//									if(innerHtml <= 0.001007) e.remove();
//									else {
//										if(innerText <= 0.000561) e.attr("species", "main");
//										else e.remove();
//									}
//								}
//								
//							} else if(tag.equals("td")) {
//								if(img <= 0.028571) {
//									if(innerHtml <= 0.001116) e.remove();
//									else {
//										if(img <= 0) e.remove();
//										else {
//											if(innerHtml <= 0.001404) e.attr("species", "main");
//											else {
//												if(domHeight <= 0.675) e.attr("species", "main");
//												else e.remove();
//											}
//										}
//									}
//								} else {
//									if(domHeight <= 0.825) e.attr("species", "main");
//									else {
//										if(img <= 0.046512) e.remove();
//										else e.attr("species", "main");
//									}
//								}
//								
//							} else if(tag.equals("i")) {
//								if(domHeight <= 0.785714) e.remove();
//								else if(domHeight <= 0.818182) e.attr("species", "main");
//								else e.remove();
//								
//							} else if(tag.equals("em")) {
//								if(domHeight <= 0.725) e.attr("species", "main");
//								else e.remove();
//								
//							} else if(tag.equals("strong")) {
//								if(innerHtml <= 0.000006) {
//									for(Element child : e.children())
//										extract(child);
//								} else {
//									if(domHeight <= 0.380952) e.remove();
//									else if(domHeight <= 0.761905) e.attr("species", "main");
//									else if(domHeight <= 0.975) e.remove();
//									else e.attr("species", "main");
//								}
//								
//							} else if(tag.equals("br")) {
//								if(domHeight <= 0.538462) e.remove();
//								else if(domHeight <= 0.583333) {
//									for(Element child : e.children())
//										extract(child);
//								} else if(domHeight <= 0.6875) e.attr("species", "main");
//								else if(domHeight <= 0.775) e.remove();
//								else if(domHeight <= 0.825) e.attr("species", "main");
//								else if(domHeight <= 0.833333) e.remove();
//								else if(domHeight <= 0.875) e.attr("species", "main");
//								else e.remove();
//								
//							} else if(tag.equals("hr")) {
//								if(domHeight <= 0.529412) e.remove();
//								else if(domHeight <= 0.625) {
//									for(Element child : e.children())
//										extract(child);
//								} else e.remove();
//								
//							} else if(tag.equals("iframe")) {
//								if(domHeight <= 0.625) e.remove();
//								else if(domHeight <= 0.652174) {
//									for(Element child : e.children())
//										extract(child);
//								} else e.remove();
//								
//							} else if(tag.equals("o:p")) {
//								e.attr("species", "main");
//							}
//						} else {
//							if(interactive <= 0) {
//								if(tag.equals("#root") || tag.equals("html") || tag.equals("head")) {
//									for(Element child : e.children())
//										extract(child);
//									
//								} else if(tag.equals("div")) {
//									if(domHeight <= 0.55) {
//										if(innerText <= 0.00346) e.remove();
//										else if(innerText <= 0.003535) e.attr("species", "main");
//										else e.remove();
//										
//									} else {
//										if(divNum <= 0.006061) {
//											if(domHeight <= 0.666667) e.attr("species", "main");
//											else e.remove();
//										} else if(divNum <= 0.0125) {
//											if(innerHtml <= 0.000988) {
//												if(divNum <= 0.008772) e.remove();
//												else e.attr("species", "main");
//											} else {
//												if(innerText <= 0.003745) e.attr("species", "main");
//												else if(innerText <= 0.004336) e.remove();
//												else {
//													if(domHeight <= 0.782609) e.attr("species", "main");
//													else if(domHeight <= 0.818182) e.remove();
//													else e.attr("species", "main");
//												}
//											}
//										} else {
//											if (img <= 0.039216) e.remove();
//											else for(Element child : e.children())
//												extract(child);
//										}
//									}
//									
//								} else if(tag.equals("center") || tag.equals("li") || tag.equals("label") || 
//										tag.equals("b") || tag.equals("textarea") || tag.equals("iframe") ||
//										tag.equals("option") || tag.equals("h2") || tag.equals("h3")) { // noisy
//									e.remove();
//									
//								} else if(tag.equals("span")) {
//									if(innerHtml <= 0.000668) e.remove();
//									else if(innerHtml <= 0.002356) {
//										if(innerText <= 0.010765) {
//											if(domHeight <= 0.775) {
//												if(innerHtml <= 0.001257) {
//													if(domHeight <= 0.5) e.remove();
//													else e.attr("species", "main");
//												} else e.remove();
//											} else e.attr("species", "main");
//										} else e.remove();
//									} else e.attr("species", "main");
//								
//								} else if(tag.equals("table")) {
//									if(tableNum <= 0.095238) {
//										if(domHeight <= 0.5) e.attr("species", "main");
//										else e.remove();
//									} else e.attr("species", "main");
//								
//								} else if(tag.equals("tbody")) {
//									if(divNum <= 0) {
//										if(innerHtml <= 0.009227) e.attr("species", "main");
//										else e.remove();
//									} else e.remove();
//									
//								} else if(tag.equals("tr")) {
//									if(innerHtml <= 0.00305) e.attr("species", "main");
//									else {
//										if(domHeight <= 0.73913) e.remove();
//										else {
//											if(innerText <= 0.017723) e.remove();
//											else e.attr("species", "main");
//										}
//									}
//								} else if(tag.equals("td")) {
//									if(innerHtml <= 0.004669) {
//										if(domHeight <= 0.777778) {
//											if(innerText <= 0.00716) e.remove();
//											else e.attr("species", "main");
//										} else e.attr("species", "main");
//									} else e.remove();
//									
//								} else if(tag.equals("i") || tag.equals("em") || tag.equals("h1")) { //main
//									e.attr("species", "main");
//									
//								} else if(tag.equals("strong")) {
//									if(innerText <= 0.018534) e.attr("species", "main");
//									else e.remove();
//									
//								} else if(tag.equals("font")) {
//									if(innerHtml <= 0.000877) e.remove();
//									else e.attr("species", "main");
//								}
//								
//							} else e.remove();
//						}
//					} else {
//						if(tag.equals("#root") || tag.equals("html") || tag.equals("head")) { // undefined
//							for(Element child : e.children())
//								extract(child);
//							
//						} else if(tag.equals("div") || tag.equals("center") || tag.equals("ul") ||
//								tag.equals("span") || tag.equals("table") || tag.equals("tbody") ||
//								tag.equals("tr") || tag.equals("td") || tag.equals("i") ||
//								tag.equals("strong") || tag.equals("label") || tag.equals("form") ||
//								tag.equals("b") || tag.equals("noscript") || tag.equals("h1") || 
//								tag.equals("h2") || tag.equals("font") || tag.equals("h3") || tag.equals("h4")) { // noisy
//							e.remove();
//						
//						} else if(tag.equals("a")) {
//							if(linkNum <= 0.009132) e.remove();
//							else if(linkNum <= 0.009174) {
//								if(domHeight <= 0.575) {
//									if(linkToText > 0.5) e.remove();
//									else for(Element child : e.children())
//										extract(child);
//								} else if(domHeight <= 0.804348) {
//									for(Element child : e.children())
//										extract(child);
//								} else e.remove();
//							} else e.remove();
//							
//						} else if(tag.equals("li")) {
//							if(linkNum <= 0.009132) e.remove();
//							
//							else if(linkNum <= 0.009174) {
//								if(domHeight > 0.652174) e.remove();
//								else for(Element child : e.children())
//									extract(child);
//								
//							} else {
//								if(img <= 0.035714) e.remove();
//								else if(img <= 0.039216) {
//									for(Element child : e.children())
//										extract(child);
//								} else e.remove();
//							}
//							
//						} else if(tag.equals("link")) {
//							if(linkNum <= 0.005747) {
//								if(domHeight <= 0.452381) e.remove();
//								else for(Element child : e.children())
//									extract(child);
//								
//							} else {
//								if(domHeight > 0.456522) e.remove();
//								else for(Element child : e.children())
//									extract(child);
//							}
//						}
//					}
//				} else {
//					if(linkToText <= 0.097185) {
//						if(innerText <= 0.000325) {
//							if(pnum <= 0.15625) {
//								if(innerHtml <= 0.002434) e.remove();
//								else e.attr("species", "main");
//								
//							} else {
//								if(domHeight <= 0.717391) {
//									if(img <= 0) {
//										if(domHeight <= 0.5) e.remove();
//										else if(domHeight <= 0.575) {
//											if(innerHtml <= 0.000529) {
//												for(Element child : e.children())
//													extract(child);
//											} else e.remove();
//											
//										} else if(domHeight <= 0.652174) {
//											if(pnum <= 0.026316) {
//												for(Element child : e.children())
//													extract(child);
//											} else if(pnum <= 0.035714) e.attr("species", "main");
//											else if(pnum <= 0.04) {
//												for(Element child : e.children())
//													extract(child);
//											} else {
//												if(domHeight <= 0.625) e.remove();
//												else e.attr("species", "main");
//											}
//											
//										} else for(Element child : e.children())
//											extract(child);
//										
//									} else {
//										if(pnum <= 0.04) e.attr("species", "main");
//										else e.remove();
//									}
//								} else if(domHeight <= 0.75) e.remove();
//								else if(domHeight <= 0.775) e.attr("species", "main");
//								else e.remove();
//							}
//							
//						} else {
//							if(divNum <= 0) {
//								if(innerHtml <= 0.001342) {
//									if(domHeight <= 0.478261) {
//										if(innerHtml <= 0.000564) e.remove();
//										else {
//											if(innerText <= 0.002645) e.attr("species", "main");
//											else e.remove();
//										}
//										
//									} else if(domHeight <= 0.0775) {
//										if(pnum <= 0.015625) e.remove();
//										
//										else if(pnum <= 0.021277) {
//											if(domHeight <= 0.652174) e.attr("species", "main");
//											else e.remove();
//											
//										} else if(pnum <= 0.040816) e.attr("species", "main");
//										
//										else {
//											if(domHeight <= 0.575) e.attr("species", "main");
//											else if(domHeight <= 0.642857) e.remove();
//											else {
//												if(pnum <= 0.055556) e.attr("species", "main");
//												else if(pnum <= 0.058824) e.remove();
//												else e.attr("species", "main");
//											}
//										}
//										
//									} else if(domHeight <= 0.928571) e.remove();
//									
//									else e.attr("species", "main");
//										
//								} else {
//									if(tag.equals("#root") || tag.equals("html") || tag.equals("head")
//											|| tag.equals("strong") || tag.equals("font")) { // undefined
//										for(Element child : e.children())
//											extract(child);
//										
//									} else if(tag.equals("ul") || tag.equals("li") || 
//											tag.equals("span") || tag.equals("form")) { // noisy
//										e.remove();
//										
//									} else if(tag.equals("p")) {
//										if(linkNum <= 0.004115) {
//											if(innerHtml <= 0.005622) {
//												
//												if(pnum <= 0.10989) {
//													if(domHeight <= 0.413043) {
//														if(innerHtml <= 0.004681) e.remove();
//														else e.attr("species", "main");
//													} else e.remove();
//													
//												} else {
//													if(domHeight <= 0.727273) { 
//														if(img <= 0.010753) {
//															if(pnum <= 0.021978) {
//																if(domHeight <= 0.636364) e.attr("species", "main");
//																else e.remove();
//																
//															} else if(pnum <= 0.042553) e.attr("species", "main");
//															
//															else {
//																if(domHeight <= 0.583333) {
//																	if(innerText <= 0.002066) e.attr("species", "main");
//																	else if(innerText <= 0.011285) e.remove();
//																	else e.attr("species", "main");
//																} else {
//																	if(pnum <= 0.05) e.remove();
//																	else if(pnum <= 0.055556) e.attr("species", "main");
//																	else {
//																		if(innerText <= 0.015689) e.attr("species", "main");
//																		else e.remove();
//																	}
//																}
//															}
//															
//														} else {
//															if(domHeight <= 0.575) e.remove();
//															else e.attr("species", "main");
//														}
//														
//													} else {
//														if(innerText <= 0.018687) {
//															if(domHeight <= 0.75) e.remove();
//															else if(innerHtml <= 0.001944) {
//																if(innerText <= 0.00992) e.attr("species", "main");
//																else e.remove();
//															} else e.attr("species", "main");
//															
//														} else {
//															if(innerHtml <= 0.004829) e.remove();
//															else if(pnum <= 0.04) e.remove();
//															else e.attr("species", "main");
//															
//														}
//													}
//												}
//												
//											} else {
//												if(img <= 0.010753) {
//													if(pnum <= 0.015625) {
//														if(domHeight <= 0.456522) e.attr("species", "main");
//														else e.remove();
//													} else e.attr("species", "main");
//												} else {
//													if(pnum <= 0.018182) e.attr("species", "main");
//													else e.remove();
//												}
//											}
//											
//										} else {
//											if(domHeight <= 0.652174) e.remove();
//											else e.attr("species", "main");
//										}
//										
//									} else if(tag.equals("table") || tag.equals("tbody")) {
//										if(pnum <= 0.027778) e.attr("species", "main");
//										else e.remove();
//										
//									} else if(tag.equals("tr")) {
//										if(tableNum <= 0) {
//											if(domHeight <= 0.785714) {
//												if(img <= 0.015152) {
//													if(domHeight <= 0.722222) e.remove();
//													else e.attr("species", "main");
//												} else e.attr("species", "main");
//											} else e.remove();
//										} else e.remove();
//										
//									} else if(tag.equals("td")) {
//										if(innerText <= 0.012744) e.attr("species", "main");
//										else {
//											if(domHeight <= 0.785714) e.remove();
//											else if(domHeight <= 0.826087) e.attr("species", "main");
//											else e.remove();
//										}
//									}
//								}
//							} else {
//								if(innerText <= 0.107691) {
//									if(linkNum <= 0) {
//										if(pnum <= 0.170732) e.remove();
//										else e.attr("species", "main");
//									} else if(linkNum <= 0.001721) {
//										for(Element child : e.children())
//											extract(child);
//									} else e.remove();
//									
//								} else {
//									if(linkNum <= 0.015106) {
//										if(domHeight <= 0.470588) {
//											for(Element child : e.children())
//												extract(child);
//										} else {
//											if(innerText <= 0.234147) e.attr("species", "main");
//											else {
//												if(img <= 0.017241) {
//													for(Element child : e.children())
//														extract(child);
//												} else e.attr("species", "main");
//											}
//										}
//										
//									} else {
//										if(pnum <= 0.705882) {
//											for(Element child : e.children())
//												extract(child);
//										} else e.remove();
//									}
//								}
//							}
//						}
//						
//					} else {
//						if(tableNum <= 0.047619) e.remove();
//						else {
//							if(innerText <= 0.20981) e.remove();
//							else for(Element child : e.children())
//								extract(child);
//						}
//					}
//				}
//			}
//			
//		} else {
//			if(getFloatAttr(e, "linknum") > 0.366505) {
//				if(getFloatAttr(e, "interactivenum") > 0.025641)
//					e.attr("species", "main");
//				else for(Element child : e.children())
//					extract(child);
//			} else for(Element child : e.children())
//				extract(child);
//		}
//	}
//	
	public static void main(String[] args) throws IOException {
		JSoupParser parser = new JSoupParser();
		String url ="http://dantri.com.vn/c728/s728-633274/cu-gia-bi-cat-than-mon-moi-cho-duoc-thi-hanh-an.htm";
			parser.process(url);
			Element body = parser.getBody();
			Extractor extractor = new Extractor();
			for(Element e : body.children()) {
				extractor.extract(e);
			}
		
			FileOutputStream fout = new FileOutputStream("input.html");
			fout.write(body.outerHtml().getBytes());
			fout.close();
	}
}

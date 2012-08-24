package extractor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import parser.JSoupParser;

public class NewExtractor {
	private Float getFloatAttr(Element e, String attr) {
		return Float.parseFloat(e.attr(attr));
	}
	
	public void extract(Element e) {

		Float domHeight = getFloatAttr(e, "domheight");
		Float innerHtml = getFloatAttr(e, "innerhtml");
		Float innerText = getFloatAttr(e, "innertext");
		Float interactive = getFloatAttr(e, "interactivenum");
		Float img = getFloatAttr(e, "imgnum");
		Float divNum = getFloatAttr(e, "divnum");
		Float tableNum = getFloatAttr(e, "tablenum");
		Float linkNum = getFloatAttr(e, "linknum");
		Float pnum = getFloatAttr(e, "pnum");
		Float linkToText = getFloatAttr(e, "linktotext");

		String tag = e.attr("tagname");
		
		if(innerText <= 0.366505) {

			if(getFloatAttr(e, "domheight") <= 0.083333) {
				if(tag.equals("#root") || tag.equals("html") || tag.equals("head")
						|| tag.equals("body") || tag.equals("a") || tag.equals("input")) { // undefined
					for(Element child : e.children())
						extract(child);
				} else if (tag.equals("div") || tag.equals("img") || tag.equals("noscript")) { // noisy
					e.attr("species", "noisy"); 
				} else if(tag.equals("script")) {
					if(innerHtml > 0.004112)
						e.attr("species", "noisy");
					else for(Element child : e.children())
						extract(child);
				}
				
			} else if(getFloatAttr(e, "domheight") <= 0.230769) {
				if(innerHtml <= 0.000126) {
					if(getFloatAttr(e, "interactivenum") <= 0.034483) {
						if(tag.equals("#root") || tag.equals("html") || tag.equals("head") || tag.equals("body") 
								|| tag.equals("a") || tag.equals("iframe") || tag.equals("link")) {
							for(Element child : e.children())
								extract(child);
							
						} else if (tag.equals("img") || tag.equals("input") || tag.equals("tbody") || tag.equals("tr")) {
							e.attr("species", "noisy");
							
						} else if(tag.equals("div")) {
							if(getFloatAttr(e, "divnum") <= 0.004) {
								if(getFloatAttr(e, "domheight") <= 0.111111) {
									if(getFloatAttr(e, "divnum") <= 0.003484) e.attr("species", "noisy");
									else for(Element child : e.children())
										extract(child);
								} else e.attr("species", "noisy");
								
							} else {
								if( domHeight <= 0.181818) {
									for(Element child : e.children())
										extract(child);
								} else if(domHeight <= 0.2) e.attr("species", "noisy");
								else for(Element child : e.children())
									extract(child);
							}
							
						} else if(tag.equals("script")) {
							if(getFloatAttr(e, "domheight") <= 0.1)
								e.attr("species", "noisy");
							else for(Element child : e.children())
								extract(child);
						} 
					} else for(Element child : e.children())
						extract(child);
					
				} else {
					if(tag.equals("#root") || tag.equals("html") || tag.equals("head") || tag.equals("body")) {
						for(Element child : e.children())
							extract(child);
						
					} else if(tag.equals("div") || tag.equals("center") || tag.equals("a") || 
							tag.equals("ul") || tag.equals("p") || tag.equals("table") || 
							tag.equals("tbody") || tag.equals("tr") || tag.equals("noscript") || tag.equals("style")) {
						e.attr("species", "noisy");
						
					} else if(tag.equals("script")) {
						if(innerHtml <= 0.001628) {
							if(domHeight <= 0.119048) e.attr("species", "noisy");
							else if(domHeight <= 0.130435) {
								for(Element child : e.children())
									extract(child);
							} else{
								if(innerHtml <= 0.000936) e.attr("species", "noisy");
								else {
									if(domHeight <= 0.176471) e.attr("species", "noisy");
									else if (domHeight <= 0.2) {
										for(Element child : e.children())
											extract(child);
									} else {
										if(innerHtml <= 0.001347) e.attr("species", "noisy");
										else for(Element child : e.children())
											extract(child);
									}
								}
							}
							
						} else e.attr("species", "noisy");
					}
				}
				
			} else {
				if(getFloatAttr(e, "pnum") <= 0) {
					if(getFloatAttr(e, "linknum") <= 0) {
						if(getFloatAttr(e, "innertext") <= 0.003174) {
							if(tag.equals("#root") || tag.equals("html") || tag.equals("head")) {
								for(Element child : e.children())
									extract(child);
								
							} else if(tag.equals("div")) {
								if(domHeight <= 0.764706) {
									if(innerHtml <= 0.000136) {
										if(getFloatAttr(e, "divnum") <= 0.007813) {
											if(innerHtml <= 0.000007) {
												
												if(domHeight <= 0.23913) e.attr("species", "noisy");
												else if(domHeight <= 0.275) {
													if(getFloatAttr(e, "divnum") > 0.006879) e.attr("species", "noisy");
													else for(Element child : e.children())
														extract(child);
													
												} else if(domHeight <= 0.347826) e.attr("species", "noisy");
												else if(domHeight <= 0.4375) {
													if(getFloatAttr(e, "divnum") <= 0.003268) e.attr("species", "noisy");
													else if(getFloatAttr(e, "divnum") <= 0.006993) {
														for(Element child : e.children())
															extract(child);
													} else e.attr("species", "noisy");
												} else e.attr("species", "noisy");
												
											} else e.attr("species", "noisy");
											
										} else {
											if(innerHtml <= 0.000058) e.attr("species", "noisy");
											else {
												if(domHeight <= 0.565217) e.attr("species", "noisy");
												else for(Element child : e.children())
													extract(child);
											}
										}
										
									} else if(innerHtml <= 0.000269) {
										if(domHeight <= 0.555556) e.attr("species", "noisy");
										else if(domHeight <= 0.575) { 
											for(Element child : e.children())
												extract(child);
										} else if(domHeight <= 588235) {
											if(getFloatAttr(e, "divnum") <= 0.008333) e.attr("species", "noisy");
											else e.attr("species", "main");
										} else e.attr("species", "noisy");
										
									} else {
										if(img <= 0.025) e.attr("species", "noisy");
										else {
											if(domHeight <= 0.555556) e.attr("species", "noisy");
											else {
												if(innerHtml <= 0.001639) e.attr("species", "noisy");
												else if(getFloatAttr(e, "innertext") <= 0) {
													if(img <= 0.035714) e.attr("species", "main");
													else e.attr("species", "noisy");
												} else e.attr("species", "noisy");
											}
										}
									}
									
								} else {
									if(getFloatAttr(e, "divnum") <= 0.008772) e.attr("species", "noisy");
									else {
										if(getFloatAttr(e, "interactivenum") <= 0.025641) {
											if(innerHtml  <= 0.000558) e.attr("species", "main");
											else if(innerHtml <= 0.004244) {
												if(getFloatAttr(e, "innertext") <= 0.00106) e.attr("species", "noisy");
												else e.attr("species", "main");
											} else e.attr("species", "main");
										} else e.attr("species", "noisy");
									}
								}
								
							} else if(tag.equals("img")) {
								if(img <= 0.037037) {
									if(domHeight <= 0.615385) e.attr("species", "noisy");
									else if(domHeight <= 0.695652) {
										if(img <= 0.033333) e.attr("species", "noisy");
										else {
											if(domHeight <= 0.630435) e.attr("species", "main");
											else if(domHeight <= 0.652174) e.attr("species", "noisy");
											else e.attr("species", "main");
										}
									} else if(domHeight <= 0.705882) e.attr("species", "main");
									else e.attr("species", "noisy");
									
								} else if(img <= 0.039216) {
									if(domHeight <= 0.826087) e.attr("species", "noisy");
									else if(domHeight <= 0.85) {
										for(Element child : e.children())
											extract(child);
									} else if(domHeight <= 0.913044) e.attr("species", "noisy");
									else if(domHeight <= 0.925) e.attr("species", "main");
									else e.attr("species", "noisy");
								} else e.attr("species", "noisy");
								
							} else if(tag.equals("object") || tag.equals("embed") || tag.equals("ul") || 
									tag.equals("li") || tag.equals("label") || tag.equals("form") ||
									tag.equals("b") || tag.equals("textarea") || tag.equals("noscript") ||
									tag.equals("select") || tag.equals("option") || tag.equals("h1") ||
									tag.equals("g:plusone") || tag.equals("h2") || tag.equals("font") ||
									tag.equals("h3") || tag.equals("map") || tag.equals("area") ||
									tag.equals("style") || tag.equals("bb:sp-like") || tag.equals("u") ||
									tag.equals("menu") || tag.equals("ie:menuitem") || tag.equals("meta") ||
									tag.equals("title") || tag.equals("h4")) {
								e.attr("species", "noisy");
								
							} else if(tag.equals("param")) {
								if(domHeight < 0.825) e.attr("species", "noisy");
								else e.attr("species", "main");
								
							} else if(tag.equals("span")) {
								if(innerHtml <= 0.000435) {
									if(domHeight <= 0.833333) e.attr("species", "noisy");
									else if(domHeight <= 0.857143) {
										if(innerHtml <= 0.00001) {
											if(domHeight <= 0.847826)  e.attr("species", "main");
											else e.attr("species", "noisy");
										} else if(innerHtml <= 0.000115) e.attr("species", "noisy");
										else e.attr("species", "main");
									} else e.attr("species", "noisy");
								} else {
									if(domHeight <= 0.595238) e.attr("species", "noisy");
									else if(domHeight <= 0.611111 )  e.attr("species", "main");
									else if(domHeight <= 0.642857) e.attr("species", "noisy");
									else if(domHeight <= 0.666667)  e.attr("species", "main");
									else if(domHeight <= 0.804348) e.attr("species", "noisy");
									else e.attr("species", "main");
								}
								
							} else if(tag.equals("input")) {
								if(interactive <= 0.055556) e.attr("species", "noisy");
								
								else if(interactive <= 0.060606) {
									if(domHeight <= 0.25) e.attr("species", "noisy");
									else if(domHeight <= 0.285714) {
										for(Element child : e.children())
											extract(child);
									} else e.attr("species", "noisy");
								
								} else {
									if(domHeight <= 0.529412) e.attr("species", "noisy");
									else if(domHeight <= 0.55) {
										for(Element child : e.children())
											extract(child);
									} else e.attr("species", "noisy");
								}
								
							} else if(tag.equals("script")) {
								if(domHeight <= 0.461538) {
									if(innerHtml <= 0.000201) {
										if(domHeight <= 0.275) {
											for(Element child : e.children())
												extract(child);
										} else if(domHeight <= 0.277778) e.attr("species", "noisy");
										else if(domHeight <= 0.285714) {
											for(Element child : e.children())
												extract(child);
										} else if(domHeight <= 0.357143) e.attr("species", "noisy");
										else if(domHeight <= 0.369565) {
											for(Element child : e.children())
												extract(child);
										} else if(domHeight <= 0.4) e.attr("species", "noisy");
										else for(Element child : e.children())
											extract(child);
									} else e.attr("species", "noisy");
								} else e.attr("species", "noisy");
								
							} else if(tag.equals("table") || tag.equals("tbody")) {
								if(img <= 0.015152) e.attr("species", "noisy");
								else e.attr("species", "main");
								
							} else if(tag.equals("tr")) {
								if(img <= 0) {
									if(innerText <= 0.001637) e.attr("species", "noisy");
									else {
										if(innerHtml <= 0.002363) e.attr("species", "main");
										else e.attr("species", "noisy");
									}
									
								} else {
									if(innerHtml <= 0.001007) e.attr("species", "noisy");
									else {
										if(innerText <= 0.000561) e.attr("species", "main");
										else e.attr("species", "noisy");
									}
								}
								
							} else if(tag.equals("td")) {
								if(img <= 0.028571) {
									if(innerHtml <= 0.001116) e.attr("species", "noisy");
									else {
										if(img <= 0) e.attr("species", "noisy");
										else {
											if(innerHtml <= 0.001404) e.attr("species", "main");
											else {
												if(domHeight <= 0.675) e.attr("species", "main");
												else e.attr("species", "noisy");
											}
										}
									}
								} else {
									if(domHeight <= 0.825) e.attr("species", "main");
									else {
										if(img <= 0.046512) e.attr("species", "noisy");
										else e.attr("species", "main");
									}
								}
								
							} else if(tag.equals("i")) {
								if(domHeight <= 0.785714) e.attr("species", "noisy");
								else if(domHeight <= 0.818182) e.attr("species", "main");
								else e.attr("species", "noisy");
								
							} else if(tag.equals("em")) {
								if(domHeight <= 0.725) e.attr("species", "main");
								else e.attr("species", "noisy");
								
							} else if(tag.equals("strong")) {
								if(innerHtml <= 0.000006) {
									for(Element child : e.children())
										extract(child);
								} else {
									if(domHeight <= 0.380952) e.attr("species", "noisy");
									else if(domHeight <= 0.761905) e.attr("species", "main");
									else if(domHeight <= 0.975) e.attr("species", "noisy");
									else e.attr("species", "main");
								}
								
							} else if(tag.equals("br")) {
								if(domHeight <= 0.538462) e.attr("species", "noisy");
								else if(domHeight <= 0.583333) {
									for(Element child : e.children())
										extract(child);
								} else if(domHeight <= 0.6875) e.attr("species", "main");
								else if(domHeight <= 0.775) e.attr("species", "noisy");
								else if(domHeight <= 0.825) e.attr("species", "main");
								else if(domHeight <= 0.833333) e.attr("species", "noisy");
								else if(domHeight <= 0.875) e.attr("species", "main");
								else e.attr("species", "noisy");
								
							} else if(tag.equals("hr")) {
								if(domHeight <= 0.529412) e.attr("species", "noisy");
								else if(domHeight <= 0.625) {
									for(Element child : e.children())
										extract(child);
								} else e.attr("species", "noisy");
								
							} else if(tag.equals("iframe")) {
								if(domHeight <= 0.625) e.attr("species", "noisy");
								else if(domHeight <= 0.652174) {
									for(Element child : e.children())
										extract(child);
								} else e.attr("species", "noisy");
								
							} else if(tag.equals("o:p")) {
								e.attr("species", "main");
							}
						} else {
							if(interactive <= 0) {
								if(tag.equals("#root") || tag.equals("html") || tag.equals("head")) {
									for(Element child : e.children())
										extract(child);
									
								} else if(tag.equals("div")) {
									if(domHeight <= 0.55) {
										if(innerText <= 0.00346) e.attr("species", "noisy");
										else if(innerText <= 0.003535) e.attr("species", "main");
										else e.attr("species", "noisy");
										
									} else {
										if(divNum <= 0.006061) {
											if(domHeight <= 0.666667) e.attr("species", "main");
											else e.attr("species", "noisy");
										} else if(divNum <= 0.0125) {
											if(innerHtml <= 0.000988) {
												if(divNum <= 0.008772) e.attr("species", "noisy");
												else e.attr("species", "main");
											} else {
												if(innerText <= 0.003745) e.attr("species", "main");
												else if(innerText <= 0.004336) e.attr("species", "noisy");
												else {
													if(domHeight <= 0.782609) e.attr("species", "main");
													else if(domHeight <= 0.818182) e.attr("species", "noisy");
													else e.attr("species", "main");
												}
											}
										} else {
											if (img <= 0.039216) e.attr("species", "noisy");
											else for(Element child : e.children())
												extract(child);
										}
									}
									
								} else if(tag.equals("center") || tag.equals("li") || tag.equals("label") || 
										tag.equals("b") || tag.equals("textarea") || tag.equals("iframe") ||
										tag.equals("option") || tag.equals("h2") || tag.equals("h3")) { // noisy
									e.attr("species", "noisy");
									
								} else if(tag.equals("span")) {
									if(innerHtml <= 0.000668) e.attr("species", "noisy");
									else if(innerHtml <= 0.002356) {
										if(innerText <= 0.010765) {
											if(domHeight <= 0.775) {
												if(innerHtml <= 0.001257) {
													if(domHeight <= 0.5) e.attr("species", "noisy");
													else e.attr("species", "main");
												} else e.attr("species", "noisy");
											} else e.attr("species", "main");
										} else e.attr("species", "noisy");
									} else e.attr("species", "main");
								
								} else if(tag.equals("table")) {
									if(tableNum <= 0.095238) {
										if(domHeight <= 0.5) e.attr("species", "main");
										else e.attr("species", "noisy");
									} else e.attr("species", "main");
								
								} else if(tag.equals("tbody")) {
									if(divNum <= 0) {
										if(innerHtml <= 0.009227) e.attr("species", "main");
										else e.attr("species", "noisy");
									} else e.attr("species", "noisy");
									
								} else if(tag.equals("tr")) {
									if(innerHtml <= 0.00305) e.attr("species", "main");
									else {
										if(domHeight <= 0.73913) e.attr("species", "noisy");
										else {
											if(innerText <= 0.017723) e.attr("species", "noisy");
											else e.attr("species", "main");
										}
									}
								} else if(tag.equals("td")) {
									if(innerHtml <= 0.004669) {
										if(domHeight <= 0.777778) {
											if(innerText <= 0.00716) e.attr("species", "noisy");
											else e.attr("species", "main");
										} else e.attr("species", "main");
									} else e.attr("species", "noisy");
									
								} else if(tag.equals("i") || tag.equals("em") || tag.equals("h1")) { //main
									e.attr("species", "main");
									
								} else if(tag.equals("strong")) {
									if(innerText <= 0.018534) e.attr("species", "main");
									else e.attr("species", "noisy");
									
								} else if(tag.equals("font")) {
									if(innerHtml <= 0.000877) e.attr("species", "noisy");
									else e.attr("species", "main");
								}
								
							} else e.attr("species", "noisy");
						}
					} else {
						if(tag.equals("#root") || tag.equals("html") || tag.equals("head")) { // undefined
							for(Element child : e.children())
								extract(child);
							
						} else if(tag.equals("div") || tag.equals("center") || tag.equals("ul") ||
								tag.equals("span") || tag.equals("table") || tag.equals("tbody") ||
								tag.equals("tr") || tag.equals("td") || tag.equals("i") ||
								tag.equals("strong") || tag.equals("label") || tag.equals("form") ||
								tag.equals("b") || tag.equals("noscript") || tag.equals("h1") || 
								tag.equals("h2") || tag.equals("font") || tag.equals("h3") || tag.equals("h4")) { // noisy
							e.attr("species", "noisy");
						
						} else if(tag.equals("a")) {
							if(linkNum <= 0.009132) e.attr("species", "noisy");
							else if(linkNum <= 0.009174) {
								if(domHeight <= 0.575) {
									if(linkToText > 0.5) e.attr("species", "noisy");
									else for(Element child : e.children())
										extract(child);
								} else if(domHeight <= 0.804348) {
									for(Element child : e.children())
										extract(child);
								} else e.attr("species", "noisy");
							} else e.attr("species", "noisy");
							
						} else if(tag.equals("li")) {
							if(linkNum <= 0.009132) e.attr("species", "noisy");
							
							else if(linkNum <= 0.009174) {
								if(domHeight > 0.652174) e.attr("species", "noisy");
								else for(Element child : e.children())
									extract(child);
								
							} else {
								if(img <= 0.035714) e.attr("species", "noisy");
								else if(img <= 0.039216) {
									for(Element child : e.children())
										extract(child);
								} else e.attr("species", "noisy");
							}
							
						} else if(tag.equals("link")) {
							if(linkNum <= 0.005747) {
								if(domHeight <= 0.452381) e.attr("species", "noisy");
								else for(Element child : e.children())
									extract(child);
								
							} else {
								if(domHeight > 0.456522) e.attr("species", "noisy");
								else for(Element child : e.children())
									extract(child);
							}
						}
					}
				} else {
					if(linkToText <= 0.097185) {
						if(innerText <= 0.000325) {
							if(pnum <= 0.15625) {
								if(innerHtml <= 0.002434) e.attr("species", "noisy");
								else e.attr("species", "main");
								
							} else {
								if(domHeight <= 0.717391) {
									if(img <= 0) {
										if(domHeight <= 0.5) e.attr("species", "noisy");
										else if(domHeight <= 0.575) {
											if(innerHtml <= 0.000529) {
												for(Element child : e.children())
													extract(child);
											} else e.attr("species", "noisy");
											
										} else if(domHeight <= 0.652174) {
											if(pnum <= 0.026316) {
												for(Element child : e.children())
													extract(child);
											} else if(pnum <= 0.035714) e.attr("species", "main");
											else if(pnum <= 0.04) {
												for(Element child : e.children())
													extract(child);
											} else {
												if(domHeight <= 0.625) e.attr("species", "noisy");
												else e.attr("species", "main");
											}
											
										} else for(Element child : e.children())
											extract(child);
										
									} else {
										if(pnum <= 0.04) e.attr("species", "main");
										else e.attr("species", "noisy");
									}
								} else if(domHeight <= 0.75) e.attr("species", "noisy");
								else if(domHeight <= 0.775) e.attr("species", "main");
								else e.attr("species", "noisy");
							}
							
						} else {
							if(divNum <= 0) {
								if(innerHtml <= 0.001342) {
									if(domHeight <= 0.478261) {
										if(innerHtml <= 0.000564) e.attr("species", "noisy");
										else {
											if(innerText <= 0.002645) e.attr("species", "main");
											else e.attr("species", "noisy");
										}
										
									} else if(domHeight <= 0.0775) {
										if(pnum <= 0.015625) e.attr("species", "noisy");
										
										else if(pnum <= 0.021277) {
											if(domHeight <= 0.652174) e.attr("species", "main");
											else e.attr("species", "noisy");
											
										} else if(pnum <= 0.040816) e.attr("species", "main");
										
										else {
											if(domHeight <= 0.575) e.attr("species", "main");
											else if(domHeight <= 0.642857) e.attr("species", "noisy");
											else {
												if(pnum <= 0.055556) e.attr("species", "main");
												else if(pnum <= 0.058824) e.attr("species", "noisy");
												else e.attr("species", "main");
											}
										}
										
									} else if(domHeight <= 0.928571) e.attr("species", "noisy");
									
									else e.attr("species", "main");
										
								} else {
									if(tag.equals("#root") || tag.equals("html") || tag.equals("head")
											|| tag.equals("strong") || tag.equals("font")) { // undefined
										for(Element child : e.children())
											extract(child);
										
									} else if(tag.equals("ul") || tag.equals("li") || 
											tag.equals("span") || tag.equals("form")) { // noisy
										e.attr("species", "noisy");
										
									} else if(tag.equals("p")) {
										if(linkNum <= 0.004115) {
											if(innerHtml <= 0.005622) {
												
												if(pnum <= 0.10989) {
													if(domHeight <= 0.413043) {
														if(innerHtml <= 0.004681) e.attr("species", "noisy");
														else e.attr("species", "main");
													} else e.attr("species", "noisy");
													
												} else {
													if(domHeight <= 0.727273) { 
														if(img <= 0.010753) {
															if(pnum <= 0.021978) {
																if(domHeight <= 0.636364) e.attr("species", "main");
																else e.attr("species", "noisy");
																
															} else if(pnum <= 0.042553) e.attr("species", "main");
															
															else {
																if(domHeight <= 0.583333) {
																	if(innerText <= 0.002066) e.attr("species", "main");
																	else if(innerText <= 0.011285) e.attr("species", "noisy");
																	else e.attr("species", "main");
																} else {
																	if(pnum <= 0.05) e.attr("species", "noisy");
																	else if(pnum <= 0.055556) e.attr("species", "main");
																	else {
																		if(innerText <= 0.015689) e.attr("species", "main");
																		else e.attr("species", "noisy");
																	}
																}
															}
															
														} else {
															if(domHeight <= 0.575) e.attr("species", "noisy");
															else e.attr("species", "main");
														}
														
													} else {
														if(innerText <= 0.018687) {
															if(domHeight <= 0.75) e.attr("species", "noisy");
															else if(innerHtml <= 0.001944) {
																if(innerText <= 0.00992) e.attr("species", "main");
																else e.attr("species", "noisy");
															} else e.attr("species", "main");
															
														} else {
															if(innerHtml <= 0.004829) e.attr("species", "noisy");
															else if(pnum <= 0.04) e.attr("species", "noisy");
															else e.attr("species", "main");
															
														}
													}
												}
												
											} else {
												if(img <= 0.010753) {
													if(pnum <= 0.015625) {
														if(domHeight <= 0.456522) e.attr("species", "main");
														else e.attr("species", "noisy");
													} else e.attr("species", "main");
												} else {
													if(pnum <= 0.018182) e.attr("species", "main");
													else e.attr("species", "noisy");
												}
											}
											
										} else {
											if(domHeight <= 0.652174) e.attr("species", "noisy");
											else e.attr("species", "main");
										}
										
									} else if(tag.equals("table") || tag.equals("tbody")) {
										if(pnum <= 0.027778) e.attr("species", "main");
										else e.attr("species", "noisy");
										
									} else if(tag.equals("tr")) {
										if(tableNum <= 0) {
											if(domHeight <= 0.785714) {
												if(img <= 0.015152) {
													if(domHeight <= 0.722222) e.attr("species", "noisy");
													else e.attr("species", "main");
												} else e.attr("species", "main");
											} else e.attr("species", "noisy");
										} else e.attr("species", "noisy");
										
									} else if(tag.equals("td")) {
										if(innerText <= 0.012744) e.attr("species", "main");
										else {
											if(domHeight <= 0.785714) e.attr("species", "noisy");
											else if(domHeight <= 0.826087) e.attr("species", "main");
											else e.attr("species", "noisy");
										}
									}
								}
							} else {
								if(innerText <= 0.107691) {
									if(linkNum <= 0) {
										if(pnum <= 0.170732) e.attr("species", "noisy");
										else e.attr("species", "main");
									} else if(linkNum <= 0.001721) {
										for(Element child : e.children())
											extract(child);
									} else e.attr("species", "noisy");
									
								} else {
									if(linkNum <= 0.015106) {
										if(domHeight <= 0.470588) {
											for(Element child : e.children())
												extract(child);
										} else {
											if(innerText <= 0.234147) e.attr("species", "main");
											else {
												if(img <= 0.017241) {
													for(Element child : e.children())
														extract(child);
												} else e.attr("species", "main");
											}
										}
										
									} else {
										if(pnum <= 0.705882) {
											for(Element child : e.children())
												extract(child);
										} else e.attr("species", "noisy");
									}
								}
							}
						}
						
					} else {
						if(tableNum <= 0.047619) e.attr("species", "noisy");
						else {
							if(innerText <= 0.20981) e.attr("species", "noisy");
							else for(Element child : e.children())
								extract(child);
						}
					}
				}
			}
			
		} else {
			if(linkNum <= 0.011925) {
				if(getFloatAttr(e, "interactivenum") > 0.025641)
					e.attr("species", "main");
				else for(Element child : e.children())
					extract(child);
			} else for(Element child : e.children())
				extract(child);
		}
	}
	
	public void clean(Element e) {
		System.out.println(e.tagName());
		System.out.println(e.attr("domheight"));
		System.out.println("species: " + e.attr("species"));
		if(e.attr("species").equals("noisy")) {
			e.empty();
			System.out.println(e.outerHtml());
		}
		for(Element element : e.children()) 
			clean(element);
	}
	
	public static void main(String[] args) throws IOException {
		JSoupParser parser = new JSoupParser();
		String url ="http://dantri.com.vn/c728/s728-633274/cu-gia-bi-cat-than-mon-moi-cho-duoc-thi-hanh-an.htm";
		parser.process(url);
		Element body = parser.getBody();
		NewExtractor extractor = new NewExtractor();
		for(Element e : body.children()) 
			extractor.extract(e);
		
		extractor.clean(body);
		FileOutputStream fout = new FileOutputStream("input.html");
		fout.write(body.outerHtml().getBytes());
		fout.close();
	}
}

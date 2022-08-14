package model;

import java.util.ArrayList;

public class WebPage implements Comparable<WebPage> {

	private String title;
	private static ArrayList<Element> pageElements;
	
	public WebPage(String title) {
		this.title = title;
		pageElements = new ArrayList<Element>();
	}
	
	public int addElement(Element element) {
		pageElements.add(element);
		
		if (element instanceof TagElement)
			return ((TagElement) element).getId();
		else
			return 1;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getWebPageHTML(int indentation) {
		String webHTML = "<!doctype html>\n"
				+ "<html>\n"
				+ "   <head>\n"
				+ "      <meta charset=\"utf-8\"/>\n"
				+ "      <title>" + title + "</title>\n"
				+ "   </head>\n"
				+ "   <body>\n";
		
		for (Element e : pageElements) {
			webHTML += e.genHTML(2);
		}
		
		webHTML += "   </body>\n"
				+ "</html>";
		
		return webHTML.indent(indentation);
	}
	
	public void writeToFile(String filename, int indentation) {
		Utilities.writeToFile(filename, getWebPageHTML(indentation));
	}
	
	public Element findElem(int id) {
		// Returns a reference to a particular element based on the id
		return pageElements.get(id);
	}
	
	public String stats() {
		// Returns information about the number of lists, paragraphs, and tables present in the page. 
		// Also, it provide table utilization information. See public tests for format.
		
		int listCount = 0;
		int pCount = 0;
		int tableCount = 0;
		float tableUtilization = 0;
		
		for (Element e : pageElements) {
			if (e instanceof ListElement)
				listCount += 1;
			else if (e instanceof ParagraphElement)
				pCount += 1;
			else if (e instanceof TableElement) {
				tableCount += 1;
				tableUtilization += ((TableElement) e).getTableUtilization();
			}
			else
				continue;
		}
			
		String result = "List Count: " + listCount 
				+ "Paragraph Count: " + pCount + "\n"
				+ "Table Count: " + tableCount + "\n"
				+ "TableElement Utilization: " + (100 * (tableUtilization / tableCount));
		
		return result;
	}
	
	public static void enableId(boolean choice) {
		// Enables the ids associated with tag elements.
		TagElement.enableId(choice);
			
	}
	
	@Override
	public int compareTo(WebPage o) {
		if (o.getTitle().equals(this.title))
			return 0;
		
		return o.getTitle().compareToIgnoreCase(title);
	}

}

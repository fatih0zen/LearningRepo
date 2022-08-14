package model;

import java.util.ArrayList;

public class ListElement extends TagElement implements Element {

	private ArrayList<Element> contents = new ArrayList<Element>();
	
	public ListElement(boolean ordered, String attributes) {
		super(isOrderedList(ordered), true, null, attributes);
	}
	
	private static String isOrderedList(boolean ordered) {
		if (ordered)
			return "ol";
		else
			return "ul";
	}
	
	public void addItem(Element item) {
		contents.add(item);
	}
	
	@Override
	public String genHTML(int indentation) {
		String allContents = "";
		
		for (Element content : contents)
			allContents += "\n<li>\n".indent(2) + content.genHTML(4) + "\n</li>".indent(2);
		
		return (super.getStartTag() + allContents).indent(indentation) + Utilities.spaces(indentation) + super.getEndTag();
	}

}

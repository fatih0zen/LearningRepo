package model;

import java.util.ArrayList;

public class ParagraphElement extends TagElement implements Element {
	
	private ArrayList<Element> contents = new ArrayList<Element>();

	public ParagraphElement(String attributes) {
		super("p", true, null, attributes);
	}
	
	public void addItem(Element item) {
		contents.add(item);
	}
	
	@Override
	public String genHTML(int indentation) {
		String allContents = "";
		
		for (Element content : contents)
			allContents += "\n" + content.genHTML(4);
		
		return (super.getStartTag() + allContents).indent(indentation) + Utilities.spaces(indentation) + super.getEndTag();
	}

}

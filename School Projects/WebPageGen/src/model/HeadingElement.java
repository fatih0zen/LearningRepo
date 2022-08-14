package model;

public class HeadingElement extends TagElement implements Element {
	
	public HeadingElement(Element content, int level, String attributes) {
		// Includes the content as part of the heading. Level can assume values from 1 up to including 6.
		super("h" + level, true, content, attributes);
	}
	
}

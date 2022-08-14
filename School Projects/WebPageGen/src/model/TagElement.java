package model;

public class TagElement implements Element {

	private String tagName;
	private boolean endTag;
	private Element content;
	private String attributes;
	private int id;
	
	private static int uniqueID = 1;
	private static boolean enableID = true;
	
	public TagElement(String tagName, boolean endTag, Element content, String attributes) {
		this.tagName = tagName;
		this.endTag = endTag;
		this.content = content;
		this.attributes = attributes;
		this.id = TagElement.uniqueID++;
	}
	
	public int getId() {
		return id;
	}
	
	public String getStringId() {
		return tagName + id;
	}
	
	public String getStartTag() {
		if (enableID && attributes != null)
			return "<" + tagName + " id=\"" + getStringId() + "\" " + attributes + ">";
		else if (enableID)
			return "<" + tagName + " id=\"" + getStringId() + "\">";
		else if (!enableID && attributes != null)
			return "<" + tagName + " " + attributes + ">";
		else
			return "<" + tagName + ">";
	}
	
	public String getEndTag() {
		if (endTag)
			return "</" + tagName + ">";
		else
			return "";
	}
	
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	
	public static void resetIds() {
		TagElement.uniqueID = 1;
	}
	
	public static void enableId(boolean choice) {
		TagElement.enableID = choice;
	}
	
	@Override
	public String genHTML(int indentation) {
		if (!endTag)
			return Utilities.spaces(indentation) + getStartTag();
		
		return (getStartTag() + content.genHTML(0) + getEndTag()).indent(indentation);
	}

}

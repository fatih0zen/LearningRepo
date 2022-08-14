package model;

public class AnchorElement extends TagElement implements Element {
	
	private String url;
	private String linkText;

	public AnchorElement(String url, String linkText, String attributes) {
		super("a", true, new TextElement(linkText), attributes);
		super.setAttributes("href=\"" + url + "\"");
		this.url = url;
		this.linkText = linkText;
	}
	
	public String getLinkText() {
		return linkText;
	}
	
	public String getUrlText() {
		return url;
	}
	
}

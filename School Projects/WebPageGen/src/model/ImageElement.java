package model;

public class ImageElement extends TagElement implements Element {
	
	private String imageURL;
	
	public ImageElement(String imageURL, int width, int height, String alt, String attributes) {
		super("img", false, null, attributes);
		super.setAttributes("src=\"" + imageURL + "\" " + "width=\"" + width + "\" " + "height=\"" + height + "\" " + "alt=\"" + alt + "\"");
		this.imageURL = imageURL;
	}
	
	public String getImageURL() {
		return imageURL;
	}
	
}

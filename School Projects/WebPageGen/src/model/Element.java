package model;

public interface Element {
	
	/*
	 * Returns a string that represents the HTML associated with the element. The
	 * string is indented based on the parameter value.
	 */
	public String genHTML(int indentation);
	
}

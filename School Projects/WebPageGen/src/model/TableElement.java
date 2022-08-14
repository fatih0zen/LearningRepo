package model;

public class TableElement extends TagElement implements Element {
	
	private int rows;
	private int cols;
	
	private Element[][] contents;

	public TableElement(int rows, int cols, String attributes) {
		super("table", true, null, attributes);
		contents = new Element[rows][cols];
		this.rows = rows;
		this.cols = cols;
	}
	
	public void addItem(int rowIndex, int colIndex, Element item) {
		// Adds/updates the element with the specified indices.
		contents[rowIndex][colIndex] = item;
			
	}
	
	public double getTableUtilization() {
		// Returns the percentage of table cells currently in used (those storing references to objects).
		float numberOfUsedCells = 0;
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (contents[i][j] != null)
					numberOfUsedCells += 1;
			}
		}
		
		if (rows * cols == 0)
			return 0;
		else
			return numberOfUsedCells / (rows * cols);
	}
	
	@Override
	public String genHTML(int indentation) {
		String allContents = super.getStartTag();
		
		String cells = "";
		
		for (int i = 0; i < rows; i++) {
			cells += "\n<tr>";
			for (int j = 0; j < cols; j++) {
				if (contents[i][j] != null)
					cells += "<td>" + contents[i][j].genHTML(0) + "</td>";
				else
					cells += "<td>" + "</td>";
			}
			cells += "</tr>";
		}
		
		allContents += cells.indent(4) + super.getEndTag();
		
		return allContents.indent(indentation);
	}

}

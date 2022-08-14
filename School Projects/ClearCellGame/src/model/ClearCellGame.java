package model;

import java.util.Random;

/* This class must extend Game */

//Random is only used for getNonEmptyRandomBoardCell
public class ClearCellGame extends Game  {
	private Random random;
	private int strategy;
	
	private int score;
	private int rowCount = 0;
	private boolean isGameOver = false;

	public ClearCellGame(int maxRows, int maxCols, Random random, int strategy) {
		super(maxRows, maxCols);
		this.random = random;
		this.strategy = strategy;
		score = 0;
	}

	@Override
	public boolean isGameOver() {
		return isGameOver;
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public void nextAnimationStep() {
		if (!isGameOver()) {
			if (isRowEmpty(board.length - 1)) {
				super.setRowWithColor(rowCount, BoardCell.getNonEmptyRandomBoardCell(random));
				for (int i = 1; i < super.getMaxCols(); i++) {
					super.setColWithColor(i, BoardCell.getNonEmptyRandomBoardCell(random));
				}
				
				BoardCell[][] newBoard = new BoardCell[super.getMaxRows()][super.getMaxCols()];
				
				for (int col = 0; col < super.getMaxCols(); col++) {
					newBoard[0][col] = board[rowCount][col];
				}
				
				for (int row = 0; row <= rowCount; row++) {
					for (int col = 0; col < newBoard[row].length; col++) {
						if (row + 1 <= rowCount) {
							newBoard[row + 1][col] = board[row][col];
						}
					}
				}
				
				for (int row = rowCount + 1; row < newBoard.length; row++) {
					for (int col = 0; col < newBoard[row].length; col++) {
						newBoard[row][col] = BoardCell.EMPTY;
					}
				}
				
				rowCount++;
				
				board = newBoard;
			} else {
				isGameOver = true;
			}
        }
	}

	@Override
	public void processCell(int rowIndex, int colIndex) {
		if (!isGameOver()) {
			
			processHorizontalAndVerticalCells(rowIndex, colIndex);
			
			collapseEmptyCells();
		}
	}
	
	private boolean isRowEmpty(int row) {
		boolean rowEmpty = false;
		int emptyCols = 0;
		for (int col = 0; col < super.getMaxCols(); col++) {
			if (board[row][col] == BoardCell.EMPTY) {
				emptyCols++;
			}
		}
		
		if (emptyCols == super.getMaxCols()) {
			rowEmpty = true;
		}
		
		return rowEmpty;
	}
	
	private void processHorizontalAndVerticalCells(int rowIndex, int colIndex) {
		BoardCell cell = super.getBoardCell(rowIndex, colIndex);
		
		for (int col = colIndex - 1; col >= 0; col--) {
			if (cell.getColor() == super.getBoardCell(rowIndex, col).getColor()) {
				super.setBoardCell(rowIndex, col, BoardCell.EMPTY);
				score++;
			} else {
				break;
			}
		}
		
		for (int col = colIndex; col < super.getMaxCols(); col++) {
			if (cell.getColor() == super.getBoardCell(rowIndex, col).getColor()) {
				super.setBoardCell(rowIndex, col, BoardCell.EMPTY);
				score++;
			} else {
				break;
			}
		}
		
		for (int row = rowIndex - 1; row >= 0; row--) {
			if (cell.getColor() == super.getBoardCell(row, colIndex).getColor()) {
				super.setBoardCell(row, colIndex, BoardCell.EMPTY);
				score++;
			} else {
				break;
			}
		}
		
		for (int row = rowIndex + 1; row < super.getMaxRows(); row++) {
			if (cell.getColor() == super.getBoardCell(row, colIndex).getColor()) {
				super.setBoardCell(row, colIndex, BoardCell.EMPTY);
				score++;
			} else {
				break;
			}
		}
	}
	
	private void collapseEmptyCells() {
		BoardCell[][] newBoard = new BoardCell[super.getMaxRows()][super.getMaxCols()];
		
		int rowCount = 0;
		for (int row = 0; row < board.length; row++) {
			int emptyCols = 0;
			for (int col = 0; col < board[row].length; col++) {
				if (board[row][col] != BoardCell.EMPTY) {
					newBoard[rowCount][col] = board[row][col];
				} else {
					emptyCols++;
				}
			}
			
			if (emptyCols < super.getMaxCols()) {
				rowCount++;
			}	
		}
		
		for (int row = 0; row < newBoard.length; row++) {
			for (int col = 0; col < newBoard[row].length; col++) {
				if (newBoard[row][col] == null) {
					newBoard[row][col] = BoardCell.EMPTY;
				}
			}
		}
		
		board = newBoard;
	}
	
}
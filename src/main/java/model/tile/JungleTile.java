package model.tile;

public class JungleTile implements Tile {

	int tileRow = 0;
	int tileColumn = 0;

	public JungleTile(int row, int column) {
		this.tileRow = row;
		this.tileColumn = column;
	}

	@Override
	public int getRow() {
		return this.tileRow;
	}

	@Override
	public int getColumn() {
		return this.tileColumn;
	}

}

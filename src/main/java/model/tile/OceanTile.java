package model.tile;

public class OceanTile implements Tile {
	
	int tileRow = 0;
	int tileColumn = 0;
	
	boolean isPoisoned = false;
	boolean isShocked = false;
	
	public OceanTile(int row, int column) {
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
	
	public boolean isPoisoned() {
		return isPoisoned;
	}
	
	public void setPoisoned(boolean poisoned) {
		this.isPoisoned = poisoned;
	}
	
	public boolean isShocked() {
		return isShocked;
	}
	
	public void setShocked(boolean shocked) {
		this.isShocked = shocked;
	}
}

package DataStructures;

public class Point {
	public int row, col;

	public Point(int row, int column) {
		this.row = row;
		this.col = column;
	}

	@Override
	public String toString() {
		return "(row " + row + ", col " + col + ")";
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	public boolean equals(Point p) {
		return p.row == row && p.col == col;
	}
}
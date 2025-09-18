import java.awt.Graphics;
import java.awt.Point;

public class Grid {
    private Cell[][] cells;
    private int rows;
    private int cols;

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        cells = new Cell[rows][cols];

        // initialize each cell with correct coordinates
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new Cell(i * Cell.SIZE, j * Cell.SIZE);
            }
        }
    }

    // Draw all cells in the grid
    public void paint(Graphics g) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j].paint(g, new Point(0, 0));
            }
        }
    }

    // Check if a point is inside any cell
    public Cell getCellAt(Point p) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (cells[i][j].contains(p)) {
                    return cells[i][j];
                }
            }
        }
        return null;
    }

    public Cell[][] getCells() {
        return cells;
    }
}

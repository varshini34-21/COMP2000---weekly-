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

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int x = i * Cell.SIZE;
                int y = j * Cell.SIZE;

                if (j == 2) cells[i][j] = new WaterCell(x, y);
                else if (j == 5) cells[i][j] = new SandCell(x, y);
                else cells[i][j] = new GrassCell(x, y);
            }
        }
    }

    public void paint(Graphics g) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j].paint(g, new Point(0, 0));
            }
        }
    }

    public Cell getCellAt(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            return cells[row][col];
        }
        return null;
    }

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
}

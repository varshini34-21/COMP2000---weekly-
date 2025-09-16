import java.awt.*;

public class Grid {
    private Cell[][] cells;

    public Grid(int rows, int cols, int cellSize, int offset) {
        cells = new Cell[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = offset + col * cellSize;
                int y = offset + row * cellSize;
                cells[row][col] = new Cell(x, y, cellSize);
            }
        }
    }

    public void paint(Graphics g, Point mousePos) {
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                cells[row][col].paint(g, mousePos);
            }
        }
    }
}

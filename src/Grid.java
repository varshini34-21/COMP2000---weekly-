import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Grid {
    private Cell[][] cells = new Cell[20][20];

    public Grid() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                int x = 10 + Cell.size * i;
                int y = 10 + Cell.size * j;
                char colLabel = colToLabel(i);

                
                if (j == 2) {
                    cells[i][j] = new WaterCell(colLabel, j, x, y);
                } else if (j == 5) {
                    cells[i][j] = new SandCell(colLabel, j, x, y);
                } else {
                    cells[i][j] = new GrassCell(colLabel, j, x, y);
                }
            }
        }
    }

    private char colToLabel(int col) {
        return (char) (col + 'A');
    }

    private int labelToCol(char col) {
        return col - 'A';
    }

    public void setCell(Cell cell, char col, int row) {
        int colIndex = labelToCol(col);
        if (colIndex >= 0 && colIndex < cells.length && row >= 0 && row < cells[colIndex].length) {
            cells[colIndex][row] = cell;
        } else {
            throw new IndexOutOfBoundsException("Invalid col/row: " + col + row);
        }
    }

    
    public void paint(Graphics g, Point mousePos) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].paint(g, mousePos);
            }
        }
    }

    
    public Optional<Cell> cellAtColRow(int c, int r) {
        if (c >= 0 && c < cells.length && r >= 0 && r < cells[c].length) {
            return Optional.of(cells[c][r]);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Cell> cellAtColRow(char c, int r) {
        return cellAtColRow(labelToCol(c), r);
    }

    
    public Optional<Cell> cellAtPoint(Point p) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].contains(p)) {
                    return Optional.of(cells[i][j]);
                }
            }
        }
        return Optional.empty();
    }

    
    public List<Cell> getRadius(Cell from, int size) {
        int i = labelToCol(from.col);
        int j = from.row;
        Set<Cell> inRadius = new HashSet<>();
        if (size > 0) {
            cellAtColRow(colToLabel(i), j - 1).ifPresent(inRadius::add);
            cellAtColRow(colToLabel(i), j + 1).ifPresent(inRadius::add);
            cellAtColRow(colToLabel(i - 1), j).ifPresent(inRadius::add);
            cellAtColRow(colToLabel(i + 1), j).ifPresent(inRadius::add);
        }

        for (Cell c : new ArrayList<>(inRadius)) {
            inRadius.addAll(getRadius(c, size - 1));
        }
        return new ArrayList<>(inRadius);
    }

   
    public void paintOverlay(Graphics g, List<Cell> cells, Color color) {
        g.setColor(color);
        for (Cell c : cells) {
            g.fillRect(c.x + 2, c.y + 2, c.width - 4, c.height - 4);
        }
    }
}

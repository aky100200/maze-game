package maze.obj;

import lombok.Getter;

@Getter
public class Board {
    private Cells[][] cells;
    private int maxX;
    private int maxY;

    public Board(int x, int y) {
        this.maxX = x;
        this.maxY = y;
        this.cells = new Cells[x][y];
    }
    public void setCell(int x,int y, Cells cell){
        cells[x][y] = cell;
    }
    public Cells getCell(int x,int y){
        return cells[x][y];
    }
}

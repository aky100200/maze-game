package maze.obj;

import lombok.Getter;

@Getter
public class Board {
    private Cells[][] cells;
    private int xLength;
    private int yLength;

    public Board(int x, int y) {
        this.xLength = x;
        this.yLength = y;
        this.cells = new Cells[x][y];
    }
    public void setCell(int x,int y, Cells cell){
        cells[x][y] = cell;
    }
    public Cells getCell(int x,int y){
        return cells[x][y];
    }
    public void print(){
        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < xLength; j++) {
                System.out.print(cells[i][j].getValue());
            }
            System.out.println();
        }
    }
}

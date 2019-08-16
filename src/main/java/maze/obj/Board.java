package maze.obj;

public class Board {
    private Cells[][] cells;

    public Board(int x, int y) {
        this.cells = new Cells[x][y];
    }
    public void setCell(int x,int y, Cells cell){
        cells[x][y] = cell;
    }
    public Cells getCell(int x,int y){
        return cells[x][y];
    }
}

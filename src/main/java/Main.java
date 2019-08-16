import maze.maker.Maker;
import maze.obj.Board;

public class Main {
    public static void main(String[] args) {
       Maker maker = new Maker(21,21);
       Board board = maker.makeBoard();
       board.print();
    }
}

package maze.maker;

import java.util.stream.IntStream;

public class Maker {
    private static final String WALL = "■";
    private static final String ROAD = "　";


    String[][] board;

    public Maker(int x , int y) {
        this.board = new String[y][x];
    }

    public void make() {
        for (int i = 0; i < board.length; i++) {
            int ix = i;
            if (i == 0 || i == (board.length - 1)) {
                IntStream.range(0, board.length).forEach(iy -> board[ix][iy] = WALL);
                continue;
            }
            if (i % 2 != 0) {
                board[ix][0] = WALL;
                IntStream.range(1, board.length - 1).forEach(iy -> board[ix][iy] = ROAD);
                board[ix][board[ix].length - 1] = WALL;
                continue;
            }
            for (int iy = 0; iy < board[ix].length; iy++) {
                board[ix][iy] = iy % 2 == 0 ? WALL : ROAD;
            }
        }
    }

    public void print() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

}

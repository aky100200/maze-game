package maze.maker;

import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

public class Maker {
    private static final String WALL = "■";
    private static final String ROAD = "　";

    String[][] board;
    Random rand = new Random();

    public Maker(int x, int y) {
        this.board = new String[y][x];
    }

    public void make() {
        makeBase();
        makeWall();
    }

    private void makeBase() {
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

    private void makeWall() {
        for (int i = 2; i < board.length - 1; i = i + 2) {
            for (int j = 2; j < board.length - 1; j = j + 2) {
                //最初の行以外は上方向へ壁を作成できない
                int bound = i == 2 ? 4 : 3;
                while (true) {
                    int ii = i;
                    int jj = j;
                    switch (rand.nextInt(bound)) {
                        case 0:
                            ii = i + 1;
                            break;
                        case 1:
                            jj = j + 1;
                            break;
                        case 2:
                            jj = j - 1;
                            break;
                        default:
                            ii = i - 1;
                            break;
                    }
                    if (ii == board.length
                            || jj == board[i].length
                            || Objects.equals(board[ii][jj], WALL)) {
                        continue;
                    }
                    board[ii][jj] = WALL;
                    break;
                }
            }
            board[0][1] = "Ｓ";
            board[board.length - 1][board[board.length - 1].length - 2] = "Ｇ";
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

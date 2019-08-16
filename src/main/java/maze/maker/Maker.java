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
        baseMake();
        //棒倒し法で作成
        for (int i = 2; i < board.length; i = i + 2) {
            for (int j = 2; j < board.length; j = j + 2) {
                String point = board[i][j];
                if (Objects.equals(point, ROAD)) {
                    continue;
                }
                if (i == 2) {
                    //壁がある場所には倒せない
                    while (true) {
                        //4方向にランダムで壁を作成
                        int r = rand.nextInt(4);
                        switch (r) {
                            case 0:
                                if (Objects.equals(board[i - 1][j], WALL)) {
                                    continue;
                                }
                                board[i - 1][j] = WALL;
                                break;
                            case 1:
                                if (Objects.equals(board[i][j - 1], WALL)) {
                                    continue;
                                }
                                board[i][j - 1] = WALL;
                                break;
                            case 2:
                                if (i + 1 == board.length || Objects.equals(board[i + 1][j], WALL)) {
                                    continue;
                                }
                                board[i + 1][j] = WALL;
                                break;
                            default:
                                if (j + 1 == board[i].length || Objects.equals(board[i][j + 1], WALL)) {
                                    continue;
                                }
                                board[i][j + 1] = WALL;
                                break;
                        }
                        break;
                    }
                } else {
                    //3方向にランダムで壁を作成
                    //壁がある場所には倒せない
                    boolean flg = false;
                    while (!flg) {
                        //4方向にランダムで壁を作成
                        int r = rand.nextInt(3);
                        try {
                            switch (r) {
                                case 0:
                                    String p = board[i][j - 1];
                                    if (Objects.equals(p, WALL)) {
                                        continue;
                                    }
                                    board[i][j - 1] = WALL;
                                    flg = true;
                                    break;
                                case 1:
                                    p = board[i + 1][j];
                                    if (Objects.equals(p, WALL)) {
                                        continue;
                                    }
                                    board[i + 1][j] = WALL;
                                    flg = true;
                                    break;
                                default:
                                    p = board[i][j + 1];
                                    if (Objects.equals(p, WALL)) {
                                        continue;
                                    }
                                    board[i][j + 1] = WALL;
                                    flg = true;
                                    break;
                            }
                        } catch (Throwable t) {
                            break;
                        }
                    }
                }
            }
            board[0][1] = "Ｓ";
            board[board.length - 1][board[board.length - 1].length - 2] = "Ｇ";
        }

    }

    private void baseMake() {
        //外壁と1マス飛ばしで内壁を作成
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

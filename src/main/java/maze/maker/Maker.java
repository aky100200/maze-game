package maze.maker;

import maze.obj.Board;
import maze.obj.Cells;

import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

public class Maker {
    private Board board;
    Random rand = new Random();

    public Maker(int x, int y) {
        this.board = new Board(y, x);
    }

    public Board makeBoard() {
        makeBase();
        makeWall();
        return board;
    }

    private void makeBase() {
        for (int i = 0; i < board.getMaxX(); i++) {
            int ix = i;
            if (i == 0 || i == (board.getMaxX() - 1)) {
                IntStream.range(0, board.getMaxX()).forEach(iy -> board.setCell(ix, iy, Cells.WALL));
                continue;
            }
            if (i % 2 != 0) {
                board.setCell(ix, 0, Cells.WALL);
                IntStream.range(1, board.getMaxX() - 1).forEach(iy -> board.setCell(ix, iy, Cells.ROAD));
                board.setCell(ix, board.getMaxY() - 1, Cells.WALL);
                continue;
            }
            for (int iy = 0; iy < board.getMaxY(); iy++) {
                Cells cell = iy % 2 == 0 ? Cells.WALL : Cells.ROAD;
                board.setCell(ix, iy, cell);
            }
        }
    }

    private void makeWall() {
        for (int i = 2; i < board.getMaxX() - 1; i = i + 2) {
            for (int j = 2; j < board.getMaxY() - 1; j = j + 2) {
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
                    if (ii == board.getMaxX()
                            || jj == board.getMaxY()
                            || Objects.equals(board.getCell(ii, jj), Cells.WALL)) {
                        continue;
                    }
                    board.setCell(ii, jj, Cells.WALL);
                    break;
                }
            }
            board.setCell(0, 1, Cells.START);
            board.setCell(board.getMaxX() - 1, board.getMaxY() - 2, Cells.GOAL);
        }
    }
}

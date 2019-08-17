package maze.game;

import lombok.AllArgsConstructor;
import maze.obj.Board;
import maze.obj.Cells;

import java.util.LinkedList;

public class SolveMaze {
    public Board solve(Board board) {
        //開始位置
        Point startCell = findStartCell(board);
        int x = startCell.x;
        int y = startCell.y;

        //通過した座標をtrueにする
        boolean[][] been = new boolean[board.getXLength()][board.getYLength()];

        //ルート記憶のためのスタック
        LinkedList<Point> stack = new LinkedList<>();
        been[x][y] = true;
        stack.push(new Point(x, y));

        while (board.getCell(x, y) != Cells.GOAL) {
            //上
            if (x > 0 && isValidCell(x - 1, y, board, been)) {
                x = x - 1;
                been[x][y] = true;
                stack.push(new Point(x, y));
                continue;
            }
            //左
            else if (y > 0 && isValidCell(x, y - 1, board, been)) {
                y = y - 1;
                been[x][y] = true;
                stack.push(new Point(x, y));
                continue;
            }
            //右
            else if (board.getXLength() > x + 1 && isValidCell(x + 1, y, board, been)) {
                x = x + 1;
                been[x][y] = true;
                stack.push(new Point(x, y));
                continue;
            }
            //下
            else if (board.getYLength() > y + 1 && isValidCell(x, y + 1, board, been)) {
                y = y + 1;
                been[x][y] = true;
                stack.push(new Point(x, y));
                continue;
            }
            //どこにも行けない場合は1つ戻る
            else {
                //1つ戻る
                stack.poll();
                //さらに1つ前を参照
                Point p = stack.peek();
                x = p.x;
                y = p.y;
            }
        }
        //キューに入っている座標の場所を置換してルートを示す
        while (stack.size() != 0) {
            Point p = stack.poll();
            board.setCell(p.x, p.y, Cells.BEEN);
        }
        return board;
    }

    private Point findStartCell(Board board) {
        for (int i = 0; i < board.getXLength(); i++) {
            for (int j = 0; j < board.getYLength(); j++) {
                if (board.getCell(i, j) == Cells.START) return new Point(i, j);
            }
        }
        throw new RuntimeException("Start Cell Not Found.");
    }


    private boolean isValidCell(int x, int y, Board board, boolean[][] been) {
        return (board.getCell(x, y) == Cells.ROAD || board.getCell(x, y) == Cells.GOAL) && !been[x][y];
    }

    @AllArgsConstructor
    private static class Point {
        int x;
        int y;
    }

}

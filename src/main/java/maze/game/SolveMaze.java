package maze.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import maze.obj.Board;
import maze.obj.Cells;

import java.util.LinkedList;

public class SolveMaze {
    public Board solve(Board board) {
        //開始位置 0,1
        int x = 0;
        int y = 1;

        //行ったことがある場合はtrue
        boolean[][] been = new boolean[21][21];

        LinkedList<Point> stack = new LinkedList<>();
        been[x][y] = true;
        stack.push(new Point(x, y));

        //4方向
        //上下左右が壁か確認
        while (board.getCell(x, y) != Cells.GOAL) {
            //上
            if (x > 0 && (board.getCell(x - 1, y) == Cells.ROAD || board.getCell(x - 1, y) == Cells.GOAL) && !been[x - 1][y]) {
                x = x - 1;
                been[x][y] = true;
                stack.push(new Point(x, y));
                continue;
            }
            //左
            else if (y > 0 && (board.getCell(x, y - 1) == Cells.ROAD || board.getCell(x, y - 1) == Cells.GOAL) && !been[x][y - 1]) {
                y = y - 1;
                been[x][y] = true;
                stack.push(new Point(x, y));
                continue;
            }
            //右
            else if (board.getXLength() > x + 1 && (board.getCell(x + 1, y) == Cells.ROAD || board.getCell(x + 1, y) == Cells.GOAL) && !been[x + 1][y]) {
                x = x + 1;
                been[x][y] = true;
                stack.push(new Point(x, y));
                continue;
            }
            //下
            else if (board.getYLength() > y + 1 && (board.getCell(x, y + 1) == Cells.ROAD || board.getCell(x, y + 1) == Cells.GOAL) && !been[x][y + 1]) {
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
                x = p.getX();
                y = p.getY();
            }
        }
        //end
        //キューに入っている座標の場所を置換してルートを示す
        while (stack.size() != 0) {
            Point p = stack.poll();
            board.setCell(p.x, p.y, Cells.BEEN);
        }
        return board;
    }

    @AllArgsConstructor
    @Data
    public static class Point{
        int x;
        int y;
    }

}

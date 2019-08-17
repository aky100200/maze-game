import lombok.AllArgsConstructor;
import lombok.Data;
import maze.maker.Maker;
import maze.obj.Board;
import maze.obj.Cells;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
       Maker maker = new Maker(21,21);
       Board board = maker.makeBoard();
       board.print();
       solveMaze(board);
    }

    private static void solveMaze(Board board) {
        //開始位置 0,1
        int x = 0;
        int y = 1;

        //行ったことがある場合はtrue
        boolean[][] been = new boolean[21][21];

//        Queue<Point> queue = new ArrayDeque<>();
//        queue.add(new Point(x,y));

        LinkedList<Point> stack = new LinkedList<>();

//        Point p = queue.peek();
//        stack.push();
//        stack.poll();
//        System.out.println(board.getCell(p.x,p.y));
        been[x][y] = true;

        //4方向
        //上下左右が壁か確認

        int index = 0;
        while (board.getCell(x,y) != Cells.GOAL) {
//            //TODO:
//            index++;
//            System.out.println(new Point(x, y));
//            if (index == 100) {
//                break;
//            }

            //上
            if (x > 0 && (board.getCell(x - 1, y) == Cells.ROAD || board.getCell(x - 1, y) == Cells.GOAL) && !been[x - 1][y]) {
                x = x - 1;
                been[x][y] = true;
                stack.push(new Point(x,y));
                continue;
            }
            //左
            else if (y > 0 && (board.getCell(x, y - 1) == Cells.ROAD ||board.getCell(x, y - 1) == Cells.GOAL )&& !been[x][y - 1]) {
                y = y - 1;
                been[x][y] = true;
                stack.push(new Point(x,y));
                continue;
            }
            //右
            else if (board.getXLength() > x + 1 && (board.getCell(x + 1, y) == Cells.ROAD || board.getCell(x + 1, y) == Cells.GOAL )&& !been[x + 1][y]) {
                x = x + 1;
                been[x][y] = true;
                stack.push(new Point(x,y));
                continue;
            }
            //下
            else if (board.getYLength() > y + 1 && (board.getCell(x, y + 1) == Cells.ROAD || board.getCell(x, y + 1) == Cells.GOAL )&& !been[x][y + 1]) {
                y = y + 1;
                been[x][y] = true;
                stack.push(new Point(x,y));
                continue;
            }
            //どこにも行けない場合は1つ戻る
            else {
//                try {
                    //1つ戻る
                    stack.poll();
                    //さらに1つ前を参照
                    Point p = stack.peek();
                    x = p.getX();
                    y = p.getY();
//                }catch (Throwable t){
//                    printBeen(board,been);
//                    t.printStackTrace();
//                }
            }
        }


            //壁でない場合、行ったことがあるか確認

        //4方向すべてが壁、または行ったことがある場合は1つ戻る



        //TODO:
        //行ったことがる場所を置換
//        for (int i = 0; i < board.getXLength(); i++) {
//            for (int j = 0; j < board.getYLength(); j++) {
//                if (been[i][j]) {
//                    board.setCell(i, j, Cells.BEEN);
//                }
//            }
//        }

        //end
        //キューに入っている座標の場所を置換してルートを示す
        //TODO:「　」→「・」
while (stack.size() != 0){
    Point p = stack.poll();
    board.setCell(p.x, p.y, Cells.BEEN);
}

        board.print();






//        for (int i = 0; i < board.getXLength(); i++) {
//            for (int j = 0; j < board.getYLength(); j++) {
//                if (board.getCell(i,j) == Cells.START) {
//                    System.out.println("i -> "+ i);
//                    System.out.println("j -> "+ j);
//                    System.out.println(board.getCell(i, j));
//                }
//            }
//        }
        System.out.println();

    }

//    private static void printBeen(Board board, boolean[][] been) {
//        for (int i = 0; i < board.getXLength(); i++) {
//            for (int j = 0; j < board.getYLength(); j++) {
//                if (been[i][j]) {
//                    board.setCell(i, j, Cells.BEEN);
//                }
//            }
//        }
//        board.print();
//    }
//
    @AllArgsConstructor
    @Data
    public static class Point{
        int x;
        int y;
    }
}

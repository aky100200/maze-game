import maze.maker.Maker;

public class Main {
    public static void main(String[] args) {
       Maker maker = new Maker(21,21);
       maker.make();
       maker.print();
    }
}

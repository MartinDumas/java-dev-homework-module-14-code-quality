public class Demo {
    public static void main(String[] args) {
        App app = new App();
        app.resetBoard();
        System.out.println("Enter box number to select. Enjoy!\n");

        while (true) {
            app.printBoard();
            app.playerTurn();
            if (app.isGameOver()) break;
            app.computerTurn();
            if (app.isGameOver()) break;
        }
    }
}

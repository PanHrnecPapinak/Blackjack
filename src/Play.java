public class Play {
    public static void main(String[] args) throws InterruptedException {
        boolean gameStatus = true;
        Game bestGame = new Game();
        bestGame.startGame();

        while (gameStatus) {
            gameStatus = bestGame.gameLoop();
        }
        bestGame.enemyTurn();


    }
}

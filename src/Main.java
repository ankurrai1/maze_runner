import Maze.Game;
import Maze.maze;
public class Main {
    public static void greetUser(){
        System.out.println("Welcome to our game maze runner");
    }

    public static void main(String[] args) {

        greetUser();
        maze myMaze = new maze(20);
        Game game = new Game(myMaze);
        game.rulesIntro();
        game.startGame();
        game.announceResult();
    }
}

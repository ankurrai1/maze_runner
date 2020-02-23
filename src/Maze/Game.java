package Maze;

import java.util.Scanner;

public class Game {

    private Scanner input = new Scanner(System.in);
    private String gameStatus = " game not started yet";
    private maze myMaze;
    private int moveCount = 0;

    public Game(maze newMaze) {
        this.myMaze = newMaze;
        myMaze.fillSolution();
    }

    public void rulesIntro(){
        System.out.println("The \"x\" represents your current position and the \".\" Represents an unknown space.");
        System.out.println(" As you move through the map the \".\" Will turn into either walls (\"-\") or free spaces (\"*\").");
        System.out.println(" There might even be more surprises in store for you as well");
        System.out.println("you have limited moves to escape from the maze otherwise you will trapped");
        System.out.println("You can move  Right (R,r), Left (L,l), Up (U,u) or Down (D,d);");
        myMaze.printMap();
    }

    public String askToMove(){
        System.out.print("Where would you like to move? (R, L, U, D) : ");
        return input.next();
    }

    public void moveMessage(int move){
        if (move == 50){
            System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes");
        }
        else if(move == 75){
            System.out.println("Alert! You have made 75 moves, you only have 25 moves left to escape.");
        }
        else if(move == 90){
            System.out.println("DANGER! You have made 90 moves, you only have 10 moves left to escape!!");
        }
    }

    public boolean canIMove(String dir){
        if (dir.equals("R") || dir.equals("r")) return myMaze.canIMoveRight();
        else if (dir.equals("L") || dir.equals("l")) return myMaze.canIMoveLeft();
        else if (dir.equals("U") || dir.equals("u")) return myMaze.canIMoveUp();
        else if (dir.equals("D") || dir.equals("d")) return myMaze.canIMoveDown();
        else {
            throw new IllegalArgumentException("invalid input direction");
        }
    }

    public void moveThere(String dir){
        if (dir.equals("R") || dir.equals("r")) this.myMaze.moveRight();
        else if (dir.equals("L") || dir.equals("l")) this.myMaze.moveLeft();
        else if (dir.equals("U") || dir.equals("u")) this.myMaze.moveUp();
        else if (dir.equals("D") || dir.equals("d")) this.myMaze.moveDown();
    }

    public void checkForPitAndJump(String dir){
       if(myMaze.isThereAPit(dir)){
        System.out.println("Watch out! There's a pit ahead, jump it?");
        System.out.print("do you want to jump Y for yes and N for no:");
        String ans = input.next();
        if(ans.startsWith("Y") || ans.startsWith("y")){
            moveCount++;
            myMaze.jumpOverPit(dir);
        }
       }
    }

    public void runGame(){
        
        while(moveCount < 100 ){

            String dir = askToMove();
            moveCount++;
            if(canIMove(dir)){
                checkForPitAndJump(dir);
                moveThere(dir);
                myMaze.printMap();
            }
            else{
                System.out.println("Sorry you hit the wall ........");
            }
            if(myMaze.didIWin()){
                setGameStatus("Congratulations, you made it out alive! in"+ moveCount +"moves" );
                break ;
            }
            if (moveCount == 50 || moveCount == 75 || moveCount == 90){
                moveMessage(moveCount);
            }
        }
        System.out.println("Oh no! You took too long to escape, and now the maze exit is closed FOREVER >:[");
        setGameStatus("Sorry, but you didn't escape in time- you lose!");
    }

    public void startGame(){
        
        try {
            runGame();
        }catch (Exception e ){
            System.out.println(e.getMessage());
            startGame();
        }
    }

    public void announceResult(){
        System.out.println(this.gameStatus);
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }
}

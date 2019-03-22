package Maze;

public class maze {
    private char[][] map;
    private char[][] solution;
    private int mapSize;
    private int row = 1;
    private int column = 0;

    public maze(int mapSize) {
        this.mapSize = mapSize;
        this.map = new char[mapSize][mapSize];
        this.solution = new char[mapSize][mapSize];
        fillMap(map,mapSize);
        fillMap(solution,mapSize);
        map[row][column] = 'x';
        printMap(map,this.mapSize);
    }

    public void fillMap(char[][] myMap ,int mapSize){
        for (int i = 0; i < mapSize; i++ ){
            for (int j = 0; j < mapSize; j++ ){
                myMap[i][j] = '.';
            }
        }
    }

    /**   here I used method overloading to refresh the map ; */

    public void printMap() {
        printMap(map,this.mapSize);
    }

    private void printMap(char[][] map, int mapSize) {
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private boolean isMoveInsideMap(int rowMove, int colMove, int mapSize){
        int nextRow = row + rowMove;
        int nextCol = column+colMove;
        if(nextCol > mapSize || nextCol < 0 || nextRow > mapSize || nextRow < 0){
            return false;
        }
        else if(solution[nextRow][nextCol]=='0'){
            map[nextRow][nextCol] = '0';
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isValidMove(String dir){
        if(dir.equals("R") || dir.equals("r")){
            return isMoveInsideMap(0,1, mapSize);
        }
        if(dir.equals("L") || dir.equals("l")){
            return isMoveInsideMap(0,-1, mapSize);
        }
        if(dir.equals("U") || dir.equals("u")){
            return isMoveInsideMap(-1,0, mapSize);
        }
        if(dir.equals("D") || dir.equals("d")){
            return isMoveInsideMap(1,0,mapSize);
        }else{
            throw new IllegalArgumentException("The entered direction is not valid ");
        }
    }
    public void jumpOverPit(String dir) {
        if(isValidMove(dir)) {
            if(dir.equals("R") || dir.equals("r")) {
                move(0, 2);
            } else if (dir.equals("L") || dir.equals("l")) {
                move(0, -2);
            } else if (dir.equals("U") || dir.equals("u")) {
                move(-2, 0);
            } else if(dir.equals("D") || dir.equals("d")) {
                move(2, 0);
            }
        }
    }
    private boolean canMove(int rowMove, int colMove, int mapSize){
        int nextRow = row + rowMove;
        int nextCol = column+colMove;
        if(nextCol > mapSize || nextCol < 0 || nextRow > mapSize || nextRow < 0){
            return false;
        }
        else if (solution[nextRow][nextCol] == '*') {
            map[nextRow][nextCol] = '*';
            return true;
        }else if(solution[nextRow][nextCol]=='0'){
            map[nextRow][nextCol] = '*';
            return true;
        } else {
            map[nextRow][nextCol] = '-';
            return false;
        }
    }

    public boolean canIMoveRight() {
        return canMove(0,1, mapSize);
    }

    public boolean canIMoveLeft() {
        return canMove(0,-1,mapSize);
    }

    public boolean canIMoveUp() {
        return canMove(-1,0, mapSize);
    }

    public boolean canIMoveDown() {
        return canMove(1,0, mapSize);
    }

    private void move(int rowMove, int colMove) {
        if(canMove(rowMove, colMove,mapSize)) {
            map[row][column] = '*';
            row += rowMove;
            column += colMove;
            if(solution[row][column] == '0') {
                throw new IllegalArgumentException("Oh NOOOOO you fell into a pit!");
            }
            map[row][column] = 'x';
        } else {
            throw new IllegalArgumentException("ERROR: You cannot move that way");
        }
    }

    public void moveRight() {
        move(0, 1);
    }

    public void moveLeft() {
        move(0, -1);
    }

    public void moveUp() {
        move(-1, 0);
    }

    public void moveDown() {
        move(1, 0);
    }

    private void fillSolution() {
        for (int i = 0; i < 6; i++) {
            solution[0][i] = '-';
        }
        for (int i = 0; i < 5; i++) {
            solution[1][i] = '*';
        }
        for (int i = 2; i < 20; i++) {
            solution[i][4] = '*';
        }
        solution[19][4] = '-';
        solution[1][5] = '|';
        solution[2][5] = '|';
        for (int i = 0; i < 14; i++) {
            solution[2][i] = '-';
        }
        solution[2][4] = '*';
        for (int i = 3; i < 20; i++) {
            solution[i][3] = '|';
        }
        for (int i = 4; i < 15; i++) {
            solution[3][i] = '*';
        }
        solution[2][14] = '|';
        solution[1][14] = '|';
        solution[0][14] = '-';
        solution[0][15] = '-';
        solution[0][16] = '-';
        for (int i = 1; i < 7; i++) {
            solution[i][16] = '|';
        }
        solution[7][14] = '-';
        solution[7][15] = '-';
        solution[7][16] = '-';
        for (int i = 5; i < 15; i++) {
            solution[4][i] = '-';
        }
        solution[5][14] = '|';
        solution[6][14] = '|';
        for (int i = 1; i < 7; i++) {
            solution[i][15] = '*';
        }
        for (int i = 5; i < 20; i++) {
            solution[i][5] = '|';
        }
        for (int i = 5; i < 17; i++) {
            solution[12][i] = '-';
            solution[13][i] = '*';
            solution[14][i] = '-';
        }
        solution[11][16] = '|';
        solution[10][16] = '|';
        solution[14][18] = '|';
        solution[13][18] = '|';
        solution[12][18] = '|';
        solution[11][18] = '-';
        solution[11][19] = '-';
        solution[9][16] = '-';
        solution[9][17] = '-';
        solution[9][18] = '-';
        solution[9][19] = '-';
        solution[14][17] = '-';
        for (int i = 10; i < 14; i++) {
            solution[i][17] = '*';
        }
        solution[10][18] = '*';
        solution[10][19] = '*';
        for(int i = 5; i < 13; i++) {
            solution[8][i] = '-';
            solution[10][i] = '-';
            solution[9][i] = '*';
        }
        for (int i = 8; i < 11; i++) {
            solution[i][13] = '|';
        }
        for(int i = 5; i < 19; i++) {
            solution[17][i] = '-';
            solution[19][i] = '-';
            solution[18][i] = '*';
        }
        for (int i = 17; i < 20; i++) {
            solution[i][19] = '|';
        }
        for(int i = 3; i >= 0; i--) {
            solution[14][i] = '-';
            solution[16][i] = '-';
            solution[15][i] = '*';
        }
        for (int i = 14; i < 17; i++) {
            solution[i][0] = '|';
        }
        addPits();
    }

    private void addPits(){
        solution[1][2] = '0';
        solution[3][7] = '0';
        solution[3][12] = '0';
        solution[6][4] = '0';
        solution[15][4] = '0';
        solution[9][10] = '0';
        solution[13][17] = '0';
        solution[13][15] = '0';
        solution[18][10] = '0';
    }

    public boolean didIWin() {
        if (row == 10 && column == 19) {
            return true;
        } else {
            return false;
        }
    }
}
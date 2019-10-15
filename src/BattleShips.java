import java.util.Arrays; import java.util.Scanner;

public class BattleShips {

    static int alt =0;
    static String[][] arena = new String[10][10];
    static String[][] enemyArena = new String[10][10];
    static int init=0;
    static int init2=0;
    static int enemyShips =5;
    static int myShips =5;

    // MAP GENERATOR, 10 x 10
    public static void myMap(){
        System.out.println("**** My Map ***");
        System.out.println("   0123456789");
        if(init == 0){
            for (int i=0; i<arena.length;i++){
                System.out.print(i + " |");
                for (int j=0; j<arena[i].length;j++)
                {
                    arena[i][j] = " ";
                    System.out.print(arena[i][j]);
                }
                System.out.println("| " + i);
            }
            init++;
        } else {
            for (int i=0; i<arena.length;i++){
                System.out.print(i + " |");
                for (int j=0; j<arena[i].length;j++)
                {
                    System.out.print(arena[i][j]);
                }
                System.out.println("| " + i);
            }
        }
        System.out.println("   0123456789");
    }
// AI's MAP 10 x 10
    public static void enemyMap(){
        System.out.println("****Enemy Map****");
        System.out.println("   0123456789");
        if(init2 == 0){
            for (int i=0; i<enemyArena.length;i++){
                System.out.print(i + " |");
                for (int j=0; j<enemyArena[i].length;j++)
                {
                    enemyArena[i][j] = " ";
                    System.out.print(enemyArena[i][j]);
                }
                System.out.println("| " + i);
            }
            init2++;
        } else {
            for (int i=0; i<enemyArena.length;i++){
                System.out.print(i + " |");
                for (int j=0; j<enemyArena[i].length;j++)
                {
                    System.out.print(enemyArena[i][j]);
                }
                System.out.println("| " + i);
            }
        }
        System.out.println("   0123456789");
    }
// PLAYER PLACES SHIPS
    public static int coor(){
        Scanner input = new Scanner(System.in);
        int x;
        int y;
        if (alt==0){
            do {
                System.out.print("Enter X coordinate for your ship: "); //HORIZONTAL COOR
                x = input.nextInt();
            } while ( x<0 || x>9); //CHECK
            alt++;
            return x;
        } else {
            do {
                System.out.print("Enter Y coordinate for your ship: "); //VERTICAL COOR
                y = input.nextInt();
            } while ( y<0 || y>9); //CHECK
            alt--;
            return y;
        }
    }
// SHOOTING BY PLAYER
    public static int myGuess(){
        Scanner input = new Scanner(System.in);
        int x;
        int y;
        System.out.println("Your Turn");
        do{
            System.out.println("Enter X coordinate: ");
            x = input.nextInt();
        } while (x<0 || x>9);
        do{
            System.out.println("Enter Y coordinate: ");
            y = input.nextInt();
        } while (y<0 || y>9);
        if(enemyArena[x][y].equals("@")){
            enemyArena[x][y] = "x";
            System.out.println("Boom! You sunk the ship!");
            enemyShips--;
            if(enemyShips==0){
                System.out.println("You win");
            }
        } else {
            enemyArena[x][y] = "0";
            System.out.println("You missed");
        }
        return enemyShips;
    }
// AI'S SHOOTING
    public static int enemyGuess(){
        Scanner input = new Scanner(System.in);
        System.out.println("Their Turn");
        int x = (int) (Math.random()*10);
        System.out.println("Enter X coordinate: "+x);
        int y = (int) (Math.random()*10);
        System.out.println("Enter Y coordinate: "+y);
        if(arena[x][y].equals("@")){
            arena[x][y] = "x";
            System.out.println("Boom! They sunk your ship!");
            myShips--;
            if(myShips==0){
                System.out.println("Computer wins");
            }
        } else {
            arena[x][y] = "0";
            System.out.println("Computer missed");
        }
        return myShips;
    }

    public static void main(String[] args){
        myMap();
        enemyMap();
        int x;
        int y;
        int enemyX;
        int enemyY;
        for(int i=0; i<5;i++)
        {
            x = coor();
            y = coor();
            if(arena[x][y].equals("@")){
                System.out.println("Coordinates are full, please enter different coordinates");
                i--;
            } else {
                arena[x][y] = "@";
            }
        }
        System.out.println("Computer is deploying ships");
        for(int j=0; j<5;j++)
        {
            enemyX = (int) (Math.random()*10);
            enemyY = (int) (Math.random()*10);
            if(enemyArena[enemyX][enemyY].equals("@")){
                j--;
            } else {
                enemyArena[enemyX][enemyY] = "@";

                System.out.println((j+1) +". ship DEPLOYED");
            }
        }
        do{
            myMap();
            enemyMap();
            x = myGuess();
            if(x==0){
                break;
            }
            y = enemyGuess();
        } while ( x!=0 && y!=0);
    }
}
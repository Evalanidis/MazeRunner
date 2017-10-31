/*
* @author Valanidis Efstathios
* @literal evalanidis@gmail.com
* @since 31/10/2017
* */

import java.util.Scanner;

public class MazeRunner {

    static Scanner input = new Scanner(System.in);
    static String decidedMove,jumpAnswer;
    static int i =0;
    static Maze myMap = new Maze();

    public static void main(String[] args){

        System.out.println("Welcome to the Maze Runner!\nYou have only 100 moves.\nHere is your current position:\n");


        myMap.printMap();
        System.out.println("-----------------------------------------------------");

        /*
        *The first if checks if the user has reached the exit of the maze and congratulate him
        *The second if checks the moves the user did and if they are over 100 is Game Over
        * and finally the main code for the user to play and enjoy the game
        * */
        while(true) {
            if(myMap.didIWin()) {
                System.out.println("Congratulations you win and you did it in " +i+" moves");
                break;
            }
            else if(i == 101){
                movesMessage(i);
                System.out.println("Sorry, but you didn't escape in time- you lose!");
                break;
            }
            else{
                decidedMove = userMove();
                decidedMove = decidedMove.toUpperCase();
                movesMessage(i);
                playGame();
                i++;
            }
        }


    }
    /*
    * the playGame method is where the program checks the direction the user types
    * to go and then calls te userMove method to move the x to the specified direction.
    * Also with the help of navigatePit method help the user to jump over the pit or choose another direction
    * Lastly, it display the according messages.
    * */
    public static void playGame(){
        switch (decidedMove) {
            case "R":
                if (myMap.isThereAPit(decidedMove)) {
                    navigatePit();
                } else if(myMap.canIMoveRight()){
                    myMap.moveRight();
                    myMap.printMap();
                    System.out.println("-----------------------------------------------------");
                }else System.out.println("Sorry you've hit a wall");
                break;
            case "L":
                if (myMap.isThereAPit(decidedMove)) {
                    navigatePit();

                } else if(myMap.canIMoveLeft()){
                    myMap.moveLeft();
                    myMap.printMap();
                    System.out.println("-----------------------------------------------------");
                }else System.out.println("Sorry you've hit a wall");
                break;
            case "D":
                if (myMap.isThereAPit(decidedMove)) {
                    navigatePit();

                } else if(myMap.canIMoveDown()){
                    myMap.moveDown();
                    myMap.printMap();
                    System.out.println("-----------------------------------------------------");
                }else System.out.println("Sorry you've hit a wall");
                break;
            case "U":
                if (myMap.isThereAPit(decidedMove)) {
                    navigatePit();

                } else if(myMap.canIMoveUp()){
                    myMap.moveUp();
                    myMap.printMap();
                    System.out.println("-----------------------------------------------------");
                }else System.out.println("Sorry you've hit a wall");
                break;
        }
    }

    /*
    * the userMove method checks if the user typed the correct direction and not something else.
    * */
    public static String userMove(){
        String move;
        System.out.println("Where would you like to move? (R, L, U, D)");
        while(true){
            move = input.nextLine();
            if((move.equalsIgnoreCase("r")) || (move.equalsIgnoreCase("l"))
                    || (move.equalsIgnoreCase("u")) || (move.equalsIgnoreCase("d"))){
                return move;
            }
            else System.out.println("Wrong direction. (R, L, U, D)");
        }
    }

    /*
    * the moveMessage method is only checking and print the proper message when the user had made a specific number of mooves
    * and warning him for the remaining moves
    * */
    public static void movesMessage(int moves){
        if(moves == 50) System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes");
        else if(moves == 75) System.out.println("Alert! You have made 75 moves, you only have 25 moves left to escape.");
        else if(moves == 90) System.out.println("DANGER! You have made 90 moves, you only have 10 moves left to escape!!");
        else if(moves == 100) System.out.println("Oh no! You took too long to escape, and now the maze exit is closed FOREVER >:[");
    }


    /*
    * the navigatePit method warns the user that is a pit blocking his path and ask him if he wants to jump it
    * */
    public static void navigatePit(){
        System.out.println("Watch out! There's a pit ahead, jump it?");
        jumpAnswer = input.nextLine();
        if(jumpAnswer.startsWith("y")||jumpAnswer.startsWith("Y")){
            myMap.jumpOverPit(decidedMove);
            myMap.printMap();
            System.out.println("-----------------------------------------------------");
        }

    }
}

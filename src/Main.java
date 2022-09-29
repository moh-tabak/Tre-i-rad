import java.util.Random;
import java.util.Scanner;

public class Main {
    private static Scanner scanner =new Scanner(System.in);

    private static Player[] players = new Player[3];
    private  static Board9 board;

    public static void main(String[] args) {
        board = new Board9();
        int choice = 0;
        //Main loop
        while (true){
            System.out.println(" ");
            System.out.println("*** Tre-i-rad ***");
            System.out.println("Huvudmeny:");
            System.out.println("[1] : Två spelare");
            System.out.println("[2] : Spela mot dator");
            switch(readChoice(2)){
                case 1:
                    //Player vs Player
                    players[1] = new Player();
                    players[2] = new Player();
                    players[1].setName(readName(1));
                    players[2].setName(readName(2));
                    matchWithHuman();
                    break;
                case 2:
                    //Player vs computer
                    break;
                default:
            }
        }
    }

    private static int readChoice(int numberOfChoices){
        int choice = 0;
        while(choice == 0){
            if(scanner.hasNextInt()){
                choice = scanner.nextInt();
                if (choice > 0 && choice <= numberOfChoices){
                    scanner.nextLine();
                    return choice;
                }
                else{
                    //Reset choice to stay in the loop
                    choice = 0;
                    scanner.nextLine();
                }
            }
            else{
                scanner.nextLine();
            }
        }
       return -1;
    }

    private static String readName(int player){
        String input;
        System.out.println(board.getSymbol(player) + "-spelaren" + ", Ange ditt namn:");
            input= scanner.nextLine();
            if(input.length()>2 && input.length()<12){
                return input;
            }
        return board.getSymbol(player) + "-spelaren";
    }

    private static int[] readSquare(){
        int[] coordinates  = new int[2];
        char[] input;
        int conditionsMet = 0;
        while (conditionsMet < 3){
            conditionsMet = 0;
            input= scanner.nextLine().toUpperCase().toCharArray();
            if(input.length == 2 && Character.isDigit(input[1])){
                switch (input[0]){
                    case 'A':
                        conditionsMet++;
                        coordinates[0] = 0;
                        break;
                    case 'B':
                        conditionsMet++;
                        coordinates[0] = 1;
                        break;
                    case'C':
                        conditionsMet++;
                        coordinates[0] = 2;
                        break;
                }
                switch (input[1]){
                    case '1':
                        conditionsMet++;
                        coordinates[1] = 0;
                        break;
                    case '2':
                        conditionsMet++;
                        coordinates[1] = 1;
                        break;
                    case'3':
                        conditionsMet++;
                        coordinates[1] = 2;
                        break;
                }
                if(conditionsMet<2) System.out.println("Ogiltiga Koordinater!");
            }else System.out.println("Ange Koordinater!");
            //check if square is already played
            if(board.isSquareAvailable(coordinates[0],coordinates[1])) {
                conditionsMet++;
            }else{
                System.out.println("Välj en ledig ruta!");
            }
        }
        return coordinates;
    }

    private static void matchWithHuman(){
        int whoseTurn =  new Random().nextInt(1, 3);;
        boolean matchEnded =false;
        //Rematch loop
        while (true){
            System.out.println("Mynt kastades... " + players[whoseTurn].getName() + " börjar först!");
            board.render();
            System.out.println("Välj en ruta genom att skriva bokstaven och sifran (t.ex. b2 )");
            //Players' turn loop
            while (!matchEnded){
                int[] coords = readSquare(); //index 0 is X , index 1 is Y
                board.play(whoseTurn, coords[0], coords[1]);
                board.render();
                //check if there's a winner
                if(board.getWinner() > 0){
                    matchEnded=true;
                    break;
                }
                //Check if there's any winnable lines left
                if(board.countWinnableLines() == 0){
                    matchEnded=true;
                    break;
                }
                //Flip whoseTurn between 1 and 2
                if(whoseTurn==1)
                    whoseTurn = 2;
                else
                    whoseTurn =1;
                System.out.println(players[whoseTurn].getName() + "s tur. Vilken ruta?");
            }

            System.out.println("Spela en gång till?");
            System.out.println("[1] Ja");
            System.out.println("[2] Tillbake till huvudmenyn");
            if(readChoice(2) == 2) {
                System.out.println("___________________________________");
                break;
            }
            System.out.println("Poängställning:");
            System.out.println(players[0].getName() +" : " + players[0].getScore());
            System.out.println(players[1].getName() +" : " + players[1].getScore());
            System.out.println("___________________________________");
        }
    }

    private static void matchWithComputer(){

    }

}
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private final static Scanner scanner =new Scanner(System.in);
    private final static Player[] players = new Player[3];
    private final static Board9 board = new Board9();

    public static void main(String[] args) {
        //Main loop
        while (true){
            System.out.println(" ");
            System.out.println("*** Tre-i-rad ***");
            System.out.println("Huvudmeny:");
            System.out.println("[1] : Två spelare");
            System.out.println("[2] : Spela mot dator");
            System.out.println("[3] : Avsluta");
            switch(readChoice(3)){
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
                    players[1] = new Player();
                    players[2] = new Player();
                    players[1].setName("Du");
                    players[2].setName("Dator");
                    matchWithComputer();
                    break;
                case 3:
                    return;
            }
        }
    }

    //The menu should always start with 1
    private static int readChoice(int numberOfChoices){
        int choice;
        while(true){
            if(scanner.hasNextInt()){
                choice = scanner.nextInt();
                if (choice > 0 && choice <= numberOfChoices){
                    scanner.nextLine();
                    return choice;
                }
                else{
                    scanner.nextLine();
                }
            }
            else{
                scanner.nextLine();
            }
        }
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
                if(conditionsMet == 2) {
                    //check if the square is already played
                    if (board.isSquareAvailable(coordinates[0], coordinates[1])) {
                        conditionsMet++;
                    } else {
                        System.out.println("Välj en ledig ruta!");
                    }
                }else System.out.println("Ogiltiga Koordinater!");
            }else System.out.println("Ange Koordinater!");
        }
        return coordinates;
    }

    private static void matchWithHuman(){
        int whoseTurn;
        int theWinner;
        //Rematch loop
        while (true){
            board.resetBoard();
            whoseTurn =  new Random().nextInt(1, 3);
            System.out.println("Mynt kastades... " + players[whoseTurn].getName() + " börjar fö1rst!");
            board.render();
            System.out.println("Välj en ruta genom att skriva bokstaven och sifran (t.ex. b2 )");
            //Turn loop
            while (true){
                int[] coords = readSquare(); //index 0 is X , index 1 is Y
                board.play(whoseTurn, coords[0], coords[1]);
                board.render();
                //check if there's a winner
                theWinner = board.getWinner();
                if(theWinner > 0){
                    players[theWinner].setScore(players[theWinner].getScore() + 1);
                    System.out.println("\\****>>  " + players[theWinner].getName() + " vann!  <<****/");
                    break;
                }
                //Check if there's any playable squares left
                if(board.getTotalPlays() == 9){
                    System.out.println("*****<<  Oavgjort!  >>*****");
                    break;
                }
                //Flip whoseTurn between 1 and 2
                if(whoseTurn==1)
                    whoseTurn = 2;
                else
                    whoseTurn =1;
                System.out.println(players[whoseTurn].getName() + "s tur. Vilken ruta?");
            }
            //Show score
            System.out.println("Poängställning:");
            System.out.println(players[1].getName() +" : " + players[1].getScore());
            System.out.println(players[2].getName() +" : " + players[2].getScore());
            System.out.println("___________________________________");
            //Ask for rematch
            System.out.println("Spela en gång till?");
            System.out.println("[1] Ja");
            System.out.println("[2] Tillbake till huvudmenyn");
            if(readChoice(2) == 2) {
                System.out.println("___________________________________");
                break;
            }
        }
    }

    private static void matchWithComputer(){
        Random randomizer = new Random();
        int whoseTurn;
        int theWinner;
        int[] coords;
        HashMap<Integer,Integer> xOfAvailableSquares;
        HashMap<Integer,Integer> yOfAvailableSquares;
        boolean humansFirstMove;
        //Rematch loop
        while (true){
            board.resetBoard();
            whoseTurn =  new Random().nextInt(1, 3);
            System.out.println("Mynt kastades... " + players[whoseTurn].getName() + " börjar fö1rst!");
            humansFirstMove = true;
            //Turn loop
            while (true){
                //Human plays
                if(whoseTurn == 1){
                    if(humansFirstMove){
                        if(board.getTotalPlays()==0)
                            board.render();
                        System.out.println("Välj en ruta genom att skriva bokstaven och sifran (t.ex. b2 )");
                        humansFirstMove = false;
                    }
                    else
                        System.out.println("Din tur. Vilken ruta?");
                    coords= readSquare();
                    board.play(1, coords[0], coords[1]);
                    board.render();
                }
                //CPU plays
                if(whoseTurn == 2){
                    System.out.println("Datorns tur");
                    //Get available squares
                    xOfAvailableSquares = new HashMap<>();
                    yOfAvailableSquares = new HashMap<>();
                    int counterOfAvailableSquares = 0;
                    for(int x = 0; x < 3; x++){
                        for(int y = 0; y < 3; y++){
                            if(board.isSquareAvailable(x,y)){
                                xOfAvailableSquares.put(counterOfAvailableSquares,x);
                                yOfAvailableSquares.put(counterOfAvailableSquares,y);
                                counterOfAvailableSquares++;
                            }
                        }
                    }
                    //Choose a random square from the available ones
                    int r = randomizer.nextInt(counterOfAvailableSquares);
                    board.play(2, xOfAvailableSquares.get(r), yOfAvailableSquares.get(r));
                    board.render();
                }
                //Check if there's a winner
                theWinner = board.getWinner();
                if(theWinner > 0) {
                    players[theWinner].setScore(players[theWinner].getScore() + 1);
                    System.out.println("\\****>>  " + players[theWinner].getName() + " vann!  <<****/");
                    break;
                }
                //Check if there's any playable squares left
                if(board.getTotalPlays() == 9){
                    System.out.println("*****<<  Oavgjort!  >>*****");
                    break;
                }
                //Flip whoseTurn between 1 and 2
                if(whoseTurn==1)
                    whoseTurn = 2;
                else
                    whoseTurn =1;
            }
            //Show score
            System.out.println("Poängställning:");
            System.out.println(players[1].getName() +" : " + players[1].getScore());
            System.out.println(players[2].getName() +" : " + players[2].getScore());
            System.out.println("___________________________________");
            //Ask for rematch
            System.out.println("Spela en gång till?");
            System.out.println("[1] Ja");
            System.out.println("[2] Tillbake till huvudmenyn");
            if(readChoice(2) == 2) {
                System.out.println("___________________________________");
                break;
            }
        }
    }
}
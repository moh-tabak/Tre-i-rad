public class Board9 implements Board {
    private int[][] squares = new int [3][3];
    private int totalPlays = 0;
    public char getSymbol(int player){
        switch (player){
            case 1:
                return 'X';
            case 2:
                return 'O';
            default:
                return ' ';
        }
    }
    @Override
    public void render() {
        String line;
        for(int y = 0; y<3 ; y++){
            if(y==0){
                //Write column names
                System.out.println("    A   B   C " );
            }
            System.out.print(y+1 + "  ");
            for(int x = 0; x<3 ; x++){
                System.out.print(" " + getSymbol(squares[x][y]) + " ");
                if (x<2){
                    System.out.print("|");
                }
            }
            System.out.print(System.lineSeparator());
            if (y<2) {
            System.out.println("   ---+---+---");
            }
        }
        System.out.println("");
    }

    @Override
    public boolean isSquareAvailable(int x, int y) {
        if(squares[x][y] == 0) return true;
        return false;
    }

    @Override
    public void play(int player, int x, int y) {
        squares[x][y] = player;
        totalPlays++;
    }

    @Override
    public int getTotalPlays() {
        return totalPlays;
    }

    @Override
    public int getWinner() {
        int playFound;
        //check for a vertical win
        for(int x1=0; x1<3 ; x1++) {
            playFound = 0;
            for (int y1 = 0; y1 < 3; y1++) {
                if (squares[x1][y1] == 0) break;
                if (playFound == 0)
                    playFound = squares[x1][y1];
                else {
                    if (squares[x1][y1] == playFound) {
                        if (y1 == 2) {
                            return playFound;
                        }
                    }
                    else{
                        //break out if the play doesn't belong to the same player
                        break;
                    }
                }
            }
        }
        //check for a horizontal win
        for(int y2=0; y2<3 ; y2++){
            playFound = 0;
            for (int x2=0; x2<3; x2++){
                if(squares[x2][y2]==0) break;
                if (playFound == 0)
                    playFound = squares[x2][y2];
                else{
                    if(squares[x2][y2] == playFound){
                        if(x2==2){
                            return playFound;
                        }
                    }
                    else{
                        //break out if the play doesn't belong to the same player
                        break;
                    }
                }
            }
        }
        //check for a diagonal win
        if(squares[1][1] != 0){
            playFound = squares[1][1];
            if(squares[0][0] == playFound)
                if(squares[2][2] == playFound)
                    return playFound;
            if(squares[0][2] == playFound)
                if(squares[2][0] == playFound)
                    return playFound;
        }
        return 0;
    }

    @Override
    public void resetBoard() {
        for(int x = 0; x < 3 ; x++){
            for(int y = 0; y < 3; y++){
                squares[x][y]=0;
            }
        }
        totalPlays = 0;
    }
}

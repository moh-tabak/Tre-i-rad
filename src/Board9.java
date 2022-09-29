public class Board9 implements Board {
    private int[][] squares = new int [3][3];

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
            System.out.println("   –––+–––+–––");
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
    }

    @Override
    public int countWinnableLines() {
        return 8;
    }

    @Override
    public int getWinner() {
        return 0;
    }
}

public class Board9 implements Board {
    private int[][] squares = new int [3][3];

    private char getSymbol(int square){
        switch (square){
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
        for(int x = 0; x<3 ; x++){
            for(int y = 0; y<3 ; y++){
                System.out.print(" " + getSymbol(squares[x][y]) + " ");
                if (y<2){
                    System.out.print("|");
                }
            }
            System.out.print(System.lineSeparator());
            if (x<2) {
            System.out.println("–––+–––+–––");
            }
        }
    }

    @Override
    public boolean isSquareAvailable(int x, int y) {
        return false;
    }

    @Override
    public void play(int player, int x, int y) {

    }

    @Override
    public int countWinnableLines() {
        return 0;
    }

    @Override
    public int getWinner() {
        return 0;
    }
}

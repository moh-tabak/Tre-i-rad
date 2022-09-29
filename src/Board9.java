public class Board9 implements Board {
    private Byte[][] squares = new Byte [2][2];

    @Override
    public void render() {

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

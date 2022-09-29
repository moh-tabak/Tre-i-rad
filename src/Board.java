public interface Board {
    public char getSymbol(int player);
    public void render();
    public boolean isSquareAvailable(int x, int y);
    public void play(int player,int x, int y);
    public int countWinnableLines();
    public int getWinner();
}

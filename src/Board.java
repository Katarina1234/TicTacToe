import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {

    private int boardSize;
    private ArrayList<Tile> tiles;
    private Player playerOne;
    private Player playerTwo;
    private Player activePlayer;

    public Board(int size){
        this.playerOne = new Player("O");
        this.playerTwo = new Player("X");
        initBoard(size);
    }

    public void initBoard(int size){
        setActivePlayer(playerOne);

        this.boardSize = size;
        setLayout(new GridLayout(boardSize,boardSize));
        removeAll();
        tiles = new ArrayList<>();
        for (int i = 0; i<(boardSize*boardSize); i++){
            Tile tile = new Tile(this, "");
            tiles.add(tile);
            add(tile, i);
        }
        revalidate();
        repaint();
    }

    public Player getPlayerOne() { return playerOne; }

    public Player getPlayerTwo() { return playerTwo; }

    public Player getActivePlayer(){ return this.activePlayer; }

    private void setActivePlayer(Player player){ this.activePlayer = player; }

    private void switchActivePlayer(){
        if (activePlayer.equals(playerOne))
            setActivePlayer(playerTwo);
        else
            setActivePlayer(playerOne);
    }

    public void updateBoard() {
        repaint();
        switchActivePlayer();
    }

    public boolean gameOver(){
        return (isWinner(playerOne) || isWinner(playerTwo) || noEmptyCells());
    }

    private boolean isWinner(Player player){
        String letter = player.getLetter();
        for (int i = 0; i < boardSize; i++) {
            if (rowWin(i*boardSize, letter)){
                return true;
            }
            if (columnWin(i, letter)){
                return true;
            }
        }
        if (diagonalWin(letter)){
            return true;
        }
        return false;
    }

    private boolean rowWin(int firstTileOfRow, String letter){
        for (int j = 0; j<boardSize; j++){
            if (!tiles.get(firstTileOfRow+j).getLetter().equals(letter)){
                return false;
            }
        }
        return true;
    }

    private boolean columnWin(int firstTileOfColumn, String letter){
        for (int j = 0; j<boardSize; j++){
            if (!tiles.get(firstTileOfColumn+j*boardSize).getLetter().equals(letter)){
                return false;
            }
        }
        return true;
    }

    private boolean diagonalWin(String letter){
        boolean oneDiagonalWin = true;
        boolean otherDiagonalWin = true;
        for (int j = 0; j<boardSize; j++){
            if (!tiles.get(j*boardSize+j).getLetter().equals(letter)){
                oneDiagonalWin = false;
            }
            if (!tiles.get((j+1)*boardSize-(j+1)).getLetter().equals(letter)){
                otherDiagonalWin = false;
            }
        }
        return oneDiagonalWin || otherDiagonalWin;
    }

    private boolean noEmptyCells(){
        for (Tile tile: tiles){
            if (tile.getLetter().equals("")){
                return false;
            }
        }
        return true;
    }

    @Override
    public void paint (Graphics g){
        super.paint(g);
        if (gameOver()) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial Bold", 20, 50));
            FontMetrics fm = g.getFontMetrics();

            centreString("GAME OVER!", g, fm, getHeight() / 3);
            centreString("Restart to replay", g, fm, getHeight() / 3 + 100);
            if (isWinner(playerOne)) {
                centreString(playerOne.getLetter()+" WINS!", g, fm, getHeight() / 3 + 50);
            } else if (isWinner(playerTwo)) {
                centreString(playerTwo.getLetter()+" WINS!", g, fm, getHeight() / 3 + 50);
            } else {
                centreString("TIE!", g, fm, getHeight() / 3 + 50);
            }
        }
    }

    private void centreString(String str, Graphics g, FontMetrics fm, int yPosition) {
        int width = fm.stringWidth(str);
        g.drawString(str, (getWidth() - width) / 2, yPosition);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tile  extends JPanel{

    private String letter;
    private Board board;

    public Tile(Board board, String letter) {
        this.letter = letter;
        this.board = board;
        setPreferredSize(new Dimension(135, 125));
        addListener();
    }

    public void setLetter(String letter){
        this.letter = letter;
    }

    public String getLetter() {
        return letter;
    }

    private void updateEmptyTileAndBoard(){
        if (this.letter.equals("") && !board.gameOver()) {
            setLetter(board.getActivePlayer().getLetter());
            board.updateBoard();
        }
    }

    private void addListener(){
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateEmptyTileAndBoard();
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    public void paint(Graphics g) {
        int height = getHeight();
        int width = getWidth();
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width, height);
        g.setFont(new Font(g.getFont().getName(), Font.PLAIN, height));

        if (this.letter.equals(board.getPlayerOne().getLetter())){
            g.setColor(Color.BLUE);
        } else if (this.letter.equals(board.getPlayerTwo().getLetter())){
            g.setColor(Color.RED);
        }
        g.drawString(letter, width / 8, height - (height / 6));
    }
}

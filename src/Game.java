import javax.swing.*;
import java.awt.event.*;

public class Game extends JFrame{

    private Board board;

    public Game(){
        super("Tic-Tac-Toe");
        initGame();
    }

    private void initGame(){
        board = new Board(3);
        this.add(board);
        makeMenu();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    private void makeMenu(){
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu restartMenu = new JMenu("Restart");
        menuBar.add(restartMenu);

        JMenuItem menuItemForThree = new JMenuItem("3x3");
        menuItemForThree.setActionCommand("3x3");
        restartMenu.add(menuItemForThree);
        addListener(menuItemForThree);

        JMenuItem menuItemForFour= new JMenuItem("4x4");
        menuItemForFour.setActionCommand("4x4");
        restartMenu.add(menuItemForFour);
        addListener(menuItemForFour);
    }

    private void addListener(JMenuItem menuItem){
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("4x4")){
                    board.initBoard(4);
                } else {
                    board.initBoard(3);
                }
            }
        });
    }

    public static void main(String[] args) {
        new Game();
    }

}
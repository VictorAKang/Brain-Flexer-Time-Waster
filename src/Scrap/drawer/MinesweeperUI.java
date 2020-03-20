package ui.drawer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinesweeperUI {
    private static final int MARGIN = 100;
    private static final int CELL_SIDE = 20;
    private static final int WIDTH  = 2 * MARGIN + CELL_SIDE * 30;
    private static final int HEIGHT = 2 * MARGIN + CELL_SIDE * 16;

    public MinesweeperUI() {
        JFrame frame = new JFrame();
        //JFrame frame = MainMenuUI.frame;
        frame.setSize(WIDTH,HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        JButton reference;
        boolean gameOver = false;

        for (int i  = 0; i < 16; i++) {
            for (int j = 0; j < 30; j++) {
                reference = new JButton();
                reference.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("a");
                    }
                });
                reference.setBounds(MARGIN + (j * CELL_SIDE),MARGIN + (i * CELL_SIDE),CELL_SIDE,CELL_SIDE);
                frame.add(reference);
            }
        }
        while (!gameOver) {}
        System.out.println("mine");
    }
}

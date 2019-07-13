import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class GUI {
    private JFrame menu;
    private JButton playButton;

    private JFrame game;

    public GUI() {
        makeMenu();
    }

    private void makeMenu() {
        menu = new JFrame("Chess Game");
        menu.setSize(250, 250);
        menu.setLocation(300,200);
        playButton = new JButton("Play");
        menu.getContentPane().add(BorderLayout.SOUTH, playButton);
        playButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button was clicked\n");
                    menu.setVisible(false);
                    game.setVisible(true);
            }
        });

        menu.setVisible(true);
    }

}

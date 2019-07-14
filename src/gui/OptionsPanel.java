package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsPanel extends JPanel {
    public OptionsPanel() {
        Dimension size = getPreferredSize();
        size.width = 200;
        setPreferredSize(size);

        setBorder(BorderFactory.createTitledBorder("Options"));

        JLabel whiteLabel = new JLabel("White PlayerColor:");
        JLabel blackLabel = new JLabel("Black PlayerColor:");
        JLabel whitePlayerLabel = new JLabel("player1");
        JLabel blackPlayerLabel = new JLabel("player2");

        JButton playButton = new JButton("Play");

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        //First column
        gc.weightx = 0.5;
        gc.weighty = 0.5;

        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 0;
        add(whiteLabel, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        add(blackLabel, gc);

        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 0;
        add(whitePlayerLabel, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        add(blackPlayerLabel, gc);

        gc.weighty = 10;

        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.gridx = 1;
        gc.gridy = 2;
        add(playButton, gc);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("play");
            }
        });

    }


}

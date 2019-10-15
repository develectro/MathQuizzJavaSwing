package com.electrodev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Settings extends JDialog implements ActionListener {

    private JPanel labelPanel, radioButtonsLabel;
    private JLabel selectDifficulty;
    private JRadioButton RB1, RB2, RB3;
    private JButton button;
    private ButtonGroup group = new ButtonGroup();
    public static int selectedRadioButton = 1;

    Settings() {
        selectDifficulty = new JLabel("Select Difficulty: ");

        labelPanel = new JPanel();
        radioButtonsLabel = new JPanel();
        
        RB1 = new JRadioButton("easy");
        RB1.setSelected(true);
        RB2 = new JRadioButton("hard");
        RB3 = new JRadioButton("insane");

        button = new JButton("OK");
        button.setBounds(190, 150, 100, 20);

        labelPanel.setBounds(10, 20, 150, 40);
        radioButtonsLabel.setBounds(20, 80, 200, 50);

        labelPanel.add(selectDifficulty);
        radioButtonsLabel.add(RB1);
        radioButtonsLabel.add(RB2);
        radioButtonsLabel.add(RB3);

        group.add(RB1);
        group.add(RB2);
        group.add(RB3);

        setSize(300, 220);
        setTitle("Preferences");
        setLayout(null);
        setFocusable(true);
        setResizable(false);
        setDefaultLookAndFeelDecorated(false);
        //setOpacity(0.9f);
        add(labelPanel);
        add(radioButtonsLabel);
        add(button);

        button.addActionListener(this);
        RB1.addActionListener(this);
        RB2.addActionListener(this);
        RB3.addActionListener(this);

        //radioButtonsLabel.setVisible(true); No need for this  just fix the height of labelPanel or use layout manager
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(d.width / 2 - this.getSize().width / 2, d.height / 2 - this.getSize().height / 2);
        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent v) {

        if (v.getSource().equals(RB1)) {
            selectedRadioButton = 1;
            Gui.level.setText("Level: easy");
        } else if (v.getSource().equals(RB2)) {
            selectedRadioButton = 2;
            Gui.level.setText("Level: hard");

        } else if (v.getSource().equals(RB3)) {
            selectedRadioButton = 3;
            Gui.level.setText("Level: insane");

        }
        if (v.getSource().equals(button)) {
            dispose();
        }

    }
}
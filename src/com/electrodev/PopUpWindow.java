package com.electrodev;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


// this class invoked inside noFun() method.

public class PopUpWindow extends JDialog{

    private JButton popB1, popB2;
    private JPanel textPanel, imagePanel;
    private JLabel l;
    BufferedImage img1;
    public static boolean disposeOlderFrame= false;

    PopUpWindow() {

        imagePanel = new JPanel();
        imagePanel.setBounds(20, 10,250,100);

        try {
            //img1 = ImageIO.read(new File("")); //ImageIO constructor is private.
            img1 = ImageIO.read(new File("/home/inspiron/IdeaProjects/res/GameOverLogoPNG.png"));

            JLabel label = new JLabel(new ImageIcon(img1));
            imagePanel.add(label); // one way to add image to JPanel is to use JLabel as a jug.

        }catch (IOException ee){}

        l = new JLabel("You lost, do you want to play again?");
        l.setFont(new Font("Comic", Font.BOLD, 10));
        textPanel = new JPanel();
        textPanel.setBounds(10, 120,250,30 );
        textPanel.add(l);

        popB1 = new JButton("Yeah!"){
            //ToolTip here
        };
        popB2 = new JButton("No :("){
            //ToolTip here
        };
        popB1.setBounds(30 ,150, 100, 20);
        popB2.setBounds(170 ,150, 100, 20);

        try{
          popB1.setBackground(Color.decode("#5eff3e"));
          popB2.setBackground(Color.decode("#f62242"));

        } catch(NumberFormatException n_exception){} //try_catch block is not critical here but would be if user may pass some value

        setTitle("You lose :( ");
        setSize(300,220);
        setLayout(null);
        add(popB1);
        add(popB2);
        add(textPanel);
        add(imagePanel);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();  // stackoverflow: https://bit.ly/2finZXP
        this.setLocation(d.width / 2 - this.getSize().width / 2, d.height / 2 - this.getSize().height / 2);
        setAlwaysOnTop(true);
        setResizable(false);
        setVisible(true);

        popB1.addActionListener(popWindowButtonsActions);
        popB2.addActionListener(popWindowButtonsActions);
        addWindowListener(w);

    }

     ActionListener popWindowButtonsActions = new ActionListener() {

       // Gui g = new Gui(); //bug if constructor used to create new Gui frame.

         @Override
         public void actionPerformed(ActionEvent actionEvent) {
             if(actionEvent.getSource().equals(popB1)){

                 dispose();}
                 //Gui g = new Gui(); //bug if constructor used to create new Gui frame.
                 //g.newGameFunc();}
                 //this causes runtime error need to be fixed. UPDATE: error fixed using static methods and classes.

                 if(actionEvent.getSource().equals(popB2)){
                 System.exit(0);
                 }

         }
     };

    WindowListener w = new WindowListener() {
        @Override
        public void windowOpened(WindowEvent windowEvent){}

        @Override
        public void windowClosing(WindowEvent windowEvent){}

        @Override
        public void windowClosed(WindowEvent windowEvent) {

            disposeOlderFrame = true;
            Gui.newGameFunc();
            // classes, Interfaces and methods needed to be static:
            //JLabels l1, l2, l3;
            //Timer interface;
            //barFunc(), noFun(), and newGameFunc();
        }

        @Override
        public void windowIconified(WindowEvent windowEvent){}

        @Override
        public void windowDeiconified(WindowEvent windowEvent){}

        @Override
        public void windowActivated(WindowEvent windowEvent){}

        @Override
        public void windowDeactivated(WindowEvent windowEvent){}
    };
}

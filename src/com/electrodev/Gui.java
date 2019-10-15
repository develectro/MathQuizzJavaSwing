package com.electrodev;


//a note moved from here.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Gui extends JFrame {

    public void testPopUpWindowStatus() {

        //if PopUpWindow closed, older frame will be disposed.

        if (PopUpWindow.disposeOlderFrame) {
            dispose();
            PopUpWindow.disposeOlderFrame= false; //return to the default status.
        }
    }

    // All frame settings are inside the constructor
    private JButton b1, b2;//for gaming by Yes or No!
    private static JLabel l1, l3, l2, plusSign; //which shows the questions
    private JMenuBar menuBar;//to add options for the game GUI
    private JMenu it1, it2, it3;// including Game, Settings and About!
    private JMenuItem newGame, score, reset, preferences, exit, about;// items for game, settings
    private ImageIcon happy, sad; // don't miss this
    private static  JProgressBar bar;
    private JPanel p1,p2,p3, plusSignPanel, levelPanel; //p1 for l1 and so on.
    private JDialog popDialog;
    protected static  JLabel level;

    private static Boolean checkAns = false;  // this is used to check weather answer correct or not.
    private static boolean ON_OFF = false;   // this is a container for checkAns value.
    private static boolean myNewGame = false; // to prevent game from starting by just clicking Yes or No
    private static  int barIndicator = 0;



    private static boolean newFun() {
        new GameLogic();
        /*Issue: x,y,z values never update*/
        //fixed: using GameLogic() constructor instead of new function in order to update values.
        l3.setFont(new Font("Comic", Font.BOLD, 24));
        l3.setForeground(Color.MAGENTA);
        int x = GameLogic.x;
        int y = GameLogic.y;
        int z = GameLogic.finalAnsView;
        l1.setText(Integer.toString(x));
        l2.setText(Integer.toString(y));
        l3.setText(Integer.toString(z));
        if ((x + y) == z) {
            checkAns = true;
        } else
            checkAns = false;
        return checkAns;

    }

    protected static void noFun() {

        l1.setText("0");
        l2.setText("0");
        l3.setText("0");

        new PopUpWindow();
    }

    public static void creatGui(){

    }

    public Gui() {

        setSize(500, 500);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("MathQuizz");

        b1 = new JButton("Yes");
        b2 = new JButton("No");

        l1 = new JLabel("Ready!");
        l2 = new JLabel("Be");
        l3 = new JLabel();// this label will be used for the quiz section
        plusSign = new JLabel("+");
        level = new JLabel("Level: easy");

        menuBar = new JMenuBar();
        it1 = new JMenu("Game"); //Game
        it2 = new JMenu("Settings"); //settings
        //it3 = new JMenu("About"); //about

        newGame = new JMenuItem("New Game"); //game
        score = new JMenuItem("Score");//game scores must be saved automatically
        reset = new JMenuItem("Reset Score");//game
        preferences = new JMenuItem("Preferences");// settings
        exit = new JMenuItem("Exit");//settings
        about = new JMenuItem("About");

        bar = new JProgressBar();
        bar.setBounds(100, 200, 300, 25);
        bar.setValue(0);
        bar.setStringPainted(true);

        it1.add(newGame);//Menu Items
        it1.add(score);
        it1.add(reset);
        it2.add(preferences);
        it2.add(about);
        it2.add(exit);

        menuBar.add(it1); //Bar Items
        menuBar.add(it2);
        //menuBar.add(it3);

        b1.setBounds(125, 300, 100, 35); //x,y, width, hieght
        b2.setBounds(275, 300, 100, 35);
        //l1.setBounds(300, 100, 100, 20);
        //l2.setBounds(100, 100, 150, 20);
        // l3.setBounds(150, 150, 400, 20);

        //plusSign.setBounds(245, 100, 50, 20);
        plusSign.setFont(new Font("Serif", Font.BOLD, 35));
        plusSign.setForeground(Color.GREEN);

        l1.setFont(new Font("Comic", Font.BOLD, 24));
        l1.setForeground(Color.PINK);
        l2.setFont(new Font("Comic", Font.BOLD, 24));
        l2.setForeground(Color.BLUE);
        l3.setText("Ready? select New Game ^_^");
        l3.setFont(new Font("Comic", Font.BOLD, 15));
        l3.setForeground(Color.ORANGE);

        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        plusSignPanel = new JPanel();
        levelPanel = new JPanel();
        p1.setBounds(300, 100, 100, 40);
        p2.setBounds(100, 100, 100, 40);
        p3.setBounds(100, 150, 300, 40);
        plusSignPanel.setBounds(220, 100, 60, 40);
        levelPanel.setBounds(100, 30, 100, 40);

        p1.add(l1);
        p2.add(l2);
        p3.add(l3);
        plusSignPanel.add(plusSign);
        levelPanel.add(level);
        p1.setOpaque(false);
        p2.setOpaque(false);
        p3.setOpaque(false);
        levelPanel.setOpaque(false);
        plusSignPanel.setOpaque(false);

        bar.setStringPainted(true);
        bar.setValue(0);
        bar.setMinimum(0);
        bar.setMaximum(100);
        //bar.setBackground(Color.blue);

        add(p1);//instead of adding l1 directly to the frame.
        add(p2);//instead of adding l1 directly to the frame.
        add(p3);//instead of adding l1 directly to the frame.
        add(plusSignPanel);//instead of adding l1 directly to the frame.
        add(levelPanel);

        add(b1); //Add components to the main frame
        add(b2);
        setJMenuBar(menuBar); // add method dosen't work always with JMenuBar
        add(bar);
        newGame.addActionListener(new_game);
        b1.addActionListener(firstButtonListener);
        b2.addActionListener(secondButtonListener);
        exit.addActionListener(settingsListener);
        about.addActionListener(settingsListener);
        preferences.addActionListener(settingsListener);
        timer.setInitialDelay(0);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(d.width / 2 - this.getSize().width / 2, d.height / 2 - this.getSize().height / 2);

        setFocusable(true);
        setVisible(true); // this must be in the bottom, otherwise you will propably get empty frame.
        getContentPane().setBackground(Color.WHITE);

    }
    static void barFunc() {

        switch (Settings.selectedRadioButton) {

            case 1: //Easy
                bar.setValue(barIndicator + 2);
                barIndicator += 2;             //INCREASE HERE TO MAKE BAR FILL FASTER. OR JUST REDUCE Timer's DELAY.
                if (barIndicator == 100) {     //if you want tou used odd number change the condition from 100 to suitable value.
                    bar.setValue(0);
                    timer.stop();
                }break;


            case 2: //Hard
                bar.setValue(barIndicator + 2);
                barIndicator += 5;             //INCREASE HERE TO MAKE BAR FILL FASTER. OR JUST REDUCE Timer's DELAY.
                if (barIndicator == 100) {     //if you want tou used odd number change the condition from 100 to suitable value.
                    bar.setValue(0);
                    timer.stop();
                }break;

            case 3: //Insane
                bar.setValue(barIndicator + 2);
                barIndicator += 10;             //INCREASE HERE TO MAKE BAR FILL FASTER. OR JUST REDUCE Timer's DELAY.
                if (barIndicator == 100) {     //if you want tou used odd number change the condition from 100 to suitable value.
                    bar.setValue(0);
                    timer.stop();
                }break;

            default:
                break;
        }

        //System.out.println(timer.isRunning());
    }

    protected static void newGameFunc(){

        myNewGame = true;
        ON_OFF = newFun();
        barIndicator = 0;
        bar.setValue(0);
        timer.restart();// here is where timer start timing.

    }


    ActionListener firstButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (myNewGame&&(e.getSource().equals(b1) && ON_OFF)) {
                ON_OFF = newFun(); //cal new numbers (Quizz)
                barIndicator = 0;
                bar.setValue(0);
                timer.restart();// here is where timer start timing.



            } else if (myNewGame&&(e.getSource().equals(b1) && !ON_OFF )){
               myNewGame = false;
                noFun();// rest Labels.
                timer.stop(); //stop the timer
                bar.setValue(0); //rest the JProgressBar
                barIndicator =0; //reset JProgressBar indicator.

        }}
    };

    ActionListener secondButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (myNewGame&&(e.getSource().equals(b2) && !ON_OFF)) {//remember this is the reverse of button1's
                ON_OFF = newFun();
                barIndicator = 0;
                bar.setValue(0);
                timer.restart();// here is where timer start timing.

            } else if (myNewGame&&(e.getSource().equals(b2) && ON_OFF)) {
                myNewGame = false;
                noFun();
                bar.setValue(0);
                barIndicator = 0;
                timer.stop();

            }
        }

    };

        ActionListener new_game = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(newGame)) {
                    newGameFunc();
                }
            }
        };
        ActionListener settingsListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(exit)) {
                    try {
                        Thread.sleep(300);
                        System.exit(0);
                    } catch (InterruptedException exp) {
                    }

                }
                if (e.getSource().equals(about)) {


                    JOptionPane.showMessageDialog(null, " \n Developed by: github.com/develectro \n \n Special thanks to: github.com/hozfia \n");

                }

                if(e.getSource().equals(preferences)){
                    new Settings();
                }
            }
        };


       static Timer timer = new Timer(100, new ActionListener() {

            //use integer to increase it each timer count and when it reach 4 which equals 4 seconds then timer is done!
            @Override
            public void actionPerformed(ActionEvent timerEvent) {
                /// this code needs some fixes.

                barFunc(); // this function responsible of filling the JProgressBar.
                if (barIndicator == 100) { // if bar fulfilled means timeout.
                    timer.stop();
                    barIndicator = 0; // to reset the bar
                    noFun(); // to rest the labels

                }

            }
        });


}


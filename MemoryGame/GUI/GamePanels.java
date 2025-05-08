package MemoryGame.GUI;

import javax.swing.*;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class GamePanels {
    private JFrame window;
    private MemoryGameTiles[] tilesArr;       //Initialize the array that will contain tiles declared from MemoryGameTiles class  
    private TextField tf;
    private JLabel timerLabel;
    private Timer timer;
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel midPanel;
    private JPanel theFirstPanel;
    private JPanel theSecondPanel;
    private JPanel theThirdPanel;
    private JLabel questionLabel;
    private JLabel roundsLabel;
    private Random random;
    
    /**
     * Constructor for objects of class FinalMemoryGame
     */
    public GamePanels() {
        window = new JFrame("Memory Game");      //Create a JFrame named window
        tf = new TextField();                   //Create a TextField that will output the result of each round
        timerLabel = new JLabel();                 //Create a timerLabel for the countdown
        timer = new Timer();                      //Create a timer for the countdown
        mainPanel = new JPanel();                  //Create a mainPanel that will contain other panels
        topPanel = new JPanel();                 //Create a topPanel that will contain roundsLabel, questionLabel, and the timer
        midPanel = new JPanel();                 //Create a midPanel that will contain textField
        theFirstPanel = new JPanel();              //Create theFirstPanel that will contain the first round tiles
        theSecondPanel = new JPanel();             //Create theSecondPanel that will contain the second round tiles
        theThirdPanel = new JPanel();              //Create theThirdPanel that will contain the third round tiles
        questionLabel = new JLabel();              //Create a questionLabel that will output instruction first and question after timer canceled
        roundsLabel = new JLabel();                //Create a roundsLabel that will says rounds number each round
        random = new Random();                   //For later use to randomize the location of tiles

        tilesArr = new MemoryGameTiles[36];                   //Set the length of the tilesArr
        
        window.setPreferredSize(new Dimension(900,500));    //Set the size of the window
        window.setResizable(false);                         //Set the window not to be resizable
        mainPanel.setLayout(new BorderLayout());            //Set the mainPanel to BorderLayout

        //Set the panels that have tiles to GridLayout so there are 3 rows, and 4 columns of tiles show up on screen 
        theFirstPanel.setLayout(new GridLayout(3,4,10,10));
        theSecondPanel.setLayout(new GridLayout(3,4,10,10));
        theThirdPanel.setLayout(new GridLayout(3,4,10,10));
        
        //Add the topPanel and midPanel to the top part and center of the mainPanel respectively 
        mainPanel.add(topPanel, BorderLayout.NORTH);                    
        mainPanel.add(midPanel, BorderLayout.CENTER);
        
        //Finish setting up the windown
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(mainPanel);
        window.pack();
        window.setVisible(true);
        window.setLocationRelativeTo(null);                 //Set the window to the center of the screen
    }//End of constructor

    /**
     * Call all the methods needed for the first round
     * @param mainPanel                             The panel theFirstPanel will be added to.
     * @param theFirstPanel, topPanel, midPanel     The parameters for the methods
     */
    public void firstRound(){ //JPanel mainPanel, JPanel theFirstPanel, JPanel topPanel, JPanel midPanel
        mainPanel.add(theFirstPanel, BorderLayout.SOUTH);       //Add the FirstPanel to the bottom part of the mainPanel
        
        setRounds(topPanel, "First Round   ");                 
        
        new CustomTiles(1, theFirstPanel, tilesArr, random);                      
        
        setInstruction(topPanel);
        
        // new Countdown(topcountdownPanel, midPanel, "Where is the blue tile that says 'blue'?   ", 0, 12, 1);     //Call the Countdown constructor
         
        setTimer("Where is the blue tile that says 'blue'?", 0, 12, 1);

    }

    /**
     * Set timer, question for each round, and change current round's tiles to white after time runs out   
     * @param topPanel   The panel timerLabel will be added to.
     * @param midPanel   The panel tf(textField) will be added to.
     */
    public void setTimer(String currQuestion, int startIndex, int endIndex, int roundNum){
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 10;

            public void run() {
                timerLabel.setText("  Time left: " + i);
                timerLabel.setFont(new Font("Verdana", Font.BOLD, 20));
                
                i--;

                if (i < 0) {
                    timer.cancel();
                    timerLabel.setText("Time Over");
                    questionLabel.setText(currQuestion);    //Change the instruction in questionLabel to third round's question
                    midPanel.add(tf);
                    tf.setFont(new Font("Verdana", Font.BOLD,35));
                    tf.setPreferredSize(new Dimension(195, 70));
                    
                    //Set the third round's tiles to white and no texts
                    for(int i = startIndex; i < endIndex; i++){
                        tilesArr[i].setBackground(Color.WHITE);
                        tilesArr[i].setText("");
                    }
                    
                    addRoundListener(roundNum);
                }
            }
        }, 0, 1000);
        
        topPanel.add(timerLabel);
    }

    public void addRoundListener(int roundNum) {
        if (roundNum == 1) {
            addListenerFirstRound();
        } else if (roundNum == 2) {
            addListenerSecondRound();
        } else {
            addListenerThirdRound();
        }
    }

    /**
     * Add actionListener to tiles from MemoryGameTiles class for the third round
     * buttonPressedThird() method will be called when click on one of the tiles
     */
    public void addListenerThirdRound(){
        for (int i = 24; i < 36; i++) {
            final int index = i;
            tilesArr[i].addActionListener(e -> buttonPressed(index, 24, 36, -1));
        }
    }//addListenerThirdRound() ends

    /**
     * Add actionListener to tiles from MemoryGameTiles class for the second round
     * buttonPressedSecond() method will be called when click on one of the tiles
     */
    public void addListenerSecondRound(){
        for (int i = 12; i < 24; i++) {
            final int index = i;
            tilesArr[i].addActionListener(e -> buttonPressed(index, 12, 24, 3));
        }
    }//addListenerSecondRound() ends
    
     /**
     * Add actionListener to tiles from MemoryGameTiles class for the first round
     * buttonPressedFirst() method will be called when click on one of the tiles
     */
    public void addListenerFirstRound(){
        for (int i = 0; i < 12; i++) {
            final int index = i;
            tilesArr[i].addActionListener(e -> buttonPressed(index, 0, 12, 2));
        }
    }//addListenerFirstRound() ends
    
     /**
     * Create a method that outputs the answer to the textField for the third round.
     * And enter next round when click on the right tile.
     * The method will be called by the actionListener.
     * The TextField will output 'Nice!' or 'Wrong:(' based on the each tile's buttonValue from MemoryGameTiles.
     * If the buttonValue is 1, 'Nice!' will be output and enter next round, otherwise, 'Wrong:(' will be output.
     * User cannot click on other tiles once clicked on one already to ensure there's no cheating.
     * 
     * @param i   int value to represent index number in tilesArr
     * @param startIndex
     * @param endIndex
     * @param nextRound
     */
    public void buttonPressed(int i, int startIndex, int endIndex, int nextRound){
        tilesArr[i].setSelected();
        
        if(tilesArr[i].isSelected){
            for (int x = startIndex; x < endIndex; x++) {
                //Set other tiles to not enabled so the user cannot choose again when they select the wrong answer
                tilesArr[x].setEnabled(false);
            }
        }
        if(tilesArr[i].buttonValue == 1) {
            if (nextRound == 2) {
                tf.setText(" Nice!");
                secondRound(mainPanel, theSecondPanel, topPanel, midPanel, theFirstPanel);           //Call for the second round
            } else if (nextRound == 3) {
                tf.setText(" Nice!");
                thirdRound(mainPanel, theThirdPanel, topPanel, midPanel, theSecondPanel);             //Call for the third round
            } else {
                tf.setText("Congrats:)");
            }
        } else {
            tf.setText("Wrong:(");
        }
    }//buttonPressed() ends
    
    /**
     * Call all the methods needed for the second round
     * @param mainPanel                             The panel theSecondPanel will be added to.
     * @param theSecondPanel, topPanel, midPanel, theFirstPanel     The parameters for the methods
     */
    public void secondRound(JPanel mainPanel, JPanel theSecondPanel, JPanel topPanel, JPanel midPanel, JPanel theFirstPanel){
        theFirstPanel.setVisible(false);            //Set theFirstPanel invisible
    
        timer = new Timer();           //Restart the timer
        
        setRounds(topPanel, "Second Round   ");
        
        new CustomTiles(2, theSecondPanel, tilesArr, random);        //Add second round tiles to theSecondPanel
        
        mainPanel.add(theSecondPanel, BorderLayout.SOUTH);      //Add theSecondPanel to the bottom part of mainPanel 
        
        setInstruction(topPanel);                   //Change the first question back to instruction
        
        setTimer("Where is the red tile that says 'hi'?  ", 12, 24, 2);
    }

    /**
     * Call all the methods needed for the third round
     * @param mainPanel                             The panel theThirdPanel will be added to.
     * @param theThirdPanel, topPanel, midPanel, theSecondPanel     The parameters for the methods
     */
    public void thirdRound(JPanel mainPanel, JPanel theThirdPanel, JPanel topPanel, JPanel midPanel, JPanel theSecondPanel){
        theSecondPanel.setVisible(false);           //Set theSecondPanel invisible
        
        timer=new Timer();                          //Restart the timer

        setRounds(topPanel, "Third Round   ");
        
        new CustomTiles(3, theThirdPanel, tilesArr, random);          //Add third round tiles to theThirdPanel
        
        mainPanel.add(theThirdPanel, BorderLayout.SOUTH);           //Add theThirdPanel to the bottom part of mainPanel 
        
        setInstruction(topPanel);                   //Change the second question back to instruction
        
        // new Countdown(topPanel,midPanel, "Where is the green tile that says 'yellow'?  ", 24, 36, 3);           //Call the Countdown constructor
        setTimer("Where is the green tile that says 'yellow'?  ", 24, 36, 3);
    }

    /**
     * Add instruction to the questionLabel, and set font of the text
     * @param topPanel   The panel questionLabel will be added to.
     */
    public void setInstruction(JPanel topPanel){
        questionLabel.setText("You will have 10 seconds to look at the tiles.  ");
        questionLabel.setFont(new Font("Verdana", Font.PLAIN,20));
        topPanel.add(questionLabel);
    }
    
    /**
     * Set rounds number, and font of it
     * @param topPanel   The panel roundsLabel will be added to.
     * @param rounds     The text will be added to the roundsLabel
     */
    public void setRounds(JPanel topPanel, String rounds){
        roundsLabel.setText(rounds);
        roundsLabel.setFont(new Font("Verdana", Font.BOLD,20));
        topPanel.add(roundsLabel);
    }
}

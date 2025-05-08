package MemoryGame.GUI;

import javax.swing.*;

import java.awt.*;
import java.util.Timer;
import java.util.Random;

public class GamePanels {
    JFrame window;
    
    public static MemoryGameTiles[] tilesArr;       //Initialize the array that will contain tiles declared from MemoryGameTiles class  
    
    TextField tf;
    
    JLabel timerLabel;
    Timer timer;

    JPanel mainPanel;
    
    JPanel topPanel;
    JPanel midPanel;
    JPanel theFirstPanel;
    JPanel theSecondPanel;
    JPanel theThirdPanel;
    
    JLabel questionLabel;
    JLabel roundsLabel;
    
    Random random;
    
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

        //firstRound(mainPanel, theFirstPanel, topPanel, midPanel);       //Call the FirstRound method
        
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
        
        new CustomTiles(1, theFirstPanel);                      
        
        setInstruction(topPanel);
        
        // new Countdown(topPanel, midPanel, "Where is the blue tile that says 'blue'?   ", 0, 12, 1);     //Call the Countdown constructor
        new Countdown(timerLabel, questionLabel, tf, timer, tilesArr,
        mainPanel,theFirstPanel, theSecondPanel, theThirdPanel,
        topPanel, midPanel,
        "Where is the blue tile that says 'blue'?", 0, 12, 1);

    }

    /**
     * Call all the methods needed for the second round
     * @param mainPanel                             The panel theSecondPanel will be added to.
     * @param theSecondPanel, topPanel, midPanel, theFirstPanel     The parameters for the methods
     */
    public void secondRound(JPanel mainPanel, JPanel theSecondPanel, JPanel topPanel, JPanel midPanel, JPanel theFirstPanel){
        theFirstPanel.setVisible(false);            //Set theFirstPanel invisible
        timer = new Timer();                          //Restart the timer
        
        setRounds(topPanel, "Second Round   ");
        
        new CustomTiles(2, theSecondPanel);        //Add second round tiles to theSecondPanel
        
        mainPanel.add(theSecondPanel, BorderLayout.SOUTH);      //Add theSecondPanel to the bottom part of mainPanel 
        
        setInstruction(topPanel);                   //Change the first question back to instruction
        
        new Countdown(timerLabel, questionLabel, tf, timer, tilesArr,
              mainPanel, theFirstPanel, theSecondPanel, theThirdPanel,
              topPanel, midPanel,
              "Where is the red tile that says 'hi'?  ", 12, 24, 2);

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
        
        new CustomTiles(3, theThirdPanel);          //Add third round tiles to theThirdPanel
        
        mainPanel.add(theThirdPanel, BorderLayout.SOUTH);           //Add theThirdPanel to the bottom part of mainPanel 
        
        setInstruction(topPanel);                   //Change the second question back to instruction
        
        // new Countdown(topPanel,midPanel, "Where is the green tile that says 'yellow'?  ", 24, 36, 3);           //Call the Countdown constructor
        new Countdown(timerLabel, questionLabel, tf, timer, tilesArr,
        mainPanel, theFirstPanel, theSecondPanel, theThirdPanel,
        topPanel, midPanel,
        "Where is the green tile that says 'yellow'?  ", 24, 36, 3);

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

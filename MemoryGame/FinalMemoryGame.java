package MemoryGame;
/**
 *FinalMemoryGame is a graphical game that contains three rounds in total and the difficulty increases in each round.
 *In order to enter the next round, user has to memorize the tiles correctly within 10 seconds, 
 *and click on the right tile according to the question after tiles turned to white,
 *questions might be tricky and the tiles will be randomized each time the user restart the game. 
 *By restarting the game, the user has to run the program again, and start from the first round.
 *
 * @author (Nancy Ma)
 * @version (final version)
 */
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class FinalMemoryGame
{
    JFrame window = new JFrame("Memory Game");      //Create a JFrame named window
    
    public static MemoryGameTiles[] tilesArr;       //Initialize the array that will contain tiles declared from MemoryGameTiles class  
    
    TextField tf=new TextField();                   //Create a TextField that will output the result of each round
    
    JLabel timerLabel=new JLabel();                 //Create a timerLabel for the countdown
    Timer timer = new Timer();                      //Create a timer for the countdown
    
    JPanel mainPanel=new JPanel();                  //Create a mainPanel that will contain other panels
    
    JPanel topPanel = new JPanel();                 //Create a topPanel that will contain roundsLabel, questionLabel, and the timer
    JPanel midPanel = new JPanel();                 //Create a midPanel that will contain textField
    JPanel theFirstPanel=new JPanel();              //Create theFirstPanel that will contain the first round tiles
    JPanel theSecondPanel=new JPanel();             //Create theSecondPanel that will contain the second round tiles
    JPanel theThirdPanel=new JPanel();              //Create theThirdPanel that will contain the third round tiles
    
    JLabel questionLabel=new JLabel();              //Create a questionLabel that will output instruction first and question after timer canceled
    JLabel roundsLabel=new JLabel();                //Create a roundsLabel that will says rounds number each round
    
    Random random = new Random();                   //For later use to randomize the location of tiles
    
    /**
     * Constructor for objects of class FinalMemoryGame
     */
    public FinalMemoryGame() {
        tilesArr=new MemoryGameTiles[36];                   //Set the length of the tilesArr
        
        window.setPreferredSize(new Dimension(900,500));    //Set the size of the window
        window.setResizable(false);                         //Set the window not to be resizable
        mainPanel.setLayout(new BorderLayout());            //Set the mainPanel to BorderLayout

        //Set the panels that have tiles to GridLayout so there are 3 rows, and 4 columns of tiles show up on screen 
        theFirstPanel.setLayout(new GridLayout(3,4,10,10));
        theSecondPanel.setLayout(new GridLayout(3,4,10,10));
        theThirdPanel.setLayout(new GridLayout(3,4,10,10));

        firstRound(mainPanel, theFirstPanel, topPanel, midPanel);       //Call the FirstRound method
        
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
    
    /**
     * Set timer, question for the first round, and change first round's tiles to white after time runs out 
     * @param topPanel   The panel timerLabel will be added to.
     * @param midPanel   The panel tf(textField) will be added to.
     */
    public void setFirstTimer(JPanel topPanel, JPanel midPanel){
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 10;         //The countdown will start from 10 seconds(10 miliseconds times 1000).

            public void run() {
                timerLabel.setText("  Time left: " + i);                    
                timerLabel.setFont(new Font("Verdana", Font.BOLD,20));

                i--;            //Counting down

                if (i < 0) {
                    timer.cancel();
                    timerLabel.setText("Time Over");        //Change the text after the counting down
                    questionLabel.setText("Where is the blue tile that says 'blue'?   ");        //Change the instruction in questionLabel to first round's question
                    midPanel.add(tf);                       
                    tf.setFont(new Font("Verdana", Font.BOLD,35));
                    tf.setPreferredSize(new Dimension(150, 70));
                    
                    //Set the first round's tiles to white and no texts
                    for(int i=0; i<12; i++){
                        tilesArr[i].setBackground(Color.WHITE);
                        tilesArr[i].setText("");
                    }
                    
                    addListenerFirstRound();                    //Call addListenerFirstRound() method to output the result
                }
            }
        }, 0, 1000);            //No delay and changes milisecondes to seconds
        
        topPanel.add(timerLabel);
    }//setFirstTimer ends
    
    /**
     * Set timer, question for the second round, and change second round's tiles to white after time runs out 
     * @param topPanel   The panel timerLabel will be added to.
     * @param midPanel   The panel tf(textField) will be added to.
     */
    public void setSecondTimer(JPanel topPanel, JPanel midPanel){
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 10;
            
            public void run() {
                timerLabel.setText("  Time left: " + i);
                timerLabel.setFont(new Font("Verdana", Font.BOLD,20));
            
                i--;

                if (i < 0) {
                    timer.cancel();
                    timerLabel.setText("Time Over");
                    questionLabel.setText("Where is the red tile that says 'hi'?  ");       //Change the instruction in questionLabel to second round's question
                    midPanel.add(tf);
                    
                    //Set the second round's tiles to white and no texts
                    for(int i=12; i<24; i++){
                        tilesArr[i].setBackground(Color.WHITE);
                        tilesArr[i].setText("");
                    }
                    addListenerSecondRound();                      //Call addListenerSecondRound() method to output the result
                }
            }
        }, 0, 1000);
        
        topPanel.add(timerLabel);
    }//setSecondTimer ends
    
    /**
     * Set timer, question for the third round, and change third round's tiles to white after time runs out   
     * @param topPanel   The panel timerLabel will be added to.
     * @param midPanel   The panel tf(textField) will be added to.
     */
    public void setThirdTimer(JPanel topPanel, JPanel midPanel){
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 10;

            public void run() {
                timerLabel.setText("  Time left: " + i);
                timerLabel.setFont(new Font("Verdana", Font.BOLD,20));
                
                i--;

                if (i < 0) {
                    timer.cancel();
                    timerLabel.setText("Time Over");
                    questionLabel.setText("Where is the green tile that says 'yellow'?  ");    //Change the instruction in questionLabel to third round's question
                    midPanel.add(tf);
                    tf.setPreferredSize(new Dimension(195, 70));
                    
                    //Set the third round's tiles to white and no texts
                    for(int i=24; i<36; i++){
                        tilesArr[i].setBackground(Color.WHITE);
                        tilesArr[i].setText("");
                    }
                    
                    addListenerThirdRound();                    //Call addListenerThirdRound() method to output the result
                }
            }
        }, 0, 1000);
        
        topPanel.add(timerLabel);
    }//setThirdTimer ends
    
    /**
     * Create tiles from MemoryGameTiles class for the first round
     * @param theFirstPanel   The panel MemoryGameTiles objects will be added to.
     */
    public void addFirstRoundTiles(JPanel theFirstPanel){
        //Create new MemoryGameTiles objects.
        tilesArr[0]= new MemoryGameTiles("hi",Color.RED,2);
        tilesArr[1]= new MemoryGameTiles("green",Color.GREEN,2);
        tilesArr[2]= new MemoryGameTiles("hello",Color.YELLOW,2);
        tilesArr[3]= new MemoryGameTiles("blue",Color.BLUE,1);
        
        tilesArr[4]= new MemoryGameTiles("blue",Color.YELLOW,2);
        tilesArr[5]= new MemoryGameTiles("orange",Color.BLUE,2);
        tilesArr[6]= new MemoryGameTiles("gray",Color.ORANGE,2);
        tilesArr[7]= new MemoryGameTiles("gray",Color.GRAY,2);
        
        tilesArr[8]= new MemoryGameTiles("yellow",Color.GRAY,2);
        tilesArr[9]= new MemoryGameTiles("red",Color.RED,2);
        tilesArr[10]= new MemoryGameTiles("yellow",Color.YELLOW,2);
        tilesArr[11]= new MemoryGameTiles("blue",Color.BLUE,1);
        
        //Add the MemoryGameTiles objects to the panel randomly. 
        //Ensures the first index will be random, and add other tiles that are left in the array one by one 
        int index=random.nextInt(12);
        for(int i=index;i>=0;i--){
            theFirstPanel.add(tilesArr[i]);
        }
        for(int a=index+1;a<12;a++){
            theFirstPanel.add(tilesArr[a]);
        }
    }//addFirstRoundTiles ends
   
    /**
     * Add actionListener to tiles from MemoryGameTiles class for the first round
     * buttonPressedFirst() method will be called when click on one of the tiles
     */
    public void addListenerFirstRound(){
        tilesArr[0].addActionListener(e -> buttonPressedFirst(0));
        tilesArr[1].addActionListener(e -> buttonPressedFirst(1));
        tilesArr[2].addActionListener(e -> buttonPressedFirst(2));
        tilesArr[3].addActionListener(e -> buttonPressedFirst(3));
        
        tilesArr[4].addActionListener(e -> buttonPressedFirst(4));
        tilesArr[5].addActionListener(e -> buttonPressedFirst(5));
        tilesArr[6].addActionListener(e -> buttonPressedFirst(6));
        tilesArr[7].addActionListener(e -> buttonPressedFirst(7));
        
        tilesArr[8].addActionListener(e -> buttonPressedFirst(8));
        tilesArr[9].addActionListener(e -> buttonPressedFirst(9));
        tilesArr[10].addActionListener(e -> buttonPressedFirst(10));
        tilesArr[11].addActionListener(e -> buttonPressedFirst(11));
    }//addListenerFirstRound() ends
    
    /**
     * Call all the methods needed for the first round
     * @param mainPanel                             The panel theFirstPanel will be added to.
     * @param theFirstPanel, topPanel, midPanel     The parameters for the methods
     */
    public void firstRound(JPanel mainPanel, JPanel theFirstPanel, JPanel topPanel, JPanel midPanel){
        mainPanel.add(theFirstPanel, BorderLayout.SOUTH);       //Add the FirstPanel to the bottom part of the mainPanel
        
        setRounds(topPanel, "First Round   ");                 
        
        addFirstRoundTiles(theFirstPanel);                      
        
        setInstruction(topPanel);
        
        setFirstTimer(topPanel,midPanel);
    }

    /**
     * Create tiles from MemoryGameTiles class for the second round
     * @param theSecondPanel   The panel MemoryGameTiles objects will be added to.
     */
    public void addSecondRoundTiles(JPanel theSecondPanel){
        //Create new MemoryGameTiles objects.
        tilesArr[12]= new MemoryGameTiles("hi",Color.RED,1);
        tilesArr[13]= new MemoryGameTiles("blue",Color.BLUE,2);
        tilesArr[14]= new MemoryGameTiles("green",Color.GREEN,2);
        tilesArr[15]= new MemoryGameTiles("hello",Color.YELLOW,2);
        
        tilesArr[16]= new MemoryGameTiles("hi",Color.GREEN,2);
        tilesArr[17]= new MemoryGameTiles("hello",Color.GRAY,2);
        tilesArr[18]= new MemoryGameTiles("hi",Color.ORANGE,2);
        tilesArr[19]= new MemoryGameTiles("hey",Color.BLUE,2);
        
        tilesArr[20]= new MemoryGameTiles("k",Color.GRAY,2);
        tilesArr[21]= new MemoryGameTiles("hey",Color.RED,2);
        tilesArr[22]= new MemoryGameTiles("hey",Color.YELLOW,2);
        tilesArr[23]= new MemoryGameTiles("hi",Color.BLUE,2);
        
        //Add the MemoryGameTiles objects to the panel randomly. 
        //Ensures the first index will be random, and add other tiles that are left in the array one by one 
        int index=random.nextInt(12);
        for(int i=index+12;i>=12;i--){
            theSecondPanel.add(tilesArr[i]);
        }
        for(int a=index+12+1;a<24;a++){
            theSecondPanel.add(tilesArr[a]);
        }
    }//addSecondRoundTiles ends
    
    /**
     * Add actionListener to tiles from MemoryGameTiles class for the second round
     * buttonPressedSecond() method will be called when click on one of the tiles
     */
    public void addListenerSecondRound(){
        tilesArr[12].addActionListener(e -> buttonPressedSecond(12));
        tilesArr[13].addActionListener(e -> buttonPressedSecond(13));
        tilesArr[14].addActionListener(e -> buttonPressedSecond(14));
        tilesArr[15].addActionListener(e -> buttonPressedSecond(15));
        
        tilesArr[16].addActionListener(e -> buttonPressedSecond(16));
        tilesArr[17].addActionListener(e -> buttonPressedSecond(17));
        tilesArr[18].addActionListener(e -> buttonPressedSecond(18));
        tilesArr[19].addActionListener(e -> buttonPressedSecond(19));
        
        tilesArr[20].addActionListener(e -> buttonPressedSecond(20));
        tilesArr[21].addActionListener(e -> buttonPressedSecond(21));
        tilesArr[22].addActionListener(e -> buttonPressedSecond(22));
        tilesArr[23].addActionListener(e -> buttonPressedSecond(23));
    }//addListenerSecondRound() ends
    
    /**
     * Call all the methods needed for the second round
     * @param mainPanel                             The panel theSecondPanel will be added to.
     * @param theSecondPanel, topPanel, midPanel, theFirstPanel     The parameters for the methods
     */
    public void secondRound(JPanel mainPanel, JPanel theSecondPanel, JPanel topPanel, JPanel midPanel, JPanel theFirstPanel){
        theFirstPanel.setVisible(false);            //Set theFirstPanel invisible
        timer=new Timer();                          //Restart the timer
        
        setRounds(topPanel, "Second Round   ");
        
        addSecondRoundTiles(theSecondPanel);        //Add second round tiles to theSecondPanel
        
        mainPanel.add(theSecondPanel, BorderLayout.SOUTH);      //Add theSecondPanel to the bottom part of mainPanel 
        
        setInstruction(topPanel);                   //Change the first question back to instruction
        
        setSecondTimer(topPanel,midPanel);          //Call the setSecondTimer() method
    }
    
    /**
     * Create tiles from MemoryGameTiles class for the third round
     * @param theThirdPanel   The panel MemoryGameTiles objects will be added to.
     */
    public void addThirdRoundTiles(JPanel theThirdPanel){
        //Create new MemoryGameTiles objects.
        tilesArr[24]= new MemoryGameTiles("green",Color.RED,2);
        tilesArr[25]= new MemoryGameTiles("green",Color.GREEN,2);
        tilesArr[26]= new MemoryGameTiles("yellow",Color.YELLOW,2);
        tilesArr[27]= new MemoryGameTiles("blue",Color.BLUE,2);
        
        tilesArr[28]= new MemoryGameTiles("yellow",Color.GREEN,1);
        tilesArr[29]= new MemoryGameTiles("gray",Color.GRAY,2);
        tilesArr[30]= new MemoryGameTiles("yellow",Color.BLUE,2);
        tilesArr[31]= new MemoryGameTiles("green",Color.ORANGE,2);
        
        tilesArr[32]= new MemoryGameTiles("red",Color.GRAY,2);
        tilesArr[33]= new MemoryGameTiles("yellow",Color.RED,2);
        tilesArr[34]= new MemoryGameTiles("green",Color.YELLOW,2);
        tilesArr[35]= new MemoryGameTiles("orange",Color.ORANGE,2);
        
        //Add the MemoryGameTiles objects to the panel randomly. 
        //Ensures the first index will be random, and add other tiles that are left in the array one by one 
        int index=random.nextInt(12);
        for(int i=index+12+12;i>=24;i--){
            theThirdPanel.add(tilesArr[i]);
        }
        for(int a=index+12+12+1;a<36;a++){
            theThirdPanel.add(tilesArr[a]);
        }
    }//addThirdRoundTiles ends
    
    /**
     * Add actionListener to tiles from MemoryGameTiles class for the third round
     * buttonPressedThird() method will be called when click on one of the tiles
     */
    public void addListenerThirdRound(){
        tilesArr[24].addActionListener(e -> buttonPressedThird(24));
        tilesArr[25].addActionListener(e -> buttonPressedThird(25));
        tilesArr[26].addActionListener(e -> buttonPressedThird(26));
        tilesArr[27].addActionListener(e -> buttonPressedThird(27));
        
        tilesArr[28].addActionListener(e -> buttonPressedThird(28));
        tilesArr[29].addActionListener(e -> buttonPressedThird(29));
        tilesArr[30].addActionListener(e -> buttonPressedThird(30));
        tilesArr[31].addActionListener(e -> buttonPressedThird(31));

        tilesArr[32].addActionListener(e -> buttonPressedThird(32));
        tilesArr[33].addActionListener(e -> buttonPressedThird(33));
        tilesArr[34].addActionListener(e -> buttonPressedThird(34));
        tilesArr[35].addActionListener(e -> buttonPressedThird(35));
    }//addListenerThirdRound() ends
    
    /**
     * Call all the methods needed for the third round
     * @param mainPanel                             The panel theThirdPanel will be added to.
     * @param theThirdPanel, topPanel, midPanel, theSecondPanel     The parameters for the methods
     */
    public void thirdRound(JPanel mainPanel, JPanel theThirdPanel, JPanel topPanel, JPanel midPanel, JPanel theSecondPanel){
        theSecondPanel.setVisible(false);           //Set theSecondPanel invisible
        timer=new Timer();                          //Restart the timer

        setRounds(topPanel, "Third Round   ");
        
        addThirdRoundTiles(theThirdPanel);          //Add third round tiles to theThirdPanel
        
        mainPanel.add(theThirdPanel, BorderLayout.SOUTH);           //Add theThirdPanel to the bottom part of mainPanel 
        
        setInstruction(topPanel);                   //Change the second question back to instruction
        
        setThirdTimer(topPanel,midPanel);           //Call the setThirdTimer() method
    }
    
    /**
     * Create a method that outputs the answer to the textField for the first round.
     * And enter next round when click on the right tile.
     * The method will be called by the actionListener.
     * The TextField will output 'Nice!' or 'Wrong:(' based on the each tile's buttonValue from MemoryGameTiles.
     * If the buttonValue is 1, 'Nice!' will be output and enter next round, otherwise, 'Wrong:(' will be output.
     * User cannot click on other tiles once clicked on one already to ensure there's no cheating.
     * 
     * @param i   int value to represent index number in tilesArr
     */
    public void buttonPressedFirst(int i){
        int x=0;       //Counting varible for the while loop that sets all the tiles to not selectable once clicked on one tile
        
        //Call setSelected() method from MemoryGameTiles class, set the tile that's clicked on to selected
        tilesArr[i].setSelected();     
        
        if(tilesArr[i].isSelected){
            while(x<12){
                //Set other tiles to not enabled so the user cannot choose again when they select the wrong answer
                tilesArr[x].setEnabled(false);          
                x++;
            }
        }
        if(tilesArr[i].buttonValue==1){
            tf.setText("  Nice!");
            secondRound(mainPanel, theSecondPanel, topPanel, midPanel,theFirstPanel);           //Call for the second round
        }
        else{
            tf.setText("Wrong:(");
        }
    }//buttonPressedFirst() ends
    
    /**
     * Create a method that outputs the answer to the textField for the second round.
     * And enter next round when click on the right tile.
     * The method will be called by the actionListener.
     * The TextField will output 'Nice!' or 'Wrong:(' based on the each tile's buttonValue from MemoryGameTiles.
     * If the buttonValue is 1, 'Nice!' will be output and enter next round, otherwise, 'Wrong:(' will be output.
     * User cannot click on other tiles once clicked on one already to ensure there's no cheating.
     * 
     * @param i   int value to represent index number in tilesArr
     */
    public void buttonPressedSecond(int i){
        int x=12;         //Counting varible for the while loop that sets all the tiles to not selectable once clicked on one tile
        
        //Call setSelected() method from MemoryGameTiles class, set the tile that's clicked on to selected
        tilesArr[i].setSelected();
        
        if(tilesArr[i].isSelected){
            while(x<24){
                //Set other tiles to not enabled so the user cannot choose again when they select the wrong answer
                tilesArr[x].setEnabled(false);
                x++;
            }
        }
        if(tilesArr[i].buttonValue==1){
            tf.setText("  Nice!");
            thirdRound(mainPanel, theThirdPanel, topPanel, midPanel,theSecondPanel);             //Call for the third round
        }
        else{
            tf.setText("Wrong:(");
        }
    }//buttonPressedSecond() ends
    
    /**
     * Create a method that outputs the answer to the textField for the third round.
     * And enter next round when click on the right tile.
     * The method will be called by the actionListener.
     * The TextField will output 'Nice!' or 'Wrong:(' based on the each tile's buttonValue from MemoryGameTiles.
     * If the buttonValue is 1, 'Nice!' will be output and enter next round, otherwise, 'Wrong:(' will be output.
     * User cannot click on other tiles once clicked on one already to ensure there's no cheating.
     * 
     * @param i   int value to represent index number in tilesArr
     */
    public void buttonPressedThird(int i){
        int x=24;      //Counting varible for the while loop that sets all the tiles to not selectable once clicked on one tile
        
        //Call setSelected() method from MemoryGameTiles class, set the tile that's clicked on to selected
        tilesArr[i].setSelected();
        
        if(tilesArr[i].isSelected){
            while(x<36){
                //Set other tiles to not enabled so the user cannot choose again when they select the wrong answer
                tilesArr[x].setEnabled(false);
                x++;
            }
        }
        if(tilesArr[i].buttonValue==1){
            tf.setText("Congrats!:)");
        }
        else{
            tf.setText("Wrong:(");
        }
    }//buttonPressedThird() ends
    
    public static void main(String [] args) {
        new FinalMemoryGame();
    }
}

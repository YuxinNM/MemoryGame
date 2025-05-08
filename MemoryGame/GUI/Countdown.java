package MemoryGame.GUI;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Countdown {
    private JLabel timerLabel;
    private JLabel questionLabel;
    private TextField tf;
    private Timer timer;
    private MemoryGameTiles[] tilesArr;
    private JPanel mainPanel,theFirstPanel, theSecondPanel, theThirdPanel, topPanel, midPanel;
    
    // constructor
    public Countdown(JLabel timerLabel, JLabel questionLabel, TextField tf, Timer timer,
                     MemoryGameTiles[] tilesArr,
                     JPanel mainPanel, JPanel first, JPanel second, JPanel third,
                     JPanel topPanel, JPanel midPanel,
                     String currQuestion, int startIndex, int endIndex, int roundNum) {
        this.timerLabel = timerLabel;
        this.questionLabel = questionLabel;
        this.tf = tf;
        this.timer = timer;
        this.tilesArr = tilesArr;
        this.mainPanel = mainPanel;
        this.theFirstPanel = first;
        this.theSecondPanel = second;
        this.theThirdPanel = third;
        this.topPanel = topPanel;
        this.midPanel = midPanel;
        
        setTimer(currQuestion, startIndex, endIndex, roundNum);
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
     * Create a method that outputs the answer to the textField for the first round.
     * And enter next round when click on the right tile.
     * The method will be called by the actionListener.
     * The TextField will output 'Nice!' or 'Wrong:(' based on the each tile's buttonValue from MemoryGameTiles.
     * If the buttonValue is 1, 'Nice!' will be output and enter next round, otherwise, 'Wrong:(' will be output.
     * User cannot click on other tiles once clicked on one already to ensure there's no cheating.
     * 
     * @param i   int value to represent index number in tilesArr
     * @param rangeStart start index
     * @param rangeEnd end index
     * @param nextRound next round
     */
    private void buttonPressed(int i, int rangeStart, int rangeEnd, int nextRound) {
        tilesArr[i].setSelected();
        if (tilesArr[i].isSelected) {
            for (int x = rangeStart; x < rangeEnd; x++) {
                tilesArr[x].setEnabled(false);
            }
        }

        if (tilesArr[i].buttonValue == 1) {
            if (nextRound == 2) {
                tf.setText("  Nice!");
                new GamePanels().secondRound(mainPanel, theSecondPanel, topPanel, midPanel, theFirstPanel); 
            } else if (nextRound == 3) {
                tf.setText("  Nice!");
                new GamePanels().thirdRound(mainPanel, theThirdPanel, topPanel, midPanel, theSecondPanel);
            } else {
                tf.setText("Congrats :)");
            }
        } else {
            tf.setText("Wrong :(");
        }
    }
}

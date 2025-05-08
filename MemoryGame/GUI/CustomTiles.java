package MemoryGame.GUI;

import javax.swing.JPanel;
import java.awt.*;

public class CustomTiles extends GamePanels{

    public CustomTiles(int roundNum, JPanel currPanel) {
        if (roundNum == 1) {
            addFirstRoundTiles(currPanel);
        } else if (roundNum == 2) {
            addSecondRoundTiles(currPanel);
        } else {
            addThirdRoundTiles(currPanel);
        }
    }

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
}

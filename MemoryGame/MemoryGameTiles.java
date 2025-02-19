package MemoryGame;

/**
 * MemoryGameTiles is a class that extends JButton. It is used to create tiles that appear in programs.
 * 
 * @author (Nancy Ma)
 * @version (3.0 May.29th)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MemoryGameTiles extends JButton
{  
    public Dimension tileSize=new Dimension(100, 100);        //Initialize tileSize field and set the size needed
    public int buttonValue;                                   //Initialize buttonValue field 
    public Font font=new Font("Arial", Font.BOLD,50);         //Initialize font field and set font
    public boolean isSelected;                                //Initialize isSelected field
    
    /**
     * Constructor for objects of class MemoryGameTile. Sets the content, background color, font, size, and value to each tile.
     * @param content          The content of each tile.
     * @param backgroundColor  The background color of the tile.
     * @param certainValue     The value of each tile, for later use to determine the text that will appear in the window.
     */
    public MemoryGameTiles(String content, Color backgroundColor, int certainValue){
        this.setText(content);
        this.setBackground(backgroundColor);
        this.setMyValue(certainValue);
        this.setFont(font);         //The font will be the same for all of the tiles
        this.setMySize();           //The size will be the same for all of the tiles
    }
    
    public void setMyFont(){
        this.setFont(font);
    }
    
    public void setMySize(){
        this.setPreferredSize(tileSize);        //To ensure the tileSize is the same and there's no need to call it every time
        this.setOpaque(true);
    }
    
    public void setMyValue(int certainValue){
        this.buttonValue=certainValue;
    }
    
    /**
     * When tile is clicked, this method is called to set the tile to be selected.
     */
    public void setSelected(){
        this.isSelected=true;
    }
}


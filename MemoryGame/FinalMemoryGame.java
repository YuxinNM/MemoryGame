package MemoryGame;

import MemoryGame.GUI.GamePanels;

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


public class FinalMemoryGame {
    public static void main(String [] args) {
        GamePanels gp = new GamePanels();
        gp.firstRound();
    }
}
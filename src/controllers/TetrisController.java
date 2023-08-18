package controllers;

import models.*;
import views.TetrisBoard;
import wheelsunh.users.Rectangle;

import java.awt.*;
import java.util.Random;

/**
 * TetrisController.java:
 * Class to hold all of the game logic for tetris
 *
 * @author Professor Rossi
 * @version 1.0 July 24, 2020
 */
public class TetrisController
{
    private final TetrisBoard TETRIS_BOARD;

    /**
     * Constructor to take in a tetris board so the controller and the board can communciate
     *
     * @param tetrisBoard A tetris board instance
     */
    public TetrisController( TetrisBoard tetrisBoard )
    {
        this.TETRIS_BOARD = tetrisBoard;
    }

    /**
     * Makes a tetronimo based off the integer passed.
     *
     * @param shape the integer code for one of the seven tetronimos.
     * @return an address to an instantiated tetronimo based off the code that was passed.
     */
    public Tetronimo parseTetromino(int shape)
    {
        Tetronimo tetronimo;
        switch(shape)
        {
            case 0:
                tetronimo = new StraightLine();
                break;
            case 1:
                tetronimo = new Square();
                break;
            case 2:
                tetronimo = new ZigLeft();
                break;
            case 3:
                tetronimo = new ZigRight();
                break;
            case 4:
                tetronimo = new LLeft();
                break;
            case 5:
                tetronimo = new LRight();
                break;
            default:
                tetronimo = new TBlock();
                break;

        }

        return tetronimo;
    }

    /**
     * Randomly chooses the next tetronimo and returns it (INCOMPLETE)
     *
     * @return The next tetronimo to be played
     */
    public Tetronimo getNextTetromino(int shape)
    {
        Tetronimo tetronimo = parseTetromino(shape);

        tetronimo.setLocation( 40 + (5 * Tetronimo.SIZE), 0 );

        return tetronimo;
    }

    /**
     * Displays a tetronimo to the right of the screen to let the user know what the next shape is.
     *
     * @param shape shape ID of the tetronimo to display.
     * @return The tetronimo address. Necessary because we have to move it later.
     */
    public Tetronimo displayTetronimo(int shape)
    {
        Tetronimo tetronimo = parseTetromino(shape);
        tetronimo.setLocation(400, 100);
        return tetronimo;
    }

    /**
     * Method to determine if the tetronimo has landed (Finished)
     *
     * @param tetronimo The tetronimo to evaluate
     * @return True if the tetronimo has landed (on the bottom of the board or another tetronimo), false if it has not
     */
    public boolean tetronimoLanded( Tetronimo tetronimo )
    {
      return tetronimo.checkShiftDown(TETRIS_BOARD.getPlayingField());
    }

    /**
     * Checks the board after a tetronimo falls. Gets the location and height of the tetronimo
     * so we don't unecessarily check every row.
     *
     * @param tetronimo The piece that just fell so we only check the rows it affected.
     * @return true if any rows were cleared to let the TetrisBoard class know to print the new
     * score.
     */
    public boolean checkBoard(Tetronimo tetronimo)
    {
        boolean flag = false;
        int start = tetronimo.getYLocation() / Tetronimo.SIZE;
        int endpoint = tetronimo.getHeight()/Tetronimo.SIZE + start ;
        while(start < endpoint)
        {
            if(checkRow(start))
            {
                TETRIS_BOARD.clearRow(start);
                flag = true;
            }
            start++;
        }
        return flag;
    }

    /**
     * Checks if a row is full and should be cleared.
     *
     * @param row The row to check.
     * @return true if the row is full, false if there's at least one empty block.
     */
    public boolean checkRow(int row)
    {
        for(int k = 0; k < 10; k++)
        {
            if(TETRIS_BOARD.getPlayingField()[k][row].getFillColor().equals(Color.WHITE))
                return false;
        }
        return true;
    }
}

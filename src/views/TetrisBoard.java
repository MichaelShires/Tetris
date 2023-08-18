package views;

import controllers.TetrisController;
import models.Tetronimo;
import wheelsunh.users.*;
import wheelsunh.users.Frame;
import wheelsunh.users.Rectangle;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * TetrisBoard.java:
 * Class to model the tetris board
 *
 * @author Professor Rossi
 * @version 1.0 July 24, 2020
 *
 * @see java.awt.Color
 * @see java.awt.event.KeyListener
 * @see java.awt.event.KeyEvent
 */
public class TetrisBoard implements KeyListener
{
    /**
     * Constant to represent the width of the board
     */
    public static final int WIDTH = 10;

    /**
     * Constant to represent the height of the board
     */
    public static final int HEIGHT = 24;
    private boolean gameOver = false;
    private int score = 0;

    private final TetrisController CONTROLLER;
    private Tetronimo tetronimo;
    private Rectangle[][] playingField;

    /**
     * Constructor to initialize the board
     *
     * @param frame The wheelsunh frame (so we can add this class as a key listener for the frame)
     */
    public TetrisBoard( Frame frame )
    {
        frame.addKeyListener( this );
        this.CONTROLLER = new TetrisController( this );

        this.buildBoard();

        this.run();
    }

    /**
     * Builds the playing field for tetris
     */
    private void buildBoard()
    {
        this.playingField = new Rectangle[ WIDTH ][ HEIGHT ];

        for ( int i = 0; i < TetrisBoard.WIDTH; i++ )
        {
            for ( int j = 0; j < TetrisBoard.HEIGHT; j++ )
            {
                this.playingField[ i ][ j ] = new Rectangle();
                this.playingField[ i ][ j ].setLocation( i * 20 + 40, j * 20 );
                this.playingField[ i ][ j ].setSize( Tetronimo.SIZE, Tetronimo.SIZE );
                this.playingField[ i ][ j ].setColor( Color.WHITE );
                this.playingField[ i ][ j ].setFrameColor( Color.BLACK );
            }
        }
    }

    /**
     * Starts gameplay and is responsible for keeping the game going (INCOMPLETE)
     */
    public void run()
    {
        int prevScore;
        System.out.println("Good luck!");

        Random random = new Random();
        int nextshape = random.nextInt(7);
        int currShape;
        Tetronimo nextPiece;
        while(!gameOver)
        {
            prevScore = score;
            currShape = nextshape;
            nextshape = random.nextInt(7);
            nextPiece = this.CONTROLLER.displayTetronimo(nextshape);
            this.tetronimo = this.CONTROLLER.getNextTetromino(currShape);

            while( this.CONTROLLER.tetronimoLanded( this.tetronimo ) )
            {
                this.tetronimo.setLocation( this.tetronimo.getXLocation(), this.tetronimo.getYLocation() + Tetronimo.SIZE );
                Utilities.sleep( 500 );
            }

            /*
             * This next line is a placeholder for now, you need to change this code so when a piece lands
             * the right squares on the board are painted the color of the tetronimo and the tetronimo itself gets hidden
             */
            this.tetronimo.applyToBoard(playingField);
            if(this.CONTROLLER.checkBoard(this.tetronimo))
            {
                if(prevScore == (score - 4))
                    score += 4;
                System.out.println("Score = " + score + "00");
            }
            if(this.tetronimo.getYLocation() < 10)
                gameOver = true;
            this.tetronimo.hide();
            nextPiece.hide();
            this.tetronimo = null;
            nextPiece = null;
        }
        System.out.println("Game over! Final score = " + score + "00");
    }

    /**
     * Getter method for the array representing the playing field, not used yet but will be needed by the controller later
     *
     * @return The playing field
     */
    public Rectangle[][] getPlayingField()
    {
        return playingField;
    }

    /**
     * This method is not used in this program
     *
     * @param e The key event
     */
    @Override
    public void keyTyped( KeyEvent e )
    {
        //not in use
    }

    /**
     * Handles the key events by the user (INCOMPLETE)
     *
     * @param e The key event
     */
    @Override
    public void keyPressed( KeyEvent e )
    {
        int key = e.getKeyCode();

        if( this.tetronimo == null )
        {
            return;
        }

        switch( key )
        {
            case 38:
                //if(this.tetronimo.checkRotate(playingField))
                    this.tetronimo.rotate(playingField);
                break;
            case 37:
                if( this.tetronimo.getXLocation() - Tetronimo.SIZE >= 40 )
                {
                    this.tetronimo.shiftLeft(playingField);
                }
                break;
            case 39:
                if( (this.tetronimo.getXLocation() + this.tetronimo.getWidth()) <
                        ((TetrisBoard.WIDTH * Tetronimo.SIZE) + 40))
                {
                    this.tetronimo.shiftRight(playingField);
                }
                break;
            case 40:
                    this.tetronimo.shiftDown(playingField);
                break;
        }

    }

    /**
     * Clears a row on the board, increments score and moves the rest of the board down.
     *
     * @param row the row to be cleared.
     */
    public void clearRow(int row)
    {
        score += 1;
        for(int i = 0; i < 10; i++)
        {
            playingField[i][row].setColor(Color.WHITE);
            playingField[i][row].setFrameColor(Color.BLACK);
        }
        for(int i = row; i > 0; i--)
        {
            for(int k = 0; k < 10; k++)
            {
                if(!playingField[k][i-1].getFillColor().equals(Color.WHITE))
                {
                    playingField[k][i].setFillColor(playingField[k][i-1].getFillColor());
                    playingField[k][i-1].setFillColor(Color.WHITE);
                }
            }
        }
    }

    /**
     * This method is not used in this program
     *
     * @param e The key event
     */
    @Override
    public void keyReleased( KeyEvent e )
    {
        //not in use
    }
}
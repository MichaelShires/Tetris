package models;

import wheelsunh.users.Rectangle;

import java.awt.*;

/**
 * StraightLine.java:
 * Creates a straight line tetronimo. Based off the template supplied by Professor Rossi.
 *
 * @author Michael Shires, inherited from Professor Rossi
 * @version 2.0 August 11th, 2023
 *
 * @see java.awt.Point
 */
public class StraightLine extends Tetronimo
{
    /**
     * Creates the tetronimo and puts it in the vertical orientation
     */
    public StraightLine()
    {
        super.r1.setLocation( 0, 0 );
        super.r2.setLocation( 0, Tetronimo.SIZE );
        super.r3.setLocation( 0, Tetronimo.SIZE * 2 );
        super.r4.setLocation( 0, Tetronimo.SIZE * 3 );

        super.add( r1 );
        super.add( r2 );
        super.add( r3 );
        super.add( r4 );
    }

    /**
     * Attempts to rotate the tetronimo, based off current rotation orientation and tetronimo
     * position.
     */
    @Override
    public void rotate(Rectangle[][] board)
    {
        if(this.curRotation % 2 == 0)
        {
            //1, 4
            if(checkShape(board, 4, 1))
            {
                super.rotate(board);
                rotateShape();
            }
        }
        else
        {
            if(checkShape(board, 1, 4))
            {
                super.rotate(board);
                rotateShape();
            }
        }
    }

    /**
     * Actually performs the rotation. Set's each tetronimo to a new location based of the original
     * location of the tetronimo
     */
    public void rotateShape()
    {

        Point curLoc = super.getLocation();
        super.setLocation( 0, 0 );

        if( super.curRotation % 2 == 0 )
        {
            super.r1.setLocation( 0, 0 );
            super.r2.setLocation( 0, Tetronimo.SIZE );
            super.r3.setLocation( 0, Tetronimo.SIZE * 2 );
            super.r4.setLocation( 0, Tetronimo.SIZE * 3 );
        }
        else
        {
            super.r1.setLocation( 0, 0 );
            super.r2.setLocation( Tetronimo.SIZE, 0 );
            super.r3.setLocation( Tetronimo.SIZE * 2, 0 );
            super.r4.setLocation( Tetronimo.SIZE * 3, 0 );
        }

        super.setLocation( curLoc );
    }


    /**
     * Gets the height of the tetronimo based on the orientation
     *
     * @return The height of the tetronimo
     */
    @Override
    public int getHeight()
    {
        if( this.curRotation % 2 == 0 )
        {
            return Tetronimo.SIZE * 4;
        }
        else
        {
            return Tetronimo.SIZE ;
        }
    }

    /**
     * Gets the width of the tetronimo based on the orientation
     *
     * @return The width of the tetronimo
     */
    @Override
    public int getWidth()
    {
        if( this.curRotation % 2 == 0 )
        {
            return Tetronimo.SIZE;
        }
        else
        {
            return Tetronimo.SIZE * 4;
        }
    }

    /**
     * Colors the board prior to getting rid of the tetronimo. Colors it in the specific color of
     * the tetronimo.
     *
     * @param rectangles the board to color in.
     */
    public void applyToBoard(Rectangle[][] rectangles)
    {
        rectangles[(this.r1.getXLocation() - 40)/ Tetronimo.SIZE][this.r1.getYLocation() / Tetronimo.SIZE].setFillColor(Color.RED);
        rectangles[(this.r2.getXLocation() - 40)/ Tetronimo.SIZE][this.r2.getYLocation() / Tetronimo.SIZE].setFillColor(Color.RED);
        rectangles[(this.r3.getXLocation() - 40)/ Tetronimo.SIZE][this.r3.getYLocation() / Tetronimo.SIZE].setFillColor(Color.RED);
        rectangles[(this.r4.getXLocation() - 40)/ Tetronimo.SIZE][this.r4.getYLocation() / Tetronimo.SIZE].setFillColor(Color.RED);
        rectangles[(this.r1.getXLocation() - 40)/ Tetronimo.SIZE][this.r1.getYLocation() / Tetronimo.SIZE].setFrameColor( Color.BLACK );
        rectangles[(this.r2.getXLocation() - 40)/ Tetronimo.SIZE][this.r2.getYLocation() / Tetronimo.SIZE].setFrameColor( Color.BLACK );
        rectangles[(this.r3.getXLocation() - 40)/ Tetronimo.SIZE][this.r3.getYLocation() / Tetronimo.SIZE].setFrameColor( Color.BLACK );
        rectangles[(this.r4.getXLocation() - 40)/ Tetronimo.SIZE][this.r4.getYLocation() / Tetronimo.SIZE].setFrameColor( Color.BLACK );
    }
}

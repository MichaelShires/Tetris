package models;

import wheelsunh.users.Rectangle;

import java.awt.*;


/**
 * Square.java:
 * Creates a square tetronimo.
 *
 * @author Michael Shires
 * @version 1.0 August 11th, 2023
 *
 * @see java.awt.Point
 */

public class Square extends Tetronimo
{
    /**
     * Creates the tetronimo and puts it in the vertical orientation
     */
    public Square()
    {
      super.r1.setLocation( 0, 0 );
      super.r2.setLocation( 0, Tetronimo.SIZE );
      super.r3.setLocation( Tetronimo.SIZE, 0 );
      super.r4.setLocation( Tetronimo.SIZE, Tetronimo.SIZE );

      super.r1.setFillColor(Color.BLUE);
      super.r2.setFillColor(Color.BLUE);
      super.r3.setFillColor(Color.BLUE);
      super.r4.setFillColor(Color.BLUE);

      super.add( r1 );
      super.add( r2 );
      super.add( r3 );
      super.add( r4 );
    }


  /**
   * Gets the height of the tetronimo based on the orientation
   *
   * @return The height of the tetronimo
   */
  @Override
  public int getHeight()
  {
    return Tetronimo.SIZE * 2;
  }

  /**
   * Gets the width of the tetronimo based on the orientation
   *
   * @return The width of the tetronimo
   */
  @Override
  public int getWidth()
  {
    return Tetronimo.SIZE * 2;
  }

  /**
   * Colors the board prior to getting rid of the tetronimo. Colors it in the specific color of
   * the tetronimo.
   *
   * @param rectangles the board to color in.
   */
  public void applyToBoard(Rectangle[][] rectangles)
  {
    rectangles[(this.r1.getXLocation() - 40)/ Tetronimo.SIZE][this.r1.getYLocation() / Tetronimo.SIZE].setFillColor(Color.BLUE);
    rectangles[(this.r2.getXLocation() - 40)/ Tetronimo.SIZE][this.r2.getYLocation() / Tetronimo.SIZE].setFillColor(Color.BLUE);
    rectangles[(this.r3.getXLocation() - 40)/ Tetronimo.SIZE][this.r3.getYLocation() / Tetronimo.SIZE].setFillColor(Color.BLUE);
    rectangles[(this.r4.getXLocation() - 40)/ Tetronimo.SIZE][this.r4.getYLocation() / Tetronimo.SIZE].setFillColor(Color.BLUE);
    rectangles[(this.r1.getXLocation() - 40)/ Tetronimo.SIZE][this.r1.getYLocation() / Tetronimo.SIZE].setFrameColor( Color.BLACK );
    rectangles[(this.r2.getXLocation() - 40)/ Tetronimo.SIZE][this.r2.getYLocation() / Tetronimo.SIZE].setFrameColor( Color.BLACK );
    rectangles[(this.r3.getXLocation() - 40)/ Tetronimo.SIZE][this.r3.getYLocation() / Tetronimo.SIZE].setFrameColor( Color.BLACK );
    rectangles[(this.r4.getXLocation() - 40)/ Tetronimo.SIZE][this.r4.getYLocation() / Tetronimo.SIZE].setFrameColor( Color.BLACK );
  }

}

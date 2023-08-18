package models;

import wheelsunh.users.Rectangle;

import java.awt.*;

/**
 * ZigRight.java:
 * Creates a ZigRight tetronimo. A ZigRight shape is a vertical zig-zag with the zag to the left,
 * giving the shape a rightward orientation.
 *
 * @author Michael Shires
 * @version 1.0 August 11th, 2023
 *
 * @see java.awt.Point
 */
public class ZigRight extends Tetronimo
{
  /**
   * Creates the tetronimo and puts it in the vertical orientation
   */
  public ZigRight()
  {
    super.r1.setLocation( Tetronimo.SIZE, 0 );
    super.r2.setLocation( Tetronimo.SIZE, Tetronimo.SIZE );
    super.r3.setLocation( 0, Tetronimo.SIZE );
    super.r4.setLocation( 0, Tetronimo.SIZE * 2 );

    super.r1.setFillColor(Color.GREEN);
    super.r2.setFillColor(Color.GREEN);
    super.r3.setFillColor(Color.GREEN);
    super.r4.setFillColor(Color.GREEN);

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
      if(checkShape(board, 3, 2))
      {
        super.rotate(board);
        rotateShape();
      }
    }
    else
    {
      if(checkShape(board, 2, 3))
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
      super.r3.setLocation( 0, Tetronimo.SIZE );
      super.r4.setLocation( 0, Tetronimo.SIZE * 2 );
    }
    else
    {
      super.r3.setLocation( 0, 0 );
      super.r4.setLocation( Tetronimo.SIZE * 2 , Tetronimo.SIZE );
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
      return Tetronimo.SIZE * 3;
    }
    else
    {
      return Tetronimo.SIZE * 2;
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
      return Tetronimo.SIZE * 2;
    }
    else
    {
      return Tetronimo.SIZE * 3;
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
    rectangles[(this.r1.getXLocation() - 40)/ Tetronimo.SIZE][this.r1.getYLocation() / Tetronimo.SIZE].setFillColor(Color.GREEN);
    rectangles[(this.r2.getXLocation() - 40)/ Tetronimo.SIZE][this.r2.getYLocation() / Tetronimo.SIZE].setFillColor(Color.GREEN);
    rectangles[(this.r3.getXLocation() - 40)/ Tetronimo.SIZE][this.r3.getYLocation() / Tetronimo.SIZE].setFillColor(Color.GREEN);
    rectangles[(this.r4.getXLocation() - 40)/ Tetronimo.SIZE][this.r4.getYLocation() / Tetronimo.SIZE].setFillColor(Color.GREEN);
    rectangles[(this.r1.getXLocation() - 40)/ Tetronimo.SIZE][this.r1.getYLocation() / Tetronimo.SIZE].setFrameColor( Color.BLACK );
    rectangles[(this.r2.getXLocation() - 40)/ Tetronimo.SIZE][this.r2.getYLocation() / Tetronimo.SIZE].setFrameColor( Color.BLACK );
    rectangles[(this.r3.getXLocation() - 40)/ Tetronimo.SIZE][this.r3.getYLocation() / Tetronimo.SIZE].setFrameColor( Color.BLACK );
    rectangles[(this.r4.getXLocation() - 40)/ Tetronimo.SIZE][this.r4.getYLocation() / Tetronimo.SIZE].setFrameColor( Color.BLACK );
  }
}

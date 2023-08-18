package models;

import wheelsunh.users.Animator;
import wheelsunh.users.Rectangle;
import wheelsunh.users.ShapeGroup;

import java.awt.Color;

/**
 * Tetronimo.java:
 * An abstract class to model the base capaabilities of a tetronimo
 *
 * @author Professor Rossi
 * @version 1.0 July 24, 2020
 *
 * @see java.awt.Color
 */
public abstract class Tetronimo extends ShapeGroup
{
    /**
     * Constant to represent the size of the tetronimo
     */
    public static final int SIZE= 20;

    protected Rectangle r1;
    protected Rectangle r2;
    protected Rectangle r3;
    protected Rectangle r4;

    protected int curRotation = 0;

    /**
     * Generates the four rectangles for the tetronino and puts them on the screen, they are at the default coordinates
     * to start
     */
    public Tetronimo()
    {
        super();
        this.r1 = new Rectangle();
        this.r1.setSize( Tetronimo.SIZE, Tetronimo.SIZE );
        this.r1.setFrameColor( Color.BLACK );

        this.r2 = new Rectangle();
        this.r2.setSize( Tetronimo.SIZE, Tetronimo.SIZE );
        this.r2.setFrameColor( Color.BLACK );

        this.r3 = new Rectangle();
        this.r3.setSize( Tetronimo.SIZE, Tetronimo.SIZE );
        this.r3.setFrameColor( Color.BLACK );

        this.r4 = new Rectangle();
        this.r4.setSize( Tetronimo.SIZE, Tetronimo.SIZE );
        this.r4.setFrameColor( Color.BLACK );
    }

    /**
     * Increments the rotation of the tetronimo, other classes need to override this to provide the full functionality
     */
    public void rotate(Rectangle[][] board )
    {
        this.curRotation++;
    }

    /**
     * Checks if a piece can move in a direction on the board. Returns true if possible, returns
     * false if the spot is occupied.
     *
     * @param board The board to check.
     * @param piece The piece to be moved. It is not a whole piece, only a Rectangle
     * @param direction direction to check. 0 is down, 1 is left, 2 (or anything else) is right.
     * @return True if the spot is empty, false if it's occupied.
     */
    public boolean checkPiece(Rectangle[][] board, Rectangle piece, int direction)
    {
        int x = (piece.getXLocation() - 40) / Tetronimo.SIZE;
        int y = (piece.getYLocation()) / Tetronimo.SIZE;
        switch (direction)
        {
            //Go down
            case 0:
                if(y >= 23)
                    return false;
                return checkPos(board, x, (y+1));

            //go left
            case 1:
                return checkPos(board, (x-1), y);

            //go right
            default:
                return checkPos(board, (x+1), y);
        }
    }

    /**
     * Checks a individual cell on the board to see if it's filled.
     *
     * @param board the board to be checked.
     * @param x the x-coordinate of the cell to be checked.
     * @param y the y-coordinate of the cell to be checked.
     * @return True if the cell is empty, and false if it's filled.
     */
    public boolean checkPos(Rectangle[][] board, int x, int y)
    {
        return board[x][y].getFillColor().equals(Color.WHITE);
    }

    /**
     * Checks if the entire shape can rotate. Passed a width and height of the new shape of the
     * tetronimo, and checks all locations that a rectangle may be moving to.
     *
     * @param board the board to check against.
     * @param width The width of the new tetromino shape.
     * @param height The height of the new tetromino shape.
     * @return
     */
    public boolean checkShape(Rectangle[][] board, int width, int height)
    {
        int startX = (this.getXLocation() - 40) / Tetronimo.SIZE;
        int startY;
        int endX = startX + width;
        if(endX > 10)
            return false;
        int endY = this.getYLocation() / Tetronimo.SIZE + height;
        if(endY > 24)
            return false;
        while(startX < endX)
        {
            startY = this.getYLocation() / Tetronimo.SIZE;
            while(startY < endY)
            {
                if(!checkPos(board, startX, startY))
                    return false;
                startY++;
            }
            startX++;
        }
        return true;
    }

    /**
     * Shifts the tetronimo left one row
     */
    public void shiftLeft(Rectangle[][] board)
    {if(checkPiece(board, r1, 1) && checkPiece(board, r2, 1) &&
        checkPiece(board, r3, 1) && checkPiece(board, r4, 1))
        super.setLocation( super.getXLocation() - Tetronimo.SIZE, super.getYLocation() );
    }

    /**
     * Shifts the tetronimo right one row
     */
    public void shiftRight(Rectangle[][] board)
    {if(checkPiece(board, r1, 2) && checkPiece(board, r2, 2) &&
        checkPiece(board, r3, 2) && checkPiece(board, r4, 2))
        super.setLocation( super.getXLocation() + Tetronimo.SIZE, super.getYLocation() );
    }

    /**
     * Checks if a shift down is eligible by checking each rectangle to see if it can move down.
     * @param board the board to check against.
     * @return true if the tetronimo can move down, false if otherwise.
     */
    public boolean checkShiftDown(Rectangle[][] board)
    {
      return checkPiece(board, r1, 0) && checkPiece(board, r2, 0) &&
          checkPiece(board, r3, 0) && checkPiece(board, r4, 0);
    }

    /**
     * Shifts the tetronimo down one row.
     */
    public void shiftDown(Rectangle[][] board)
    {
        if(checkShiftDown(board))
            super.setLocation( super.getXLocation(), super.getYLocation() + Tetronimo.SIZE );
    }

    /**
     * Abstract method because each tetronimo needs to apply its own color.
     * @param rectangles the board to color in.
     */
    abstract public void applyToBoard(Rectangle[][] rectangles);

    /**
     * Moves used tetronimos offscreen. Javas garbage collector takes care of them eventually.
     */
    public void hide()
    {
        this.r1.setLocation(-600, 0);
        this.r2.setLocation(-600, 0);
        this.r3.setLocation(-600, 0);
        this.r4.setLocation(-600, 0);
    }
}
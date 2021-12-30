/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package gui;

/**
 *
 * Thrown to indicate that a given coordinate is out of the bounds of the map
 * (This could mean a negative value passed as the row or column, or a value
 * that is bigger than gridSize that is passed as the row or column)
 *
 * @author Dylan Johnson
 */
public class CoordinateOutOfBoundsException extends RuntimeException {

    /**
     * Creates a new instance of <code>CoordinateOutOfBoundsException</code>
     * without detail message.
     */
    public CoordinateOutOfBoundsException() {
        super();
    }

    /**
     * Constructs an instance of <code>CoordinateOutOfBoundsException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public CoordinateOutOfBoundsException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>CoordinateOutOfBoundsException</code>
     * with the parameters in the detailed message
     *
     * @param r Row of the out of bounds coordinate
     * @param c Column of the out of bounds coordinate
     */
    public CoordinateOutOfBoundsException(int r, int c) {
        super("Grid Coordinate Out of Range: (" + r + ", " + c + ")");
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package gui;

/**
 *
 * Thrown to indicate that a grid array has an invalid size in relation to the
 * gridSize of the map (such as a character array or image array that is not of
 * the correct row and column length)
 *
 * @author Dylan Johnson
 */
public class IllegalGridSizeException extends RuntimeException {

    /**
     * Creates a new instance of <code>IllegalGridSizeException</code> without
     * detail message.
     */
    public IllegalGridSizeException() {
        super();
    }

    /**
     * Constructs an instance of <code>IllegalGridSizeException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public IllegalGridSizeException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>IllegalGridSize</code> with the
     * parameters in the detailed message
     *
     * @param grid Illegal character grid attempted to be used
     */
    public IllegalGridSizeException(char[][] grid) {
        super("Invalid grid size: " + grid.length + "X" + grid[0].length);
    }

    /**
     * Constructs an instance of <code>IllegalGridSize</code> with the
     * parameters in the detailed message
     *
     * @param grid Illegal grid attempted to be used
     */
    public IllegalGridSizeException(Object[][] grid) {
        super("Invalid grid size: " + grid.length + "X" + grid[0].length);
    }
}

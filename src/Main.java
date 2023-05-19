import processing.core.PApplet;

//QUESTIONS
/*
Is there a better way to write constructors for classes with a bajillion variables? I hate typing them
    out every time.
Do I need "Player" and "Enemy" subclasses or can I just make Entity non-abstract?
    might just need to play around more and see.
in Main: "object collision"? would a private boolean in Cell work?
in Cell: do I need separate x and row, and y and col, or can those be merged?
    probably just need to look at Game of Life to answer this.
in Entity: does my attack() idea work? dependent on object collision mechanism first.
 */

//TO DO
/*
Instantiate cells
Implement "object collision"—make it so that player and enemy
    Maybe have Cell have a "hasEntity" boolean where if it there is an Entity on it, the boolean is true,
    when it is empty, the boolean is false?
Create a "level editor"? (easy way to make levels, basically)
 */

//The code for the cell class and construction of a grid of cells was taken from my Game of Life project.

public class Main extends PApplet{
    public static PApplet app;
    public final int NUM_ROWS = 20;
    public final int NUM_COLS = 10;

    public final int CELL_SIZE = 20;

    public Cell[][] cells;

    public static void main(String[] args) {
        PApplet.main("Main");
    }
    public Main() {
        super();
        app = this;
    }
    public void settings() {
        size(CELL_SIZE*NUM_COLS, CELL_SIZE*NUM_ROWS);
    }

    public void setup() {
        background(169);
        cells = new Cell[NUM_ROWS][NUM_COLS];

        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                Cell c = new Cell(
                        CELL_SIZE * col,
                        CELL_SIZE * row,
                        CELL_SIZE,
                        row,
                        col);
                cells[row][col] = c;
            }
        }
    }

    public void draw() {
        //Make draw update ONLY when player presses a button (arrow key, attack, wait)
    }

    public void keyPressed() {

    }
}
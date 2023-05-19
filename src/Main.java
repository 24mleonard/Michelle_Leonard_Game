import processing.core.PApplet;

//QUESTIONS
/*
Do I make Cell have an Entity or Entity have a Cell?
                                    ^ going to try this
If I wrote cell.getEntity().setCurrentHP() would that work? or would it set the HP
        of the entity returned which is not the entity in the cell?
should I just make cell HAVE an entity, and when entities move they just copy themselves over to different cells?
Is there a better way to write constructors for classes with a bajillion variables? I hate typing them
    out every time.
in Main: "object collision"? would a private boolean in Cell work?
in Cell: do I need separate x and row, and y and col, or can those be merged?
    probably just need to look at Game of Life to answer this.
in Entity: does my attack() idea work? dependent on object collision mechanism first.
 */

//TO DO
/*
Fix attack more
Implement "object collision"—make it so that player and enemy
    Maybe have Cell have a "hasEntity" boolean where if it there is an Entity on it, the boolean is true,
    when it is empty, the boolean is false?
Create a "level editor"? (easy way to make levels, basically)
 */

//The code for the cell class and construction of a grid of cells was taken from my Game of Life project.

public class Main extends PApplet{
    public static PApplet app;
    public final int NUM_ROWS = 15;
    public final int NUM_COLS = 20;

    public final int CELL_SIZE = 50;

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
        for (Cell[] cs : cells) {
            for (Cell c : cs) {
                c.display();
            }
        }
        Entity player =
                new Entity(cells[NUM_COLS/2][NUM_ROWS/2],
                        3,
                        3,
                        true,
                        1
                );
        Entity enemy =
                new Entity(cells[NUM_COLS/2 + 1][NUM_ROWS/2],
                        3,
                        3,
                        true,
                        1);
    }

    public void draw() {
        //Make draw update ONLY when player presses a button (arrow key, attack, wait)
    }

    public void keyPressed() {

    }
}
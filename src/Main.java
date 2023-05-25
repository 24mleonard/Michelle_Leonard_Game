import processing.core.PApplet;
import processing.core.PImage;

//TO DO
/*
when player entity dies, display a game over screen (only if all players are dead—more than one?)
        how tf would i handle the controls on that...no i don't think so
         THEY ALL MOVE SIMULTANEOUSLY LIKE IN BABA IS YOU (this is dumb)
frameRate(0) and keyPressed calls draw()?

FUN FEATURES (not core)
Create a "level editor"? (easy way to make levels, basically)
Move patterns? (Stride length?)
Attack patterns? (Weapons?)
Images: optimize resizing to a square? (Crop, don't just stretch? get()?)
 */

//QUESTIONS
/*
Is there a better way to write constructors for classes with a bajillion variables? I hate typing them
    out every time.
 */

//CREDITS
/*
The construction of a grid of cells was based on my Game of Life project.
Caitlyn Flexer gave some advice with interactions between Entity objects and moral support.
*/
public class Main extends PApplet{
    public static PApplet app;
    public final static int NUM_ROWS = 15; //hopefully this being static is OK
    public final static int NUM_COLS = 20;

    public final static int CELL_SIZE = 50;

    public Entity[][] entities; //this array is going to be mostly EMPTY i think
    public PImage playerImage;
    public PImage enemyImage;

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
        playerImage = loadImage("mika.jpg");
        playerImage.resize(CELL_SIZE, CELL_SIZE);
        entities = new Entity[NUM_ROWS][NUM_COLS];
        displayGrid();
        Entity player = new Entity(NUM_ROWS/2, NUM_COLS/2, 3, 3, 1, playerImage);
        player.display();
    }

    public void draw() {
        removeDeadEntities();
        displayEntities();
        //^ let this update constantly
        //let rest of draw update ONLY when player presses a button (arrow key, attack, wait)

    }

    public void keyPressed() {
        //draw(); //would this work?
    }

    private void displayGrid() {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                square(col*CELL_SIZE, row*CELL_SIZE, CELL_SIZE);
            }
        }
    }
    private void removeDeadEntities() {
        for (int row = 0; row < entities.length; row++) {
            for (int col = 0; col < entities[row].length; col++) {
                if (entities[row][col] != null && entities[row][col].getCurrentHP() < 0) {
                    //do a little animation!
                    entities[row][col] = null;
                }
            }
        }
    }

    private void displayEntities() {
        for (Entity[] es : entities) {
            for (Entity e: es) {
                if (e != null) {
                    e.display();
                }
            }
        }
    }
}
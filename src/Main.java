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
Attack patterns? (Weapons?)
Images: optimize resizing to a square? (Crop, don't just stretch? get()?)
 */

//QUESTIONS
/*
How to handle collisions where both entities want to move to the same spot at the same time? Precedence?
For now I can only have one player at a time (I am having difficulties getting keyPressed to apply to all isPlayer Entity objects in entities.
    Hopefully i can make that not the case later?
Is there a better way to write constructors for classes with a bajillion variables? I hate typing them
    out every time.
 */

//CREDITS
/*
The construction of a grid of cells was based on my Game of Life project.
Caitlyn Flexer gave some advice with interactions between Entity objects and moral support.
The default images of players and enemies were taken from Anya and Mika's Schoology profiles.
*/

public class Main extends PApplet{
    public static PApplet app;
    public final static int NUM_ROWS = 10; //hopefully this being static is OK
    public final static int NUM_COLS = 20;
    public final static int CELL_SIZE = 50;

    public static Entity[][] entities; //this array is going to be mostly EMPTY i think
    //lololo hopefully static is OK
    public PImage playerImage;
    public PImage enemyImage;
    Entity player;

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
        playerImage = loadImage("mika.jpeg");
        enemyImage = loadImage("anya.jpeg");
        playerImage.resize(CELL_SIZE, CELL_SIZE);
        enemyImage.resize(CELL_SIZE, CELL_SIZE); //Is there a way to make this more efficient so resize() occurs automatically? Overloading?

        entities = new Entity[NUM_ROWS][NUM_COLS];
        displayGrid();
        player = new Entity(NUM_ROWS/2, NUM_COLS/2, 3, 3, 1, playerImage, true);
        entities[NUM_ROWS/2][NUM_COLS/2] = player;
        entities[NUM_ROWS/2 + 1][NUM_COLS/2] = new Entity(NUM_ROWS/2 + 1, NUM_COLS/2, 3, 3, 1, enemyImage, false);
    }

    public void draw() {
        displayGrid();
        removeDeadEntities();
        displayEntities();
        //^ let this update constantly
        //let rest of draw update ONLY when player presses a button (arrow key, attack, wait)

    }

    public void keyPressed() {
        //draw(); //would this work?
//        if (keyCode == UP || key == 'w') {
//            player.move(1);
//        } else if (keyCode == LEFT || key == 'a') {
//            player.move(2);
//        } else if (keyCode == DOWN || key == 's') {
//            player.move(3);
//        } else if (keyCode == RIGHT || key == 'd') {
//            player.move(4);
//        }
        for (Entity[] es : entities) {
            for (Entity e : es) {
                if (player != null && player.isPlayer()) {
                    System.out.println(key + " entered");
                    if (keyCode == UP || key == 'w') {
                        player.setWillMove(1);
                    } else if (keyCode == LEFT || key == 'a') {
                        player.setWillMove(2);
                    } else if (keyCode == DOWN || key == 's') {
                        player.setWillMove(3);
                    } else if (keyCode == RIGHT || key == 'd') {
                        player.setWillMove(4);
                    }
                }
            }
        }
        for (Entity[] es : entities) {
            for (Entity e : es) {
                if (e != null && e.getWillMove() != 0) {
                    e.move(e.getWillMove());
                    e.setWillMove(0);
                }
            }
        }
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
                if (entities[row][col] != null && entities[row][col].getCurrentHP() < 1) {
                    //do a little animation!
                        //uh oh this might actually be more complicated than i thought
                        //bc of calling animations that are supposed to be in draw outside
                        //of draw or whatever. mika's apple thing.
                        //whatever it can be a later feature
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
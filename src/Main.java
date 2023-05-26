import processing.core.PApplet;
import processing.core.PImage;

//TO DO
/*
Add visualizations of data
    HP /tint?
    Collision
    Optimize attack animation

Fix attack so it only affects enemies who are currently on squares (not last turn's enemies but this turn's)

       WillMove for AutoMove

when player entity dies, display a game over screen (only if all players are dead—more than one?)
        how tf would i handle the controls on that...no i don't think so
         THEY ALL MOVE SIMULTANEOUSLY LIKE IN BABA IS YOU (this is dumb)
    //i don't think this is necessary it's working already
    Make it so that will move works (so that when mika is 1 row above anya, and they are both players and user inputs down,
    they both move down ("iT'S nOt a BuG iT'S a FeATuRe)

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

    public static boolean friendlyFire = true; //can players deal damage to other players?

    public static Entity[][] entities; //this array is going to be mostly EMPTY i think
    //lololo hopefully static is OK
    public PImage playerImage;
    public PImage enemyImage;
    public static int count = 0;

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
        frameRate(10);
        background(169);
        playerImage = loadImage("mika.jpeg");
        enemyImage = loadImage("anya.jpeg");
        playerImage.resize(CELL_SIZE, CELL_SIZE);
        enemyImage.resize(CELL_SIZE, CELL_SIZE); //Is there a way to make this more efficient so resize() occurs automatically? Overloading?

        entities = new Entity[NUM_ROWS][NUM_COLS];
        displayGrid();
        entities[NUM_ROWS/2][NUM_COLS/2] = new Entity(count, NUM_ROWS/2, NUM_COLS/2, playerImage, 3, 3, 1, true);
        entities[NUM_ROWS/2 + 1][NUM_COLS/2] = new Entity(count, NUM_ROWS/2 + 1, NUM_COLS/2, enemyImage, 3, 3, 1, false);
    }

    public void draw() {
        displayGrid();
        removeDeadEntities();
        displayEntities();
        //^ let this update constantly
        //let rest of draw update ONLY when player presses a button (arrow key, attack, wait)

    }

    public void keyPressed() {
        System.out.println("TURN START");
        for (Entity[] es : entities) {
            for (Entity e : es) {
                if (e != null && e.isPlayer()) {
                    if (key == ' ') {
                        e.attack(entities);
                    } else {
                        if (keyCode == UP || key == 'w') {
                            e.setWillMove(1);
                        } else if (keyCode == LEFT || key == 'a') {
                            e.setWillMove(2);
                        } else if (keyCode == DOWN || key == 's') {
                            e.setWillMove(3);
                        } else if (keyCode == RIGHT || key == 'd') {
                            e.setWillMove(4);
                        }
                    }
                } else {
                    if (e != null && !e.isPlayer()) {
                        int whatAction = (int)(Math.random()*3); //0, 1, 2
                        if (whatAction == 0) {
                            int direction = (int)(Math.random()*3)+1;
                            e.move(direction);
                                //PROBLEM: when entitiy moves to the next entity being evaluated to move, it moves twice.
                                //SOLUTION: do a round of WillMove, then a round of Move.
                        } else if (whatAction == 1) {
                            e.attack(entities);
                        }
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
    public static boolean getFriendlyFire() {
        return friendlyFire;
    }
}
public class Enemy extends Entity {
    /*
    Not going to implement until I figure out exactly  how Enemy's implementation differs from player
    (so far, most differences
    —e.g. in movement & attack of player being controlled by keypressed—
    appear to be client-side rather than something that needs to be defined within the class?
    not really sure how this all works though.
     */
    public void move(int direction) {
        //some kind of random decision to stay in place, move up down left right.
        //no wait, actually, make enemy move take a parameter for direction, and put the randomness
        //in main.
    }
    public void attack() {

    }
}

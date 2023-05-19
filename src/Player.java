public class Player extends Entity {
    public Player() {
        super();
    }
    public Player(int x, int y, int row, int col, int maxHP, int currentHP, boolean alive, int attack) {
        //there has to be a better way to construct objects with a billion variables. i hate this
            //maybe assume that currentHP always starts = maxHP, or that alive always starts = true
            //but i feel like that makes it less robust in case in the future you do need to start
            //with a "dead" entity, for example
        super(x, y, row, col, maxHP, currentHP, alive);
    }
    public void move(int direction) { //does this need to take a parameter for user input?
        //super.setX();
        //super.setY();
        //super.setRow();
        //super.setCol();
    }

    public void attack() {
    }
}

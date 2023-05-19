//some import of PImage to display an image
public class Entity {
//    private int x;
//    private int y;
//    private int row; //is both an x and a row necessary?
//    private int col; //is both a y and a col necessary?
    private int maxHP;
    private int currentHP;
    private boolean alive;
    private int attack; //damage dealt to other entities

    private Cell c;

    public Entity() {}

    public Entity(Cell c, int maxHP, int currentHP, boolean alive, int attack) {
//        this.x = x;
//        this.y = y;
//        this.row = row;
//        this.col = col;
        this.c = c;
        this.maxHP = maxHP;
        this.currentHP = currentHP;
        this.alive = alive; //Is it necessary to make alive its own boolean, rather than defining it
                            //according to whether currentHP > 0?
        this.attack = attack;
    }

//    public void setX(int x) {
//        this.x = x;
//    }
//    public void setY(int y) {
//        this.y = y;
//    }
//    public void setRow(int row) {
//        this.row = row;
//    }
//    public void setCol(int col) {
//        this.col = col;
//    }

    public String toString() {
        // IMPLEMENT
        return "";
    }
    public void setMaxHP(int maxHP) {
        System.out.println("Max HP set from " + this.maxHP + " to " + maxHP);
        this.maxHP = maxHP;
    }

    public void setCurrentHP(int currentHP) {
        System.out.println("HP set from " + this.currentHP + " to " + currentHP);
        this.currentHP = currentHP;

    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
    public void setC(Cell c) {
        System.out.println(this + " moved to " + c);
        this.c = c;
    }

//    public int getX() {
//        return x;
//    }
//    public int getY() {
//        return y;
//    }
//    public int getRow() {
//        return row;
//    }
//    public int getCol() {
//        return col;
//    }
    public int getMaxHP() {
        return maxHP;
    }
    public int getCurrentHP() {
        return currentHP;
    }
    public boolean getAlive() {
        return alive;
    }
    public int getAttack() {
        return attack;
    }
    public Cell getC() {
        return c;
    }
    public void move(int direction) {
        //i think define this behavior in Main, and in Entity just make it so that Entity
        // objects move in Direction.
        //It would be ambitious to try and define different "stride lengths" or anything like that.
        //But not a bad idea to leave code flexible enough to implement that, if so desired...
        //new private instance int moveLength?
    }
    public void attack(Cell[][] cells) {
        for (int r = -1; r <= 1; r++) {
            for (int c = -1; c <= 1; c++) {
                Cell cell = cells[getC().getRow()+r][getC().getColumn()+c];
                if (cell.hasEntity()) {
                    cell.getEntity().setCurrentHP(cell.getEntity().getCurrentHP()-getAttack());
                    //oops this doesn't work anymore
                }
            }
        }
        /*
        oooohh this is going to be tricky. some kind of scan of surrounding cells
        & whether they "haveEntity"? and for each cell that hasEntity, remove a
        certain amount of currentHP according to "this"'s attack value?

        Maybe consider attack pattern as well; are all attacks straight in front? a radius around? does it
        vary based on the class, or on the object?
            Definitely a later feature rather than a now thing, though.
         */
    }
}

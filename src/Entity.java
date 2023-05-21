//array of cells & array of entities, each entity has a row & col, each cell has a boolean
//move: update row and column, then have a display method
    //display—

//some import of PImage to display an image
public class Entity {
//    private int x;
//    private int y;
//    private int row; //is both an x and a row necessary?
//    private int col; //is both a y and a col necessary?
    public static int count;
    private int maxHP;
    private int currentHP;
    private int attack; //damage dealt to other entities

    private int row;
    private int col;

    public Entity() {
        count++;
    }

    public Entity(int row, int col, int maxHP, int currentHP, int attack) {
        count++;
        this.row = row;
        this.col = col;
        this.maxHP = maxHP;
        this.currentHP = currentHP; //maybe get rid of this if everyting starts at full HP
        this.attack = attack;
    }

    public void move(int direction) {
        //calculate target row & col based on direction & move rules
        //if target location in entities is empty,
            //entities[target row][target col] = this;
            //entities[this.row][this.col] = null;
            //setRow(target row);
            //setCol(target col);
        //else
            //do a lil animation?

        //It would be ambitious to try and define different "stride lengths" or anything like that.
        //But not a bad idea to leave code flexible enough to implement that, if so desired...
        //new private instance int moveLength?
    }
    public void attack(Entity[][] entities) {
        //do a lil animation (sword swing, e.g.)
        for (int r = -1; r <= 1; r++) {
            for (int c = -1; c <= 1; c++) {
                Entity target = entities[r+this.getRow()][c+this.getCol()];
                //this should be ok b/c of object aliasing—just pointing to the Entity in entities, not a new Entity?
                if (target != null) {
                    //do a lil animation (stagger, e.g.)
                    target.setCurrentHP(target.getCurrentHP()-this.getAttack());
                }
            }
        }
        /*
        Maybe consider attack pattern as well; are all attacks straight in front? a radius around? does it
        vary based on the class, or on the object?
            Definitely a later feature rather than a now thing, though.
         */
    }

    public void setRow(int row) {
        this.row = row;
    }
    public void setCol(int col) {
        this.col = col;
    }

    public String toString() {
        // IMPLEMENT
        return "entity" + count;
    }
    public void setMaxHP(int maxHP) {
        System.out.println("Max HP set from " + this.maxHP + " to " + maxHP);
        this.maxHP = maxHP;
    }

    public void setCurrentHP(int currentHP) {
        System.out.println("HP set from " + this.currentHP + " to " + currentHP);
        this.currentHP = currentHP;

    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public int getMaxHP() {
        return maxHP;
    }
    public int getCurrentHP() {
        return currentHP;
    }
    public int getAttack() {
        return attack;
    }
}

public class Entity {
    public static int count; //this is kind of dumb but i'm keeping it for convenience for now
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
                Entity target = entities[r+this.getRow()][c+this.getCol()]; //this should be ok b/c of object aliasing—just pointing to the Entity in entities, not a new Entity?
                if (target != null) {
                    //do a lil animation (stagger, e.g.)
                    target.setCurrentHP(target.getCurrentHP()-this.getAttack());
                }
            }
        }
        //Consider attack patterns? Maybe consider attack pattern as well; are all attacks straight in front? a radius around? does it
    }

    public void display() {
        //some PImage stuff
    }

    public String toString() {
        return "entity " + count + ": I think I am at row " + row + " and col " + col;
    }

    public void setRow(int row) {
        System.out.println("row changed from " + this.row + " to " + row);
        this.row = row;
    }
    public void setCol(int col) {
        System.out.println("col changed from " + this.col + " to " + col);
        this.col = col;
    }
    public void setMaxHP(int maxHP) {
        System.out.println("maxHP changed from " + this.maxHP + " to " + maxHP);
        this.maxHP = maxHP;
    }

    public void setCurrentHP(int currentHP) {
        System.out.println("currentHP changed from " + this.currentHP + " to " + currentHP);
        this.currentHP = currentHP;

    }

    public void setAttack(int attack) {
        System.out.println("attack changed from " + this.attack + " to " + attack);
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

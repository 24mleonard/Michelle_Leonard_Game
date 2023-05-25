import processing.core.PImage;

public class Entity {
    public static int count; //this is kind of dumb but i'm keeping it for convenience for now
    private int maxHP;
    private int currentHP;
    private int attack; //damage dealt to other entities
    private int row;
    private int col;
    private int stride;

    private boolean isPlayer; //determines whether target moves autonomously or according to user input
    private int willMove; //1 will move up, 2 will move left, 3 will move down, 4 will move right, 0 will not move

    private PImage image;

    public Entity() {
        count++;
    }

    public Entity(int row, int col, int maxHP, int currentHP, int attack, PImage image, boolean isPlayer) {
        count++;
        this.row = row;
        this.col = col;
        this.maxHP = maxHP;
        this.currentHP = currentHP; //maybe get rid of this if everyting starts at full HP
        this.attack = attack;
        this.image = image;
        this.isPlayer = isPlayer;
        stride = 1;
        willMove = 0;
    }

    public void move(int direction) { //W 1, A 2, S 3, D 4
        //calculate target location based on direction & move rules
        int targetRow = this.getRow();
        int targetCol = this.getCol();
        if (direction == 1) { //up
            targetRow -= stride;
        } else if (direction == 2) { //left
            targetCol = targetCol - stride;
        } else if (direction == 3) { //down
            targetRow = targetRow + stride;
        } else if (direction == 4) { //right
            targetCol += stride;
        }
        //move this to target location
        if (targetRow <= -1 || targetRow >= Main.entities.length
                || targetCol <= -1 || targetCol >= Main.entities[targetRow].length) {
            //animation?
            System.out.println(this + " failed to move to " + targetRow + ", " + targetCol + " because it is out of bounds");
        } else if (Main.entities[targetRow][targetCol] != null) {
            //animation?
            System.out.println(this + " failed to move to " + targetRow + ", " + targetCol + " because there is already an entity there");
        } else {
           Main.entities[targetRow][targetCol] = this;
            Main.entities[this.getRow()][this.getCol()] = null;
            this.setRow(targetRow);
            this.setCol(targetCol);
            System.out.println(this + " moved to " + targetRow + ", " + targetCol);
        }
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
        Main.app.image(image, col*Main.CELL_SIZE, row*Main.CELL_SIZE);
    }

    public String toString() {
        return "entity " + count;
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

    public void setIsPlayer(boolean isPlayer) { //I don't know why this would ever be used.
        System.out.println(this + "is player: " + isPlayer);
        this.isPlayer = isPlayer;
    }

    public void setWillMove(int willMove) {
        this.willMove = willMove;
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
    public boolean isPlayer() {return isPlayer;}

    public int getWillMove() {
        return willMove;
    }
}

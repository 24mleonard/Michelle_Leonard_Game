import processing.core.PImage;

import processing.core.PImage;

public class Creature extends Entity {
    private int maxHP;
    private int currentHP;
    private int attack; //damage dealt to other entities
    private int stride;
    private boolean isPlayer; //determines whether target moves autonomously or according to user input
    private int willMove; //1 will move up, 2 will move left, 3 will move down, 4 will move right, 0 will not move

    public Creature() {
        count++;
    }

    public Creature(int row, int col, PImage image, int maxHP, int currentHP, int attack, boolean isPlayer) {
        super(row, col, image);
        this.maxHP = maxHP;
        this.currentHP = currentHP; //maybe get rid of this if everything starts at full HP
        this.attack = attack;
        this.isPlayer = isPlayer;
        stride = 1;
        willMove = 0;
        setIsCreature(true); //this is kind of scuffed
    }

    public void move(int direction) { //W 1, A 2, S 3, D 4
        //calculate target location based on direction & move rules
        int targetRow = this.getRow();
        int targetCol = this.getCol();
        if (direction == 1) { //up
            targetRow -= stride;
        } else if (direction == 2) { //left
            targetCol -= stride;
        } else if (direction == 3) { //down
            targetRow += stride;
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
                if (target != null && ) {
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
        return "Entity Creature " + count;
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


import processing.core.PImage;

public class Entity {
    private int count;
    private int row;
    private int col;
    private PImage image;
    private int maxHP;
    private int currentHP;
    private int attack; //damage dealt to other entities
    private int stride;
    private boolean isPlayer; //determines whether target moves autonomously or according to user input
    private int willMove; //1 will move up, 2 will move left, 3 will move down, 4 will move right, 0 will not move

    public Entity() {
        Main.count++;
        this.count = count;
    }

    public Entity(int count, int row, int col, PImage image, int maxHP, int currentHP, int attack, boolean isPlayer) {
        Main.count++;
        this.count = count;
        this.row = row;
        this.col = col;
        this.image = image;
        this.maxHP = maxHP;
        this.currentHP = currentHP; //maybe get rid of this if everything starts at full HP
        this.attack = attack;
        this.isPlayer = isPlayer;
        stride = 1;
        willMove = 0;
    }

    public void display() {
        //some PImage stuff
        Main.app.image(image, col*Main.CELL_SIZE, row*Main.CELL_SIZE);
        Main.app.textSize(30);
        Main.app.fill(100, 255, 100);
        Main.app.text(currentHP, col*Main.CELL_SIZE+Main.CELL_SIZE/2, row*Main.CELL_SIZE+Main.CELL_SIZE/2);
        Main.app.fill(255);
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
        Main.app.fill(169);
        Main.app.circle(this.col*Main.CELL_SIZE+Main.CELL_SIZE/2, this.row*Main.CELL_SIZE+Main.CELL_SIZE/2, (float) (Main.CELL_SIZE*2.5));
        Main.app.fill(255);
        for (int r = -1; r <= 1; r++) {
            for (int c = -1; c <= 1; c++) {
                int targetRow = r + this.getRow();
                int targetCol = c + this.getCol();
                if (targetRow < 0) {
                    targetRow = 0;
                }
                if (targetRow >= entities.length) {
                    targetRow = entities.length - 1;
                }
                if (targetCol < 0) {
                    targetCol = 0;
                }
                if (targetCol >= entities[targetRow].length) {
                    targetCol = entities[targetRow].length-1;
                }
                Entity target = entities[targetRow][targetCol]; //this should be ok b/c of object aliasing—just pointing to the Entity in entities, not a new Entity?
                if (target != null && target != this) {
                    //do a lil animation (stagger, e.g.)
                    if (Main.getFriendlyFire() || target.isPlayer() != this.isPlayer()) { //if friendly fire is on, OR this is a player and that is not, OR that is a player and this is not
                        target.setCurrentHP(target.getCurrentHP() - this.getAttack());
                    }
                }
            }
        }
        //Consider attack patterns? Maybe consider attack pattern as well; are all attacks straight in front? a radius around? does it
    }

    public String toString() {
        return "entity" + count;
    }

    public void setRow(int row) {
        System.out.println(this + " row " + this.row + "->" + row);
        this.row = row;
    }
    public void setCol(int col) {
        System.out.println(this + " col " + this.col + "->" + col);
        this.col = col;
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public PImage getImage() {
        return image;
    }

    public void setMaxHP(int maxHP) {
        System.out.println(this + " maxHP changed from " + this.maxHP + " to " + maxHP);
        this.maxHP = maxHP;
    }

    public void setCurrentHP(int currentHP) {
        System.out.println(this + " currentHP changed from " + this.currentHP + " to " + currentHP);
        this.currentHP = currentHP;
    }

    public void setAttack(int attack) {
        System.out.println(this + " attack changed from " + this.attack + " to " + attack);
        this.attack = attack;
    }

    public void setIsPlayer(boolean isPlayer) { //I don't know why this would ever be used.
        System.out.println(this + " is player: " + isPlayer);
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

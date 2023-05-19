public abstract class Entity {
    private int x;
    private int y;
    private int row; //is both an x and a row necessary?
    private int col; //is both a y and a col necessary?
    private int maxHP;
    private int currentHP;
    private boolean alive;
    private int attack; //damage dealt to other entities

    public Entity() {}

    public Entity(int x, int y, int row, int col, int maxHP, int currentHP, boolean alive) {
        this.x = x;
        this.y = y;
        this.row = row;
        this.col = col;
        this.maxHP = maxHP;
        this.currentHP = currentHP;
        this.alive = alive; //Is it necessary to make alive its own boolean, rather than defining it
                            //according to whether currentHP > 0?
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public void setCol(int col) {
        this.col = col;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
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
    public boolean getAlive() {
        return alive;
    }
    public int getAttack() {
        return attack;
    }
    abstract void move(int direction);
        //i think define this behavior in Main, and in Entity just make it so that Entity
        // objects move in Direction.
        //It would be ambitious to try and define different "stride lengths" or anything like that.
            //But not a bad idea to leave code flexible enough to implement that, if so desired...
            //new private instance int moveLength?
    abstract void attack();
        /*
        oooohh this is going to be tricky. some kind of scan of surrounding cells
        & whether they "haveEntity"? and for each cell that hasEntity, remove a
        certain amount of currentHP according to "this"'s attack value?

        Maybe consider attack pattern as well; are all attacks straight in front? a radius around? does it
        vary based on the class, or on the object?
            Definitely a later feature rather than a now thing, though.
         */
}

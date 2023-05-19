import processing.core.PFont;

public class Cell {
    private int x;
    private int y;
    private int size;
    private int row;
    private int column;
    private boolean hasEntity; //maybe this is bad actually

    public Cell() {
    }

    public Cell(int x, int y, int size, int row, int column) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.row = row;
        this.column = column;
        hasEntity = false;
    }

    public void display() {
        Main.app.rect((float) this.x, (float) this.y, (float) this.size, (float) this.size);
        //if has Entity? idk?
    }

    public String toString() {
        return "Cell[" + this.row + "][" + this.column + "]";
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public int getSize() {
        return this.size;
    }

    public boolean hasEntity() {
        return this.hasEntity;
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

    public void setColumn(int column) {
        this.column = column;
    }

    public void setHasEntity(boolean hasEntity) {
        this.hasEntity = hasEntity;
    }
}
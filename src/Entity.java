import processing.core.PImage;

public class Entity {
    public static int count; //this is kind of dumb but i'm keeping it for convenience for now
    private int row;
    private int col;
    private PImage image;
    private boolean isCreature = false;

    public Entity() {
        count++;
    }

    public Entity(int row, int col, PImage image) {
        count++;
        this.row = row;
        this.col = col;
        this.image = image;
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
    public void setIsCreature(boolean isCreature) {
        this.isCreature = isCreature;
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
    public boolean isCreature() {
        return isCreature;
    }
}

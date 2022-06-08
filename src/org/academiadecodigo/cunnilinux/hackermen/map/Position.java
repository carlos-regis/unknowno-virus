package org.academiadecodigo.cunnilinux.hackermen.map;

import org.academiadecodigo.cunnilinux.hackermen.map.Canvas;

public class Position {

    private int col;
    private int row;

    public Position(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int colToX() {
        return col * Canvas.CELL_SIZE;
    }

    public int rowToY() {
        return row * Canvas.CELL_SIZE;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }
}

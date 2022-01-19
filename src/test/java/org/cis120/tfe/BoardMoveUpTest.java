package org.cis120.tfe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardMoveUpTest {

    @Test
    public void moveUpBoundaryTest() {
        Board b = new Board();
        b.setTile(0, 0, 2);
        b.moveUp();

        assertEquals(2, b.getTile(0, 0).getValue());
    }

    @Test
    public void moveUpFreeCellsTest() {
        Board b = new Board();
        b.setTile(0, 2, 2);
        b.moveUp();

        assertEquals(2, b.getTile(0, 0).getValue());
    }

    @Test
    public void moveUpFreeCellsMultipleTest() {
        Board b = new Board();
        b.setTile(0, 2, 2);
        b.setTile(0, 3, 4);
        b.moveUp();

        assertEquals(2, b.getTile(0, 0).getValue());
        assertEquals(4, b.getTile(0, 1).getValue());
    }

    @Test
    public void moveUpNoFreeCellsTest() {
        Board b = new Board();
        b.setTile(0, 0, 2);
        b.setTile(0, 1, 4);
        b.setTile(0, 2, 8);
        b.setTile(0, 3, 16);
        b.moveUp();

        assertEquals(2, b.getTile(0, 0).getValue());
        assertEquals(4, b.getTile(0, 1).getValue());
        assertEquals(8, b.getTile(0, 2).getValue());
        assertEquals(16, b.getTile(0, 3).getValue());
    }

    @Test
    public void moveUpMultipleColumnsTest() {
        Board b = new Board();
        b.setTile(0, 2, 2);
        b.setTile(1, 3, 2);
        b.setTile(2, 2, 2);
        b.setTile(3, 3, 2);
        b.moveUp();

        assertEquals(2, b.getTile(0, 0).getValue());
        assertEquals(2, b.getTile(1, 0).getValue());
        assertEquals(2, b.getTile(2, 0).getValue());
        assertEquals(2, b.getTile(3, 0).getValue());
    }

    @Test
    public void moveUpAdditionTest() {
        Board b = new Board();
        b.setTile(0, 0, 2);
        b.setTile(0, 1, 2);
        b.moveUp();

        assertEquals(4, b.getTile(0, 0).getValue());
    }

    @Test
    public void moveUpMultipleAdditionTest() {
        Board b = new Board();
        b.setTile(0, 0, 2);
        b.setTile(0, 1, 2);
        b.setTile(0, 2, 2);
        b.setTile(0, 3, 2);
        b.moveUp();

        assertEquals(4, b.getTile(0, 0).getValue());
        assertEquals(4, b.getTile(0, 1).getValue());
    }

    @Test
    public void moveUpMultipleAdditionMultipleColumnsTest() {
        Board b = new Board();
        b.setTile(0, 0, 2);
        b.setTile(0, 1, 2);
        b.setTile(0, 2, 2);
        b.setTile(0, 3, 2);
        b.setTile(1, 0, 2);
        b.setTile(1, 1, 2);
        b.setTile(1, 2, 2);
        b.setTile(1, 3, 2);
        b.moveUp();

        assertEquals(4, b.getTile(0, 0).getValue());
        assertEquals(4, b.getTile(0, 1).getValue());
        assertEquals(4, b.getTile(1, 0).getValue());
        assertEquals(4, b.getTile(1, 1).getValue());
        b.moveUp();

        assertEquals(8, b.getTile(0, 0).getValue());
        assertEquals(8, b.getTile(1, 0).getValue());
    }

    @Test
    public void moveUpAdditionNoAdditionTest() {
        Board b = new Board();
        b.setTile(0, 0, 2);
        b.setTile(0, 1, 2);
        b.setTile(0, 2, 4);
        b.setTile(0, 3, 8);
        b.moveUp();

        assertEquals(4, b.getTile(0, 0).getValue());
        assertEquals(4, b.getTile(0, 1).getValue());
        assertEquals(8, b.getTile(0, 2).getValue());
        b.moveUp();

        assertEquals(8, b.getTile(0, 0).getValue());
        assertEquals(8, b.getTile(0, 1).getValue());
    }

    @Test
    public void moveUpNoAdditionTest() {
        Board b = new Board();
        b.setTile(0, 2, 2);
        b.setTile(0, 3, 4);
        b.moveUp();

        assertEquals(2, b.getTile(0, 0).getValue());
        assertEquals(4, b.getTile(0, 1).getValue());
    }
}

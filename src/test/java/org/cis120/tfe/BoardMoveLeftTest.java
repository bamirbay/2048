package org.cis120.tfe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardMoveLeftTest {

    @Test
    public void moveLeftBoundaryTest() {
        Board b = new Board();
        b.setTile(0, 0, 2);
        b.moveLeft();

        assertEquals(2, b.getTile(0, 0).getValue());
    }

    @Test
    public void moveLeftFreeCellsTest() {
        Board b = new Board();
        b.setTile(2, 0, 2);
        b.moveLeft();

        assertEquals(2, b.getTile(0, 0).getValue());
    }

    @Test
    public void moveLeftFreeCellsMultipleTest() {
        Board b = new Board();
        b.setTile(2, 0, 2);
        b.setTile(3, 0, 4);
        b.moveLeft();

        assertEquals(2, b.getTile(0, 0).getValue());
        assertEquals(4, b.getTile(1, 0).getValue());
    }

    @Test
    public void moveLeftNoFreeCellsTest() {
        Board b = new Board();
        b.setTile(0, 0, 2);
        b.setTile(1, 0, 4);
        b.setTile(2, 0, 8);
        b.setTile(3, 0, 16);
        b.moveLeft();

        assertEquals(2, b.getTile(0, 0).getValue());
        assertEquals(4, b.getTile(1, 0).getValue());
        assertEquals(8, b.getTile(2, 0).getValue());
        assertEquals(16, b.getTile(3, 0).getValue());
    }

    @Test
    public void moveLeftMultipleRowsTest() {
        Board b = new Board();
        b.setTile(2, 0, 2);
        b.setTile(3, 1, 2);
        b.setTile(2, 2, 2);
        b.setTile(3, 3, 2);

        b.moveLeft();

        assertEquals(2, b.getTile(0, 0).getValue());
        assertEquals(2, b.getTile(0, 1).getValue());
        assertEquals(2, b.getTile(0, 2).getValue());
        assertEquals(2, b.getTile(0, 3).getValue());
    }

    @Test
    public void moveLeftAdditionTest() {
        Board b = new Board();
        b.setTile(0, 0, 2);
        b.setTile(1, 0, 2);
        b.moveLeft();

        assertEquals(4, b.getTile(0, 0).getValue());
    }

    @Test
    public void moveLeftMultipleAdditionTest() {
        Board b = new Board();
        b.setTile(0, 0, 2);
        b.setTile(1, 0, 2);
        b.setTile(2, 0, 2);
        b.setTile(3, 0, 2);
        b.moveLeft();

        assertEquals(4, b.getTile(0, 0).getValue());
        assertEquals(4, b.getTile(1, 0).getValue());
    }

    @Test
    public void moveLeftMultipleAdditionMultipleRowsTest() {
        Board b = new Board();
        b.setTile(0, 0, 2);
        b.setTile(1, 0, 2);
        b.setTile(2, 0, 2);
        b.setTile(3, 0, 2);
        b.setTile(0, 1, 2);
        b.setTile(1, 1, 2);
        b.setTile(2, 1, 2);
        b.setTile(3, 1, 2);
        b.moveLeft();

        assertEquals(4, b.getTile(0, 0).getValue());
        assertEquals(4, b.getTile(1, 0).getValue());
        assertEquals(4, b.getTile(0, 1).getValue());
        assertEquals(4, b.getTile(1, 1).getValue());

        b.moveLeft();
        assertEquals(8, b.getTile(0, 0).getValue());
        assertEquals(8, b.getTile(0, 1).getValue());
    }

    @Test
    public void moveLeftAdditionNoAdditionTest() {
        Board b = new Board();
        b.setTile(0, 0, 2);
        b.setTile(1, 0, 2);
        b.setTile(2, 0, 4);
        b.setTile(3, 0, 8);

        b.moveLeft();

        assertEquals(4, b.getTile(0, 0).getValue());
        assertEquals(4, b.getTile(1, 0).getValue());
        assertEquals(8, b.getTile(2, 0).getValue());

        b.moveLeft();
        assertEquals(8, b.getTile(0, 0).getValue());
        assertEquals(8, b.getTile(1, 0).getValue());
    }

    @Test
    public void moveLeftNoAdditionTest() {
        Board b = new Board();
        b.setTile(2, 0, 2);
        b.setTile(3, 0, 4);
        b.moveLeft();

        assertEquals(2, b.getTile(0, 0).getValue());
        assertEquals(4, b.getTile(1, 0).getValue());
    }
}

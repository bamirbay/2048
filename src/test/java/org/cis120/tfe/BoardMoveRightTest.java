package org.cis120.tfe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardMoveRightTest {

    @Test
    public void moveRightBoundaryTest() {
        Board b = new Board();
        b.setTile(3, 3, 2);
        b.moveRight();

        assertEquals(2, b.getTile(3, 3).getValue());
    }

    @Test
    public void moveRightFreeCellsTest() {
        Board b = new Board();
        b.setTile(2, 0, 2);
        b.moveRight();

        assertEquals(2, b.getTile(3, 0).getValue());
    }

    @Test
    public void moveRightFreeCellsMultipleTest() {
        Board b = new Board();
        b.setTile(0, 0, 2);
        b.setTile(1, 0, 4);

        b.moveRight();

        assertEquals(2, b.getTile(2, 0).getValue());
        assertEquals(4, b.getTile(3, 0).getValue());
    }

    @Test
    public void moveRightNoFreeCellsTest() {
        Board b = new Board();
        b.setTile(0, 0, 2);
        b.setTile(1, 0, 4);
        b.setTile(2, 0, 8);
        b.setTile(3, 0, 16);

        b.moveRight();

        assertEquals(2, b.getTile(0, 0).getValue());
        assertEquals(4, b.getTile(1, 0).getValue());
        assertEquals(8, b.getTile(2, 0).getValue());
        assertEquals(16, b.getTile(3, 0).getValue());
    }

    @Test
    public void moveRightMultipleRowsTest() {
        Board b = new Board();
        b.setTile(1, 0, 2);
        b.setTile(0, 1, 2);
        b.setTile(1, 2, 2);
        b.setTile(0, 3, 2);

        b.moveRight();

        assertEquals(2, b.getTile(3, 0).getValue());
        assertEquals(2, b.getTile(3, 1).getValue());
        assertEquals(2, b.getTile(3, 2).getValue());
        assertEquals(2, b.getTile(3, 3).getValue());
    }

    @Test
    public void moveRightAdditionTest() {
        Board b = new Board();
        b.setTile(2, 0, 2);
        b.setTile(3, 0, 2);
        b.moveRight();

        assertEquals(4, b.getTile(3, 0).getValue());
    }

    @Test
    public void moveRightMultipleAdditionTest() {
        Board b = new Board();
        b.setTile(0, 0, 2);
        b.setTile(1, 0, 2);
        b.setTile(2, 0, 2);
        b.setTile(3, 0, 2);
        b.moveRight();

        assertEquals(4, b.getTile(2, 0).getValue());
        assertEquals(4, b.getTile(3, 0).getValue());
    }

    @Test
    public void moveRightMultipleAdditionMultipleRowsTest() {
        Board b = new Board();
        b.setTile(0, 0, 2);
        b.setTile(1, 0, 2);
        b.setTile(2, 0, 2);
        b.setTile(3, 0, 2);
        b.setTile(0, 1, 2);
        b.setTile(1, 1, 2);
        b.setTile(2, 1, 2);
        b.setTile(3, 1, 2);

        b.moveRight();

        assertEquals(4, b.getTile(3, 0).getValue());
        assertEquals(4, b.getTile(2, 0).getValue());
        assertEquals(4, b.getTile(3, 1).getValue());
        assertEquals(4, b.getTile(2, 1).getValue());

        b.moveRight();

        assertEquals(8, b.getTile(3, 0).getValue());
        assertEquals(8, b.getTile(3, 1).getValue());
    }

    @Test
    public void moveRightAdditionNoAdditionTest() {
        Board b = new Board();
        b.setTile(0, 0, 2);
        b.setTile(1, 0, 2);
        b.setTile(2, 0, 4);
        b.setTile(3, 0, 2);

        b.moveRight();

        assertEquals(4, b.getTile(1, 0).getValue());
        assertEquals(4, b.getTile(2, 0).getValue());
        assertEquals(2, b.getTile(3, 0).getValue());

        b.moveRight();
        assertEquals(8, b.getTile(2, 0).getValue());
        assertEquals(2, b.getTile(3, 0).getValue());
    }

    @Test
    public void moveRightNoAdditionTest() {
        Board b = new Board();
        b.setTile(0, 0, 2);
        b.setTile(1, 0, 4);

        b.moveRight();

        assertEquals(2, b.getTile(2, 0).getValue());
        assertEquals(4, b.getTile(3, 0).getValue());
    }
}

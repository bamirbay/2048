package org.cis120.tfe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BoardMoveDownTest {

    @Test
    public void moveDownBoundaryTest() {
        Board b = new Board();
        b.setTile(3, 3, 2);
        b.moveDown();

        assertEquals(2, b.getTile(3, 3).getValue());
    }

    @Test
    public void moveDownFreeCellsTest() {
        Board b = new Board();
        b.setTile(0, 2, 2);
        b.moveDown();

        assertEquals(2, b.getTile(0, 3).getValue());
    }

    @Test
    public void moveDownFreeCellsMultipleTest() {
        Board b = new Board();
        b.setTile(0, 0, 2);
        b.setTile(0, 1, 4);
        b.moveDown();

        assertEquals(2, b.getTile(0, 2).getValue());
        assertEquals(4, b.getTile(0, 3).getValue());
    }

    @Test
    public void moveDownNoFreeCellsTest() {
        Board b = new Board();
        b.setTile(0, 0, 2);
        b.setTile(0, 1, 4);
        b.setTile(0, 2, 8);
        b.setTile(0, 3, 16);
        b.moveDown();

        assertEquals(2, b.getTile(0, 0).getValue());
        assertEquals(4, b.getTile(0, 1).getValue());
        assertEquals(8, b.getTile(0, 2).getValue());
        assertEquals(16, b.getTile(0, 3).getValue());
    }

    @Test
    public void moveDownMultipleColumnsTest() {
        Board b = new Board();
        b.setTile(0, 1, 2);
        b.setTile(1, 0, 2);
        b.setTile(2, 1, 2);
        b.setTile(3, 0, 2);
        b.moveDown();

        assertEquals(2, b.getTile(0, 3).getValue());
        assertEquals(2, b.getTile(1, 3).getValue());
        assertEquals(2, b.getTile(2, 3).getValue());
        assertEquals(2, b.getTile(3, 3).getValue());
    }

    @Test
    public void moveDownAdditionTest() {
        Board b = new Board();
        b.setTile(0, 2, 2);
        b.setTile(0, 3, 2);
        b.moveDown();

        assertEquals(4, b.getTile(0, 3).getValue());
    }

    @Test
    public void moveDownMultipleAdditionTest() {
        Board b = new Board();
        b.setTile(0, 0, 2);
        b.setTile(0, 1, 2);
        b.setTile(0, 2, 2);
        b.setTile(0, 3, 2);
        b.moveDown();

        assertEquals(4, b.getTile(0, 2).getValue());
        assertEquals(4, b.getTile(0, 3).getValue());
    }

    @Test
    public void moveDownMultipleAdditionMultipleColumnsTest() {
        Board b = new Board();
        b.setTile(0, 0, 2);
        b.setTile(0, 1, 2);
        b.setTile(0, 2, 2);
        b.setTile(0, 3, 2);
        b.setTile(1, 0, 2);
        b.setTile(1, 1, 2);
        b.setTile(1, 2, 2);
        b.setTile(1, 3, 2);
        b.moveDown();

        assertEquals(4, b.getTile(0, 3).getValue());
        assertEquals(4, b.getTile(0, 2).getValue());
        assertEquals(4, b.getTile(1, 3).getValue());
        assertEquals(4, b.getTile(1, 2).getValue());

        b.moveDown();

        assertEquals(8, b.getTile(0, 3).getValue());
        assertEquals(8, b.getTile(1, 3).getValue());
    }

    @Test
    public void moveDownAdditionNoAdditionTest() {
        Board b = new Board();
        b.setTile(0, 0, 2);
        b.setTile(0, 1, 2);
        b.setTile(0, 2, 4);
        b.setTile(0, 3, 2);
        b.moveDown();

        assertEquals(4, b.getTile(0, 1).getValue());
        assertEquals(4, b.getTile(0, 2).getValue());
        assertEquals(2, b.getTile(0, 3).getValue());

        b.moveDown();
        assertEquals(8, b.getTile(0, 2).getValue());
        assertEquals(2, b.getTile(0, 3).getValue());
    }

    @Test
    public void moveDownNoAdditionTest() {
        Board b = new Board();
        b.setTile(0, 0, 2);
        b.setTile(0, 1, 4);
        b.moveDown();

        assertEquals(2, b.getTile(0, 2).getValue());
        assertEquals(4, b.getTile(0, 3).getValue());
    }
}

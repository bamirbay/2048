package org.cis120.tfe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BoardGameOverTest {

    @Test
    public void boardGameOverLostTest() {
        Board b = new Board();
        b.setTile(0, 0, 2);
        b.setTile(1, 0, 4);
        b.setTile(2, 0, 8);
        b.setTile(3, 0, 16);
        b.setTile(0, 1, 4);
        b.setTile(1, 1, 8);
        b.setTile(2, 1, 16);
        b.setTile(3, 1, 32);
        b.setTile(0, 2, 8);
        b.setTile(1, 2, 16);
        b.setTile(2, 2, 32);
        b.setTile(3, 2, 64);
        b.setTile(0, 3, 16);
        b.setTile(1, 3, 32);
        b.setTile(2, 3, 64);
        b.setTile(3, 3, 128);

        b.printGrid();
        assertEquals(GameStatus.LOSE, b.gameOver());
    }

    @Test
    public void boardGameNotOverTest() {
        Board b = new Board();
        b.setTile(0, 0, 2);
        b.setTile(1, 0, 4);
        b.setTile(2, 0, 8);
        b.setTile(3, 0, 16);
        b.setTile(0, 1, 4);
        b.setTile(1, 1, 8);
        b.setTile(2, 1, 16);
        b.setTile(3, 1, 32);
        b.setTile(0, 2, 8);
        b.setTile(1, 2, 16);
        b.setTile(2, 2, 32);
        b.setTile(3, 2, 64);
        b.setTile(0, 3, 16);
        b.setTile(1, 3, 32);
        b.setTile(2, 3, 64);

        b.printGrid();
        b.moveRight();
        b.printGrid();
        assertEquals(GameStatus.CONTINUE, b.gameOver());
    }

    @Test
    public void boardGameOverWonTest() {
        Board b = new Board();
        b.setTile(0, 0, 1024);
        b.setTile(1, 0, 1024);
        b.setTile(2, 0, 8);
        b.setTile(3, 0, 16);
        b.setTile(0, 1, 4);
        b.setTile(1, 1, 8);
        b.setTile(2, 1, 16);
        b.setTile(3, 1, 32);
        b.setTile(0, 2, 8);
        b.setTile(1, 2, 16);
        b.setTile(2, 2, 32);
        b.setTile(3, 2, 64);
        b.setTile(0, 3, 16);
        b.setTile(1, 3, 32);
        b.setTile(2, 3, 64);

        b.printGrid();
        assertEquals(GameStatus.CONTINUE, b.gameOver());
        b.moveLeft();
        b.printGrid();
        assertEquals(GameStatus.WIN, b.gameOver());
    }

}

package org.cis120.tfe;

import java.awt.*;

public class TwentyFortyEight {
    private Board board;
    private GameStatus gameStatus;

    public TwentyFortyEight() {
        reset();
    }

    public TwentyFortyEight(Tile[][] newGrid, int newScore) {
        this.board = new Board(newGrid, newScore);
        this.gameStatus = GameStatus.CONTINUE;
    }

    public void reset() {
        this.board = new Board();
        this.gameStatus = GameStatus.CONTINUE;
    }

    public void reset(Tile[][] newGrid, int newScore) {
        this.board = new Board(newGrid, newScore);
        this.gameStatus = GameStatus.CONTINUE;
    }

    public Board getBoard() {
        return board;
    }

    public void draw(Graphics g) {
        board.draw(g);
    }

    public void playMove(Direction dir) {
        if (dir == Direction.UP) {
            board.moveUp();
        } else if (dir == Direction.LEFT) {
            board.moveLeft();
        } else if (dir == Direction.DOWN) {
            board.moveDown();
        } else if (dir == Direction.RIGHT) {
            board.moveRight();
        }
    }

    public GameStatus checkGameStatus() {
        gameStatus = board.gameOver();
        return gameStatus;
    }

    public void printGameState() {
        board.printGrid();
    }

    public static void main(String[] args) {
        TwentyFortyEight t = new TwentyFortyEight();
        t.printGameState();
        t.playMove(Direction.UP);
        t.printGameState();
        t.playMove(Direction.LEFT);
        t.printGameState();
    }
}

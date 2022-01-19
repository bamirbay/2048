package org.cis120.tfe;

import java.awt.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;

public class Board {
    private Tile[][] grid;
    private int score;

    private static final int BOARD_WIDTH = 500;
    private static final int BOARD_HEIGHT = 500;
    private static final int TILE_DIMS_X = BOARD_WIDTH / 4;
    private static final int TILE_DIMS_Y = BOARD_HEIGHT / 4;

    BufferedImage img_2 = null;
    BufferedImage img_4 = null;
    BufferedImage img_8 = null;
    BufferedImage img_16 = null;
    BufferedImage img_32 = null;
    BufferedImage img_64 = null;
    BufferedImage img_128 = null;
    BufferedImage img_256 = null;
    BufferedImage img_512 = null;
    BufferedImage img_1024 = null;
    BufferedImage img_2048 = null;
    BufferedImage img_grid = null;

    public Board() {
        this.grid = new Tile[4][4];
        this.score = 0;
        this.spawn(false);
    }

    public Board(Tile[][] newGrid, int newScore) {
        this.grid = newGrid;
        this.score = newScore;
        this.spawn(true);
    }

    /**
     * A copy constructor. Creates a new object with the same fields.
     * 
     * @param b - board that is meant to be copied
     */
    public Board(Board b) {
        this.grid = b.getGrid();
        this.score = b.getScore();
        this.img_2 = b.getImg_2();
        this.img_4 = b.getImg_4();
        this.img_8 = b.getImg_8();
        this.img_16 = b.getImg_16();
        this.img_32 = b.getImg_32();
        this.img_64 = b.getImg_64();
        this.img_128 = b.getImg_128();
        this.img_256 = b.getImg_256();
        this.img_512 = b.getImg_512();
        this.img_1024 = b.getImg_1024();
        this.img_2048 = b.getImg_2048();
        this.img_grid = b.getImg_grid();
        this.spawn(false);
    }

    public Tile[][] getGrid() {
        return grid.clone();
    }

    public void setGrid(Tile[][] grid) {
        this.grid = grid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Board copy() {
        return new Board(this);
    }

    public Tile getTile(int x, int y) {
        if (grid[y][x] == null) {
            return null;
        }
        return grid[y][x].copy();
    }

    public void setTile(int x, int y, int value) {
        grid[y][x] = new Tile(value);
    }

    public void moveLeft() {
        Tile[][] prevGrid = grid.clone();
        slide(Direction.LEFT);
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                if (grid[y][x] == null)
                    continue;
                if (grid[y][x].equals(grid[y][x + 1])) {
                    grid[y][x].setValue();
                    this.score += grid[y][x].getValue();
                    grid[y][x + 1] = null;
                }
            }
        }
        slide(Direction.LEFT);
        spawn(Arrays.deepEquals(prevGrid, grid));
    }

    public void moveRight() {
        Tile[][] prevGrid = grid.clone();
        slide(Direction.RIGHT);
        for (int y = 3; y > -1; y--) {
            for (int x = 3; x > 0; x--) {
                if (grid[y][x] == null)
                    continue;
                if (grid[y][x].equals(grid[y][x - 1])) {
                    grid[y][x].setValue();
                    this.score += grid[y][x].getValue();
                    grid[y][x - 1] = null;
                }
            }
        }
        slide(Direction.RIGHT);
        spawn(Arrays.deepEquals(prevGrid, grid));
    }

    public void moveUp() {
        Tile[][] prevGrid = grid.clone();
        slide(Direction.UP);
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 3; y++) {
                if (grid[y][x] == null)
                    continue;
                if (grid[y][x].equals(grid[y + 1][x])) {
                    grid[y][x].setValue();
                    this.score += grid[y][x].getValue();
                    grid[y + 1][x] = null;
                }
            }
        }
        slide(Direction.UP);
        spawn(Arrays.deepEquals(prevGrid, grid));
    }

    public void moveDown() {
        Tile[][] prevGrid = grid.clone();
        slide(Direction.DOWN);
        for (int x = 3; x > -1; x--) {
            for (int y = 3; y > 0; y--) {
                if (grid[y][x] == null)
                    continue;
                if (grid[y][x].equals(grid[y - 1][x])) {
                    grid[y][x].setValue();
                    this.score += grid[y][x].getValue();
                    grid[y - 1][x] = null;
                }
            }
        }
        slide(Direction.DOWN);
        spawn(Arrays.deepEquals(prevGrid, grid));
    }

    private void slide(Direction dir) {
        Tile[][] tempGrid = new Tile[4][4];

        if (dir == Direction.LEFT) {
            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < 4; x++) {
                    int finalX = x;
                    Tile tempTile = grid[y][x];
                    if (grid[y][x] != null) {
                        while (finalX > 0 && tempGrid[y][finalX - 1] == null) {
                            finalX--;
                        }
                    }
                    tempGrid[y][finalX] = tempTile;
                }
            }
        } else if (dir == Direction.RIGHT) {
            for (int y = 3; y > -1; y--) {
                for (int x = 3; x > -1; x--) {
                    int finalX = x;
                    Tile tempTile = grid[y][x];
                    if (grid[y][x] != null) {
                        while (finalX < 3 && tempGrid[y][finalX + 1] == null) {
                            finalX++;
                        }
                    }
                    tempGrid[y][finalX] = tempTile;
                }
            }
        } else if (dir == Direction.UP) {
            for (int x = 0; x < 4; x++) {
                for (int y = 0; y < 4; y++) {
                    int finalY = y;
                    Tile tempTile = grid[y][x];
                    if (grid[y][x] != null) {
                        while (finalY > 0 && tempGrid[finalY - 1][x] == null) {
                            finalY--;
                        }
                    }
                    tempGrid[finalY][x] = tempTile;
                }
            }
        } else if (dir == Direction.DOWN) {
            for (int x = 3; x > -1; x--) {
                for (int y = 3; y > -1; y--) {
                    int finalY = y;
                    Tile tempTile = grid[y][x];
                    if (grid[y][x] != null) {
                        while (finalY < 3 && tempGrid[finalY + 1][x] == null) {
                            finalY++;
                        }
                    }
                    tempGrid[finalY][x] = tempTile;
                }
            }
        }
        this.setGrid(tempGrid);
    }

    public void spawn(boolean boardUnchanged) {
        if (!boardUnchanged) {
            int newValue = (Math.random() >= .9 ? 4 : 2);
            ArrayList<Point> possibleTiles = new ArrayList<>();

            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    if (grid[row][col] == null) {
                        possibleTiles.add(new Point(col, row));
                    }
                }
            }
            Collections.shuffle(possibleTiles);
            int x = (int) possibleTiles.get(0).getX();
            int y = (int) possibleTiles.get(0).getY();

            grid[y][x] = new Tile(newValue);
        }
    }

    public GameStatus gameOver() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (grid[row][col] != null) {
                    if (grid[row][col].getValue() == 2048) {
                        return GameStatus.WIN;
                    }
                    if (row < 3 && grid[row][col].equals(grid[row + 1][col])) {
                        return GameStatus.CONTINUE;
                    }
                    if (col < 3 && grid[row][col].equals(grid[row][col + 1])) {
                        return GameStatus.CONTINUE;
                    }
                } else {
                    return GameStatus.CONTINUE;
                }
            }
        }
        return GameStatus.LOSE;
    }

    public void printGrid() {
        String val;

        for (int row = 0; row < 4; row++) {
            System.out.println("");
            System.out.println("-----------------");

            for (int column = 0; column < 5; column++) {
                if (column < 4 && grid[row][column] != null) {
                    val = String.valueOf(grid[row][column].getValue());
                } else {
                    val = " ";
                }
                System.out.print("| " + val + " ");
            }
        }
        System.out.println("");
        System.out.println("-----------------");
    }

    public void reset() {
        this.grid = new Tile[4][4];
        this.score = 0;
        this.spawn(false);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Board))
            return false;
        Board board1 = (Board) o;
        return getScore() == board1.getScore() &&
                Arrays.deepEquals(getGrid(), board1.getGrid());
    }

    /**
     * Drawing the board
     * 
     * @param g - the Graphics component
     */
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);

        try {
            img_2 = ImageIO.read(new File("files/tile2.jpg"));
            img_4 = ImageIO.read(new File("files/tile4.jpg"));
            img_8 = ImageIO.read(new File("files/tile8.jpg"));
            img_16 = ImageIO.read(new File("files/tile16.jpg"));
            img_32 = ImageIO.read(new File("files/tile32.jpg"));
            img_64 = ImageIO.read(new File("files/tile64.jpg"));
            img_128 = ImageIO.read(new File("files/tile128.jpg"));
            img_256 = ImageIO.read(new File("files/tile256.jpg"));
            img_512 = ImageIO.read(new File("files/tile512.jpg"));
            img_1024 = ImageIO.read(new File("files/tile1024.jpg"));
            img_2048 = ImageIO.read(new File("files/tile2048.jpg"));
            img_grid = ImageIO.read(new File("files/grid.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        g.drawImage(img_grid, 0, 0, BOARD_WIDTH, BOARD_HEIGHT, null);

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (grid[y][x] != null) {
                    if (grid[y][x].getValue() == 2) {
                        g.drawImage(
                                img_2,
                                TILE_DIMS_X * x,
                                TILE_DIMS_Y * y,
                                TILE_DIMS_X,
                                TILE_DIMS_Y,
                                null
                        );
                    } else if (grid[y][x].getValue() == 4) {
                        g.drawImage(
                                img_4,
                                TILE_DIMS_X * x,
                                TILE_DIMS_Y * y,
                                TILE_DIMS_X,
                                TILE_DIMS_Y,
                                null
                        );
                    } else if (grid[y][x].getValue() == 8) {
                        g.drawImage(
                                img_8,
                                TILE_DIMS_X * x,
                                TILE_DIMS_Y * y,
                                TILE_DIMS_X,
                                TILE_DIMS_Y,
                                null
                        );
                    } else if (grid[y][x].getValue() == 16) {
                        g.drawImage(
                                img_16,
                                TILE_DIMS_X * x,
                                TILE_DIMS_Y * y,
                                TILE_DIMS_X,
                                TILE_DIMS_Y,
                                null
                        );
                    } else if (grid[y][x].getValue() == 32) {
                        g.drawImage(
                                img_32,
                                TILE_DIMS_X * x,
                                TILE_DIMS_Y * y,
                                TILE_DIMS_X,
                                TILE_DIMS_Y,
                                null
                        );
                    } else if (grid[y][x].getValue() == 64) {
                        g.drawImage(
                                img_64,
                                TILE_DIMS_X * x,
                                TILE_DIMS_Y * y,
                                TILE_DIMS_X,
                                TILE_DIMS_Y,
                                null
                        );
                    } else if (grid[y][x].getValue() == 128) {
                        g.drawImage(
                                img_128,
                                TILE_DIMS_X * x,
                                TILE_DIMS_Y * y,
                                TILE_DIMS_X,
                                TILE_DIMS_Y,
                                null
                        );
                    } else if (grid[y][x].getValue() == 256) {
                        g.drawImage(
                                img_256,
                                TILE_DIMS_X * x,
                                TILE_DIMS_Y * y,
                                TILE_DIMS_X,
                                TILE_DIMS_Y,
                                null
                        );
                    } else if (grid[y][x].getValue() == 512) {
                        g.drawImage(
                                img_512,
                                TILE_DIMS_X * x,
                                TILE_DIMS_Y * y,
                                TILE_DIMS_X,
                                TILE_DIMS_Y,
                                null
                        );
                    } else if (grid[y][x].getValue() == 1024) {
                        g.drawImage(
                                img_1024,
                                TILE_DIMS_X * x,
                                TILE_DIMS_Y * y,
                                TILE_DIMS_X,
                                TILE_DIMS_Y,
                                null
                        );
                    } else if (grid[y][x].getValue() == 2048) {
                        g.drawImage(
                                img_2048,
                                TILE_DIMS_X * x,
                                TILE_DIMS_Y * y,
                                TILE_DIMS_X,
                                TILE_DIMS_Y,
                                null
                        );
                    }
                }
            }
        }
    }

    public BufferedImage getImg_2() {
        return img_2;
    }

    public BufferedImage getImg_4() {
        return img_4;
    }

    public BufferedImage getImg_8() {
        return img_8;
    }

    public BufferedImage getImg_16() {
        return img_16;
    }

    public BufferedImage getImg_32() {
        return img_32;
    }

    public BufferedImage getImg_64() {
        return img_64;
    }

    public BufferedImage getImg_128() {
        return img_128;
    }

    public BufferedImage getImg_256() {
        return img_256;
    }

    public BufferedImage getImg_512() {
        return img_512;
    }

    public BufferedImage getImg_1024() {
        return img_1024;
    }

    public BufferedImage getImg_2048() {
        return img_2048;
    }

    public BufferedImage getImg_grid() {
        return img_grid;
    }
}

package org.cis120.tfe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GameBoard extends JPanel {

    private TwentyFortyEight tfe; // model for the game
    private JLabel status; // current status text
    private JLabel score;
    private ArrayList<Integer> prevHighScores;

    // Game constants
    private static final int BOARD_WIDTH = 500;
    private static final int BOARD_HEIGHT = 500;

    static final String PATH_TO_HIGH_SCORES = "files/prev_high_scores.txt";
    static final String PATH_TO_SAVED_GAME = "files/saved_game.txt";

    /**
     * Initializes the game board.
     */
    public GameBoard(JLabel statusInit, JLabel scoreInit) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        tfe = new TwentyFortyEight();
        status = statusInit;
        score = scoreInit;

        updateStatus();
        repaint();
        /*
         * Listens for key presses. Updates the model, then updates the game
         * board based off of the updated model.
         */
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    tfe.playMove(Direction.LEFT);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    tfe.playMove(Direction.RIGHT);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    tfe.playMove(Direction.DOWN);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    tfe.playMove(Direction.UP);
                }

                updateStatus();
                repaint();
            }
        });
    }

    /**
     * Updates the JLabel to reflect the current state of the game.
     */
    private void updateStatus() {
        GameStatus gameStatus = tfe.checkGameStatus();
        if (gameStatus == GameStatus.CONTINUE) {
            status.setText("");
            String newScoreText = "Score: " + tfe.getBoard().getScore();
            score.setText(newScoreText);
        } else if (gameStatus == GameStatus.LOSE) {
            status.setText("You lost..");
            score.setText("");
        } else {
            status.setText("You won!");
            score.setText("");
        }
    }

    public TwentyFortyEight getTfe() {
        return tfe;
    }

    /**
     * (Re-)sets the game to its initial state.
     */
    public void reset() {
        tfe.reset();
        status.setText("Press a key to start the game.");
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }

    public void reset(Tile[][] newGrid, int newScore) {
        tfe.reset(newGrid, newScore);
        status.setText("Loaded.");
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }

    public void resume() {
        repaint();
        requestFocusInWindow();
    }

    /**
     * Draws the game board.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        tfe.getBoard().draw(g);
    }

    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }

    public void saveGame() {
        String saveGameString = "";
        Tile[][] grid = tfe.getBoard().getGrid();

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (grid[y][x] == null) {
                    saveGameString += "0,";
                } else {
                    String val = String.valueOf(grid[y][x].getValue()) + ",";
                    saveGameString += val;
                }
            }
        }
        saveGameString += tfe.getBoard().getScore();
        writeStringsToFile(saveGameString.toString(), PATH_TO_SAVED_GAME, false);
    }

    public Tile[][] getProgress() {
        return tfe.getBoard().getGrid();
    }

    public int getProgressScore() {
        return tfe.getBoard().getScore();
    }

    public void writeStringsToFile(
            String stringToWrite, String filePath,
            boolean append
    ) {
        File file = Paths.get(filePath).toFile();
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(stringToWrite);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String[] readStringsFromFile(String filePath) {
        File file = Paths.get(filePath).toFile();
        String[] line = new String[17];
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            line = br.readLine().split(",");
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return line;
    }
}

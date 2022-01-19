package org.cis120.tfe;

/**
 * CIS 120 HW09 - TicTacToe Demo
 * (c) University of Pennsylvania
 * Created by Bayley Tuch, Sabrina Green, and Nicolas Corona in Fall 2020.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class sets up the top-level frame and widgets for the GUI.
 * 
 * This game adheres to a Model-View-Controller design framework. This
 * framework is very effective for turn-based games. We STRONGLY
 * recommend you review these lecture slides, starting at slide 8,
 * for more details on Model-View-Controller:
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec37.pdf
 * 
 * In a Model-View-Controller framework, Game initializes the view,
 * implements a bit of controller functionality through the reset
 * button, and then instantiates a GameBoard. The GameBoard will
 * handle the rest of the game's view and controller functionality, and
 * it will instantiate a TicTacToe object to serve as the game's model.
 */
public class RunTwentyFortyEight implements Runnable {
    public void run() {
        // NOTE: the 'final' keyword denotes immutability even for local variables.

        // Top-level frame in which game components live
        final JFrame frame = new JFrame("2048");
        frame.setLocation(500, 500);

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Setting up...");
        JLabel score = new JLabel("Score: 0");
        status_panel.add(status);
        status_panel.add(score);

        final String INSTRUCTIONS = "\n WELCOME TO 2048 \n "
                + "\n To win, you must add tiles until you reach 2048."
                + "\n The game is lost when you have no moves left. \n"
                + "\n CONTROLS \n"
                + "\n Key UP: Slide all the tiles up."
                + "\n Key DOWN: Slide all the tiles down. "
                + "\n Key LEFT: Slide all the tiles left. "
                + "\n Key RIGHT: Slide all the tiles right \n"
                + "\n HAVE FUN PLAYING \n";
        JOptionPane
                .showMessageDialog(frame, INSTRUCTIONS, "INSTRUCTIONS:", JOptionPane.PLAIN_MESSAGE);

        // asking if user wants to load progress from last time
        int choice = JOptionPane.showConfirmDialog(frame, "Do you want to load the progress");
        Tile[][] newGrid = new Tile[4][4];
        int newScore = 0;

        // create leader board
        ArrayList<String> leaderBoard = new ArrayList<String>();

        if (choice == JOptionPane.YES_OPTION) {
            File file = Paths.get("files/saved_game.txt").toFile();
            String[] line = new String[17];
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(file));
                line = br.readLine().split(",");
                br.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            newScore = Integer.parseInt(line[line.length - 1]);
            newGrid = new Tile[4][4];
            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < 4; x++) {
                    if (line[(y * 4) + x].equals("0")) {
                        newGrid[y][x] = null;
                    } else {
                        int val = Integer.parseInt(line[(y * 4) + x]);
                        newGrid[y][x] = new Tile(val);
                    }
                }
            }
        }

        // Game gameBoard
        final GameBoard gameBoard = new GameBoard(status, score);
        frame.add(gameBoard, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        // Note here that when we add an action listener to the reset button, we
        // define it as an anonymous inner class that is an instance of
        // ActionListener with its actionPerformed() method overridden. When the
        // button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("New Game");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameBoard.reset();
                gameBoard.resume();
            }
        });
        control_panel.add(reset);

        final JButton saveGame = new JButton("Save Game and Quit");
        saveGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameBoard.saveGame();
                System.exit(-1);
            }
        });
        control_panel.add(saveGame);

        // create button to show leader board
        final JLabel leaderboard = new JLabel("");
        status_panel.add(leaderboard);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start the game
        gameBoard.reset(newGrid, newScore);
    }
}
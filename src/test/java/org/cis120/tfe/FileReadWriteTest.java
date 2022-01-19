package org.cis120.tfe;

import org.cis120.Game;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class FileReadWriteTest {
    static final String PATH_TO_TEST = "files/test_saved_game.txt";

    @Test
    public void correctReadTest() {
        GameBoard gb = new GameBoard(
                new JLabel("lol"), new JLabel("LOL")
        );

        String stringToWrite = "4,4,0,0,2,0,0,0,0,0,0,0,0,0,0,4,8";

        gb.writeStringsToFile(stringToWrite, PATH_TO_TEST, false);

        String[] stringArray = GameBoard.readStringsFromFile(PATH_TO_TEST);

        String[] expected = new String[] { "4", "4", "0", "0", "2", "0", "0", "0", "0", "0", "0",
            "0", "0", "0", "0", "4", "8" };

        assertArrayEquals(expected, stringArray);
    }
}

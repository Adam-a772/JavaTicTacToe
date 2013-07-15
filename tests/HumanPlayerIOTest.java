import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HumanPlayerIOTest {
    HumanPlayerIO playerIO;
    InputStream inputStream;
    PrintStream outputStream;
    File tmpOut;
    File tmpIn;
    Scanner outputReader;
    PrintStream inputWriter;


    @Before
    public void initialize() throws IOException {
        tmpOut = File.createTempFile("tmpOut", null);
        outputStream = new PrintStream(tmpOut);
        tmpIn = File.createTempFile("tmpIn", null);
        inputStream = new FileInputStream(tmpIn);

        playerIO = new HumanPlayerIO(inputStream, outputStream);

        outputReader = new Scanner(tmpOut);
        inputWriter = new PrintStream(tmpIn);
    }

    @Test
    public void shouldInitializeWithGivenIOStreams() {
        assertEquals(outputStream, playerIO.getOutputStream());
        assertEquals(inputStream, playerIO.getInputStream());
    }

    @Test
    public void shouldAskForMoveInOutputStream() {
        playerIO.askForMove();
        assertTrue(outputReader.nextLine().matches("Where.*move.*cell number.*"));
    }

    @Test
    public void shouldNotifyInvalidCell() {
        playerIO.notifyInvalidCell();
        assertTrue(outputReader.nextLine().matches(".*not valid.*"));
    }

    @Test
    public void shouldReadMoveFromInputStream() {
        inputWriter.println("4");
        assertTrue(playerIO.readMove().matches("4"));
    }

    @Test
    public void shouldReadMultipleMovesFromInputStream() {
        inputWriter.println("4");
        inputWriter.println("0");
        inputWriter.println("8");
        inputWriter.println("14");
        assertTrue(playerIO.readMove().matches("4"));
        assertTrue(playerIO.readMove().matches("0"));
        assertTrue(playerIO.readMove().matches("8"));
        assertTrue(playerIO.readMove().matches("14"));
    }

    @After
    public void cleanUp() {
        tmpOut.delete();
        tmpIn.delete();
    }
}

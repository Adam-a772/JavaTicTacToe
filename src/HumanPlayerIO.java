import java.io.*;

public class HumanPlayerIO {
    private PrintWriter outputStream;
    private BufferedReader inputStream;

    public HumanPlayerIO(Reader reader, Writer writer) {
        inputStream = new BufferedReader(reader);
        outputStream = new PrintWriter(writer, true);
    }

    public void askForMove() {
        outputStream.println("Where would you like to move? (enter the cell number):");
    }

    public String readMove() throws IOException {
        return inputStream.readLine();
    }

    public void notifyInvalidCell() {
        outputStream.println("The cell you entered is not valid!");
    }
}

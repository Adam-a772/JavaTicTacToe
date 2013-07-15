import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayerIO {
    private PrintStream outputStream;
    private InputStream inputStream;
    private Scanner inputScanner;

    public HumanPlayerIO(InputStream inputStream, PrintStream outputStream) {
        this.outputStream = outputStream;
        this.inputStream = inputStream;
        inputScanner = new Scanner(inputStream);
    }

    public PrintStream getOutputStream() {
        return outputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void askForMove() {
        outputStream.println("Where would you like to move? (enter the cell number):");
    }

    public String readMove() {
        return inputScanner.nextLine();
    }

    public void notifyInvalidCell() {
        outputStream.println("The cell you entered is not valid!");
    }
}

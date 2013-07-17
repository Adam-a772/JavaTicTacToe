import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created with IntelliJ IDEA.
 * User: robert
 * Date: 7/16/13
 * Time: 9:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class Driver {
    public static void main(String[] args) {
        ConsoleIO io = new ConsoleIO(new InputStreamReader(System.in), new OutputStreamWriter(System.out));
        io.askForMove();
        System.out.println(io.readMove());
    }
}

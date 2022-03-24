import by.epam.lab.services.Drop;
import by.epam.lab.threads.Reader;
import by.epam.lab.threads.Writer;

public class Runner {
    public static void main(String[] args) {
        Drop drop = new Drop();
        (new Thread(new Reader(drop))).start();
        (new Thread(new Writer(drop))).start();
    }
}


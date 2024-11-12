import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

// Component: Abstract class for reading input
abstract class ReaderDecorator extends Reader {
    protected Reader reader;

    public ReaderDecorator(Reader reader) {
        this.reader = reader;
    }

    public abstract int read(char[] cbuf, int off, int len) throws IOException;
}

// Concrete Component: Simple Reader that reads input as-is
class SimpleReader extends Reader {
    private String input;

    public SimpleReader(String input) {
        this.input = input;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        if (input == null || input.length() == 0) {
            return -1; // End of input
        }
        int n = Math.min(len, input.length());
        input.getChars(0, n, cbuf, off);
        input = input.substring(n); // Update input to read next part
        return n;
    }

    @Override
    public void close() throws IOException {
        // No specific resource to close
    }
}

// Concrete Decorator: Lowercase Decorator that converts uppercase to lowercase
class LowerCaseDecorator extends ReaderDecorator {

    public LowerCaseDecorator(Reader reader) {
        super(reader);
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int numRead = reader.read(cbuf, off, len);
        if (numRead == -1) {
            return -1; // End of input
        }
        for (int i = off; i < off + numRead; i++) {
            cbuf[i] = Character.toLowerCase(cbuf[i]);
        }
        return numRead;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}

// Main class to test the I/O Decorator Pattern
public class DecoratorPatternExample {
    public static void main(String[] args) {
        try {
            // Take user input
            System.out.print("Enter a string (uppercase): ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine();

            // Create SimpleReader and apply LowerCaseDecorator
            Reader reader = new SimpleReader(input);
            Reader lowercaseReader = new LowerCaseDecorator(reader);

            // Read and display the converted input (to lowercase)
            char[] buffer = new char[1024];
            int numRead;
            System.out.print("Converted to lowercase: ");
            while ((numRead = lowercaseReader.read(buffer, 0, buffer.length)) != -1) {
                System.out.print(new String(buffer, 0, numRead));
            }

            lowercaseReader.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

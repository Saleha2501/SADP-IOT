import java.io.*;

// Custom decorator to convert uppercase to lowercase
class UpperToLowerCaseInputStream extends FilterInputStream {
    protected UpperToLowerCaseInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        if (c == -1) return c; // End of stream
        return Character.toLowerCase(c); // Convert to lowercase
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int result = super.read(b, off, len);
        if (result == -1) return result; // End of stream
        for (int i = off; i < off + result; i++) {
            b[i] = (byte) Character.toLowerCase((char) b[i]);
        }
        return result;
    }
}

public class IODecoratorExample {
    public static void main(String[] args) {
        String input = "HELLO JAVA! Decorator Pattern Example.";
        System.out.println("Original Input: " + input);

        // Convert the input string to a ByteArrayInputStream
        try (InputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
             UpperToLowerCaseInputStream lowerCaseStream = new UpperToLowerCaseInputStream(byteArrayInputStream);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            // Read from the decorated stream and write to the output stream
            int data;
            while ((data = lowerCaseStream.read()) != -1) {
                outputStream.write(data);
            }

            // Print the transformed result
            String transformedOutput = outputStream.toString();
            System.out.println("Transformed Output: " + transformedOutput);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

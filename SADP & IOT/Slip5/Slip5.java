import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

// Adapter Class: Converts Enumeration to Iterator
class EnumerationIteratorAdapter<T> implements Iterator<T> {
    private final Enumeration<T> enumeration;

    public EnumerationIteratorAdapter(Enumeration<T> enumeration) {
        this.enumeration = enumeration;
    }

    @Override
    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }

    @Override
    public T next() {
        return enumeration.nextElement();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove not supported in Enumeration.");
    }
}

// Main Class
public class AdapterPatternExample {
    public static void main(String[] args) {
        // Legacy data in a Vector (Enumeration supported)
        Vector<String> legacyVector = new Vector<>();
        legacyVector.add("Item1");
        legacyVector.add("Item2");
        legacyVector.add("Item3");

        // Get Enumeration from Vector
        Enumeration<String> enumeration = legacyVector.elements();

        // Use Adapter to convert Enumeration to Iterator
        Iterator<String> iterator = new EnumerationIteratorAdapter<>(enumeration);

        // Iterate through the elements using the Iterator
        System.out.println("Iterating using Iterator:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

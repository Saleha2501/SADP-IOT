import java.util.*;
import java.util.Scanner;

// Subject: Number (Decimal)
class Number {
    private int decimalValue;
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void setDecimalValue(int decimalValue) {
        this.decimalValue = decimalValue;
        notifyObservers();
    }

    public int getDecimalValue() {
        return decimalValue;
    }

    // Notify all observers of the change
    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

// Observer interface
interface Observer {
    void update();
}

// Observer: Hexadecimal Representation
class HexadecimalObserver implements Observer {
    private Number number;

    public HexadecimalObserver(Number number) {
        this.number = number;
        number.addObserver(this);
    }

    @Override
    public void update() {
        System.out.println("Hexadecimal: " + Integer.toHexString(number.getDecimalValue()).toUpperCase());
    }
}

// Observer: Octal Representation
class OctalObserver implements Observer {
    private Number number;

    public OctalObserver(Number number) {
        this.number = number;
        number.addObserver(this);
    }

    @Override
    public void update() {
        System.out.println("Octal: " + Integer.toOctalString(number.getDecimalValue()));
    }
}

// Observer: Binary Representation
class BinaryObserver implements Observer {
    private Number number;

    public BinaryObserver(Number number) {
        this.number = number;
        number.addObserver(this);
    }

    @Override
    public void update() {
        System.out.println("Binary: " + Integer.toBinaryString(number.getDecimalValue()));
    }
}

// Client: Testing the Observer Pattern
public class ObserverPatternNumberConversion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a Number (Subject)
        Number number = new Number();

        // Create observers (Hexadecimal, Octal, Binary)
        HexadecimalObserver hexObserver = new HexadecimalObserver(number);
        OctalObserver octObserver = new OctalObserver(number);
        BinaryObserver binObserver = new BinaryObserver(number);

        // Accept number input from user
        System.out.print("Enter a number in Decimal: ");
        int inputNumber = scanner.nextInt();
        number.setDecimalValue(inputNumber);
        System.out.println();

        // Change the number by asking the user again
        System.out.print("Enter another number in Decimal to change: ");
        inputNumber = scanner.nextInt();
        number.setDecimalValue(inputNumber);
        System.out.println();

        // Remove one observer (e.g., OctalObserver)
        number.removeObserver(octObserver);
        System.out.println("Removed OctalObserver");

        // Change the number again
        System.out.print("Enter another number in Decimal to change: ");
        inputNumber = scanner.nextInt();
        number.setDecimalValue(inputNumber);

        scanner.close();  // Close the scanner resource
    }
}

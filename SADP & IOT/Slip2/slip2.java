class Singleton {
    // Static instance of Singleton class
    private static Singleton instance;

    // Private constructor to prevent instantiation
    private Singleton() {
        System.out.println("Singleton Instance Created");
    }

    // Public method to provide global access to the instance
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    // Example method to demonstrate Singleton functionality
    public void showMessage() {
        System.out.println("Hello from Singleton Instance");
    }
}

// Multithreading Test
public class SingletonMultithreadingTest {
    public static void main(String[] args) {
        // Create multiple threads to test Singleton
        Thread thread1 = new Thread(() -> {
            Singleton singleton1 = Singleton.getInstance();
            singleton1.showMessage();
        });

        Thread thread2 = new Thread(() -> {
            Singleton singleton2 = Singleton.getInstance();
            singleton2.showMessage();
        });

        // Start threads
        thread1.start();
        thread2.start();
    }
}

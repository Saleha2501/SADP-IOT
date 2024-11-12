// Volt class to represent voltage
class Volt {
    private int volts;

    public Volt(int volts) {
        this.volts = volts;
    }

    public int getVolts() {
        return volts;
    }

    public void setVolts(int volts) {
        this.volts = volts;
    }

    @Override
    public String toString() {
        return volts + "V";
    }
}

// Socket class to produce constant voltage
class Socket {
    public Volt getVolt() {
        return new Volt(120); // Default socket voltage is 120V
    }
}

// Adapter interface for voltage conversion
interface SocketAdapter {
    Volt get3Volt();

    Volt get12Volt();

    Volt get120Volt();
}

// Adapter implementation using Class Adapter (inheritance)
class SocketClassAdapterImpl extends Socket implements SocketAdapter {

    @Override
    public Volt get3Volt() {
        return convertVolt(getVolt(), 40); // Convert 120V to 3V
    }

    @Override
    public Volt get12Volt() {
        return convertVolt(getVolt(), 10); // Convert 120V to 12V
    }

    @Override
    public Volt get120Volt() {
        return getVolt(); // Default voltage is 120V
    }

    // Helper method to convert voltage
    private Volt convertVolt(Volt v, int divisor) {
        return new Volt(v.getVolts() / divisor);
    }
}

// Client code to test the Adapter
public class AdapterPatternMobileCharger {
    public static void main(String[] args) {
        // Create an adapter
        SocketAdapter adapter = new SocketClassAdapterImpl();

        // Get and print different voltages
        Volt v3 = adapter.get3Volt();
        System.out.println("Mobile Charging Voltage (3V): " + v3);

        Volt v12 = adapter.get12Volt();
        System.out.println("Laptop Charging Voltage (12V): " + v12);

        Volt v120 = adapter.get120Volt();
        System.out.println("Default Socket Voltage (120V): " + v120);
    }
}

import java.util.Observable;
import java.util.Observer;

// WeatherData class (Observable)
class WeatherData extends Observable {
    private float temperature;
    private float humidity;
    private float pressure;

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setMeasurement(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public void measurementsChanged() {
        setChanged(); // Mark the observable object as changed
        notifyObservers(); // Notify all observers
    }
}

// CurrentConditionsDisplay class (Observer)
class CurrentConditionsDisplay implements Observer {
    private float temperature;
    private float humidity;

    public CurrentConditionsDisplay(Observable observable) {
        observable.addObserver(this); // Register itself as an observer
    }

    @Override
    public void update(Observable observable, Object arg) {
        if (observable instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) observable;
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            display();
        }
    }

    public void display() {
        System.out.println("Current conditions: " + temperature + "°C and " + humidity + "% humidity");
    }
}

// PressureDisplay class (Observer)
class PressureDisplay implements Observer {
    private float pressure;

    public PressureDisplay(Observable observable) {
        observable.addObserver(this); // Register itself as an observer
    }

    @Override
    public void update(Observable observable, Object arg) {
        if (observable instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) observable;
            this.pressure = weatherData.getPressure();
            display();
        }
    }

    public void display() {
        System.out.println("Current pressure: " + pressure + " hPa");
    }
}

// Main Class
public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        // Create observers
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
        PressureDisplay pressureDisplay = new PressureDisplay(weatherData);

        // Simulate new weather measurements
        weatherData.setMeasurement(25.0f, 65.0f, 1013.0f);
        weatherData.setMeasurement(22.5f, 70.0f, 1012.5f);
        weatherData.setMeasurement(28.0f, 60.0f, 1011.0f);
    }
}

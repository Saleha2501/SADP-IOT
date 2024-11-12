import java.util.Observable;
import java.util.Observer;

// Observable class: WeatherData
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

    public void setMeasurements(float temperature, float humidity, float pressure) {
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

// Observer class: CurrentConditionsDisplay
class CurrentConditionsDisplay implements Observer {
    private float temperature;
    private float humidity;

    public CurrentConditionsDisplay(Observable observable) {
        observable.addObserver(this); // Register as an observer
    }

    @Override
    public void update(Observable obs, Object arg) {
        if (obs instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) obs;
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            display();
        }
    }

    public void display() {
        System.out.println("Current conditions: " + temperature + "°C and " + humidity + "% humidity.");
    }
}

// Observer class: StatisticsDisplay
class StatisticsDisplay implements Observer {
    private float maxTemp = 0.0f;
    private float minTemp = 200;
    private float tempSum = 0.0f;
    private int numReadings;

    public StatisticsDisplay(Observable observable) {
        observable.addObserver(this); // Register as an observer
    }

    @Override
    public void update(Observable obs, Object arg) {
        if (obs instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) obs;
            float temp = weatherData.getTemperature();
            tempSum += temp;
            numReadings++;

            if (temp > maxTemp) {
                maxTemp = temp;
            }

            if (temp < minTemp) {
                minTemp = temp;
            }

            display();
        }
    }

    public void display() {
        System.out.println("Avg/Max/Min temperature = " + (tempSum / numReadings) + "/" + maxTemp + "/" + minTemp);
    }
}

// Main class
public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);

        System.out.println("Updating measurements to 25°C, 65% humidity, and 1013 hPa pressure...");
        weatherData.setMeasurements(25, 65, 1013);

        System.out.println("\nUpdating measurements to 28°C, 70% humidity, and 1010 hPa pressure...");
        weatherData.setMeasurements(28, 70, 1010);

        System.out.println("\nUpdating measurements to 22°C, 90% humidity, and 1008 hPa pressure...");
        weatherData.setMeasurements(22, 90, 1008);
    }
}

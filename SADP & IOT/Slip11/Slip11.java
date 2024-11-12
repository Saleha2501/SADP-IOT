// Target Interface: BeatModel
interface BeatModel {
    void initialize();
    void start();
    void stop();
    int getBeatRate();
}

// Adaptee: HeartModel
class HeartModel {
    private int heartRate;

    public HeartModel(int heartRate) {
        this.heartRate = heartRate;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public void simulateHeartBeat() {
        System.out.println("Heart is beating at " + heartRate + " bpm.");
    }
}

// Adapter: Adapts HeartModel to BeatModel
class HeartToBeatAdapter implements BeatModel {
    private HeartModel heartModel;

    public HeartToBeatAdapter(HeartModel heartModel) {
        this.heartModel = heartModel;
    }

    @Override
    public void initialize() {
        System.out.println("Initializing Heart Model as Beat Model...");
    }

    @Override
    public void start() {
        System.out.println("Starting Heart Simulation...");
        heartModel.simulateHeartBeat();
    }

    @Override
    public void stop() {
        System.out.println("Stopping Heart Simulation...");
    }

    @Override
    public int getBeatRate() {
        return heartModel.getHeartRate();
    }
}

// Client: Testing the Adapter
public class AdapterPatternHeartToBeat {
    public static void main(String[] args) {
        // Adaptee
        HeartModel heartModel = new HeartModel(72);

        // Adapter
        BeatModel beatModel = new HeartToBeatAdapter(heartModel);

        // Using the BeatModel interface to interact with HeartModel
        beatModel.initialize();
        beatModel.start();
        System.out.println("Current Beat Rate: " + beatModel.getBeatRate() + " bpm.");
        beatModel.stop();
    }
}

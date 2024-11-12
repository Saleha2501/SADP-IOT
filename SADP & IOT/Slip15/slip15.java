// Subsystem 1: Amplifier
class Amplifier {
    public void on() {
        System.out.println("Amplifier is ON");
    }

    public void off() {
        System.out.println("Amplifier is OFF");
    }

    public void setVolume(int level) {
        System.out.println("Setting amplifier volume to " + level);
    }
}

// Subsystem 2: DvdPlayer
class DvdPlayer {
    public void on() {
        System.out.println("DVD Player is ON");
    }

    public void off() {
        System.out.println("DVD Player is OFF");
    }

    public void play(String movie) {
        System.out.println("Playing movie: " + movie);
    }

    public void stop() {
        System.out.println("Stopping movie");
    }
}

// Subsystem 3: Projector
class Projector {
    public void on() {
        System.out.println("Projector is ON");
    }

    public void off() {
        System.out.println("Projector is OFF");
    }

    public void setMode(String mode) {
        System.out.println("Setting projector mode to " + mode);
    }
}

// Subsystem 4: Screen
class Screen {
    public void down() {
        System.out.println("Screen is DOWN");
    }

    public void up() {
        System.out.println("Screen is UP");
    }
}

// Subsystem 5: Lights
class Lights {
    public void dim(int level) {
        System.out.println("Dimming lights to " + level + "%");
    }

    public void on() {
        System.out.println("Lights are ON");
    }
}

// Facade Class: HomeTheaterFacade
class HomeTheaterFacade {
    private Amplifier amplifier;
    private DvdPlayer dvdPlayer;
    private Projector projector;
    private Screen screen;
    private Lights lights;

    // Constructor to initialize subsystems
    public HomeTheaterFacade(Amplifier amplifier, DvdPlayer dvdPlayer, Projector projector, Screen screen, Lights lights) {
        this.amplifier = amplifier;
        this.dvdPlayer = dvdPlayer;
        this.projector = projector;
        this.screen = screen;
        this.lights = lights;
    }

    // Simple interface to watch a movie
    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        lights.dim(10);
        screen.down();
        projector.on();
        projector.setMode("Movie");
        amplifier.on();
        amplifier.setVolume(20);
        dvdPlayer.on();
        dvdPlayer.play(movie);
    }

    // Simple interface to end the movie
    public void endMovie() {
        System.out.println("Shutting down the home theater...");
        dvdPlayer.stop();
        projector.off();
        screen.up();
        lights.on();
        amplifier.off();
    }
}

// Client: Testing the Facade
public class FacadePatternHomeTheater {
    public static void main(String[] args) {
        // Subsystems
        Amplifier amplifier = new Amplifier();
        DvdPlayer dvdPlayer = new DvdPlayer();
        Projector projector = new Projector();
        Screen screen = new Screen();
        Lights lights = new Lights();

        // Facade to simplify interaction with the subsystems
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(amplifier, dvdPlayer, projector, screen, lights);

        // Use the facade to control the home theater system
        homeTheater.watchMovie("Inception");
        System.out.println("\n--- Movie Ended ---\n");
        homeTheater.endMovie();
    }
}

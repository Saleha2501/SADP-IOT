// Command Interface
interface Command {
    void execute();
}

// Receiver Class for Light
class Light {
    public void on() {
        System.out.println("Light is ON");
    }

    public void off() {
        System.out.println("Light is OFF");
    }
}

// Receiver Class for Garage Door
class GarageDoor {
    public void up() {
        System.out.println("Garage Door is UP");
    }

    public void down() {
        System.out.println("Garage Door is DOWN");
    }
}

// Receiver Class for Stereo
class Stereo {
    public void on() {
        System.out.println("Stereo is ON");
    }

    public void off() {
        System.out.println("Stereo is OFF");
    }

    public void setCD() {
        System.out.println("Stereo is set to CD");
    }

    public void setVolume(int volume) {
        System.out.println("Stereo volume set to " + volume);
    }
}

// Concrete Command for Light On
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}

// Concrete Command for Light Off
class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }
}

// Concrete Command for Garage Door Up
class GarageDoorUpCommand implements Command {
    private GarageDoor garageDoor;

    public GarageDoorUpCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        garageDoor.up();
    }
}

// Concrete Command for Stereo On with CD
class StereoOnWithCDCommand implements Command {
    private Stereo stereo;

    public StereoOnWithCDCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.on();
        stereo.setCD();
        stereo.setVolume(11); // Default volume
    }
}

// Remote Control Class (Invoker)
class RemoteControl {
    private Command[] onCommands;
    private Command[] offCommands;

    public RemoteControl() {
        onCommands = new Command[5];
        offCommands = new Command[5];
        
        // Initialize commands with no-op commands
        for (int i = 0; i < 5; i++) {
            onCommands[i] = new NoCommand();
            offCommands[i] = new NoCommand();
        }
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void pressOnButton(int slot) {
        onCommands[slot].execute();
    }

    public void pressOffButton(int slot) {
        offCommands[slot].execute();
    }
}

// NoCommand class for default action
class NoCommand implements Command {
    @Override
    public void execute() {
        // Do nothing
    }
}

// Main Class
public class CommandPatternExample {
    public static void main(String[] args) {
        // Create the receiver objects
        Light livingRoomLight = new Light();
        GarageDoor garageDoor = new GarageDoor();
        Stereo stereo = new Stereo();

        // Create command objects
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);
        Command garageDoorUp = new GarageDoorUpCommand(garageDoor);
        Command stereoOnWithCD = new StereoOnWithCDCommand(stereo);

        // Create remote control (Invoker)
        RemoteControl remote = new RemoteControl();

        // Set commands for different slots
        remote.setCommand(0, lightOn, lightOff);
        remote.setCommand(1, garageDoorUp, null);
        remote.setCommand(2, stereoOnWithCD, null);

        // Test the remote control
        System.out.println("Testing Remote Control:");
        
        remote.pressOnButton(0); // Light On
        remote.pressOffButton(0); // Light Off
        
        remote.pressOnButton(1); // Garage Door Up
        remote.pressOnButton(2); // Stereo On with CD
    }
}

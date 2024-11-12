// Command Interface
interface Command {
    void execute();
    void undo();
}

// Receiver Class
class Light {
    private String location;

    public Light(String location) {
        this.location = location;
    }

    public void on() {
        System.out.println(location + " Light is ON");
    }

    public void off() {
        System.out.println(location + " Light is OFF");
    }
}

// Concrete Command to Turn On the Light
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}

// Concrete Command to Turn Off the Light
class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}

// Invoker Class (Remote Control)
class RemoteControl {
    private Command onCommand;
    private Command offCommand;

    public void setCommands(Command onCommand, Command offCommand) {
        this.onCommand = onCommand;
        this.offCommand = offCommand;
    }

    public void pressOnButton() {
        if (onCommand != null) {
            onCommand.execute();
        }
    }

    public void pressOffButton() {
        if (offCommand != null) {
            offCommand.execute();
        }
    }

    public void pressUndoButton() {
        if (onCommand != null) {
            onCommand.undo();
        }
    }
}

// Main Class
public class CommandPatternExample {
    public static void main(String[] args) {
        // Create the receiver
        Light livingRoomLight = new Light("Living Room");

        // Create the command objects
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);

        // Create the invoker (remote control)
        RemoteControl remote = new RemoteControl();
        remote.setCommands(lightOn, lightOff);

        // Simulate button presses
        System.out.println("Testing Remote Control:");
        remote.pressOnButton();
        remote.pressOffButton();
        remote.pressUndoButton();
    }
}

// Command Interface
interface Command {
    void execute();
    void undo();
}

// Receiver Class (Ceiling Fan)
class CeilingFan {
    private String location;
    private int speed;

    // Speed levels
    public static final int HIGH = 3;
    public static final int MEDIUM = 2;
    public static final int LOW = 1;
    public static final int OFF = 0;

    public CeilingFan(String location) {
        this.location = location;
        speed = OFF;
    }

    public void high() {
        speed = HIGH;
        System.out.println(location + " Ceiling Fan is set to HIGH");
    }

    public void medium() {
        speed = MEDIUM;
        System.out.println(location + " Ceiling Fan is set to MEDIUM");
    }

    public void low() {
        speed = LOW;
        System.out.println(location + " Ceiling Fan is set to LOW");
    }

    public void off() {
        speed = OFF;
        System.out.println(location + " Ceiling Fan is OFF");
    }

    public int getSpeed() {
        return speed;
    }
}

// Abstract Ceiling Fan Command
abstract class CeilingFanCommand implements Command {
    protected CeilingFan ceilingFan;
    protected int prevSpeed;

    public CeilingFanCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void undo() {
        switch (prevSpeed) {
            case CeilingFan.HIGH:
                ceilingFan.high();
                break;
            case CeilingFan.MEDIUM:
                ceilingFan.medium();
                break;
            case CeilingFan.LOW:
                ceilingFan.low();
                break;
            case CeilingFan.OFF:
                ceilingFan.off();
                break;
        }
    }
}

// Concrete Commands
class CeilingFanHighCommand extends CeilingFanCommand {
    public CeilingFanHighCommand(CeilingFan ceilingFan) {
        super(ceilingFan);
    }

    @Override
    public void execute() {
        prevSpeed = ceilingFan.getSpeed();
        ceilingFan.high();
    }
}

class CeilingFanMediumCommand extends CeilingFanCommand {
    public CeilingFanMediumCommand(CeilingFan ceilingFan) {
        super(ceilingFan);
    }

    @Override
    public void execute() {
        prevSpeed = ceilingFan.getSpeed();
        ceilingFan.medium();
    }
}

class CeilingFanLowCommand extends CeilingFanCommand {
    public CeilingFanLowCommand(CeilingFan ceilingFan) {
        super(ceilingFan);
    }

    @Override
    public void execute() {
        prevSpeed = ceilingFan.getSpeed();
        ceilingFan.low();
    }
}

class CeilingFanOffCommand extends CeilingFanCommand {
    public CeilingFanOffCommand(CeilingFan ceilingFan) {
        super(ceilingFan);
    }

    @Override
    public void execute() {
        prevSpeed = ceilingFan.getSpeed();
        ceilingFan.off();
    }
}

// Invoker (Remote Control)
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }

    public void pressUndo() {
        command.undo();
    }
}

// Main Class
public class CeilingFanUndoTest {
    public static void main(String[] args) {
        // Receiver
        CeilingFan livingRoomFan = new CeilingFan("Living Room");

        // Commands
        Command fanHigh = new CeilingFanHighCommand(livingRoomFan);
        Command fanMedium = new CeilingFanMediumCommand(livingRoomFan);
        Command fanLow = new CeilingFanLowCommand(livingRoomFan);
        Command fanOff = new CeilingFanOffCommand(livingRoomFan);

        // Invoker
        RemoteControl remote = new RemoteControl();

        // Test commands
        remote.setCommand(fanHigh);
        remote.pressButton(); // Set to HIGH
        remote.pressUndo();   // Undo: OFF

        remote.setCommand(fanMedium);
        remote.pressButton(); // Set to MEDIUM
        remote.pressUndo();   // Undo: HIGH

        remote.setCommand(fanLow);
        remote.pressButton(); // Set to LOW
        remote.pressUndo();   // Undo: MEDIUM

        remote.setCommand(fanOff);
        remote.pressButton(); // Turn OFF
        remote.pressUndo();   // Undo: LOW
    }
}

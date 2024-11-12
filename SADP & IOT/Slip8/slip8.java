// State Interface
interface State {
    void insertCoin();

    void ejectCoin();

    void turnCrank();

    void dispense();
}

// NoCoinState: No coin is inserted
class NoCoinState implements State {
    private GumballMachine gumballMachine;

    public NoCoinState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertCoin() {
        System.out.println("You inserted a coin.");
        gumballMachine.setState(gumballMachine.getHasCoinState());
    }

    @Override
    public void ejectCoin() {
        System.out.println("You haven't inserted a coin.");
    }

    @Override
    public void turnCrank() {
        System.out.println("You turned the crank, but there's no coin.");
    }

    @Override
    public void dispense() {
        System.out.println("You need to pay first.");
    }
}

// HasCoinState: Coin is inserted
class HasCoinState implements State {
    private GumballMachine gumballMachine;

    public HasCoinState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertCoin() {
        System.out.println("You can't insert another coin.");
    }

    @Override
    public void ejectCoin() {
        System.out.println("Coin returned.");
        gumballMachine.setState(gumballMachine.getNoCoinState());
    }

    @Override
    public void turnCrank() {
        System.out.println("You turned the crank...");
        gumballMachine.setState(gumballMachine.getSoldState());
    }

    @Override
    public void dispense() {
        System.out.println("No gumball dispensed.");
    }
}

// SoldState: Dispensing a gumball
class SoldState implements State {
    private GumballMachine gumballMachine;

    public SoldState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Please wait, we're already giving you a gumball.");
    }

    @Override
    public void ejectCoin() {
        System.out.println("Sorry, you already turned the crank.");
    }

    @Override
    public void turnCrank() {
        System.out.println("Turning twice doesn't get you another gumball!");
    }

    @Override
    public void dispense() {
        gumballMachine.releaseBall();
        if (gumballMachine.getCount() > 0) {
            gumballMachine.setState(gumballMachine.getNoCoinState());
        } else {
            System.out.println("Oops, out of gumballs!");
            gumballMachine.setState(gumballMachine.getSoldOutState());
        }
    }
}

// SoldOutState: No gumballs left
class SoldOutState implements State {
    private GumballMachine gumballMachine;

    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertCoin() {
        System.out.println("You can't insert a coin. The machine is sold out.");
    }

    @Override
    public void ejectCoin() {
        System.out.println("You can't eject. You haven't inserted a coin yet.");
    }

    @Override
    public void turnCrank() {
        System.out.println("You turned, but there are no gumballs.");
    }

    @Override
    public void dispense() {
        System.out.println("No gumball dispensed.");
    }
}

// Gumball Machine
class GumballMachine {
    private State noCoinState;
    private State hasCoinState;
    private State soldState;
    private State soldOutState;

    private State currentState;
    private int count;

    public GumballMachine(int numberOfGumballs) {
        noCoinState = new NoCoinState(this);
        hasCoinState = new HasCoinState(this);
        soldState = new SoldState(this);
        soldOutState = new SoldOutState(this);

        this.count = numberOfGumballs;
        currentState = (count > 0) ? noCoinState : soldOutState;
    }

    public void insertCoin() {
        currentState.insertCoin();
    }

    public void ejectCoin() {
        currentState.ejectCoin();
    }

    public void turnCrank() {
        currentState.turnCrank();
        currentState.dispense();
    }

    public void setState(State state) {
        this.currentState = state;
    }

    public void releaseBall() {
        if (count > 0) {
            System.out.println("A gumball comes rolling out...");
            count--;
        }
    }

    public int getCount() {
        return count;
    }

    public State getNoCoinState() {
        return noCoinState;
    }

    public State getHasCoinState() {
        return hasCoinState;
    }

    public State getSoldState() {
        return soldState;
    }

    public State getSoldOutState() {
        return soldOutState;
    }
}

// Test the State Pattern
public class GumballMachineTest {
    public static void main(String[] args) {
        GumballMachine gumballMachine = new GumballMachine(3);

        System.out.println("\n--- Testing Gumball Machine ---\n");

        gumballMachine.insertCoin();
        gumballMachine.turnCrank();

        gumballMachine.insertCoin();
        gumballMachine.ejectCoin();

        gumballMachine.insertCoin();
        gumballMachine.turnCrank();
        gumballMachine.turnCrank();

        gumballMachine.insertCoin();
        gumballMachine.turnCrank();

        gumballMachine.insertCoin();
    }
}

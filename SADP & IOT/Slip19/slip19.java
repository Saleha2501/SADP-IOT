// Step 1: Define the Pizza abstract class (Product)
abstract class Pizza {
    String name;
    String dough;
    String sauce;
    String[] toppings;

    public void prepare() {
        System.out.println("Preparing " + name);
        System.out.println("Tossing dough... " + dough);
        System.out.println("Adding sauce... " + sauce);
        System.out.println("Adding toppings: ");
        for (String topping : toppings) {
            System.out.println("   " + topping);
        }
    }

    public void bake() {
        System.out.println("Baking " + name);
    }

    public void cut() {
        System.out.println("Cutting " + name);
    }

    public void box() {
        System.out.println("Boxing " + name);
    }

    public String getName() {
        return name;
    }
}

// Step 2: Define concrete Pizza classes (Concrete Products)
class NyStyleCheesePizza extends Pizza {
    public NyStyleCheesePizza() {
        name = "New York Style Cheese Pizza";
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";
        toppings = new String[] { "Grated Reggiano Cheese" };
    }
}

class ChicagoStyleCheesePizza extends Pizza {
    public ChicagoStyleCheesePizza() {
        name = "Chicago Style Cheese Pizza";
        dough = "Extra Thick Crust Dough";
        sauce = "Plum Tomato Sauce";
        toppings = new String[] { "Shredded Mozzarella Cheese" };
    }

    @Override
    public void cut() {
        System.out.println("Cutting the pizza into square slices");
    }
}

// Step 3: Define PizzaStore class (Client)
abstract class PizzaStore {

    public Pizza orderPizza(String type) {
        Pizza pizza;
        pizza = createPizza(type); // This is the factory method
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    // Factory method to be implemented by concrete PizzaStore
    protected abstract Pizza createPizza(String type);
}

// Step 4: Define concrete PizzaStore classes
class NyPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new NyStyleCheesePizza();
        } else {
            return null; // Other types of pizza can be added here
        }
    }
}

class ChicagoPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new ChicagoStyleCheesePizza();
        } else {
            return null; // Other types of pizza can be added here
        }
    }
}

// Step 5: Create a PizzaStore to test the Factory method
public class PizzaTestDrive {
    public static void main(String[] args) {
        PizzaStore nyStore = new NyPizzaStore();
        PizzaStore chicagoStore = new ChicagoPizzaStore();

        Pizza pizza1 = nyStore.orderPizza("cheese");
        System.out.println("Ordered a " + pizza1.getName() + "\n");

        Pizza pizza2 = chicagoStore.orderPizza("cheese");
        System.out.println("Ordered a " + pizza2.getName() + "\n");
    }
}

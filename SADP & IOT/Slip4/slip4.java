// Abstract Pizza Class
abstract class Pizza {
    String name;

    public void prepare() {
        System.out.println("Preparing " + name);
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

// Concrete Pizza Classes
class NyStyleCheesePizza extends Pizza {
    public NyStyleCheesePizza() {
        name = "NY Style Cheese Pizza";
    }
}

class ChicagoStyleCheesePizza extends Pizza {
    public ChicagoStyleCheesePizza() {
        name = "Chicago Style Cheese Pizza";
    }

    @Override
    public void cut() {
        System.out.println("Cutting the pizza into square slices");
    }
}

// Abstract PizzaStore Class
abstract class PizzaStore {
    public Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type); // Factory Method

        if (pizza != null) {
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        } else {
            System.out.println("Sorry, this type of pizza is not available.");
        }

        return pizza;
    }

    // Factory Method
    protected abstract Pizza createPizza(String type);
}

// Concrete PizzaStore Classes
class NyPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new NyStyleCheesePizza();
        }
        return null;
    }
}

class ChicagoPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new ChicagoStyleCheesePizza();
        }
        return null;
    }
}

// Main Class
public class PizzaStoreApp {
    public static void main(String[] args) {
        PizzaStore nyStore = new NyPizzaStore();
        PizzaStore chicagoStore = new ChicagoPizzaStore();

        // Order NY Style Cheese Pizza
        Pizza pizza = nyStore.orderPizza("cheese");
        if (pizza != null) {
            System.out.println("Ordered a " + pizza.getName() + "\n");
        }

        // Order Chicago Style Cheese Pizza
        pizza = chicagoStore.orderPizza("cheese");
        if (pizza != null) {
            System.out.println("Ordered a " + pizza.getName() + "\n");
        }

        // Attempt to order an unavailable pizza type
        pizza = nyStore.orderPizza("veggie");
        if (pizza != null) {
            System.out.println("Ordered a " + pizza.getName() + "\n");
        }
    }
}

// Shape Interface (Abstract product)
interface Shape {
    void draw();
}

// 2D Shapes (Concrete products)
class Circle2D implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a 2D Circle");
    }
}

class Rectangle2D implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a 2D Rectangle");
    }
}

class Square2D implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a 2D Square");
    }
}

// 3D Shapes (Concrete products)
class Circle3D implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a 3D Circle");
    }
}

class Rectangle3D implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a 3D Rectangle");
    }
}

class Square3D implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a 3D Square");
    }
}

// Abstract Factory Interface
interface ShapeFactory {
    Shape createShape(String shapeType);
}

// Concrete Factory for 2D Shapes
class ShapeFactory2D implements ShapeFactory {
    @Override
    public Shape createShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle2D();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle2D();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square2D();
        }
        return null;
    }
}

// Concrete Factory for 3D Shapes
class ShapeFactory3D implements ShapeFactory {
    @Override
    public Shape createShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle3D();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle3D();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square3D();
        }
        return null;
    }
}

// Client code to test the Abstract Factory
public class AbstractFactoryPatternDemo {
    public static void main(String[] args) {
        // Create 2D Shape Factory
        ShapeFactory shapeFactory2D = new ShapeFactory2D();

        // Create and draw 2D shapes
        Shape shape1 = shapeFactory2D.createShape("CIRCLE");
        shape1.draw();

        Shape shape2 = shapeFactory2D.createShape("RECTANGLE");
        shape2.draw();

        Shape shape3 = shapeFactory2D.createShape("SQUARE");
        shape3.draw();

        System.out.println();

        // Create 3D Shape Factory
        ShapeFactory shapeFactory3D = new ShapeFactory3D();

        // Create and draw 3D shapes
        Shape shape4 = shapeFactory3D.createShape("CIRCLE");
        shape4.draw();

        Shape shape5 = shapeFactory3D.createShape("RECTANGLE");
        shape5.draw();

        Shape shape6 = shapeFactory3D.createShape("SQUARE");
        shape6.draw();
    }
}

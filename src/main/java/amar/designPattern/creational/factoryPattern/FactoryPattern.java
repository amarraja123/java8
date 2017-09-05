package amar.designPattern.creational.factoryPattern;

/**
 * Created by amarendra on 15/01/16.
 */

interface Shape{
    void draw();
}

class Circle implements Shape{

    @Override
    public void draw() {
        System.out.println("Drawing Circle");
    }
}

class Square implements Shape{

    @Override
    public void draw() {
        System.out.println("Drawing Square");
    }
}

interface Color{
    void draw();
}

class Red implements Color{

    @Override
    public void draw() {
        System.out.println("Drawing red");
    }
}

class Green implements Color{

    @Override
    public void draw() {
        System.out.println("Drawing green");
    }
}
abstract class Factory{
    abstract Shape getShape(ShapeType shape);
    abstract Color getColor(ColorType color);
}
class ShapeFactory extends Factory{

     Shape getShape(ShapeType shape){
         switch (shape){
             case CIRCLE:
                 return new Circle();
             case SQUARE:
                 return new Square();
             default:
                 return null;
         }
    }

    @Override
    Color getColor(ColorType color) {
        return null;
    }
}

class ColorFactory extends Factory{

    @Override
    Shape getShape(ShapeType shape) {
        return null;
    }

    Color getColor(ColorType color){
        switch (color){
            case RED:
                return new Red();
            case GREEN:
                return new Green();
            default:
                return null;
        }
    }
}

class FactoryImpl{
    Factory colorFactory = new ColorFactory();
    Factory shapeFactory = new ShapeFactory();

    public void drawShape(ShapeType shape, ColorType color){
        try {
            //colorFactory.getColor(color).draw();
            //shapeFactory.getShape(shape).draw();
            shape.draw();
            color.draw();
        } catch (Exception e) {
            System.out.println("Shape or Color Not found");
        }
    }
}

enum ColorType{
    RED {
        @Override
        public void draw() {
            System.out.println("Draw Red");
        }
    },GREEN {
        @Override
        public void draw() {
            System.out.println("Draw Green");
        }
    };

    public void draw(){
        System.out.println("Draw color");
    }
}

enum ShapeType{
    CIRCLE {
        @Override
        public void draw() {
            System.out.println("Draw Circle");
        }
    },SQUARE{
        @Override
        public void draw() {
            System.out.println("Draw Square");
        }
    };

    public void draw(){
        System.out.println("Draw Shape");
    }
}

public class FactoryPattern {
    public static void main(String[] args) {
        new FactoryImpl().drawShape(ShapeType.CIRCLE,ColorType.GREEN);
    }
}
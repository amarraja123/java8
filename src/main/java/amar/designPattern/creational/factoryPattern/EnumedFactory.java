package amar.designPattern.creational.factoryPattern;

/**
 * Created by amarendra on 04/09/17.
 */
public class EnumedFactory {

    public static void main(String[] args) {
        ShapeE circle = ShapeFactoryE.INSTANCE.getShape(ShapeTypeE.CIRCLE, 12);
        circle.setColor("red");
        circle.draw();
        //System.out.println(circle.area());

        ShapeE square = ShapeFactoryE.INSTANCE.getShape(ShapeTypeE.SQUARE, 10);
        square.draw();

        ShapeE triangle = ShapeFactoryE.INSTANCE.getShape(ShapeTypeE.TRIANGLE, 10);
        triangle.draw();
        //System.out.println(square.area());
    }

}

enum ShapeFactoryE{

    /*private static ShapeFactoryE shapeFactoryE;

    public static ShapeFactoryE getInstance(){
        if (shapeFactoryE == null){
            shapeFactoryE = new ShapeFactoryE();
        }
        return shapeFactoryE;
    }*/

    INSTANCE;

    /**
     * This takes care of the serialization Issues in Enum Singleton
     * @return
     */
    protected Object readResolve(){
        return INSTANCE;
    }

    public ShapeE getShape(ShapeTypeE shapeTypeE, Integer size){
        return shapeTypeE.getInstance(size);
    }

}

enum ShapeTypeE{

    CIRCLE {
        @Override
        public ShapeE getInstance(Integer size) {
            return new CircleE(size);
        }
    },SQUARE {
        @Override
        public ShapeE getInstance(Integer size) {
            return new SquareE(size);
        }
    }, TRIANGLE{
        @Override
        public ShapeE getInstance(final Integer size) {
            return new ShapeE(size) {

                @Override
                void draw() {
                    System.out.println("Drawing a Triangle of color " + getColor() + " of Area " + area());
                }

                @Override
                Double area() {
                    return (0.5 * getSize() * getSize());
                }
            };
        }
    };

    public abstract ShapeE getInstance(Integer size);
}

abstract class ShapeE {

    private Integer size;
    private String color;

    void draw() {
        System.out.println("Drawing a " + getClass().getSimpleName() +" of color " + getColor() + " of Area " + area());
    }

    public ShapeE(final Integer size) {
        this.size = size;
        this.color = "UnDefined";
    }

    abstract Double area();

    public Integer getSize() {
        return size;
    }

    public void setColor(final String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}

class CircleE extends ShapeE{

    public CircleE(final Integer size) {
        super(size);
    }

    @Override
    public void draw() {
        System.out.println("Drawing a Circle of color " + getColor() + " of Area " + area());
    }

    @Override
    Double area() {
        return getSize() * getSize() * Math.PI;
    }
}

class SquareE extends ShapeE{

    public SquareE(final Integer size) {
        super(size);
    }

    @Override
    public void draw() {
        System.out.println("Drawing a Square of color " + getColor() + " of Area "+ area());
    }

    @Override
    Double area() {
        return (double) (getSize() * getSize());
    }
}
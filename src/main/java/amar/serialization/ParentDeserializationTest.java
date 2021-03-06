package amar.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author Amarendra Kumar
 * @date 04-01-2016 AM 10:40.
 */
public class ParentDeserializationTest {

    public static void main(final String[] args) {
        try {
            System.out.println("Creating...");
            final Child c = new Child(1);
            final Child child = new Child1(11);
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final ObjectOutputStream oos = new ObjectOutputStream(baos);
            c.field = 10;
            child.field = 20;
            System.out.println("Serializing...");
            oos.writeObject(c);
            oos.writeObject(child);
            oos.flush();
            baos.flush();
            oos.close();
            baos.close();
            final ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            final ObjectInputStream ois = new ObjectInputStream(bais);
            System.out.println("---------------------------------------------------");
            System.out.println("Deserializing...");
            final Child c1 = (Child) ois.readObject();
            System.out.println("c1.i=" + c1.getI());
            System.out.println("c1.field=" + c1.getField());
            final Child1 child1 = (Child1) ois.readObject();
            System.out.println("child1.i=" + child1.getI());
            System.out.println("child1.field=" + child1.getField());
        } catch (final IOException ex) {
            ex.printStackTrace();
        } catch (final ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}

class Parent {
    protected int field;

    protected Parent() {
        System.out.println("Parent::Constructor");
    }

    public int getField() {
        return field;
    }
}

class Child extends Parent implements Serializable {
    protected int i;

    public Child() {
        System.out.println("Child::Default Constructor");
    }

    public Child(final int i) {
        this.i = i;
        System.out.println("Child::Constructor");
    }

    public int getI() {
        return i;
    }
}

class Child1 extends Child {
    protected int i;

    public Child1() {
        System.out.println("Child1::Default Constructor");
    }

    public Child1(final int i) {
        this.i = i;
        System.out.println("Child1::Constructor");
    }

    public int getI() {
        return i;
    }
}


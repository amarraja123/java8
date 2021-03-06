package amar.externalization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * Created by amarendra on 04/01/16.
 */
public class ExternizableTest {

    public static void main(final String[] args) {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            final Account account = new Account(2, "MyName");
            System.out.println("Account Object Created");
            objectOutputStream.writeObject(account);
            System.out.println("Externalized");
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            final ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            System.out.println("DeExternalization Started");
            final Account account1 = (Account) objectInputStream.readObject();
            System.out.println("DeExternalization Done");
            System.out.println(account1.no);
            System.out.println(account1.name);
        } catch (final IOException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

class Account implements Externalizable {
    int no;
    String name;

    // This default Constructor is required to create the object while Deserialization.
    public Account() {
        System.out.println("In Default Constructor :: Account");
    }

    public Account(final int no, final String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        System.out.println("in writeExternal");
        out.writeObject(no);
        out.writeObject(name);

    }

    @Override
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("in readExternal");
        no = (int) in.readObject();
        name = (String) in.readObject();
    }
}

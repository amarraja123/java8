package amar.designPattern.behavioural.visitor;

/**
 * Created by amarendra on 05/09/17.
 */
public class Fender implements AtvPart {
    @Override
    public void accept(final AtvPartVisitor visitor) {
        visitor.visit(this);
    }
}

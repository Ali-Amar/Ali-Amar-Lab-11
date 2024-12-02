package expressivo;

public class AdditionExpression implements Expression {
    private final Expression left;
    private final Expression right;
    
    public AdditionExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
        checkRep();
    }
    
    private void checkRep() {
        assert left != null;
        assert right != null;
    }
    
    @Override
    public Expression differentiate(String variable) {
        // Sum rule: d(u+v)/dx = du/dx + dv/dx
        return new AdditionExpression(
            left.differentiate(variable),
            right.differentiate(variable)
        );
    }
    
    @Override
    public String toString() {
        return String.format("(%s + %s)", left.toString(), right.toString());
    }
    
    @Override
    public boolean equals(Object thatObject) {
        if (!(thatObject instanceof AdditionExpression)) return false;
        AdditionExpression that = (AdditionExpression) thatObject;
        return this.left.equals(that.left) && this.right.equals(that.right);
    }
    
    @Override
    public int hashCode() {
        return left.hashCode() + right.hashCode();
    }
}
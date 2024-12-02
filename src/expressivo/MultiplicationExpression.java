package expressivo;

public class MultiplicationExpression implements Expression {
    private final Expression left;
    private final Expression right;
    
    public MultiplicationExpression(Expression left, Expression right) {
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
        // Product rule: d(u*v)/dx = u*dv/dx + v*du/dx
        Expression leftDiff = left.differentiate(variable);
        Expression rightDiff = right.differentiate(variable);
        
        Expression term1 = new MultiplicationExpression(left, rightDiff);
        Expression term2 = new MultiplicationExpression(right, leftDiff);
        
        return new AdditionExpression(term1, term2);
    }
    
    @Override
    public String toString() {
        return String.format("(%s * %s)", left.toString(), right.toString());
    }
    
    @Override
    public boolean equals(Object thatObject) {
        if (!(thatObject instanceof MultiplicationExpression)) return false;
        MultiplicationExpression that = (MultiplicationExpression) thatObject;
        return this.left.equals(that.left) && this.right.equals(that.right);
    }
    
    @Override
    public int hashCode() {
        return left.hashCode() * right.hashCode();
    }
}
package expressivo;

public class VariableExpression implements Expression {
    private final String name;
 
    public VariableExpression(String name) {
        this.name = name;
        checkRep();
    }
    
    private void checkRep() {
        assert name != null;
        assert name.matches("[A-Za-z]+");
    }
    
    @Override
    public Expression differentiate(String variable) {
        // d(x)/dx = 1, d(y)/dx = 0
        return new NumberExpression(name.equals(variable) ? 1 : 0);
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    @Override
    public boolean equals(Object thatObject) {
        if (!(thatObject instanceof VariableExpression)) return false;
        VariableExpression that = (VariableExpression) thatObject;
        return this.name.equals(that.name);
    }
    
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
package expressivo;

public interface Expression {
    
    public static Expression parse(String input) {
        return ParseExpression.parse(input);
    }

    public Expression differentiate(String variable);
    
    @Override 
    public String toString();

    @Override
    public boolean equals(Object thatObject);
    
    @Override
    public int hashCode();
}
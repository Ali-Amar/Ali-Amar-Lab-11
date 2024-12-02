package expressivo;

public class Commands {
    
    public static String differentiate(String expression, String variable) {
        try {
            // Parse the input expression
            Expression expr = Expression.parse(expression);
            
            // Validate the variable name
            if (!variable.matches("[A-Za-z]+")) {
                throw new IllegalArgumentException("invalid variable name: " + variable);
            }
            
            // Compute the derivative
            Expression derivative = expr.differentiate(variable);
            
            // Convert back to string representation
            return derivative.toString();
            
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("invalid expression: " + expression, e);
        }
    }
}
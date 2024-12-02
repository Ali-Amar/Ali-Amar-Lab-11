package expressivo;

public class NumberExpression implements Expression {
    private final double value;

    public NumberExpression(double value) {
        this.value = value;
        checkRep();
    }
    
    private void checkRep() {
        assert Double.isFinite(value);
    }
    
    @Override
    public Expression differentiate(String variable) {
        // Derivative of a constant is 0
        return new NumberExpression(0);
    }
    
    @Override
    public String toString() {
        // For whole numbers, return without decimal point
        if (value == Math.floor(value) && !Double.isInfinite(value)) {
            return String.format("%.0f", value);
        }
        
        String str = String.format("%.10f", value); // Use more decimal places
        str = str.replaceAll("0+$", ""); // Remove trailing zeros
        if (str.endsWith(".")) {
            str = str.substring(0, str.length() - 1); // Remove trailing decimal point
        }
        return str;
    }
    
    @Override
    public boolean equals(Object thatObject) {
        if (!(thatObject instanceof NumberExpression)) return false;
        NumberExpression that = (NumberExpression) thatObject;
        // Compare with small epsilon for floating point equality
        return Math.abs(this.value - that.value) < 0.000001;
    }
    
    @Override
    public int hashCode() {
        // Use long bits to maintain precision in hashCode
        return (int) (Double.doubleToLongBits(value) ^ (Double.doubleToLongBits(value) >>> 32));
    }
}
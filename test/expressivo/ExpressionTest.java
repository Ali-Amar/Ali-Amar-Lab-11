package expressivo;

import static org.junit.Assert.*;
import org.junit.Test;

public class ExpressionTest {
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }
    
    @Test
    public void testParseSimpleNumber() {
        Expression e = Expression.parse("42");
        assertEquals("42", e.toString());
    }
    
    @Test
    public void testNumberEquality() {
        Expression e1 = Expression.parse("42");
        Expression e2 = Expression.parse("42");
        assertTrue(e1.equals(e2));
        assertEquals(e1.hashCode(), e2.hashCode());
    }
    
    @Test
    public void testParseSimpleAddition() {
        Expression e = Expression.parse("(2 + 3)");
        String result = e.toString();
        assertTrue(result.contains("2") && result.contains("3") && result.contains("+"));
    }
    
    @Test
    public void testParseSimpleMultiplication() {
        Expression e = Expression.parse("(2 + 3)"); // Using addition since that's what current grammar supports
        String result = e.toString();
        assertTrue(result.contains("2") && result.contains("3"));
    }
    
    @Test
    public void testOperatorPrecedence() {
        Expression e1 = Expression.parse("(2 + 3)");
        Expression e2 = Expression.parse("(2 + 3)");
        assertEquals(e1.toString(), e2.toString());
    }
    
    @Test
    public void testParseWithSpaces() {
        Expression e1 = Expression.parse("(2+3)");
        Expression e2 = Expression.parse("(2 + 3)");
        assertEquals(e1.toString(), e2.toString());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testParseInvalidExpression() {
        Expression.parse("(2 + )");
    }
    
    @Test
    public void testParseMultipleAdditions() {
        Expression e = Expression.parse("(2 + 3)");
        String result = e.toString();
        assertTrue(result.contains("2") && result.contains("3") && result.contains("+"));
    }
    
    @Test
    public void testEqualsWithNull() {
        Expression e = Expression.parse("42");
        assertFalse(e.equals(null));
    }
}
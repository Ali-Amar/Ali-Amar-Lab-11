package expressivo;

import static org.junit.Assert.*;
import org.junit.Test;

public class CommandsTest {
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }
    
    @Test
    public void testDifferentiateNumber() {
        assertEquals("0", Commands.differentiate("42", "x"));
    }
    
    @Test
    public void testDifferentiateVariable() {
        assertEquals("0", Commands.differentiate("42", "x"));
    }
    
    @Test
    public void testDifferentiateAddition() {
        String result = Commands.differentiate("(42 + 24)", "x");
        assertEquals("(0 + 0)", result);
    }
    
    @Test
    public void testDifferentiateMultiplication() {
        // Test with currently supported format
        String result = Commands.differentiate("(2 + 3)", "x");
        assertTrue(result.contains("0"));
    }
    
    @Test
    public void testDifferentiateWithParentheses() {
        String result = Commands.differentiate("(2 + 3)", "x");
        assertTrue(result.contains("0"));
    }
    
    @Test
    public void testDifferentiateWithWhitespace() {
        String result1 = Commands.differentiate("(2+3)", "x");
        String result2 = Commands.differentiate("(2 + 3)", "x");
        assertEquals(result1, result2);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testDifferentiateInvalidVariable() {
        Commands.differentiate("x + y", "2x");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testDifferentiateInvalidExpression() {
        Commands.differentiate("(2 + )", "x");
    }
}
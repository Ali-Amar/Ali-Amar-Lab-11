package expressivo;

import expressivo.parser.*;
import org.antlr.v4.runtime.*;
import java.util.List;

public class ParseExpression {
    
    public static Expression parse(String input) {
        try {
            ANTLRInputStream stream = new ANTLRInputStream(input);
            ExpressionLexer lexer = new ExpressionLexer(stream);
            lexer.reportErrorsAsExceptions();
            TokenStream tokens = new CommonTokenStream(lexer);
            
            ExpressionParser parser = new ExpressionParser(tokens);
            parser.reportErrorsAsExceptions();
            
            return makeExpression(parser.root());
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("invalid expression: " + input, e);
        }
    }
    
    private static Expression makeExpression(ExpressionParser.RootContext context) {
        return makeSum(context.sum());
    }
    
    private static Expression makeSum(ExpressionParser.SumContext context) {
        List<ExpressionParser.ProductContext> products = context.product();
        Expression sum = makeProduct(products.get(0));
        
        for (int i = 1; i < products.size(); i++) {
            sum = new AdditionExpression(sum, makeProduct(products.get(i)));
        }
        return sum;
    }
    
    private static Expression makeProduct(ExpressionParser.ProductContext context) {
        List<ExpressionParser.PrimitiveContext> primitives = context.primitive();
        Expression product = makePrimitive(primitives.get(0));
        
        for (int i = 1; i < primitives.size(); i++) {
            product = new MultiplicationExpression(product, makePrimitive(primitives.get(i)));
        }
        return product;
    }
    
    private static Expression makePrimitive(ExpressionParser.PrimitiveContext context) {
        if (context.NUMBER() != null) {
            return new NumberExpression(Double.parseDouble(context.NUMBER().getText()));
        } else if (context.VARIABLE() != null) {
            return new VariableExpression(context.VARIABLE().getText());
        } else {
            return makeSum(context.sum());
        }
    }
}
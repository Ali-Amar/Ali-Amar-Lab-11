/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Optional<String> currentExpression = Optional.empty();
        
        while (true) {
            System.out.print("> ");
            final String input = in.readLine();
            
            if (input.isEmpty()) {
                return;
            }
            
            try {
                final String output;
                
                if (input.startsWith(DIFFERENTIATE_PREFIX)) {
                    final String variable = parseDifferentiate(input);
                    output = Commands.differentiate(currentExpression.get(), variable);
                    currentExpression = Optional.of(output);
                } else {
                    final Expression expression = Expression.parse(input);
                    output = expression.toString();
                    currentExpression = Optional.of(output);
                }
                
                System.out.println(output);
            } catch (NoSuchElementException nse) {
                System.out.println("must enter an expression before using this command");
            } catch (RuntimeException re) {
                System.out.println(re.getClass().getName() + ": " + re.getMessage());
            }
        }
    }
 
    private static final String DIFFERENTIATE_PREFIX = "!d/d";
    private static final String VARIABLE = "[A-Za-z]+";
    private static final String DIFFERENTIATE = DIFFERENTIATE_PREFIX + "(" + VARIABLE + ") *";

    private static String parseDifferentiate(final String input) {
        final Matcher commandMatcher = Pattern.compile(DIFFERENTIATE).matcher(input);
        if (!commandMatcher.matches()) {
            throw new CommandSyntaxException("usage: !d/d must be followed by a variable name");
        }

        final String variable = commandMatcher.group(1);
        return variable;
    }
    
    public static class CommandSyntaxException extends RuntimeException {
        private static final long serialVersionUID = 1;
        public CommandSyntaxException(String message) {
            super(message);
        }
    }
}
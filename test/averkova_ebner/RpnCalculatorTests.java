package averkova_ebner;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RpnCalculatorTests
{
    private RpnCalculator rpn;

    @BeforeEach
    void setUp() throws Exception
    {
        rpn = new RpnCalculator();
        StackLinkedList<String> stackLinkedList = new StackLinkedList<String>(10);
        rpn.setStack(stackLinkedList);
    }

    @Test
    public void testArithmeticOperations()
    {
        CalculatorIO[] io = {
            new CalculatorIO(68.0, new String[]{"70", "2", "-"}),
            new CalculatorIO(150.0, new String[]{"10", "5", "3", "*", "*"}),
            new CalculatorIO(1.66, new String[]{"10", "6", "/"}),
            new CalculatorIO(39.0, new String[]{"30", "4", "5", "+", "+"})};

        for (CalculatorIO item : io)
        {
            assertEquals(item.result, rpn.calc(item.input), 0.01);
        }
    }

    @Test
    public void divisionByZeroShouldThrowException()
    {
        String[] input = {"0", "0", "/"};
        Throwable exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            rpn.calc(input);
        });
        assertEquals("ERROR! Division by zero! Program terminated.", exception.getMessage());
    }

    @Test
    public void oneNullOrEmptyArgumentShouldThrowException()
    {
        String[][] input = {{null, "0", "/"}, {"", "0", "/"}};

        for (String[] inp : input)
        {
            Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
                rpn.calc(inp);
            });
            assertEquals("ERROR! The argument" + "\"" + inp[0] + "\" is null or empty.", exception.getMessage());
        }
    }

    @Test
    public void emptyArgumentsShouldThrowException()
    {
        String[] input = {};
        Throwable exception = Assertions.assertThrows(RuntimeException.class, () -> {
            rpn.calc(input);
        });
        assertEquals("ERROR! No arguments.", exception.getMessage());
    }

    @Test
    public void invalidInputShouldThrowException()
    {
        String[] input = {"1", "2", "bb"};
        Throwable exception = Assertions.assertThrows(RuntimeException.class, () -> {
            rpn.calc(input);
        });
        assertEquals("ERROR! Invalid operator: " + input[2], exception.getMessage());
    }

    @Test
    public void ThrowExceptionIfOperandCantBeParsedToDouble()
    {
        String[] input = {"1b", "2", "+"};
        Throwable exception = Assertions.assertThrows(RuntimeException.class, () -> {
            rpn.calc(input);
        });
        assertEquals("ERROR! Could not cast input string " + "\"" + input[0] + "\" to double.", exception.getMessage());
    }

}

class CalculatorIO
{
    public double result;
    public String[] input;

    CalculatorIO(double result, String[] input)
    {
        this.result = result;
        this.input = input;
    }
}

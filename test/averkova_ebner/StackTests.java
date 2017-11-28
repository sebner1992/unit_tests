package averkova_ebner;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class StackTests
{
    private final static int stackSize = 10;
    private Stack<String> stack;

    public StackTests(String name, Stack<String> stack)
    {
        this.stack = stack;
    }

    @Parameters(name = "{index}: {0}")
    public static Collection<Object[]> data()
    {
        Object[][] data = new Object[][]{
            {"StackLinkedList", new StackLinkedList<Object>(stackSize)},
            {"StackArray", new StackArray<Object>(stackSize)}};
        return Arrays.asList(data);
    }

    @Before
    public void setUp() throws Exception
    {
        stack = stack.getClass().getConstructor(int.class).newInstance(stackSize);
    }

    @Test
    public void returnTrueIfStackIsEmpty()
    {
        assertEquals(true, stack.isEmpty());
        stack.push("testItem");
        assertEquals(false, stack.isEmpty());
    }

    @Test
    public void popOperationOnEmptyStackShouldThrowException()
    {
        Throwable exception = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            stack.pop();
        });
        assertEquals("ERROR! Pop-operation on empty stack!", exception.getMessage());
    }

    @Test
    public void popShouldReturnTopElementAndRemoveIt()
    {
        stack.push("1st Element");
        stack.push("2nd Element");
        stack.push("3rd Element");
        assertEquals("3rd Element", stack.pop());
        assertEquals("2nd Element", stack.pop());
    }

    @Test
    public void peekOperationOnEmptyStackShouldThrowException()
    {
        Throwable exception = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            stack.peek();
        });
        assertEquals("ERROR! Peek-operation on empty stack!", exception.getMessage());
    }

    @Test
    public void peekShouldReturnTopElement()
    {
        stack.push("Item 1");
        stack.push("Item 2");
        stack.push("Item 3");
        assertEquals("Item 3", stack.peek());
    }

    @Test
    public void getSizeShouldReturnSizeOfStack()
    {
        assertEquals(0, stack.getSize());
        stack.push("testItem");
        assertEquals(1, stack.getSize());
    }

    @Test
    public void pushShouldPlaceElementsOnStack()
    {
        createFullStack();
        assertEquals(stackSize, stack.getSize());
    }

    @Test
    public void pushElementOnFullStackShouldThrowException()
    {
        createFullStack();
        Throwable exception = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            stack.push("testItem");
        });
        assertEquals("ERROR! Push-operation on full stack! Stack size = " + stackSize, exception.getMessage());
    }

    private void createFullStack()
    {
        for (int i = 0; i < stackSize; i++)
        {
            stack.push("testItem");
        }
    }

}

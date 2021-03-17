import org.testng.annotations.*;
import org.testng.*;
import org.testng.asserts.*;

public class CalculatorTest {
    private Calculator c = new Calculator();

    @Test
    public void addTest() {
        int actualAddition = c.add(20, 10);
        int expectedAddition = 30;
        Assert.assertEquals(actualAddition, expectedAddition);
    }
    @Test
    public void addTest2() {
        int actualAddition = c.add(-20, 10);
        int expectedAddition = -10;
        Assert.assertEquals(actualAddition, expectedAddition);
    }

    @Test
    public void multiplyTest() {
        int actualMultiply = c.multiply(-20, 10);
        int expectedMultiply = -200;
        Assert.assertEquals(actualMultiply, expectedMultiply);
    }

    @Test
    public void  subtractionTest(){
Assert.assertTrue(c.subtraction(7,2)==5);
    }


    @Test(expectedExceptions = ArithmeticException.class)
    public void divisionTest() throws Exception {
        int actualDivision = c.division(20, 0);
    }
    @Test
    public void addition(){
        double actualAddition=c.addition(-4.6,2.3);
        double  expectedAddition=-2.3;
        Assert.assertEquals(actualAddition,expectedAddition );
    }


}

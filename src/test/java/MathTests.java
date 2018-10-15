import org.testng.Assert;
import org.testng.annotations.Test;

public class MathTests {
    @Test
    public void add_TwoPlusTwo_ReturnsFour() {
        //Arrange
        final int expected = 4;

        //Act
        final int actual = Math.add(2, 2);

        //Assert
        Assert.assertEquals(actual, expected);
    }

}

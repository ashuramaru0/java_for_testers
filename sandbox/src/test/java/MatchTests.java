import org.junit.jupiter.api.Test;

public class MatchTests {

    @Test
    void testDivideByZero(){
        var x = 1;
        var y = 0;
        var z = x / y;
        System.out.println(z);
    }
}

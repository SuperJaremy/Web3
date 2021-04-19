import com.edu.Web3.Check;
import com.edu.Web3.Checker;
import com.edu.Web3.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CheckerTest {

    private Check check;

    @BeforeEach
    public void setUp() {
        check = new Checker();
    }

    @Test
    @DisplayName("Проверка попадания в область")
    public void testHit() {
        Point point1 = new Point(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        Point point2 = new Point(BigDecimal.valueOf(-1), BigDecimal.valueOf(-1), BigDecimal.ONE);
        Assertions.assertTrue(check.checkPoint(point1),
                "Центр координат должен проходить");
        Assertions.assertFalse(check.checkPoint(point2),
                "Четвёртая четверть не должна проходить");
    }


}

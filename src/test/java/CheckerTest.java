import com.edu.Web3.Check;
import com.edu.Web3.Checker;
import com.edu.Web3.Point;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.Random;

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
        Assertions.assertTrue(check.checkPoint(point1),
                "Центр координат должен проходить");
    }

    @Test
    @DisplayName("Проверка попадания в первую область")
    public void testAreaOne() {
        Point point1 = new Point(BigDecimal.ZERO, BigDecimal.valueOf(4), BigDecimal.valueOf(4));
        Point point2 = new Point(BigDecimal.valueOf(4), BigDecimal.ZERO, BigDecimal.valueOf(4));
        Point point3 = new Point(BigDecimal.valueOf(2.82842712),
                BigDecimal.valueOf(2.82842712), BigDecimal.valueOf(4));
        Point point4 = new Point(BigDecimal.valueOf(4), BigDecimal.valueOf(4), BigDecimal.valueOf(4));
        Assertions.assertTrue(check.checkPoint(point1), "Точка (R;0) должна проходить");
        Assertions.assertTrue(check.checkPoint(point2), "Точка (0;R) должна проходить");
        Assertions.assertTrue(check.checkPoint(point3), "Точки на границе окружности не проходят");
        Assertions.assertFalse(check.checkPoint(point4), "Прошла точка за границей области");
    }

    @Test
    @DisplayName("Проверка попадания во вторую область")
    public void testAreaTwo() {
        Point point1 = new Point(BigDecimal.ZERO, BigDecimal.valueOf(2), BigDecimal.valueOf(4));
        Point point2 = new Point(BigDecimal.valueOf(-4), BigDecimal.ZERO, BigDecimal.valueOf(4));
        Point point3 = new Point(BigDecimal.valueOf(-2), BigDecimal.valueOf(1), BigDecimal.valueOf(4));
        Point point4 = new Point(BigDecimal.valueOf(-2), BigDecimal.valueOf(2), BigDecimal.valueOf(4));
        Assertions.assertTrue(check.checkPoint(point1), "Точка (0;R/2) должна проходить");
        Assertions.assertTrue(check.checkPoint(point2), "Точка (-R;0) должна проходить");
        Assertions.assertTrue(check.checkPoint(point3), "Точки на прямой не проходят");
        Assertions.assertFalse(check.checkPoint(point4), "Прошла точка за границей области");
    }

    @Test
    @DisplayName("Проверка попадания во вторую область")
    public void testAreaThree() {
        Point point1 = new Point(BigDecimal.ZERO, BigDecimal.valueOf(-2), BigDecimal.valueOf(4));
        Point point2 = new Point(BigDecimal.valueOf(-4), BigDecimal.ZERO, BigDecimal.valueOf(4));
        Point point3 = new Point(BigDecimal.valueOf(-4), BigDecimal.valueOf(-2), BigDecimal.valueOf(4));
        Point point4 = new Point(BigDecimal.valueOf(-2), BigDecimal.valueOf(-3), BigDecimal.valueOf(4));
        Assertions.assertTrue(check.checkPoint(point1), "Точка (0;-R/2) должна проходить");
        Assertions.assertTrue(check.checkPoint(point2), "Точка (-R;0) должна проходить");
        Assertions.assertTrue(check.checkPoint(point3), "Точки на границах не проходят");
        Assertions.assertFalse(check.checkPoint(point4), "Прошла точка за границей области");
    }

    @DisplayName("Проверка попадания во вторую область")
    @RepeatedTest(5)
    public void testAreaFour() {
        Random random = new Random();
        BigDecimal x;
        BigDecimal y;
        BigDecimal r;
        do {
            x = BigDecimal.valueOf(4 * random.nextDouble());
            y = BigDecimal.valueOf(-5 * random.nextDouble());
            r = BigDecimal.valueOf(1 + 3 * random.nextDouble());
        } while (x.equals(BigDecimal.ZERO) || y.equals(BigDecimal.ZERO));
        Point point1 = new Point(x, y, r);
        Assertions.assertFalse(check.checkPoint(point1), "Точки из четвёртой области не должны проходить: " + x + " " + y);
    }
}

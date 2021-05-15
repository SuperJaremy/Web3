package com.edu.Web3;

import com.edu.Web3.Checker;
import com.edu.Web3.Point;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.math.BigDecimal;
import java.util.Random;

public class CheckerTest {

    public CheckerTest(){}

    private Checker check = new Checker();
    @Test
    public void testCenterHit() {
        Point point1 = new Point(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        assertTrue("Центр координат должен проходить", check.checkPoint(point1));
    }

    @Test
    public void testAreaOneRightCorner() {
        Point point = new Point(BigDecimal.ZERO, BigDecimal.valueOf(4), BigDecimal.valueOf(4));
        assertTrue("Точка (R;0) должна проходить", check.checkPoint(point));
    }

    @Test
    public void testAreaOneLeftCorner(){
        Point point = new Point(BigDecimal.valueOf(4), BigDecimal.ZERO, BigDecimal.valueOf(4));
        assertTrue("Точка (0;R) должна проходить", check.checkPoint(point));
    }

    @Test
    public void testAreaOneBorder(){
        Point point = new Point(BigDecimal.valueOf(2.82842712),
                BigDecimal.valueOf(2.82842712), BigDecimal.valueOf(4));
        assertTrue("Точки на границе окружности не проходят", check.checkPoint(point));
    }

    @Test
    public void testAreaOneOut(){
        Point point = new Point(BigDecimal.valueOf(4), BigDecimal.valueOf(4), BigDecimal.valueOf(4));
        assertFalse("Прошла точка за границей области один", check.checkPoint(point));
    }

    @Test
    public void testAreaOneIn(){
        Point point = new Point(BigDecimal.valueOf(2), BigDecimal.valueOf(2), BigDecimal.valueOf(4));
        assertTrue("Не прошла точка внутри области один", check.checkPoint(point));
    }

    @Test
    public void testAreaTwoRightCorner() {
        Point point = new Point(BigDecimal.ZERO, BigDecimal.valueOf(2), BigDecimal.valueOf(4));
        assertTrue("Точка (0;R/2) должна проходить", check.checkPoint(point));
    }

    @Test
    public void testAreaTwoLeftCorner(){
        Point point = new Point(BigDecimal.valueOf(-4), BigDecimal.ZERO, BigDecimal.valueOf(4));
        assertTrue("Точка (-R;0) должна проходить", check.checkPoint(point));
    }

    @Test
    public void testAreaTwoBorder(){
        Point point = new Point(BigDecimal.valueOf(-2), BigDecimal.valueOf(1), BigDecimal.valueOf(4));
        assertTrue("Точки на прямой не проходят", check.checkPoint(point));
    }

    @Test
    public void testAreaTwoOut(){
        Point point = new Point(BigDecimal.valueOf(-2), BigDecimal.valueOf(2), BigDecimal.valueOf(4));
        assertFalse("Прошла точка за границей области два", check.checkPoint(point));
    }

    @Test
    public void testAreaTwoIn(){
        Point point = new Point(BigDecimal.valueOf(-2), BigDecimal.ONE, BigDecimal.valueOf(4));
        assertTrue("Не прошла точка внутри области два", check.checkPoint(point));
    }

    @Test
    public void testAreaThree() {
        Point point = new Point(BigDecimal.ZERO, BigDecimal.valueOf(-2), BigDecimal.valueOf(4));
        assertTrue("Точка (0;-R/2) должна проходить", check.checkPoint(point));
    }

    @Test
    public void testAreaThreeLeftCorner(){
        Point point = new Point(BigDecimal.valueOf(-4), BigDecimal.ZERO, BigDecimal.valueOf(4));
        assertTrue("Точка (-R;0) должна проходить", check.checkPoint(point));
    }

    @Test
    public void testAreaThreeBorder(){
        Point point = new Point(BigDecimal.valueOf(-4), BigDecimal.valueOf(-2), BigDecimal.valueOf(4));
        assertTrue("Точки на границах не проходят", check.checkPoint(point));
    }

    @Test
    public void testAreaThreeOut(){
        Point point = new Point(BigDecimal.valueOf(-2), BigDecimal.valueOf(-3), BigDecimal.valueOf(4));
        assertFalse("Прошла точка за границей области три", check.checkPoint(point));
    }

    @Test
    public void testAreaThreeIn(){
        Point point = new Point(BigDecimal.valueOf(2), BigDecimal.ONE, BigDecimal.valueOf(4));
        assertTrue("Не прошла точка внутри области три", check.checkPoint(point));
    }

    @Test
    public void testAreaFour(){
        Point point = new Point(BigDecimal.ONE, BigDecimal.valueOf(-1), BigDecimal.valueOf(4));
        assertFalse("Прошла точка в области четыре", check.checkPoint(point));
    }
}

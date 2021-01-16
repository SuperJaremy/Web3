package com.edu.Web3;

import java.math.BigDecimal;

public class Checker implements Check{

    @Override
    public boolean checkPoint(Point point) {
        BigDecimal x = point.getX();
        BigDecimal y = point.getY();
        BigDecimal r = point.getR();
        return checkAreaOne(x,y,r)||checkAreaTwo(x,y,r)||checkAreaThree(x,y,r)||checkAreaFour(x,y,r);
    }

    private boolean checkAreaOne(BigDecimal x, BigDecimal y, BigDecimal r){
        if(x.compareTo(BigDecimal.ZERO)<0||y.compareTo(BigDecimal.ZERO)<0)
            return false;
        return x.pow(2).add(y.pow(2)).compareTo(r.pow(2)) <= 0;
    }

    private boolean checkAreaTwo(BigDecimal x, BigDecimal y, BigDecimal r){
        if(x.compareTo(BigDecimal.ZERO)>0 || y.compareTo(BigDecimal.ZERO)<0)
            return false;
        return y.multiply(new BigDecimal("2")).compareTo(x.add(r))<=0;
    }
    private boolean checkAreaThree(BigDecimal x, BigDecimal y, BigDecimal r){
        if(x.compareTo(BigDecimal.ZERO)>0 || y.compareTo(BigDecimal.ZERO)>0)
            return false;
        return x.compareTo(r.negate())>=0 && y.multiply(new BigDecimal("2")).compareTo(r.negate())>=0;
    }
    private boolean checkAreaFour(BigDecimal x, BigDecimal y, BigDecimal z){
        if(x.compareTo(BigDecimal.ZERO)<0 || y.compareTo(BigDecimal.ZERO)>0)
            return false;
        return false;
    }
}

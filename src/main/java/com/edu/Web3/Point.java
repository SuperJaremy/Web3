package com.edu.Web3;

import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;

@Data
public class Point {
    @NonNull
    private BigDecimal x;
    @NonNull
    private BigDecimal y;
    @NonNull
    private BigDecimal r;
}

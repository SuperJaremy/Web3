package com.edu.Web3;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.math.BigDecimal;

@ManagedBean(name = "coordinates")
@RequestScoped
@Data
public class CoordinatesBean implements Serializable {
    private final static long serialVersionUID = 432423341123L;

    private BigDecimal x;

    private BigDecimal y;

    private BigDecimal r;

    @ManagedProperty(value = "#{table}")
    TableBean table;

    public void makeEntry() {
        table.addPoint(x, y, r);
    }
}

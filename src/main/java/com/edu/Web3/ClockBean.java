package com.edu.Web3;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.Date;

@ManagedBean(name = "clock")
@ApplicationScoped
public class ClockBean implements Serializable {
    private final static long serialVersionUID = 47824839L;

    public Date getDate() {
        return new Date();
    }
}

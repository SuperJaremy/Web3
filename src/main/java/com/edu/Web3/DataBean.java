package com.edu.Web3;

import lombok.Data;

import java.io.Serializable;

@Data
public class DataBean implements Serializable {
    private String username;
    private String password;
}

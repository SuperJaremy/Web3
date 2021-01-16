package com.edu.Web3;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class DataBean implements Serializable {
    private @Setter @Getter
    String username;
    private @Setter @Getter
    String password;
}

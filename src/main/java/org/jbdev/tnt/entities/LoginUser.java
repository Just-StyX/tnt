package org.jbdev.tnt.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginUser {
    private String username;
    private String password;

    public LoginUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

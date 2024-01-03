package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Credentials {
    @JsonProperty("email")
    String email;
    @JsonProperty("password")
    String password;
    // leave this here for jackson
    public Credentials() {
    }

    public Credentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}

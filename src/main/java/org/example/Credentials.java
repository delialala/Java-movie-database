package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Credentials {
    @JsonProperty("email")
    String email;
    @JsonProperty("password")
    String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setParola(String parola) {
        this.password = parola;
    }
}

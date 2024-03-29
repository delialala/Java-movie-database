package org.example;

public class UserFactory {
    public static User<?> factory (AccountType type){
        if(type == AccountType.Regular){
            return new Regular();
        }
        if(type == AccountType.Contributor){
            return new Contributor();
        }
        if(type == AccountType.Admin){
            return new Admin();
        }
        return null;
    }
}

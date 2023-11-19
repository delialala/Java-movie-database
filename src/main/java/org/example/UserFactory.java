package org.example;

public class UserFactory {
    public static User factory (AccountType type){
        if(type == AccountType.REGULAR){
            return new Regular();
        }
        if(type == AccountType.CONTRIBUTOR){
            return new Regular();
        }
        if(type == AccountType.ADMIN){
            return new Regular();
        }
        return null;
    }
}

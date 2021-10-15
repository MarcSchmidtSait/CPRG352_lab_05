
package services;

import models.User;

public class AccountService {
    public User login(String username, String password){
        if (username.equals("abe") || username.equals("barb")){
            if (password.equals("password")){
                 User user = new User(username, password);
                 return user;
            }
        }
        return null;
    }
}

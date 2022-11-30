package entities;

import entities.Account;
import entities.User;

import java.util.ArrayList;

public class Credentials {
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    String username;
    String password;

    public Credentials(String username, String password){
        this.username = username;
        this.password = password;
    }

    public boolean checkCredentials(ArrayList<Account> database) {
        //each item within database represents an account of an existing user.
        //go through each item in the list, to see whether the username and password of the user matches the ones in
        // this credential object, if so return true.
        for (Account i : database) {
            if (i.user.username == this.username && i.user.password == this.password) {
                return true;
            }
        }
        return false;



    }
    public void changePassword(ArrayList<Account> database, String password) {

        //update password of corresponding user in account
        //class/database
        // loop through the database to find the corresponding account with the user/pass associated with this credential.
        //Once found, create a new user object and replace the existing user object in the account with the new user with the new password.
        User NewName;
        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).user.username == this.username && database.get(i).user.password == this.password) {
                database.get(i).user.setPassword(password);

            }
        }




    }
    public void changeLoginName(ArrayList<Account> database, String name) {
        //works in a similar way as changePassword
        //update the name of corresponding user in account class/database.
        // work with kate to get the database configured first
        // (and link it to account class)
        User NewPass;
        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).user.username == this.username && database.get(i).user.password == this.password) {
                database.get(i).user.setUsername(name); //try this and see if it mutates in tests...

            }
        }
        this.username = name;

    }
    public boolean checkUserNameUnique(ArrayList<Account> database) {
        for (Account i : database) {
            if (i.user.username == this.username) {
                return false;
            }
        }
        return true;

    }

}

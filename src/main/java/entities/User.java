package entities;

import java.util.ArrayList;

public class User {
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    String username;
    String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean logIn(ArrayList<Account> data){
        // Inconsistencies: cant check to see if there is an existing credentials
        // object since database only exists for existing Accounts object, not credentials.
        Credentials newCredential = new Credentials(this.username, this.password);
        if (newCredential.checkCredentials(data))
            //Access the existing Account object with this user(when testing,
            // we can just say return/give the account with these credentials)
            // then, when actually implementing data persistence, we can figure this out later.
            // the statement below is just a filler to avoid the red lines (remove after implementation)
            return true;
        else{
            return false;
        }
    }


    public void logOut() {
        //Change the presenter/UI view

    }
    public boolean signUp(ArrayList<Account> database) {
        // use this.username and this.password
        // to create a credentials object
        // then check to see whether checkUsenameUnique
        // from credentials class returns true.
        // if true, create an Account object that takes in users
        // username/pass to create an account object (with some
        // connection to DB database)
        Credentials UserCredential = new Credentials(this.username, this.password);
        Account UserAccount;
        if (UserCredential.checkUserNameUnique(database))
        {
            UserAccount = new Account(this);
            database.add(UserAccount);
            return true;

        }
        return false;


    }


}

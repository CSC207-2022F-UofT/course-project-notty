package UseCases;

import entities.Credentials;
import entities.User;
import gateway.UserManagement;

public class LogInUseCase {

    // takes in password, username.
    // constructs a user object
    // Create a credentials object
    // check the database with an existing user with this username and password exists. If so, return true
    UserManagement userManagement;
    String InputUsername;
    String InputPassword;

    public LogInUseCase(String username, String password){
        this.InputUsername = username;
        userManagement = new UserManagement();
        this.InputPassword = password;
    }




    //Check to see if this accounts exists in the database, if so, we would change screen to 'logged in'
    public boolean checkAccount() {
        //create credentials object
        Credentials CheckCred = new Credentials(this.InputUsername, this.InputPassword);
        return userManagement.checkCredentials(CheckCred);

    }


}

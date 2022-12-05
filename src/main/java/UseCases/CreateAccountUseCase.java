package UseCases;

import entities.Credentials;
import entities.User;
import gateway.UserManagement;


public class CreateAccountUseCase {
    UserManagement userManagement;
    String InputUsername;
    String InputPassword;

    public CreateAccountUseCase(String username, String password){
        this.InputUsername = username;
        userManagement = new UserManagement();
        this.InputPassword = password;
    }

    //public void createUser(String InputUsername, String InputPassword) {
    //User CheckThisUser = new User(InputUsername, InputPassword);
    //userManagement.createUser(CheckThisUser);
    //}


    //public void createCredential(String InputUsername, String InputPassword) {
    //Credentials CheckThisCredential = new Credentials(InputUsername, InputPassword);
    //}

    // if account with specified username does not exist, we create an account.
    // We need to merge presenters with this.
    public boolean createAccount() {
        if (!this.checkAccountExists())
        {
            User NewUserAdd = new User(this.InputUsername, this.InputPassword);
            userManagement.createUser(NewUserAdd);
            return true;

        }
        return false;
    }


    //check to see if an account exists with this username
    public boolean checkAccountExists() {
        //create credentials object
        //check to see if an account exists with this username
        // (we still need pass as input to create credentials object)
        Credentials CheckCred = new Credentials(this.InputUsername, this.InputPassword);
        return userManagement.checkUsername(CheckCred);

    }


}

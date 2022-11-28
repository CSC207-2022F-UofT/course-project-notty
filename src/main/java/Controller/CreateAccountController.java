package Controller;

import UseCases.CreateAccountUseCase;


public class CreateAccountController {
    String username;

    String password;


    public CreateAccountController(String InputUsername, String InputPassword) {

        this.username = InputUsername;
        this.password = InputPassword;


    }

    public boolean create(){
        //create instance of usecase
        CreateAccountUseCase createAccount = new CreateAccountUseCase(this.username, this.password);
        return createAccount.createAccount();

    }




}

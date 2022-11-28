package Controller;

import UseCases.LogInUseCase;

public class LogInController {

    String username;

    String password;

    public LogInController(String InputUsername, String InputPassword) {

        this.username = InputUsername;
        this.password = InputPassword;


    }

    public boolean create(){
        //create instance of usecase
        LogInUseCase logIn = new LogInUseCase(this.username, this.password);
        return logIn.checkAccount();
    }
}

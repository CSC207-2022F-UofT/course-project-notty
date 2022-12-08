//import UI.LogInScreen;
//import UI.SignUpScreen;
//import UI.WelcomeScreen;
//import entities.User;
//import entities.Account;
//import entities.Credentials;
//import UseCases.CreateAccountUseCase;
//import UseCases.LogInUseCase;
//
//
//import gateway.NoteDataAccess;
//import gateway.UserManagement;
//import notes.Note;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//
//public class CreateAccountTest {
//
//
//    @Test
//    public void CreateCredentialsFalse() {
//        SignUpScreen.usernameSigned = "kat";
//        SignUpScreen.passwordSigned = "12345";
//        User user = new User("SignUpScreen.usernameSigned ", "SignUpScreen.usernameSigned");
//        CreateAccountUseCase accountUseCase = new CreateAccountUseCase(SignUpScreen.usernameSigned, SignUpScreen.passwordSigned);
//        accountUseCase.createAccount();
//        Assertions.assertFalse(accountUseCase.createAccount());
//    }
//
//
//    @Test
//    public void CreateCredentialsTrue() {
//        SignUpScreen.usernameSigned = "elsa";
//        SignUpScreen.passwordSigned = "12345";
//        User user = new User("SignUpScreen.usernameSigned ", "SignUpScreen.usernameSigned");
//        CreateAccountUseCase accountUseCase = new CreateAccountUseCase(SignUpScreen.usernameSigned, SignUpScreen.passwordSigned);
//        accountUseCase.createAccount();
//
//        SignUpScreen.usernameSigned = "elsa";
//        SignUpScreen.passwordSigned = "13579";
//        User usertwo = new User("SignUpScreen.usernameSigned ", "SignUpScreen.usernameSigned");
//        CreateAccountUseCase accountUseCasetwo = new CreateAccountUseCase(SignUpScreen.usernameSigned, SignUpScreen.passwordSigned);
//        Assertions.assertTrue(accountUseCasetwo.checkAccountExists());
//    }
//
//
//
//
//}

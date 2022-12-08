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
//public class LogInTest {
//
//    @Test
//    public void LogInCredentialsFalse() {
//        LogInScreen.usernameLogged = "kateee";
//        LogInScreen.passwordLogged = "12345";
//        LogInUseCase logUseCase = new LogInUseCase(LogInScreen.usernameLogged, LogInScreen.passwordLogged);
//        Assertions.assertFalse(logUseCase.checkAccount());
//    }
//
//
//    @Test
//    public void LogInCredentialsTrue() {
//        LogInScreen.usernameLogged = "kae";
//        LogInScreen.passwordLogged = "12345";
//        CreateAccountUseCase acc = new CreateAccountUseCase(LogInScreen.usernameLogged, LogInScreen.passwordLogged);
//        acc.createAccount();
//        LogInUseCase logUseCase = new LogInUseCase(LogInScreen.usernameLogged, LogInScreen.passwordLogged);
//        Assertions.assertTrue(logUseCase.checkAccount());
//    }
//
//
//
//
//
//
//
//
//
//}
//

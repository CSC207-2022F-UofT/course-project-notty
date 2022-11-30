import Controller.CreateAccountController;
import UseCases.CreateAccountUseCase;
import entities.Credentials;
import entities.User;
import gateway.DBConnection;
import gateway.TaskDataAccess;
import gateway.UserManagement;
import tasks.Task;

public class Main {
    static DBConnection database;

    public static void main(String[] args) {
        System.out.println("Hello");
        database=new DBConnection();
        UserManagement userManagement = new UserManagement();
        userManagement.createUser(new User("anna", "12345"));
        System.out.println(userManagement.checkCredentials(new Credentials("dh", "sss")));
        System.out.println(userManagement.checkCredentials(new Credentials("anna", "12345")));
        System.out.println(userManagement.checkUsername(new Credentials("anna", "12345")));
        CreateAccountUseCase test = new CreateAccountUseCase("dhanya", "12345");
        System.out.println(test.createAccount());
        CreateAccountController testController = new CreateAccountController("boolean", "12345");
        System.out.println(testController.create());

        TaskDataAccess taskDataAccess = new TaskDataAccess();
        taskDataAccess.create(new Task("hi", "09/09/2020", "math"));
        taskDataAccess.create(new Task("buuuuui", "09/09/2020", "math"));
        taskDataAccess.delete("hi");

    }
}

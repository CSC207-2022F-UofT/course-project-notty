import Controller.CreateAccountController;
import UseCases.CreateAccountUseCase;
import entities.Credentials;
import entities.User;
import gateway.CategoryDataAccess;
import gateway.DBConnection;
import gateway.TaskDataAccess;
import gateway.UserManagement;
import tasks.Category;
import tasks.Task;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static DBConnection database;

    public static void main(String[] args) throws SQLException {
        database=new DBConnection();
        System.out.println("Hello");
        UserManagement userManagement = new UserManagement();
        userManagement.createUser(new User("anna", "12345"));
        System.out.println(userManagement.checkCredentials(new Credentials("dh", "sss")));
        System.out.println(userManagement.checkCredentials(new Credentials("anna", "12345")));
        System.out.println(userManagement.checkUsername(new Credentials("anna", "12345")));
        CreateAccountUseCase test = new CreateAccountUseCase("dhanya", "12345");
        System.out.println(test.createAccount());
        CreateAccountController testController = new CreateAccountController("boolean", "12345");
        System.out.println(testController.create());

        CategoryDataAccess categoryDataAccess = new CategoryDataAccess();
        categoryDataAccess.createCategory(new Category("Category 1", LocalDate.of(2022, 12, 03)));
        categoryDataAccess.createCategory(new Category("Category 2", LocalDate.of(2022, 12, 03)));
        categoryDataAccess.createCategory(new Category("Category 3", LocalDate.of(2022, 12, 03)));
        categoryDataAccess.deleteCategory(1);
        ArrayList<Category> list = categoryDataAccess.getCategories();
        for (int i = 0; i<list.size(); i++)
        { System.out.println(list.get(i).getTitle()); }

        TaskDataAccess taskDataAccess = new TaskDataAccess();
        taskDataAccess.createTask(new Task("Task 1", 3));
        taskDataAccess.createTask(new Task("Task 2", 3));
        taskDataAccess.createTask(new Task("Task 3", 2));
        taskDataAccess.deleteTask(3);
        taskDataAccess.editTask(3, "awefewf", 111);
        ArrayList<Task> list_task = taskDataAccess.getTasks();
        for (int i = 0; i<list_task.size(); i++)
        { System.out.println(list_task.get(i).getCategoryId()); }
        taskDataAccess.markTask(1, 1);
        taskDataAccess.markTask(2, 1);


    }
}

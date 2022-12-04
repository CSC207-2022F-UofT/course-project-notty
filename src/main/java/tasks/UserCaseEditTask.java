package tasks;

import gateway.TaskDataAccess;
import gateway.CategoryDataAccess;

import java.util.ArrayList;
import java.util.Objects;

public class UserCaseEditTask{
    int id;
    String title;
    String categoryName;
    public UserCaseEditTask(int id, String title, String categoryName) {
        this.id = id;
        this.title = title;
        this.categoryName = categoryName;
    }
    public void action() {
        TaskDataAccess taskDataAccess = new TaskDataAccess();
        CategoryDataAccess categoryDataAccess = new CategoryDataAccess();
        ArrayList<Category> categoryList = categoryDataAccess.getCategories();
        int categoryId = 0;
        for (Category category : categoryList) {
            if (Objects.equals(this.categoryName, category.getTitle())) {
                categoryId = category.getId();
                break;
            }
        }
        taskDataAccess.editTask(this.id, this.title, categoryId);
    }
}
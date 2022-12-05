package tasks;

import gateway.CategoryDataAccess;

public class UserCaseEditCategory{
    int id;
    String title;
    public UserCaseEditCategory(int id, String title) {
        this.id = id;
        this.title = title;
    }
    public void action() {
        CategoryDataAccess categoryDataAccess = new CategoryDataAccess();
        categoryDataAccess.editCategory(this.id, this.title);}
}
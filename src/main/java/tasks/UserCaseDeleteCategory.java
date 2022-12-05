package tasks;

import gateway.CategoryDataAccess;

public class UserCaseDeleteCategory{
    int id;
    public UserCaseDeleteCategory(int id) {
        this.id = id;
    }
    public void action() {
        CategoryDataAccess categoryDataAccess = new CategoryDataAccess();
        categoryDataAccess.deleteCategory(this.id);}
}
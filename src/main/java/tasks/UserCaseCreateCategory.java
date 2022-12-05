package tasks;

import gateway.CategoryDataAccess;
import java.time.LocalDate;

public class UserCaseCreateCategory{
    String title;
    public UserCaseCreateCategory(String title) { this.title = title; }

    public void action(LocalDate daily) {
            Category category = new Category(this.title, daily);
            CategoryDataAccess.createCategory(category);}
}
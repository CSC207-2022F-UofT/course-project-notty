package tasks;
import UI.LogInScreen;
import gateway.CategoryDataAccess;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class UserCaseReadCategory {
    private final int year;
    private final int month;
    private final int date;
    public UserCaseReadCategory(int year, int month, int date)
    {
        this.year = year;
        this.month = month;
        this.date = date;
    }
    public ArrayList<Category> accessData() {
        LocalDate daily = LocalDate.of(this.year, this.month, this.date);
        CategoryDataAccess categoryDataAccess = new CategoryDataAccess();
        ArrayList<Category> list = categoryDataAccess.getCategories();
        ArrayList<Category> categoryList = new ArrayList<>();
        String currentUsername = LogInScreen.usernameLogged;

        for (Category category : list) {
            if (daily.isEqual(category.getDaily()) && Objects.equals(category.getUserName(),currentUsername)) {
                categoryList.add(category);
            }
        }
        return categoryList;
    }
}
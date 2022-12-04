package tasks;
import gateway.CategoryDataAccess;

import java.time.LocalDate;
import java.util.ArrayList;

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
        for (Category category : list) {
            if (daily.isEqual(category.getDaily())) {
                categoryList.add(category);
            }
        }
        return categoryList;
    }
}
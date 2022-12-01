package gateway;

import tasks.Category;

import java.util.ArrayList;

public interface ICategoryDataAccess {
    void create(Category category);
    void delete(Category category);
    void edit(Category category);
    ArrayList<Category> getAll();
}

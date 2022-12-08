package tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;

class CategoriesTest {
    @Test
    void getTitle() {
        // Checks that this method returns the title of a category regardless of constructor
        LocalDate day1 = LocalDate.of(2022, 12, 7);
        Category category1 = new Category("UnitTests", day1);
        Assertions.assertEquals(category1.getTitle(), "UnitTests");

        LocalDate day2 = LocalDate.of(2022, 12, 8);
        Category category2 = new Category();
        category2.setId(1);
        category2.setTitle("Documentations");
        category2.setDaily(day2);
        category2.setUserName("admin");
        Assertions.assertEquals(category2.getTitle(), "Documentations");
    }

    @Test
    void getDaily() {
        // Checks that this method returns the date of the category regardless of constructor
        LocalDate day1 = LocalDate.of(2022, 12, 7);
        Category category1 = new Category("UnitTests", day1);

        LocalDate day3 = LocalDate.of(2022, 12, 7);
        Assertions.assertEquals(category1.getDaily(), day3);

        LocalDate day2 = LocalDate.of(2022, 12, 8);
        Category category2 = new Category();
        category2.setId(1);
        category2.setTitle("Documentations");
        category2.setDaily(day2);
        category2.setUserName("admin");


        LocalDate day4 = LocalDate.of(2022, 12, 8);
        Assertions.assertEquals(category2.getDaily(), day4);
    }

    @Test
    void getId() {
        // Checks that this method returns the id of the category regardless of constructor
        LocalDate day0 = LocalDate.of(2022, 12, 8);
        ArrayList<Category> ArrayList = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            ArrayList.add(new Category("Test", day0));
            ArrayList.get(i).setId(i);
        }

        for (int i = 0; i < 5; i++){
            Assertions.assertEquals(ArrayList.get(i).getId(), i);
        }
    }

    @Test
    void getUserName() {
        // Checks that this method returns the Username of the owner of the category
        LocalDate day0 = LocalDate.of(2022, 12, 8);
        ArrayList<Category> ArrayList = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            ArrayList.add(new Category("Test", day0));
            ArrayList.get(i).setUserName(Integer.toString(i));
        }

        for (int i = 0; i < 5; i++){
            Assertions.assertEquals(ArrayList.get(i).getUserName(), Integer.toString(i));
        }
    }
}
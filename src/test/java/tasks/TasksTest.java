package tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TasksTest {
    @Test
    void getTitle() {
        // Checks that this method returns the title of a task regardless of constructor
        Task task1 = new Task("UnitTests", 0);
        Assertions.assertEquals(task1.getTitle(), "UnitTests");

        Task task2 = new Task(1, "Documentations", 2, 0);
        Assertions.assertEquals(task2.getTitle(), "Documentations");
    }
//
    @Test
    void getId() {
        // Checks that this method returns the id of a task regardless of constructor
        Task task1 = new Task("UnitTests", 0);
        task1.setId(1);
        Assertions.assertEquals(task1.getId(), 1);

        Task task2 = new Task(2, "Documentations", 2, 0);
        Assertions.assertEquals(task2.getId(), 2);
    }

    @Test
    void getCategoryId() {
        // Checks that this method returns the category id of a task regardless of constructor
        Task task1 = new Task("UnitTests", 0);
        Assertions.assertEquals(task1.getCategoryId(), 0);

        Task task2 = new Task(2, "Documentations", 2, 0);
        Assertions.assertEquals(task2.getCategoryId(), 2);
    }

    @Test
    void getMarked() {
        // Checks that this method returns the category id of a task
        Task task2 = new Task(2, "Documentations", 2, 0);
        Assertions.assertEquals(task2.getMarked(), 0);
    }
}
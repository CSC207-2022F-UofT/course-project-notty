package entities;

import entities.User;

public class Account {
    User user;
    public Account(User user) {
        this.user = user;
    }
    public void getNotes() {
        //fetch notes for this user from database
    }
    public void getTasks() {
        //fetch tasks for this user from database
    }


}

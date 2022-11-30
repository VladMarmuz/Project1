package com.marmuz.models;

public class Person {
    private int id;

    //@NotEmpty(message = "Имя не должно быть пустым")
   // @Size(min = 2, max = 100, message = "Имя должно быть минимум 2 символа,максимум - 100")
    private String fullName;
    //@Size(min = 1920, message = "Год рождения должен быть больше 1920")
    private int yearOfBirth;

    public Person(int id, String fullName, int yearOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}

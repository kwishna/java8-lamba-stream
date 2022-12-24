package org.lambda.pojo;

public class Student {
    private String name;
    private int rollNo;
    private char section;

    @Override
    public String toString() {
        return "Student{" +
                "string='" + name + '\'' +
                ", rollNo=" + rollNo +
                ", section=" + section +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public char getSection() {
        return section;
    }

    public void setSection(char section) {
        this.section = section;
    }
}

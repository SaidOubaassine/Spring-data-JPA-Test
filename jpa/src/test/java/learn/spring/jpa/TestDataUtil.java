package learn.spring.jpa;

import learn.spring.jpa.domain.Student;

public class TestDataUtil {
    private TestDataUtil(){

    }
    public static Student createTestStudentA(){
        return Student.builder()
                .id(1)
                .name("Said")
                .country("Maroc")
                .age(25)
                .build();
    }
    public static Student createTestStudentB(){
        return Student.builder()
                .id(2)
                .name("Mohamed")
                .country("France")
                .age(22)
                .build();
    }
    public static Student createTestStudentC(){
        return Student.builder()
                .id(3)
                .name("Jaime")
                .country("USA")
                .age(20)
                .build();
    }
}

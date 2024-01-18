package learn.spring.jpa.repositories;

import learn.spring.jpa.TestDataUtil;
import learn.spring.jpa.domain.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Iterator;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class StudentRepositoryIntegrationTests {
    private StudentRepository underTest;
    @Autowired
    public StudentRepositoryIntegrationTests(StudentRepository underTest){
        this.underTest = underTest;
    }
    @Test
    public void testThatStudentCreatedAndRecalled(){
        Student student = TestDataUtil.createTestStudentA();
        underTest.save(student);
        Optional<Student> result = underTest.findById(student.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(student);
    }
    @Test
    public void testThatMultipleStudentCanBeCreatedAndRecalled(){
        Student studentA = TestDataUtil.createTestStudentA();
        underTest.save(studentA);
        Student studentB = TestDataUtil.createTestStudentB();
        underTest.save(studentB);
        Student studentC = TestDataUtil.createTestStudentC();
        underTest.save(studentC);

        Iterable<Student> result = underTest.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactly(studentA, studentB, studentC);
    }
    @Test
    public void testThatStudentCanBeUpdated(){
        Student studentA = TestDataUtil.createTestStudentA();
        underTest.save(studentA);
        studentA.setName("UPDATED");
        underTest.save(studentA);
        Optional<Student> result = underTest.findById(studentA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(studentA);
    }
    @Test
    public void testThatStudentCanBeDeleted(){
        Student studentA = TestDataUtil.createTestStudentA();
        underTest.save(studentA);
        underTest.deleteById(studentA.getId());
        Optional<Student> result = underTest.findById(studentA.getId());
        assertThat(result).isEmpty();
    }
    @Test
    public void testThatGetStudentsWithAgelessThan(){
        Student testStudentA = TestDataUtil.createTestStudentA();
        underTest.save(testStudentA);
        Student testAuthorB = TestDataUtil.createTestStudentB();
        underTest.save(testAuthorB);
        Student testAuthorC = TestDataUtil.createTestStudentC();
        underTest.save(testAuthorC);

        Iterable<Student> result = underTest.ageLessThan(22);
        assertThat(result).containsExactly(testAuthorC);
    }
    @Test
    public void testThatGetAuthorsWithAgeGreaterThan(){
        Student studentA = TestDataUtil.createTestStudentA();
        underTest.save(studentA);
        Student studentB = TestDataUtil.createTestStudentB();
        underTest.save(studentB);
        Student studentC = TestDataUtil.createTestStudentC();
        underTest.save(studentC);

        Iterable<Student> result = underTest.findStudentsWithAgeGreaterThan(22);
        assertThat(result).containsExactly(studentA);
    }
}

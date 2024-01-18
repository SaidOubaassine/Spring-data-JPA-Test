package learn.spring.jpa.repositories;

import learn.spring.jpa.TestDataUtil;
import learn.spring.jpa.domain.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
}

package learn.spring.jpa.repositories;

import learn.spring.jpa.domain.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
    Iterable<Student> ageLessThan(int age);
}

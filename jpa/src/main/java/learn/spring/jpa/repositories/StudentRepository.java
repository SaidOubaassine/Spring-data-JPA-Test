package learn.spring.jpa.repositories;

import learn.spring.jpa.domain.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
    Iterable<Student> ageLessThan(int age);
    @Query("SELECT a from Student a where a.age > ?1")
    Iterable<Student> findStudentsWithAgeGreaterThan(int age);
}

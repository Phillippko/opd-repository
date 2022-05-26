package opd.repository.repository;

import opd.repository.entity.Lection;
import opd.repository.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectionRepository extends JpaRepository<Lection, Long> {
    Lection findByName(String name);
}

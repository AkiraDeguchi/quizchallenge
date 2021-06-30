package cm.itspj.quizchallenge.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoiceRepository extends JpaRepository<Choice, Integer> {
    
}

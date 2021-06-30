package cm.itspj.quizchallenge.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    @Query(nativeQuery = true, value="select min(id) from quiz where id> :minQ ")
    Integer min (@Param("minQ") int minQ);



    // select min(id)
    // from Quiz
    // where id>?
    // order by id Asc
}

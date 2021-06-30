package cm.itspj.quizchallenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Choice {
    
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(name = "choice_text", nullable = false)
    private String choiceText;

    
    @Column(name = "answer")
    private boolean answer;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;


}

package cm.itspj.quizchallenge.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "quiz")
public class Quiz {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(name = "title")
    private String title;

    @NotBlank
    @Column(name = "q_text", nullable = false)
    private String questionText;

    @NotBlank
    @Column(name = "explanation", nullable = false)
    private String explanation;
    
    @OneToMany(mappedBy = "quiz")
    private List<Choice> choices;


}

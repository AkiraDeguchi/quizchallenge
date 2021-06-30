package cm.itspj.quizchallenge.model;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Form {
    
    @NotBlank
   
    private String title;

    @NotBlank
    private String questionText;

    @NotBlank
    private String explanation;

    @NotBlank
    private String choiceText1;

    private boolean checkbox1;

    @NotBlank
    private String choiceText2;

    private boolean checkbox2;
    
    @NotBlank
    private String choiceText3;

    private boolean checkbox3;

    @NotBlank
    private String choiceText4;

    private boolean checkbox4;
    
}

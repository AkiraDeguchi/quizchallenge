package cm.itspj.quizchallenge.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cm.itspj.quizchallenge.model.Choice;
import cm.itspj.quizchallenge.model.ChoiceRepository;
import cm.itspj.quizchallenge.model.Quiz;
import cm.itspj.quizchallenge.model.QuizRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/quizzes")
@Controller
public class QuizController {
    private final QuizRepository qrep;
    private final ChoiceRepository crep;

    @GetMapping("")
    public String index(Model model) {
        List<Quiz> qList = qrep.findAll();
        model.addAttribute("qlist", qList);
        return "quiz/toppage";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        Quiz quiz = qrep.findById(id).get();
        model.addAttribute("quiz", quiz);
        return "quiz/show";
    }

    @PatchMapping("/{id}/check")
    public String receiveAnswer
    (@PathVariable int id, @RequestParam(name = "choice") int choiceId, Model model) {
        
        Choice c = crep.findById(choiceId).get();
       
        model.addAttribute("answer", c.isAnswer());
        model.addAttribute("quiz", qrep.findById(id).get());
        model.addAttribute("nextId", qrep.min(id));
        return "quiz/answer";
    }

    
    
   


}

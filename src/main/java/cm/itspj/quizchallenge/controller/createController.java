package cm.itspj.quizchallenge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cm.itspj.quizchallenge.model.Choice;
import cm.itspj.quizchallenge.model.ChoiceRepository;
import cm.itspj.quizchallenge.model.Form;
import cm.itspj.quizchallenge.model.Quiz;
import cm.itspj.quizchallenge.model.QuizRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class createController {
    private final QuizRepository qrep;
    private final ChoiceRepository crep;

    @GetMapping("/new")
    public String newQuestion(@ModelAttribute Form form, Model model) {

        return "quiz/new";
    }

    @PostMapping("/quizzes/submit")
    public String newQuiz(@Validated @ModelAttribute Form form, Model model, BindingResult result,
            RedirectAttributes attrs) {
        if (result.hasErrors()) {
            model.addAttribute("main", "quiz/new::main");
            return "quiz/new";
        }

        Quiz q = new Quiz();
        q.setTitle(form.getTitle());
        q.setQuestionText(form.getQuestionText());
        q.setExplanation(form.getExplanation());

        Quiz newQuiz = qrep.save(q);// 無事に登録できる状態から.getId()と追加

        Choice c1 = new Choice();
        c1.setQuiz(newQuiz);
        c1.setChoiceText(form.getChoiceText1());
        c1.setAnswer(form.isCheckbox1());
        crep.save(c1);

        Choice c2 = new Choice();
        c2.setQuiz(newQuiz);
        c2.setChoiceText(form.getChoiceText2());
        c2.setAnswer(form.isCheckbox2());
        crep.save(c2);

        Choice c3 = new Choice();
        c3.setQuiz(newQuiz);
        c3.setChoiceText(form.getChoiceText3());
        c3.setAnswer(form.isCheckbox3());
        crep.save(c3);

        Choice c4 = new Choice();
        c4.setQuiz(newQuiz);
        c4.setChoiceText(form.getChoiceText4());
        c4.setAnswer(form.isCheckbox4());
        crep.save(c4);

        attrs.addFlashAttribute("success", "データ登録完了");

        return "redirect:/quizzes";
    }
    

    public String newChoice(@Validated @ModelAttribute Choice choice, Model model, BindingResult result,
            RedirectAttributes attrs) {
        if (result.hasErrors()) {
            model.addAttribute("main", "quiz/new::main");
            return "quiz/new";
        }
        crep.save(choice);
        attrs.addFlashAttribute("success", "完了");
        return "redirect:/quizzes";

    }

}

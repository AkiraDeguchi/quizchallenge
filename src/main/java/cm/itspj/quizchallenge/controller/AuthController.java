package cm.itspj.quizchallenge.controller;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import cm.itspj.quizchallenge.model.CustomUser;
import cm.itspj.quizchallenge.model.CustomUserRepository;
import cm.itspj.quizchallenge.model.Role;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class AuthController {
  private final BCryptPasswordEncoder passwordEncoder;
  private final CustomUserRepository userRep;

  @GetMapping("/login")
  public String login() {
    return "quiz/login";
  }

  @GetMapping("/register")
  public String register(@ModelAttribute("user") CustomUser user) {
    return "quiz/register";
  }

  @PostMapping("/register")
  public String createUser(@Validated @ModelAttribute("user") CustomUser user, BindingResult result) {
    if (result.hasErrors()) {
      return "quiz/register";
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRole(user.isAdmin() ? Role.ROLE_ADMIN.name() : Role.ROLE_USER.name());
    userRep.save(user);
    return "redirect:/login?register";
  }
}

package News.News.controllers;

import News.News.models.User;
import News.News.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String usersAdd(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "registration";
    }
    @PostMapping("/registration")
    public String newUsersAdd(@RequestParam String name, @RequestParam String password,
                              @RequestParam String eMail, Model model) {
        User user = new User(name, password, eMail);
        userRepository.save(user);
        return "redirect:/";
    }
}

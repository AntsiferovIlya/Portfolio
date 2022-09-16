package News.News.controllers;


import News.News.models.Post;
import News.News.repo.PostRepository;
import News.News.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class RedactController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    private String email;
    private String emailIn = "antsiferov.ilya@mail.ru";
    private String passwordIn = "123";
    private String password;

    @GetMapping("/login")
    public String loginOp(Model model) {
        return "login";
    }
    @PostMapping("/login")
    public String login (@RequestParam String email, @RequestParam String password, Model model) {

        model.addAttribute("email", email);
        model.addAttribute("password", password);
        this.email = email;
        this.password = password;
        return "redirect:/redact";
    }

    @GetMapping("/redact")
    public String newsMain(Model model) {
        if(email.equals(emailIn) && password.equals(passwordIn)) {
        Iterable<Post> redactPosts = postRepository.findAll();
        model.addAttribute("redactPosts", redactPosts);
        return "redactMain";
        }
        return "redirect:/login";
    }

    @GetMapping("/redact/{id}")
    public String newsDetails(@PathVariable(value = "id") long id, Model model) {
        if(!postRepository.existsById(id)) {
            return "redirect:/redact";
        }
        Optional<Post> redactDetails = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        redactDetails.ifPresent(res::add);
        model.addAttribute("redactDetails", res);
        return "redactDetails";
    }

    @GetMapping("/redact/{id}/edit")
    public String editNews(@PathVariable(value = "id")long id, Model model) {
            if (!postRepository.existsById(id)) {
                return "redirect:/redact";
            }
            Optional<Post> redactDetails = postRepository.findById(id);
            ArrayList<Post> res = new ArrayList<>();
            redactDetails.ifPresent(res::add);
            model.addAttribute("redactDetails", res);
            return "redactEdit";
        }
    @PostMapping("/redact/{id}/edit")
    public String updateNews(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons,
                             @RequestParam String fullText, @RequestParam String topic, Model model) {
        Post redactPost = postRepository.findById(id).orElseThrow();
        redactPost.setTitle(title);
        redactPost.setAnons(anons);
        redactPost.setFullText(fullText);
        redactPost.setTopic(topic);
        postRepository.save(redactPost);
        return "redirect:/redact";
    }

    @GetMapping("/loginAdd")
    public String loginAdd(Model model) {
        return "loginAdd";
    }
    @PostMapping("/loginAdd")
    public String loginADD (@RequestParam String email, @RequestParam String password, Model model) {

        model.addAttribute("email", email);
        model.addAttribute("password", password);
        this.email = email;
        this.password = password;
        return "redirect:/add";
    }

    @GetMapping("/add")
    public String newsAdd(Model model) {
        if(email.equals(emailIn) && password.equals(passwordIn)) {
            Iterable<Post> posts = postRepository.findAll();
            model.addAttribute("posts", posts);
            return "newsAdd";
        }
        return "redirect:/loginAdd";
    }
    @PostMapping("/add")
    public String newsPostAdd(@RequestParam String title, @RequestParam String anons,
                              @RequestParam String fullText, @RequestParam String topic, Model model) {
        Post post = new Post(title, anons, fullText, topic);
        postRepository.save(post);
        return "redirect:/add";
    }

}

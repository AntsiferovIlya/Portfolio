package News.News.controllers;

import News.News.models.Post;
import News.News.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class NewsController {

    @Autowired
    private PostRepository postRepository;

    private String email;

    @GetMapping("/")
    public String newsMain(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "newsMain";
    }

    @GetMapping("/cyberCrime")
    public String crimeNews(Model model) {
        List<Post> crime = postRepository.findAllByTopic("Кибер-преступность");
        model.addAttribute("crime", crime);
        return "cyberCrime";
    }

    @GetMapping("/newIron")
    public String newIron(Model model) {
        List<Post> iron = postRepository.findAllByTopic("Новое железо");
        model.addAttribute("iron", iron);
        return "newIron";
    }

    @GetMapping("/{id}")
    public String newsDetails(@PathVariable(value = "id") long id, Model model) {
        if(!postRepository.existsById(id)) {
            return "redirect:/";
        }
        Optional<Post> details = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        details.ifPresent(res::add);
        model.addAttribute("details", res);
        return "newsDetails";
    }

    @GetMapping("/aboutAs")
    public String aboutAs(Model model) {
        model.addAttribute("title", "Про нас");
        return "aboutAs";
    }

    @PostMapping("redact/{id}/remove")
    public String removeNews(@PathVariable(value = "id") long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/redact";
    }

}
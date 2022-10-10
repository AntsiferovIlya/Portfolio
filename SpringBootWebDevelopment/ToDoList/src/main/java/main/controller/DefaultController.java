package main.controller;

import main.model.DoList;
import main.model.DoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class DefaultController {

    @Autowired
    DoListRepository doListRepository;

    @Value("${someParameter}")
    private Integer someParameter;

    @RequestMapping("/")
    public String index(Model model) {
        Iterable<DoList> doListIterable = doListRepository.findAll();
        ArrayList<DoList> doLists = new ArrayList<>();
        for(DoList doList : doListIterable) {
            doLists.add(doList);
        }
        model.addAttribute("doLists", doLists)
                .addAttribute("doListsCount", doLists.size())
                .addAttribute("someParameter", someParameter);
        return "index";
    }
}

package main.controller;

import main.Storage;
import main.model.DoList;
import main.model.DoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ToDoListController {

    @Autowired
    DoListRepository doListRepository;


    @GetMapping("/toDoList/")
    public List<DoList> list() {
        Iterable<DoList> doListIterable = doListRepository.findAll();
        ArrayList<DoList> doLists = new ArrayList<>();
        for(DoList doList : doListIterable) {
            doLists.add(doList);
        }
        return doLists;
    }

    @PostMapping("/toDoList/")
    public int addToList(DoList doList) {
        DoList newDoList = doListRepository.save(doList);
        return newDoList.getId();
    }

    @DeleteMapping("/toDoList/")
    public void deleteDoList() {
        doListRepository.deleteAll();
    }

    @GetMapping("/toDoList/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Optional<DoList> optionalDoList = doListRepository.findById(id);
        if(!optionalDoList.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return new ResponseEntity(optionalDoList.get(), HttpStatus.OK);
    }

    @PutMapping("/toDoList/{id}")
    public DoList updateDo(@PathVariable int id, String newName, String newAbout) {

        DoList doList = doListRepository.findById(id).get();
        doList.setName(newName);
        doList.setAbout(newAbout);
        doListRepository.save(doList);
        return doList;
    }

    @DeleteMapping("/toDoList/{id}")
    public int deleteById(@PathVariable int id) {
        doListRepository.deleteById(id);
        return id;
    }

}

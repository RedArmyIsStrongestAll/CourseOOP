package com.example.kursuch.webView;

import com.example.kursuch.customType.color.Color;
import com.example.kursuch.models.Cat;
import com.example.kursuch.repositories.RepositoryCatPostgres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
public class RestControllerForTest {
    final RepositoryCatPostgres rep;

    @Autowired
    public RestControllerForTest(RepositoryCatPostgres rep) {
        this.rep = rep;
    }


    @PostMapping("/")
    public void methodPost(@RequestBody Cat cat){
        cat.setColor(Color.ORANGE);
        rep.create(cat);
    }

    @PostMapping("/up/{id}")
    public void methodPut(@PathVariable(name = "id") long id, @RequestBody Cat cat){
        rep.update(id, cat);
    }

    @GetMapping("/{id}")
    public Object methodGetOne(@PathVariable(name = "id") long id){
        Cat cat = rep.read(id).get();
        System.out.println(cat);
        return rep.read(id).get();
    }

    @GetMapping("/")
    public Object methodGetAll(){
        return rep.readAll().orElse(null);
    }

    @DeleteMapping("/{id}")
    public void methodDelet(@PathVariable(name = "id") long id){
        rep.delete(id);
    }
}

package com.example.kursuch.webView;

import com.example.kursuch.models.Cat;
import com.example.kursuch.otherTools.Color;
import com.example.kursuch.repositories.RepositoryCatPostgres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    final RepositoryCatPostgres rep;

    @Autowired
    public RestController(RepositoryCatPostgres rep) {
        this.rep = rep;
    }


    @PostMapping("/")
    public void methodPost(@RequestBody Cat cat){
        try {
            /*cat.setColor(Color.ORANGE);*/
            rep.create(cat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*@GetMapping("/{id}")
    public Object methodPut(@PathVariable(name = "id") long id){
        try {
            Cat cat = new Cat(1,"asd", "asd", Color.BLACK, "sdasd");
            rep.update(id, cat);
            //return rep.readAll().orElse(null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }*/

    @GetMapping("/{id}")
    public Object methodGetOne(@PathVariable(name = "id") long id){
        try {
            return rep.read(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/")
    public Object methodGetAll(){
        try {
            return rep.readAll().orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

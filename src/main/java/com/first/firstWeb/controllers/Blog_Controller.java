package com.first.firstWeb.controllers;


import com.first.firstWeb.models.Post;
import com.first.firstWeb.repo.Post_Repository;
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
public class Blog_Controller {

    @Autowired
    private Post_Repository post_repository;

    @GetMapping("/blog")
    public String blogMain(Model model){
        Iterable<Post> posts = post_repository.findAll();
        model.addAttribute("posts",posts);
        return "blog_main";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model){
        return "blog_add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title,@RequestParam String anons,@RequestParam String full_text,Model model){
        Post post = new Post(title,anons,full_text);
        post_repository.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model){
        if(!post_repository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Post> post = post_repository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post",res);
        return "blog_details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model){
        if(!post_repository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Post> post = post_repository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post",res);
        return "blog_edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") long id, @RequestParam String title,@RequestParam String anons,@RequestParam String full_text,Model model){
        Post post = post_repository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        post_repository.save(post);
        return "redirect:/blog";
    }
    @PostMapping("/blog/{id}/remove")
    public String blogPostRemove(@PathVariable(value = "id") long id,Model model){
        Post post = post_repository.findById(id).orElseThrow();
        post_repository.delete(post);
        return "redirect:/blog";
    }
}

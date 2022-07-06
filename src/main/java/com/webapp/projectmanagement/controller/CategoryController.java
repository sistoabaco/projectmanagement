package com.webapp.projectmanagement.controller;

import com.webapp.projectmanagement.model.Category;
import com.webapp.projectmanagement.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Locale;

@RestController
//@CrossOrigin("http://localhost:3000/")

public class CategoryController {
    @Autowired
    CategoryRepo categoryRepo;

    @GetMapping("/categoryList")
    public List<Category> getCategory(){
        return categoryRepo.findAll();
    }

    @GetMapping("/findCategory/{id}")
    public Category findOne(@PathVariable int id){
        return categoryRepo.findById(id).orElse(null);
    }

    @PostMapping("/saveCategory")
    public String createCategory(@RequestBody Category category){

        category.setDescription(category.getDescription().toUpperCase(Locale.ROOT));

        if(categoryRepo.findByName(category.getDescription()) != null)
            return "Erro! Nome da categoria existente!!!";

        categoryRepo.save(category);
        return "redirect:/categoryList";
    }

    @PutMapping("/updateCategory/{id}")
    public Category updateCategory(@PathVariable int id, @RequestBody Category category) {
        Category oldCatategory =  categoryRepo.findById(id).orElse(null);

        if (oldCatategory == null) return null;

        oldCatategory.setDescription(category.getDescription());
        oldCatategory.setSalary(category.getSalary());

        return categoryRepo.save(oldCatategory);
    }

    @DeleteMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable int id) {
        categoryRepo.deleteById(id);
        return "redirect:/categoryList";
    }

}

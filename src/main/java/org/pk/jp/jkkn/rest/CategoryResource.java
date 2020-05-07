package org.pk.jp.jkkn.rest;

import com.google.gson.Gson;
import org.pk.jp.jkkn.data.CategoryRepository;
import org.pk.jp.jkkn.data.model.Category;

import org.pk.jp.jkkn.data.model.Recipe;
import org.pk.jp.jkkn.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping(value = "/rest", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryResource {
    private static final Gson gson = new Gson();
    private CategoryService categoryService;

    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/category")
    @ResponseBody
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(category));
        } catch (Exception e) {
            return new ResponseEntity(gson.toJson("Failed to create category: " + e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/category/{ID}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable Long ID) {
        if (categoryService.deleteCategory(ID)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/category/edit/{ID}")
    @ResponseBody
    public ResponseEntity<Category> replaceCategory(@PathVariable Long ID, @RequestBody Category category) {
        try {
            return ResponseEntity.ok(categoryService.replaceCategory(ID, category));
        } catch (Exception e) {
            return new ResponseEntity(gson.toJson("Failed to replace category: " + e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/category/id/{ID}")
    @ResponseBody
    public ResponseEntity<Optional<Category>> getCategoryByID(@PathVariable Long ID) {
        try {
            return ResponseEntity.ok(categoryService.getCategoryByID(ID));
        } catch (Exception e) {
            return new ResponseEntity(gson.toJson("Failed to get category: " + e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/category/name/{name}")
    @ResponseBody
    public ResponseEntity<Category> getCategoryByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(categoryService.getCategoryByName(name));
        } catch (Exception e) {
            return new ResponseEntity(gson.toJson("Failed to get category: " + e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/category")
    @ResponseBody
    public ResponseEntity<List<Category>> getAllCategories() {
        try {
            return ResponseEntity.ok(categoryService.getAllCategories());
        } catch (Exception e) {
            return new ResponseEntity(gson.toJson("Failed to get categories: " + e.toString()), HttpStatus.BAD_REQUEST);
        }
    }
}
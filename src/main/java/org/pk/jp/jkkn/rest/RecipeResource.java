package org.pk.jp.jkkn.rest;
import com.google.gson.Gson;

import com.google.gson.annotations.Expose;
import org.pk.jp.jkkn.data.model.Recipe;
import org.pk.jp.jkkn.services.RecipeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/rest", produces = MediaType.APPLICATION_JSON_VALUE)
@Transactional
public class RecipeResource {
    private static final Gson gson = new Gson();
    private RecipeService recipeService;

    public RecipeResource(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/recipe")
    @ResponseBody
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe newRecipe) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.createRecipe(newRecipe));
        } catch (Exception e) {
            return new ResponseEntity(gson.toJson("Failed to create recipe: " + e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/recipe/{ID}")
    public ResponseEntity<Boolean> deleteRecipe(@PathVariable Long ID) {
        if (recipeService.deleteRecipe(ID)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/recipe/edit/{ID}")
    @ResponseBody
    public ResponseEntity<Recipe> replaceRecipe(@PathVariable Long ID, @RequestBody Recipe copiedRecipe) {
        try {
            return ResponseEntity.ok(recipeService.replaceRecipe(ID, copiedRecipe));
        } catch (Exception e) {
            return new ResponseEntity(gson.toJson("Failed to replace recipe: " + e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/recipe/edit/name/{ID}")
    @ResponseBody
    public ResponseEntity<Recipe> editRecipeName(@PathVariable Long ID, @RequestBody String name) {
        try {
            return ResponseEntity.ok(recipeService.editRecipeName(ID, name));
        } catch (Exception e) {
            return new ResponseEntity(gson.toJson("Failed to edit recipe name: " + e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/recipe/edit/description/{ID}")
    @ResponseBody
    public ResponseEntity<Recipe> editRecipeDescription(@PathVariable Long ID, @RequestBody String description) {
        try {
            return ResponseEntity.ok(recipeService.editRecipeDescription(ID, description));
        } catch (Exception e) {
            return new ResponseEntity(gson.toJson("Failed to edit recipe description: " + e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/recipe/edit/preparationtime/{ID}")
    @ResponseBody
    public ResponseEntity<Recipe> editRecipePreparationTime(@PathVariable Long ID, @RequestBody String preparationTime) {
        try {
            return ResponseEntity.ok(recipeService.editRecipePreparationTime(ID, preparationTime));
        } catch (Exception e) {
            return new ResponseEntity(gson.toJson("Failed to edit recipe preparation time: " + e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/recipe/id/{ID}")
    @ResponseBody
    public ResponseEntity<Optional<Recipe>> getRecipeByID(@PathVariable Long ID) {
        try {
            return ResponseEntity.ok(recipeService.getRecipeByID(ID));
        } catch (Exception e) {
            return new ResponseEntity(gson.toJson("Failed to find recipe by ID: " + e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/recipe/name/{name}")
    @ResponseBody
    public ResponseEntity<Recipe> getRecipeByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(recipeService.getRecipeByName(name));
        } catch (Exception e) {
            return new ResponseEntity(gson.toJson("Failed to find recipe by name: " + e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/recipe")
    @ResponseBody

    public ResponseEntity<List<Recipe>> getAllRecipies() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }
}

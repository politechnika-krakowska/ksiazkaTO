package org.pk.jp.jkkn.services;

import org.pk.jp.jkkn.data.RecipeRepository;
import org.pk.jp.jkkn.data.model.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecipeService {
    private RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe createRecipe(@NotNull Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public boolean deleteRecipe(@NotNull Long recipeID) {
        if (recipeRepository.existsById(recipeID)) {
            recipeRepository.deleteById(recipeID);
            return true;
        } else {
            return false;
        }
    }

    public Recipe replaceRecipe(Long recipeID, Recipe newRecipe) {
        Optional<Recipe> oldRecipe = recipeRepository.findById(recipeID);
        oldRecipe.get().replaceRecipe(newRecipe);
        return oldRecipe.get();
    }

    public Recipe editRecipeName(Long recipeID, String name) {
        Recipe recipe = recipeRepository.findById(recipeID).get();
        recipe.setName(name);
        return recipe;
    }

    public Recipe editRecipeDescription(Long recipeID, String description) {
        Recipe recipe = recipeRepository.findById(recipeID).get();
        recipe.setDescription(description);
        return recipe;
    }

    public Recipe editRecipePreparationTime(Long recipeID, String preparationTime) {
        Recipe recipe = recipeRepository.findById(recipeID).get();
        recipe.setPreparationTime(preparationTime);
        return recipe;
    }

    public Recipe editRecipeDifficulty(Long recipeID, Difficulty difficulty) {
        Recipe recipe = recipeRepository.findById(recipeID).get();
        recipe.setDifficulty(difficulty);
        return recipe;
    }

    public Recipe editRecipeTimeOfDay(Long recipeID, TimeOfDay timeOfDay) {
        Recipe recipe = recipeRepository.findById(recipeID).get();
        recipe.setTimeOfDay(timeOfDay);
        return recipe;
    }

    public Recipe editRecipeWayOfPreparation(Long recipeID, WayOfPreparation wayOfPreparation) {
        Recipe recipe = recipeRepository.findById(recipeID).get();
        recipe.setWayOfPreparation(wayOfPreparation);
        return recipe;
    }

    public Recipe editRecipeCategory(Long recipeID, Category category) {
        Recipe recipe = recipeRepository.findById(recipeID).get();
        recipe.setCategory(category);
        return recipe;
    }

    public Optional<Recipe> getRecipeByID(@NotNull Long ID) {
        return recipeRepository.findById(ID);
    }

    public Recipe getRecipeByName(@NotNull String name) {
        return recipeRepository.findByName(name);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }
}

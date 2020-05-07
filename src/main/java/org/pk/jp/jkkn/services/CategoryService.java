package org.pk.jp.jkkn.services;

import org.pk.jp.jkkn.data.CategoryRepository;
import org.pk.jp.jkkn.data.model.Category;
import org.pk.jp.jkkn.data.model.Recipe;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService
{
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(@NotNull Category category)
    {
        return categoryRepository.save(category);
    }

    public boolean deleteCategory(@NotNull Long categoryID)
    {
        if(categoryRepository.existsById(categoryID))
        {
            categoryRepository.deleteById(categoryID);
            return true;
        }
        else
        {
            return false;
        }
    }

    public Category replaceCategory(Long categoryID, Category newCategory)
    {
        Optional<Category> oldCategory = categoryRepository.findById(categoryID);
        oldCategory.get().replaceCategory(newCategory);
        return oldCategory.get();
    }

    public Optional<Category> getCategoryByID(@NotNull Long ID)
    {
        return categoryRepository.findById(ID);
    }

    public Category getCategoryByName(@NotNull String name)
    {
        return categoryRepository.findByName(name);
    }

    public List<Category> getAllCategories()
    {
        return categoryRepository.findAll();
    }
}

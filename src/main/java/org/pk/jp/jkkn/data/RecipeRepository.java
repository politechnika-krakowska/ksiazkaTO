package org.pk.jp.jkkn.data;

import org.pk.jp.jkkn.data.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface RecipeRepository extends CrudRepository<Recipe, Long>
{
    Recipe findById(long id);
    Recipe findByName(String name);
    List<Recipe> findAll();
}
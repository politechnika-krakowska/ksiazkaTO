package org.pk.jp.jkkn.data;

import org.pk.jp.jkkn.data.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long>
{
    Category findByName(String name);
    Category findById(long id);
    List<Category> findAll();
}

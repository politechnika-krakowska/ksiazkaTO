package org.pk.jp.jkkn.data.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public Category(){};

    @JsonCreator
    public Category(String name)
    {
        this.name = name;
    }

    public void replaceCategory(Category category) {
        this.name = category.name;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

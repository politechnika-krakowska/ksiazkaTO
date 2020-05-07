package org.pk.jp.jkkn.data.model;

import javax.persistence.*;

@Entity
public class Recipe
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;

    @Lob
    private String description;

    //TODO: Enum or relation
    private String preparationTime;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @Enumerated(EnumType.STRING)
    private TimeOfDay timeOfDay;

    @Enumerated(EnumType.STRING)
    private WayOfPreparation wayOfPreparation;

    @ManyToOne
    private Category category;

    protected Recipe() {}

    public Recipe(String name, String description, String preparationTime, Difficulty difficulty, TimeOfDay timeOfDay, WayOfPreparation wayOfPreparation, Category category)
    {
        this.name = name;
        this.description = description;
        this.preparationTime = preparationTime;
        this.difficulty = difficulty;
        this.timeOfDay = timeOfDay;
        this.wayOfPreparation = wayOfPreparation;
        this.category = category;
    }

    public void replaceRecipe(Recipe copiedRecipe)
    {
        this.name = copiedRecipe.getName();
        this.description = copiedRecipe.getDescription();
        this.preparationTime = copiedRecipe.getPreparationTime();
        this.difficulty = copiedRecipe.getDifficulty();
        this.timeOfDay = copiedRecipe.getTimeOfDay();
        this.wayOfPreparation = copiedRecipe.getWayOfPreparation();
        this.category = copiedRecipe.getCategory();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) { this.description = description; }

    public String getPreparationTime() {
        return preparationTime;
    }
    public void setPreparationTime(String preparationTime) { this.preparationTime = preparationTime; }

    public Difficulty getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(Difficulty difficulty) { this.difficulty = difficulty; }

    public TimeOfDay getTimeOfDay() {
        return timeOfDay;
    }
    public void setTimeOfDay(TimeOfDay timeOfDay) { this.timeOfDay = timeOfDay; }

    public WayOfPreparation getWayOfPreparation() {
        return wayOfPreparation;
    }
    public void setWayOfPreparation(WayOfPreparation wayOfPreparation) { this.wayOfPreparation = wayOfPreparation; }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {this.category = category; }
}

package lt.envy.coffeemachine.entity;

public class Recipe {

    private int water;
    private int beans;
    private int sugar;
    private int milk;
    private String recipeName;

    public Recipe(int water, int beans, int sugar, int milk, String recipeName) {
        this.water = water;
        this.beans = beans;
        this.sugar = sugar;
        this.milk = milk;
        this.recipeName = recipeName;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getBeans() {
        return beans;
    }

    public void setBeans(int beans) {
        this.beans = beans;
    }

    public int getSugar() {
        return sugar;
    }

    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }
}

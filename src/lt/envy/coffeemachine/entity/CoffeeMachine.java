package lt.envy.coffeemachine.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CoffeeMachine {

    public static final int USAGE_CAP = 2;
    public static final int SUGAR_CAP = 200;
    public static final int BEANS_CAP = 800;
    public static final int WATER_CAP = 1000;
    public static final int MILK_CAP = 500;
    public static final int RECIPE_CAP = 8;
    private static final Recipe BLACK_COFFEE = new Recipe(5, 6, 1, 0, "Black Coffee");
    private static final Recipe LATTE = new Recipe(4, 4, 1, 2, "Latte");
    private static final Recipe ESPRESSO = new Recipe(3, 8, 0, 0, "Espresso");
    private static final Recipe HOT_WATER = new Recipe(5, 0, 0, 0, "Hot Water");

    private int sugar;
    private int beans;
    private int water;
    private int milk;
    private int usage = USAGE_CAP;
    private ArrayList<Recipe> recipeList = new ArrayList<>();

    public CoffeeMachine() {
    }

    public CoffeeMachine(int sugar, int beans, int water, int milk) {
        this.sugar = sugar;
        this.beans = beans;
        this.water = water;
        this.milk = milk;
        preLoadDefaultRecipes();
    }

    public void addRecipe(Recipe recipe) {
        try {
            if (recipeList.size() >= RECIPE_CAP) {
                throw new Exception("Coffee machine cannot accept any more recipes... Capacity: 8");
            }
            for (Recipe aRecipeList : recipeList) {
                if (aRecipeList.getRecipeName().equals(recipe.getRecipeName())) {
                    throw new Exception("Recipe with name " + recipe.getRecipeName() + " already exists");
                }
            }
            recipeList.add(recipe);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        setRecipeList(recipeList);
    }


    private ArrayList<Recipe> preLoadDefaultRecipes() {
        addRecipe(BLACK_COFFEE);
        addRecipe(LATTE);
        addRecipe(ESPRESSO);
        addRecipe(HOT_WATER);
        return recipeList;
    }

    public int getSugar() {
        return sugar;
    }

    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    public int getBeans() {
        return beans;
    }

    public void setBeans(int beans) {
        this.beans = beans;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public int getUsage() {
        return usage;
    }

    public void setUsage(int usage) {
        this.usage = usage;
    }

    public ArrayList<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(ArrayList<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

}

package lt.envy.coffeemachine;

import com.sun.istack.internal.NotNull;
import lt.envy.coffeemachine.entity.CoffeeMachine;
import lt.envy.coffeemachine.entity.Recipe;
import lt.envy.coffeemachine.services.CoffeeMachineService;

import java.util.*;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static CoffeeMachineService service;

    public static void main(String[] args) throws Exception {

        CoffeeMachine aparatas = new CoffeeMachine(200, 800, 1000, 500);
        CoffeeMachine aparatas2 = new CoffeeMachine(220, 123, 123, 123);
        aparatas.addRecipe(new Recipe(3, 3, 1, 0, "Small cup of black coffee"));
        aparatas.addRecipe(new Recipe(3, 3, 1, 0, "Small cup of black coffee"));
        service = new CoffeeMachineService();
        mainMenu(aparatas);
        sc.close();
    }

    public static void mainMenu(CoffeeMachine machine) throws Exception {
        System.out.println("Welcome to the magical coffee machine\n");
        service.isReady(machine);
        int selection = -1;
        boolean running = true;

        while (running) {
            System.out.println("___MENU___\n1.Recipes\n2.Status\n3.Refill\n4.Clean\n0.Exit");
            System.out.println("Enter a number to select an item from menu:\n");
            selection = intValidation(sc, selection);
            try {
                switch (selection) {
                    case 0:
                        System.out.println("SEE YOU LATER ALIGATOR");
                        running = false;
                        break;
                    case 1:
                        recipesMenu(machine);
                        continue;
                    case 2:
                        service.getStatus(machine);
                        continue;
                    case 3:
                        refillMenu(machine);
                        continue;
                    case 4:
                        service.clean(machine);
                        continue;
                    default:
                        System.out.println("Entered invalid command");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                recipesMenu(machine);
            }
        }
    }

    private static void recipesMenu(CoffeeMachine coffeeMachine) throws Exception {
        ArrayList<Recipe> machinesRecipeList = coffeeMachine.getRecipeList();
        int selection = -1;
        try {
            printRecipeMenu(machinesRecipeList);            // prints out recipe menu from recipeList
            selection = intValidation(sc, selection);
            if (machinesRecipeList.size() >= selection - 1) {
                service.makeCoffee(coffeeMachine, machinesRecipeList.get(selection - 1));
                System.out.println("Your " + machinesRecipeList.get(selection - 1).getRecipeName().toLowerCase() + " is ready. ENJOY!");
            } else {
                throw new Exception("Invalid command");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            service.getStatus(coffeeMachine);
            recipesMenu(coffeeMachine);
        }
    }

    public static void refillMenu(CoffeeMachine machine) {
        System.out.println("___REFILL___\n1.Coffee beans\n2.Water\n3.Sugar\n4.Milk\n0.Back to main menu");
        System.out.println("Select an item to refill");
        int selection = -1;
        try {
            selection = intValidation(sc, selection);
            if (selection > 4) {
                throw new Exception("Enter a valid option to select an item from menu");
            }
            //TODO WHAT THE FUCK? how does it work. USE DEBUGGER.
            else if (selection == 0);
            else {
                System.out.println("Enter the ammount you want to refill: ");
                int ammount = 0;
                ammount = intValidation(sc, ammount);
                service.refill(ammount, selection, machine);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            refillMenu(machine);
        }
    }


    private static int intValidation(@NotNull Scanner sc, int selection) {
        while (!sc.hasNextInt()) {
            System.out.println("Enter a valid number!!");
            sc.next();
        }
        if (sc.hasNextInt()) {
            selection = sc.nextInt();
        }
        return selection;

    }

    private static void printRecipeMenu(List<Recipe> recipeList) {
        System.out.println("___RECIPES_MENU___");
        int i = 1;
        for (Recipe recipe : recipeList) {
            System.out.println(i + ". " + recipe.getRecipeName());
            i++;
        }
        System.out.println("0. Exit");
        System.out.println("Select an item from menu\n");
    }

}


package lt.envy.coffeemachine.services;

import lt.envy.coffeemachine.entity.CoffeeMachine;
import lt.envy.coffeemachine.entity.Recipe;

import java.io.IOException;

import static lt.envy.coffeemachine.Main.mainMenu;
import static lt.envy.coffeemachine.Main.refillMenu;

public class CoffeeMachineService {

    public void makeCoffee(CoffeeMachine coffeeMachine, Recipe recipe) {
        try {
            if (coffeeMachine.getBeans() - recipe.getBeans() < 0) {
                throw new Exception("Not enough beans, please refill");
            }
            coffeeMachine.setBeans(coffeeMachine.getBeans() - recipe.getBeans());

            if (coffeeMachine.getWater() - recipe.getWater() < 0) {
                throw new Exception("Not enough water, please refill");
            }
            coffeeMachine.setWater(coffeeMachine.getWater() - recipe.getWater());

            if (coffeeMachine.getSugar() - recipe.getSugar() < 0) {
                getStatus(coffeeMachine);
                throw new Exception("Not enough sugar, please refill");
            }
            coffeeMachine.setSugar(coffeeMachine.getSugar() - recipe.getSugar());

            if (coffeeMachine.getMilk() - recipe.getMilk() < 0) {
                getStatus(coffeeMachine);
                throw new Exception("Not enough milk, please refill");
            }
            coffeeMachine.setMilk(coffeeMachine.getMilk() - recipe.getMilk());

            if ((coffeeMachine.getUsage() - 1) <= 0) {
                throw new Exception("Machine not clean, please cleane the machine");
            }
            coffeeMachine.setUsage(coffeeMachine.getUsage() - 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
//TODO logika atskirti nuo interface. T.y. visi printai turi buti tik (siuo atveju) Main() metode

    public void refill(int ammount, int selection, CoffeeMachine coffeeMachine) throws Exception {
        switch (selection) {
            case 1:
                int beans = coffeeMachine.getBeans();
                if (beans == CoffeeMachine.BEANS_CAP) {
                    throw new Exception("Beans container is already full\nBean ammount: " + coffeeMachine.getBeans());
                } else if ((beans + ammount) > CoffeeMachine.BEANS_CAP) {
                    ammount = CoffeeMachine.BEANS_CAP - beans;
                    beans += ammount;
                    coffeeMachine.setBeans(beans);
                    System.out.println("Beans refilled.\n Bean ammount: " + coffeeMachine.getBeans());
                } else {
                    coffeeMachine.setBeans(ammount);
                    System.out.println("Beans refilled.\n Bean ammount: " + coffeeMachine.getBeans());
                }
                refillMenu(coffeeMachine);
                break;
            case 2:
                int water = coffeeMachine.getWater();
                if (water == CoffeeMachine.WATER_CAP) {
                    throw new Exception("Water container is already full\nWater ammount: " + coffeeMachine.getWater());
                } else if ((water + ammount) > CoffeeMachine.WATER_CAP) {
                    ammount = CoffeeMachine.WATER_CAP - water;
                    water += ammount;
                    coffeeMachine.setWater(water);
                    System.out.println("Water refilled.\n Water ammount: " + coffeeMachine.getWater());
                } else {
                    coffeeMachine.setWater(ammount);
                    System.out.println("Water refilled.\n Water ammount: " + coffeeMachine.getWater());
                }
                refillMenu(coffeeMachine);
                break;
            case 3:
                int sugar = coffeeMachine.getSugar();
                if (sugar == CoffeeMachine.SUGAR_CAP) {
                    throw new Exception("Sugar container is already full\nSugar ammount: " + coffeeMachine.getWater());
                } else if ((sugar + ammount) > CoffeeMachine.SUGAR_CAP) {
                    ammount = CoffeeMachine.SUGAR_CAP - sugar;
                    sugar += ammount;
                    coffeeMachine.setSugar(sugar);
                    System.out.println("Sugar refilled.\n Sugar ammount: " + coffeeMachine.getSugar());
                } else {
                    coffeeMachine.setSugar(ammount);
                    System.out.println("Sugar refilled.\n Sugar ammount: " + coffeeMachine.getSugar());
                }
                refillMenu(coffeeMachine);
                break;
            case 4:
                int milk = coffeeMachine.getMilk();
                if (milk == CoffeeMachine.MILK_CAP) {
                    throw new Exception("Sugar container is already full\nSugar ammount: " + coffeeMachine.getMilk());
                } else if ((milk + ammount) > CoffeeMachine.MILK_CAP) {
                    ammount = CoffeeMachine.MILK_CAP - milk;
                    milk += ammount;
                    coffeeMachine.setMilk(milk);
                    System.out.println("Water refilled.\n Water ammount: " + coffeeMachine.getMilk());
                } else {
                    coffeeMachine.setMilk(ammount);
                    System.out.println("Water refilled.\n Water ammount: " + coffeeMachine.getMilk());
                }
                refillMenu(coffeeMachine);
                break;
            case 5:
//                returnToMainMenu(coffeeMachine);
                break;
            default:
                refillMenu(coffeeMachine);
                throw new Exception("Entered an invalid command");
        }
    }
// TODO make methods return value. E.x. return a String here.
    public void getStatus(CoffeeMachine coffeeMachine) throws IOException {
        StringBuilder statusMenu = new StringBuilder("___STATUS___\n" +
                "Coffee beans ammount: " + coffeeMachine.getBeans() + "\n" +
                "Water ammount: " + coffeeMachine.getWater() + "\n" +
                "Milk ammount: " + coffeeMachine.getMilk() + "\n" +
                "Sugar ammount: " + coffeeMachine.getSugar() + "\n" +
                "Actions before cleaning: " + coffeeMachine.getUsage());
        System.out.println(statusMenu);
        System.out.println("Press enter to continue");
        System.in.read();
    }

    public void clean(CoffeeMachine coffeeMachine) throws Exception {
        coffeeMachine.setUsage(CoffeeMachine.USAGE_CAP);
        System.out.println("SO FRESH AND SO CLEAN");
        mainMenu(coffeeMachine);
    }

    public boolean isReady(CoffeeMachine coffeeMachine) {
        if (coffeeMachine.getWater() == 0 || coffeeMachine.getBeans() == 0
                || coffeeMachine.getMilk() == 0 || coffeeMachine.getSugar() == 0 || coffeeMachine.getUsage() == 0) {
            System.out.println("NOT READY");
            System.out.println("PLEASE REFILL BEFORE USING");
            return false;
        }
        System.out.println("MACHINE IS READY TO USE");
        return true;
    }
}


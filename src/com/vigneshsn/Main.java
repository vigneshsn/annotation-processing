package com.vigneshsn;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
	// write your code here
        Main mainClass = new Main();
        Lion lion = new Lion("Lion king");
        Tiger tiger = new Tiger("Sheer");
        Human person = new Human("Mick", 20);

        mainClass.printAllAnimals(Arrays.asList(lion, tiger, person));
    }

    private void printAllAnimals(List<Object> objects) throws InvocationTargetException, IllegalAccessException {

        for(Object object : objects) {
            if(object.getClass().isAnnotationPresent(Animal.class)) {
                System.out.println("This instance belongs to "  + object.toString());
                for(Method method : object.getClass().getMethods()) {
                    if(method.isAnnotationPresent(Eat.class)) {
                        method.invoke(object, method.getAnnotation(Eat.class).food());
                    }
                }
            }
        }

    }
}


class Human {
    String name;
    int age;

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}


@Animal
class Lion {
    String name;

    public Lion(String name) {
        this.name = name;
    }

    @Eat(food = "meat")
    public void eat(String food) {
        System.out.println("The lion is eating " + food);
    }

    @Override
    public String toString() {
        return "Lion{" +
                "name='" + name + '\'' +
                '}';
    }
}

@Animal
class Tiger {
    public Tiger(String name) {
        this.name = name;
    }

    String name;

    @Eat(food = "grass")
    public void eat(String food) {
        System.out.println("The tiger is eating " + food);
    }

    @Override
    public String toString() {
        return "Tiger{" +
                "name='" + name + '\'' +
                '}';
    }
}
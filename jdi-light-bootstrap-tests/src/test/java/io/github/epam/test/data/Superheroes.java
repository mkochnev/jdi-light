package io.github.epam.test.data;

import io.github.com.entities.Superhero;

public class Superheroes {
    public static Superhero EXAMPLE_HERO = new Superhero().set(field -> {
        field.currentAlias = "Venom";
        field.alterEgo = "Peter Parker";
        field.species = "Symbiote";
        field.selectUniverse = "Marvel Earth-616";
        field.superheroRange = "10.0";
        field.superheroSwitch = "true";
    });
    public static Superhero TEMPLATE_HERO = new Superhero().set(field -> {
        field.currentAlias = "";
        field.alterEgo = "";
        field.species = "Human";
        field.selectUniverse = "Select character's universe";
        field.superheroRange = "50.0";
        field.superheroSwitch = "false";
    });
}

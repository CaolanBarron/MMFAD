package com.mmfad.model;

import com.mmfad.GUI.BooleanDetailComponent;
import com.mmfad.GUI.NumericalDetailComponent;
import com.mmfad.GUI.SelectionDetailComponent;
import com.mmfad.GUI.ToggleDetailComponent;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

public class Drink extends Sellable {


    public boolean isIced() {
        return iced;
    }

    public void setIced(boolean iced) {
        this.iced = iced;
    }

    public int getEspressoShots() {
        return espressoShots;
    }

    public void setEspressoShots(int espressoShots) {
        this.espressoShots = espressoShots;
    }

    public String[] getMilk() {
        return milk;
    }

    public void setMilk(String[] milk) {
        this.milk = milk;
    }

    public String getSelectedMilk() {
        return selectedMilk;
    }

    public void setSelectedMilk(String selectedMilk) {
        this.selectedMilk = selectedMilk;
    }

    public String[] getTemperature() {
        return temperature;
    }

    public void setTemperature(String[] temperature) {
        this.temperature = temperature;
    }

    public String getSelectedTemperature() {
        return selectedTemperature;
    }

    public void setSelectedTemperature(String selectedTemperature) {
        this.selectedTemperature = selectedTemperature;
    }

    public boolean isWhippedCream() {
        return whippedCream;
    }

    public void setWhippedCream(boolean whippedCream) {
        this.whippedCream = whippedCream;
    }

    public String[] getSyrup() {
        return syrup;
    }

    public void setSyrup(String[] syrup) {
        this.syrup = syrup;
    }

    public String[] getSelectedSyrups() {
        return selectedSyrups;
    }

    public void setSelectedSyrups(String[] selectedSyrups) {
        this.selectedSyrups = selectedSyrups;
    }

    public String[] getOther() {
        return other;
    }

    public void setOther(String[] other) {
        this.other = other;
    }

    public String[] getSelectedOther() {
        return selectedOther;
    }

    public void setSelectedOther(String[] selectedOther) {
        this.selectedOther = selectedOther;
    }

    boolean iced;
    int espressoShots;
    String[] milk = {"Skimmed","Semi-skimmed","Full fat","Oat","Soya","Almond","Coconut"};
    String selectedMilk;
    String[] temperature = {"Low", "Regular", "Extra hot"};
    String selectedTemperature;
    boolean whippedCream;
    String[] syrup = {"Caramel", "Vanilla", "Hazelnut"};
    String[] selectedSyrups = new String[syrup.length];
    String[] other = {"Option1", "Option 2", "Option 3"};
    String[] selectedOther = new String[other.length];

    public Drink(String name, float price) {
        super(name, price);
    }

    @Override
    public List<AnchorPane> DisplayOptions() {
        List<AnchorPane> components = new ArrayList<>();

        // Boolean Detail Component for Iced
        components.add((new BooleanDetailComponent<Drink>("Iced", this, Drink::isIced, Drink::setIced)));
        // Numerical Detail Component for Espresso Shots
        components.add(new NumericalDetailComponent<Drink>("Espresso Shots", this, Drink::getEspressoShots, Drink::setEspressoShots));
        // Toggle Detail Component for Milk
        components.add(new ToggleDetailComponent<Drink>("Milk", this, Drink::getSelectedMilk, Drink::setSelectedMilk, getMilk()));
        // Toggle Detail Component for Temperature
        components.add(new ToggleDetailComponent<Drink>("Temperature", this, Drink::getSelectedTemperature, Drink::setSelectedTemperature, getTemperature()));
        // Boolean Detail Component for Whipped Cream
        components.add(new BooleanDetailComponent<Drink>("Whipped cream", this, Drink::isIced, Drink::setIced));
        // List Detail Component for Syrup
        components.add(new SelectionDetailComponent<Drink>("Syrup", this, Drink::getSelectedSyrups, Drink::setSelectedSyrups, getSyrup()));
        // List Detail Component for Other
        components.add(new SelectionDetailComponent<Drink>("Other", this, Drink::getSelectedOther, Drink::setSelectedOther, getOther()));
        return components;
    }
}

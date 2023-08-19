package com.mmfad.model;

import com.mmfad.GUI.BooleanDetailComponent;
import com.mmfad.GUI.SelectionDetailComponent;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

public class Food extends Sellable {



    boolean warmed;
    String[] condiments = {"Ketchup", "BBQ", "Salt"};
    String[] selectedCondiments = new String[condiments.length];
    String[] other = {"Bag", "Fork", "Knife", "Spoon"};
    String[] selectedOther = new String[other.length];


    public Food(String name, float price) {
        super(name, price);
    }

    @Override
    public List<AnchorPane> GetItemOptions() {
        List<AnchorPane> components = new ArrayList<>();

        // Boolean detail component for warmed
        components.add(new BooleanDetailComponent<>("Warmed", this, Food::isWarmed, Food::setWarmed));
        // List Detail component for condiments
        components.add(new SelectionDetailComponent<>("Condiment", this, Food::getSelectedCondiments, Food::setSelectedCondiments, getCondiments()));
        // List Detail component for other
        components.add(new SelectionDetailComponent<>("Other", this, Food::getSelectedOther, Food::setSelectedOther, getOther()));
        return components;
    }

    @Override
    public String DisplayItemOptions() {
        String options = this.name + "\n" + this.price  + "\n";
        if (isWarmed())
            options = options.concat("Warmed\n");
        for (String condiment:
             getSelectedCondiments()) {
            if(condiment != null)
                options = options.concat(condiment + "\n");
        }
        for (String other:
             getSelectedOther()) {
            if(other != null)
                options = options.concat(other + "\n");
        }

        return options;
    }



    public boolean isWarmed() {
        return warmed;
    }
    public void setWarmed(boolean warmed) {
        this.warmed = warmed;
    }
    public String[] getCondiments() {
        return condiments;
    }
    public void setCondiments(String[] condiments) {
        this.condiments = condiments;
    }
    public String[] getSelectedCondiments() {
        return selectedCondiments;
    }
    public void setSelectedCondiments(String[] selectedCondiments) {
        this.selectedCondiments = selectedCondiments;
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
}

package purchasingcars.business;

import java.io.Serializable;

/**
 * Created by lucas on 21/05/16.
 */
public class Car implements Serializable {
    private String nom;

    public Car(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "Car: " + nom;
    }
}

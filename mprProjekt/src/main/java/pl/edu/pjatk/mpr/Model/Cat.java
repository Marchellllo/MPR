package pl.edu.pjatk.mpr.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String color;
    private Long identificator;
    public Cat(){           // <- Potrzebne

    }
    public Cat(String name, String color){
        this.name=name;
        this.color=color;
        this.identificator = calculateIdentifier();

    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
        this.identificator = calculateIdentifier();
    }
    public String getColor(){
        return color;
    }
    public void setColor(String color){
        this.color=color;
        this.identificator = calculateIdentifier();
    }


    public void setIdentificator(Long identificator) {
        this.identificator = identificator;
    }

    public Long calculateIdentifier() {     //Identyfikator
        long suma = 0;
        // Obliczanie sumy wartości znaków z pola name
        if (this.name != null) {
            for (char c : this.name.toCharArray()) {
                suma += c;
            }
        }

        // Obliczanie sumy wartości znaków z pola color
        if (this.color != null) {
            for (char c : this.color.toCharArray()) {
                suma += c;
            }
        }

        return suma;        //Return sumy wartości znaków z pól name i color
    }


}

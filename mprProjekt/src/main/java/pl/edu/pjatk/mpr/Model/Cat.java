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
    public Cat(){           // <- Potrzebne

    }
    public Cat(String name, String color){
        this.name=name;
        this.color=color;

    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getColor(){
        return color;
    }
    public void setColor(String color){
        this.color=color;
    }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }


}

package pl.edu.pjatk.mpr.Services;

import org.springframework.stereotype.Component;
import pl.edu.pjatk.mpr.Model.Cat;

import java.util.ArrayList;
import java.util.List;
@Component
public class CatService {
    List<Cat> CatList = new ArrayList<>();

    public CatService(){
        CatList.add(new Cat("Tom","White"));
        CatList.add(new Cat("Henry","Black"));
        CatList.add(new Cat("James","Grey"));
    }
    public List<Cat> getCatList() {
        return this.CatList;
    }

    public void add(Cat cat) {
        this.CatList.add(cat);
    }

    public Cat getCat(Integer id) {
        return this.CatList.get(id);
    }
    public void deleteCat(int id) {
        if (id >= 0 && id < this.CatList.size()){
            this.CatList.remove(id);
        } else {
            throw new IndexOutOfBoundsException("Nieprawidłowe id: " + id);
        }
    }

    public void updateCat(int id, Cat cat) {
            Cat existingCat = this.CatList.get(id);
            existingCat.setName(cat.getName());
            existingCat.setColor(cat.getColor());
    }
}





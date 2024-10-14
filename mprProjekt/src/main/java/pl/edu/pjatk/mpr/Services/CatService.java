package pl.edu.pjatk.mpr.Services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.mpr.Model.Cat;
import pl.edu.pjatk.mpr.Repository.CatRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class CatService {
    private CatRepository catRepository;
    private List<Cat> CatList = new ArrayList<>();
    public CatService(CatRepository catRepository) {
       this.catRepository = catRepository;

        CatList.add(new Cat("Tom","White"));
        CatList.add(new Cat("Henry","Black"));
        CatList.add(new Cat("James","Grey"));
    }
    public List<Cat> getAllCats() {
        return this.CatList;
    }
    public List<Cat> getByName(String name) {
        return this.catRepository.findByName(name);
    }
    public List<Cat> getByColor(String color) {
        return this.catRepository.findByColor(color);
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
    public void setId(Long id, Cat cat){
        cat.setId(id);
    }
}





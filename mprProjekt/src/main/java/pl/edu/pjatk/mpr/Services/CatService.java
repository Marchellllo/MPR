package pl.edu.pjatk.mpr.Services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.mpr.Model.Cat;
import pl.edu.pjatk.mpr.Repository.CatRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class CatService {
    private final CatRepository catRepository;


    //Dane które będą w repozytorium
    public CatService(CatRepository catRepository) {
       this.catRepository = catRepository;
       if(catRepository.count()==0){
           catRepository.save(new Cat("Tom","White"));
           catRepository.save(new Cat("Henry","Black"));
           catRepository.save(new Cat("James","Grey"));
       }

    }

    public List<Cat> getAllCats() {                 //Metoda wyszukania wszystkich kotów
        return (List<Cat>) catRepository.findAll();
    }
    public List<Cat> getByName(String name) {       //Metoda wyszukania kota po nazwie
        return catRepository.findByName(name);
    }
    public List<Cat> getByColor(String color) {     //Metoda wyszukania kota po kolorze
        return catRepository.findByColor(color);
    }

    public Cat add(Cat cat) {                       //Metoda dodania kota
        return catRepository.save(cat);
    }

    public Cat getCat(Long id) {                    //Metoda wyszukania kota po id
        return catRepository.findById(id).orElse(null);
    }

    public void deleteCat(Long id) {
        catRepository.deleteById(id);
    }       //Metoda usunięcia kota


    public void updateCat(Long id, Cat cat) {                               //Metoda zaktualizowania kota
        if(catRepository.existsById(id)){
            Cat existingCat = catRepository.findById(id).get();
            existingCat.setName(cat.getName());
            existingCat.setColor(cat.getColor());
            existingCat.setIdentificator(existingCat.calculateIdentifier());
            catRepository.save(existingCat);
        }


    }


}





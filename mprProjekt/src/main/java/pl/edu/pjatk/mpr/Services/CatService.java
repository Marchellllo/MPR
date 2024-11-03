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
    private StringUtilsService stringUtilsService;


    //Dane które będą w repozytorium
    public CatService(CatRepository catRepository, StringUtilsService stringUtilsService) {
       this.catRepository = catRepository;
       this.stringUtilsService = stringUtilsService;

       if(catRepository.count()==0){
           catRepository.save(new Cat("Tom","White"));
           catRepository.save(new Cat("Henry","Black"));
           catRepository.save(new Cat("James","Grey"));
       }

    }

    public List<Cat> getAllCats() {                 //Metoda wyszukania wszystkich kotów
        List<Cat> cats = (List<Cat>) catRepository.findAll();
        return getFormattedCats(cats);
    }
    public List<Cat> getByName(String name) {       //Metoda wyszukania kota po nazwie
        return catRepository.findByName(name);
    }
    public List<Cat> getByColor(String color) {     //Metoda wyszukania kota po kolorze
        return catRepository.findByColor(color);
    }

    public Cat add(Cat cat) {
        cat.setIdentificator(cat.calculateIdentifier());
        String upperCaseName = stringUtilsService.toUpperCase(cat.getName());
        String upperCaseColor = stringUtilsService.toUpperCase(cat.getColor());
        cat.setName(upperCaseName);
        cat.setColor(upperCaseColor);

        return catRepository.save(cat);

    }

    public Cat getCat(Long id) {
        Cat cat = catRepository.findById(id).orElse(null);
        if (cat != null) {
            cat.setName(stringUtilsService.capitalizeFirstLetter(cat.getName()));
            cat.setColor(stringUtilsService.capitalizeFirstLetter(cat.getColor()));
        }

        return cat;
    }

    public void deleteCat(Long id) {
        catRepository.deleteById(id);
    }       //Metoda usunięcia kota


    public void updateCat(Long id, Cat cat) {                               //Metoda zaktualizowania kota
        if(catRepository.existsById(id)){
            Cat existingCat = catRepository.findById(id).get();
            existingCat.setName(stringUtilsService.toUpperCase(cat.getName()));
            existingCat.setColor(stringUtilsService.toUpperCase(cat.getColor()));
            existingCat.setIdentificator(existingCat.calculateIdentifier());
            catRepository.save(existingCat);
        }


    }
    public List<Cat> getFormattedCats(List<Cat> cats){          //Metoda do formatowania nazw i kolorów kotów
        List<Cat> formattedCats = new ArrayList<>();
        for(Cat cat: cats){
            String formattedName = stringUtilsService.capitalizeFirstLetter(cat.getName());
            String formattedColor = stringUtilsService.capitalizeFirstLetter(cat.getColor());
            Cat formattedCat = new Cat(formattedName, formattedColor);
            formattedCats.add(formattedCat);
        }
        return formattedCats;
    }


}





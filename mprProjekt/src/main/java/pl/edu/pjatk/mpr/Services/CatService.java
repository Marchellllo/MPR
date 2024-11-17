package pl.edu.pjatk.mpr.Services;

import org.hibernate.boot.model.naming.Identifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.mpr.Exception.CatAlreadyExists;
import pl.edu.pjatk.mpr.Exception.CatIsEmpty;
import pl.edu.pjatk.mpr.Exception.CatNotFound;
import pl.edu.pjatk.mpr.Model.Cat;
import pl.edu.pjatk.mpr.Repository.CatRepository;
import java.util.stream.Collectors;

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
        if(cats.isEmpty()){
            throw new CatNotFound();
        }
        return getFormattedCats(cats);
    }
    public List<Cat> getByName(String name) {       //Metoda wyszukania kota po nazwie
        List<Cat> cats = catRepository.findByName(name);
        if(cats.isEmpty()){
            throw new CatNotFound();
        }
        return getFormattedCats(cats);
    }
    public List<Cat> getByColor(String color) {     //Metoda wyszukania kota po kolorze
        List<Cat> cats = catRepository.findByColor(color);
        if(cats.isEmpty()){
            throw new CatNotFound();
        }
        return getFormattedCats(cats);
    }

    public Cat add(Cat cat) {
        if (cat.getName() == null || cat.getName().isEmpty()) {
            throw new CatIsEmpty();
        }
        if (cat.getColor() == null || cat.getColor().isEmpty()) {
            throw new CatIsEmpty();
        }

        // Oblicz identyfikator
        cat.setIdentificator(cat.calculateIdentifier());

        // Używamy StringUtils do zamiany na wielkie litery
        String upperCaseName = stringUtilsService.toUpperCase(cat.getName());
        String upperCaseColor = stringUtilsService.toUpperCase(cat.getColor());
        cat.setName(upperCaseName);
        cat.setColor(upperCaseColor);

        // Sprawdzamy, czy kot o tym identyfikatorze już istnieje w bazie
        if (catRepository.findByIdentificator(cat.getIdentificator()).isPresent()) {
            throw new CatAlreadyExists();
        }

        // Zapisz kota do repozytorium
        return catRepository.save(cat);
    }

    public Cat getCat(Long id) {
        Cat cat = catRepository.findById(id).orElseThrow(CatNotFound::new);
        cat.setName(stringUtilsService.capitalizeFirstLetter(cat.getName()));
        cat.setColor(stringUtilsService.capitalizeFirstLetter(cat.getColor()));
        return cat;
    }

    public void deleteCat(Long id) {
        if (id == null){
            throw new CatNotFound();
        }

        catRepository.deleteById(id);
    }       //Metoda usunięcia kota


    public void updateCat(Long id, Cat cat) {
        //Metoda zaktualizowania kota
        if (cat.getName() == null || cat.getName().isEmpty()) {
            throw new CatIsEmpty();
        }
        if (cat.getColor() == null || cat.getColor().isEmpty()) {
            throw new CatIsEmpty();
        }

        Cat existingCat = catRepository.findById(id)
                .orElseThrow(CatNotFound::new);

        existingCat.setName(stringUtilsService.toUpperCase(cat.getName()));
        existingCat.setColor(stringUtilsService.toUpperCase(cat.getColor()));
        existingCat.setIdentificator(existingCat.calculateIdentifier());
        catRepository.save(existingCat);


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





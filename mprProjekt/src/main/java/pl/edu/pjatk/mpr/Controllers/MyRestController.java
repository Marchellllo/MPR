package pl.edu.pjatk.mpr.Controllers;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.mpr.Model.Cat;
import pl.edu.pjatk.mpr.Services.CatService;

import java.util.List;

@RestController
@RequestMapping("cat")
public class MyRestController {

    private final CatService catService;

    @Autowired
    public MyRestController(CatService catService) {
        this.catService = catService;
    }



    @GetMapping("all")        // Endpoint do znalezienia wszystkich kotów
    public List<Cat> getAll() {
        return catService.getAllCats();
    }


    @GetMapping("{id}")         // Endpoint do znalezienia kota po id
    public ResponseEntity<Cat> getId(@PathVariable Long id) {
        Cat cat = catService.getCat(id);

        if(cat == null){
            return ResponseEntity.notFound().build();  // Jeśli kot nie istnieje błąd 404
        }

        return ResponseEntity.ok(cat); // Zwracamy kota, jeśli istnieje
    }


    @GetMapping("name/{name}")      // Endpoint do znalezienia kota po nazwie
    public List<Cat> findByName(@PathVariable String name) {
        return this.catService.getByName(name);
    }


    @GetMapping("color/{color}")        // Endpoint do znalezienia kota po kolorze
    public List<Cat> findByColor(@PathVariable String color) {
        return this.catService.getByColor(color);
    }


    @PostMapping("add")     // Endpoint do dodawania kota
    public  ResponseEntity<Cat> addCat(@RequestBody Cat cat) {
        this.catService.add(cat);
        return ResponseEntity.ok(cat);
    }


    @PatchMapping("update/{id}")        // Endpoint do aktualizacji kota
    public ResponseEntity<Cat> updateCat(@PathVariable Long id, @RequestBody Cat cat) {
        Cat existingCat = catService.getCat(id);
        if (existingCat == null) {
            return ResponseEntity.notFound().build();
        }
        catService.updateCat(id, cat);

        return ResponseEntity.ok(existingCat);
    }


    @DeleteMapping("delete/{id}")       // Endpoint do usuwania kotów
    public ResponseEntity<Void> deleteCat(@PathVariable Long id){
        Cat cat = catService.getCat(id);
        if (id==null){
            return ResponseEntity.notFound().build();
        }
        catService.deleteCat(id);
        return ResponseEntity.noContent().build();
    }



}








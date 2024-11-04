package pl.edu.pjatk.mpr.Controllers;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.mpr.Exception.CatNotFound;
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
    public ResponseEntity<List<Cat>> getAll() {

        return new ResponseEntity<>(catService.getAllCats(), HttpStatus.OK);
    }


    @GetMapping("{id}")         // Endpoint do znalezienia kota po id
    public ResponseEntity<Cat> getId(@PathVariable Long id) {

        return new ResponseEntity<>(catService.getCat(id), HttpStatus.OK); // Zwracamy kota, jeśli istnieje
    }


    @GetMapping("name/{name}")      // Endpoint do znalezienia kota po nazwie
    public ResponseEntity<List<Cat>> findByName(@PathVariable String name) {

        return new ResponseEntity<>(catService.getByName(name), HttpStatus.OK);
    }



    @GetMapping("color/{color}")        // Endpoint do znalezienia kota po kolorze
    public ResponseEntity<List<Cat>> findByColor(@PathVariable String color) {

        return new ResponseEntity<>(catService.getByColor(color), HttpStatus.OK);
    }



    @PostMapping("add")     // Endpoint do dodawania kota
    public  ResponseEntity<Cat> addCat(@RequestBody Cat cat) {
        Cat addedCat = catService.add(cat);

        return ResponseEntity.ok(addedCat);
    }


    @PatchMapping("update/{id}")        // Endpoint do aktualizacji kota
    public ResponseEntity<Cat> updateCat(@PathVariable Long id, @RequestBody Cat cat) {



        return ResponseEntity.ok(catService.updateCat(id, cat));
    }


    @DeleteMapping("delete/{id}")       // Endpoint do usuwania kotów
    public ResponseEntity<Void> deleteCat(@PathVariable Long id){
        Cat cat = catService.getCat(id);

        catService.deleteCat(id);
        return ResponseEntity.noContent().build();
    }



}








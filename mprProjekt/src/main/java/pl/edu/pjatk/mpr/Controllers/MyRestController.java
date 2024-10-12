package pl.edu.pjatk.mpr.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("all") // <- endpoint
    public List<Cat> getAll() {
        return this.catService.getCatList();
    }

    @GetMapping("{id}") // <- endpoint
    public Cat get(@PathVariable int id) {
        return this.catService.getCat(id);
    }

    @PostMapping("add")
    public void addCapybara(@RequestBody Cat cat) {
        this.catService.add(cat);
    }

    @DeleteMapping("{id}")
    public void deleteCat(@PathVariable int id){
        catService.deleteCat(id);
    }

    @PutMapping("{id}")
    public void updateCat(@PathVariable int id, @RequestBody Cat cat){
        catService.updateCat(id, cat);
    }
}








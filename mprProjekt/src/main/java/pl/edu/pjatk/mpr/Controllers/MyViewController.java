package pl.edu.pjatk.mpr.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.mpr.Model.Cat;
import pl.edu.pjatk.mpr.Services.CatService;

@Controller //RestController
public class MyViewController {

    private final CatService catService;
    public MyViewController(CatService catService) {
        this.catService = catService;
    }
    @GetMapping("view/all")
    public String displayAllCat(Model model) {
        model.addAttribute("cats", catService.getAllCats());
        return "viewAll";
    }


    @GetMapping("addForm")
    public String displayAddForm(Model model) {
        model.addAttribute("cat", new Cat());
        return "addForm";
    }

    @PostMapping("addForm")
    public String submitAddForm(@ModelAttribute Cat cat){
        this.catService.add(cat);
        return "redirect:/view/all";
    }

    @GetMapping("/deleteForm/{id}")
    public String displayDeleteForm(@PathVariable Long id, Model model) {
        Cat cat = catService.getCat(id);
        model.addAttribute("cat", cat);
        return "deleteForm";
    }
    @PostMapping("/deleteForm")
    public String submitDeleteForm(@ModelAttribute Cat cat) {
        catService.deleteCat(cat.getId());
        return "redirect:/view/all";
    }



    @GetMapping("updateForm/{id}")
    public String displayUpdateForm(@PathVariable Long id, Model model){
        Cat cat = catService.getCat(id);
        model.addAttribute("cat", cat);
        return "updateForm";
    }

    @PostMapping("updateForm")
    public String submitUpdateForm(@RequestParam Long id, @ModelAttribute Cat cat){
        this.catService.updateCat(id,cat);
        return "redirect:/view/all";
    }
}

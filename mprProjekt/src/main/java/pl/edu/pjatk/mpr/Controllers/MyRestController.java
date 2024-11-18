package pl.edu.pjatk.mpr.Controllers;
import org.apache.pdfbox.pdmodel.PDDocument;

import org.apache.coyote.Response;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1CFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.mpr.Exception.CatNotFound;
import pl.edu.pjatk.mpr.Model.Cat;
import pl.edu.pjatk.mpr.Services.CatService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import java.io.IOException;
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
        List<Cat> cat = catService.getByColor(color);
        if (cat.isEmpty()) {
            return ResponseEntity.notFound().build(); // Jeśli nie ma kota o danej nazwie
        }
        return new ResponseEntity<>(catService.getByColor(color), HttpStatus.OK);
    }



    @PostMapping("add")     // Endpoint do dodawania kota
    public  ResponseEntity<Cat> addCat(@RequestBody Cat cat) {
        Cat addedCat = catService.add(cat);

        return ResponseEntity.ok(addedCat);
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
        if (cat==null){
            return ResponseEntity.notFound().build();
        }
        catService.deleteCat(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("information/{id}")
    public ResponseEntity<byte[]> generatePDF(@PathVariable Long id) {
        Cat cat = catService.getCat(id);


        PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

    try{
        PDPageContentStream contentStream = new PDPageContentStream(document,page);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.COURIER,16);
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(100,700);

        contentStream.showText("Cat ID: "+ cat.getId());
        contentStream.newLine();
        contentStream.showText("Name: "+ cat.getName());
        contentStream.newLine();
        contentStream.showText("Color: "+ cat.getColor());
        contentStream.newLine();
        contentStream.showText("Identificator: "+ cat.getIdentificator());
        contentStream.endText();
        contentStream.close();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        document.save(outputStream);

        document.close();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=PDF.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(outputStream.toByteArray());

    } catch (IOException e) {
        throw new CatNotFound();
    }

    }
}








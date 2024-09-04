package ru.itmo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.dto.CatDto;
import ru.itmo.services.CatService;
import java.util.List;

@RestController
@RequestMapping("/cats")
public class CatController {
    private final CatService catService;

   @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
    }
    @GetMapping(value = "/{id}")
    public CatDto findById(@PathVariable(name = "id") int id) {
        return catService.findById(id);
    }
    @PostMapping
    public CatDto creation(@RequestBody CatDto catDto) {
        return catService.creation(catDto);
    }
    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable(name = "id") int id) {
        catService.deleteById(id);
    }
    @PutMapping("/{id}/make-friend")
    public void makeFriends(@PathVariable(value = "id") int cat1Id, @RequestParam(value = "id") int cat2Id) {
        catService.makeFriends(cat1Id, cat2Id);
    }
    @PutMapping("/{id}/drop-friend")
    public void dropFriends(@PathVariable(value = "id") int cat1Id, @RequestParam(value = "id") int cat2Id) {
        catService.dropFriends(cat1Id, cat2Id);
    }
    @PutMapping("/{id}/change-owner")
    public void changeOwner(@PathVariable(name = "id") int catId, @RequestParam(name = "newOwnerId") int newOwnerId) {
        catService.changeOwner(catId, newOwnerId);
    }

    @GetMapping
    public List<CatDto> getAll() {
        return catService.getAll();
    }
    @GetMapping(value = "/cats/breed")
    public List<CatDto> getCatsByBreed(@RequestParam(name = "breed") String breed) {
        return catService.getCatsByBreed(breed);
    }
    @GetMapping(value = "/cats/color")
    public List<CatDto> getCatsByColor(@RequestParam(name = "color") String color) {
        return catService.getCatsByColor(color);
    }
    @GetMapping(value = "/cats/color-breed")
    public List<CatDto> getCatsByColorAndBreed(@RequestParam(name = "color") String color,
                                               @RequestParam(name = "breed") String breed) {
        return catService.getCatsByColorAndBreed(color, breed);
    }
}

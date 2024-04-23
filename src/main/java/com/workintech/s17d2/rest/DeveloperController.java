package com.workintech.s17d2.rest;
import com.workintech.s17d2.tax.Taxtable;

import com.workintech.s17d2.dto.DeveloperResponse;
import com.workintech.s17d2.model.Developer;
import com.workintech.s17d2.model.DeveloperFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/developers")
public class DeveloperController {
    public Map<Integer, Developer> developers;
    private Taxtable taxtable;

@Autowired
    public DeveloperController(Taxtable taxtable) {
        this.taxtable = taxtable;
    }
    @PostConstruct
    public  void init(){
    developers =new HashMap<>();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  Developer save(@RequestBody Developer developer){
    Developer createdDeveloper = DeveloperFactory.createDeveloper(developer, taxtable);
    if (Objects.nonNull(createdDeveloper)){
        developers.put(developer.getId(), developer);
    }
    return null;
    }
    @GetMapping
    public List<Developer> getAll(){
    return developers.values().stream().toList();
    }
    @GetMapping("/{id}")
public  Developer GET(@PathVariable Integer id){
  Developer developer = developers.get(id);
return  new Developer(developer.getId(),developer.getName(),developer.getSalary(),developer.getExperience());
    }

@PutMapping("/{id}")
public  Developer update(@PathVariable Integer id, @RequestBody Developer developer){
  developer.setId(id);
  Developer newDeveloper =DeveloperFactory.createDeveloper(developer,taxtable);
  this.developers.put(newDeveloper.getId(), newDeveloper);
  return new DeveloperResponse(newDeveloper,"update başarılı", HttpStatus.OK.value()).getDeveloper();
    }


    @DeleteMapping("/{id}")

    public DeveloperResponse delete(@PathVariable("id") int id){
        Developer removeDeveloper =developers.get(id);
   this.developers.remove(id);

        return new DeveloperResponse(removeDeveloper,"silme başarılı",HttpStatus.NO_CONTENT.value());
    }

}

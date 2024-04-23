package com.workintech.s17d2.model;
import com.workintech.s17d2.tax.Taxtable;
public class DeveloperFactory {
    public static  Developer createDeveloper(Developer developer, Taxtable taxtable){
        Developer createDeveloper = null;
        if (developer.getExperience().equals(Experience.JUNIOR)){

            createDeveloper = new JuniorDeveloper(developer.getId(), developer.getName(),
                    (developer.getSalary()-(developer.getSalary()* taxtable.getSimpleTaxRate())/100));
        } else if (developer.getExperience().equals(Experience.MID)){

            createDeveloper = new JuniorDeveloper(developer.getId(), developer.getName(),
                    (developer.getSalary()-(developer.getSalary()* taxtable.getSimpleTaxRate())/100));
        } else if (developer.getExperience().equals(Experience.SENIOR)){

            createDeveloper = new JuniorDeveloper(developer.getId(), developer.getName(),
                    (developer.getSalary()-(developer.getSalary()* taxtable.getSimpleTaxRate())/100));
        }
        return  createDeveloper;
    }
}

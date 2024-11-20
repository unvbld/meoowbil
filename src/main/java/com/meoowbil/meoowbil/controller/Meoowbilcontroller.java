package com.meoowbil.meoowbil.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.meoowbil.meoowbil.models.Meoowbil;
import com.meoowbil.meoowbil.services.Meoowbilservice;



@Controller
@RequestMapping("/meoowbil")
public class Meoowbilcontroller {
    private Meoowbilservice meoowbilservice;


    
    public Meoowbilcontroller(Meoowbilservice meoowbilservice) {
        this.meoowbilservice = meoowbilservice;
    }

    @GetMapping
    public String listMeoowbils(Model model) {
        List<Meoowbil> meoowbil = meoowbilservice.getAllMeoowbils();
        model.addAttribute("meoowbil", meoowbil);
        return "Meoowbil-list";
    }

    @GetMapping("meoowbil/new")
    public String createMeoowbil(Model model) {
        Meoowbil meoowbil = new Meoowbil();
        model.addAttribute("meoowbil", meoowbil);
        return "Meoowbil-create";
    }
    

    
    



}

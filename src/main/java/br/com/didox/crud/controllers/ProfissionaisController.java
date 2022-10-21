package br.com.didox.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.didox.crud.DAOs.IProfissionalRepository;

@Controller
@RequestMapping("/profissionais")
public class ProfissionaisController {
    @Autowired
    private IProfissionalRepository repo;
    
    @GetMapping("")
    public String index(Model modelView){
        var profissionais = repo.findAll();
        modelView.addAttribute("profissionais", profissionais);
        return "profissionais/index";
    }
}

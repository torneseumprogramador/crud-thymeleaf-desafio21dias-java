package br.com.didox.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.didox.crud.DAOs.IProfissionalRepository;
import br.com.didox.crud.DTOs.ProfissionalDTO;
import br.com.didox.crud.models.Profissional;
import br.com.didox.crud.servicos.GenericBuilderDTOModel;

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

    @GetMapping("/novo")
    public String novo(Model modelView){
        modelView.addAttribute("action", "/profissionais");
        return "profissionais/form";
    }

    @PostMapping("")
    public String cadastrar(Model modelView, ProfissionalDTO profissionalDTO){
        modelView.addAttribute("action", "/profissionais");
        modelView.addAttribute("profissional", profissionalDTO);
        modelView.addAttribute("profissional", profissionalDTO);

        if(profissionalDTO.getNome().equals("")){
            modelView.addAttribute("erro", "Nome é obrigatório");
            return "profissionais/form";
        }
        try{
            var profissional = new GenericBuilderDTOModel<Profissional>(Profissional.class).build(profissionalDTO);
            repo.save(profissional);
            return "redirect:/profissionais";
        }
        catch(Exception err){
            modelView.addAttribute("erro", err.getMessage());
            return "profissionais/form";
        }
    }

    @GetMapping("/{id}/editar")
    public String editar(Model modelView, @PathVariable int id){
        modelView.addAttribute("action", "/profissionais/" + id);

        var profissionalOptional = repo.findById(id);
        if(profissionalOptional.isEmpty()){
            modelView.addAttribute("erro", "Id do usuário não encontrado, digite os dados para cadastrar um novo");
            return "profissionais/form";
        }

        var profissional = profissionalOptional.get();
        modelView.addAttribute("profissional", profissional);
        return "profissionais/form";
    }

    @PostMapping("/{id}")
    public String salvar(Model modelView, @PathVariable int id, ProfissionalDTO profissionalDTO){
        modelView.addAttribute("action", "/profissionais/" + id);
        modelView.addAttribute("profissional", profissionalDTO);

        if(!repo.existsById(id)){
            modelView.addAttribute("erro", "Id do usuário não encontrado");
            return "profissionais/form";
        }
        try{
            var profissional = new GenericBuilderDTOModel<Profissional>(Profissional.class).build(profissionalDTO, repo.findById(id).get());
            repo.save(profissional);
            return "redirect:/profissionais";
        }
        catch(Exception err){
            modelView.addAttribute("erro", err.getMessage());
            return "profissionais/form";
        }
    }

    @GetMapping("/{id}/excluir")
    public String novo(@PathVariable int id){
        repo.deleteById(id);

        return "redirect:/profissionais";
    }
}

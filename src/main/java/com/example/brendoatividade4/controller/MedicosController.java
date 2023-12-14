package com.example.brendoatividade4.controller;

import com.example.brendoatividade4.model.entity.Consulta;
import com.example.brendoatividade4.model.entity.Medico;
import com.example.brendoatividade4.model.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

@Transactional
@Controller
@RequestMapping("medicos")
public class MedicosController {

    @Autowired
    MedicoRepository repository;



    /**
     * @param medico necessário devido utilizar no form.html o th:object que faz referência ao objeto esperado no controller.
     * @return
     */
    @GetMapping("/form")
    public String form(Medico medico){
        return "/medicos/form";
    }



    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("medicos", repository.medicos());
        return new ModelAndView("medicos/list", model);
    }





    @GetMapping("/listconsulmedico/{id}")
    public ModelAndView listConsultasMedico(@PathVariable("id") Long id, ModelMap model) {
        Medico medico = repository.findMedicoById(id);
        List<Consulta> consultas = medico.getConsultas();
        model.addAttribute("consultas", consultas);
        return new ModelAndView("medicos/listconsulmedico", model);
    }


    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        repository.remove(id);
        return new ModelAndView("redirect:/medicos/list");
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid Medico medico, BindingResult result){
        if (result.hasErrors()) {
            // Tratar os erros de validação
            return new ModelAndView ("/medicos/form");
        }

        repository.save(medico);
        return new ModelAndView("redirect:/medicos/list");
    }


    @PostMapping("/update")
    public ModelAndView update(@Valid Medico medico, BindingResult result) {
        if (result.hasErrors()) {
            // Tratar os erros de validação
            return new ModelAndView("/medicos/form");
        }

        repository.update(medico);
        return new ModelAndView("redirect:/medicos/list");
    }

        /*@GetMapping("/sucess")
    public String sucesso(RedirectAttributes attributes) {
        // Faça a ação desejada, por exemplo, salvar algo no banco de dados

        // Adicione um atributo para redirecionar
        attributes.addFlashAttribute("sucesso", "Ação concluída com sucesso!");

        // Redirecione para outra página
        return "redirect:/medicos/list";
    }*/

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("medico", repository.findMedicoById(id));
        return new ModelAndView("/medicos/form", model);
    }

    @GetMapping("/buscar")
    public String buscarMedicosPorNome(@RequestParam("nome") String nome, Model model) {
        List<Medico> medicos = repository.findMedicosByNome(nome);
        model.addAttribute("medicos", medicos);
        return "/medicos/list";
    }
    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */


}
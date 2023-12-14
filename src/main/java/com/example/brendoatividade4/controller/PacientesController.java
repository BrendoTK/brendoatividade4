package com.example.brendoatividade4.controller;

import com.example.brendoatividade4.model.entity.Consulta;
import com.example.brendoatividade4.model.entity.Paciente;
import com.example.brendoatividade4.model.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Transactional
@Controller
@RequestMapping("pacientes")
public class PacientesController {

    @Autowired
    PacienteRepository repository;



    /**
     * @param paciente necessário devido utilizar no form.html o th:object que faz referência ao objeto esperado no controller.
     * @return
     */
    @GetMapping("/form")
    public String form(Paciente paciente){
        return "/pacientes/form";
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("pacientes", repository.pacientes());
        return new ModelAndView("pacientes/list", model);
    }

    @GetMapping("/listconsulpaciente/{id}")
    public ModelAndView listConsultasPaciente(@PathVariable("id") Long id, ModelMap model) {
        Paciente paciente = repository.findPacienteById(id);
        List<Consulta> consultas = paciente.getConsultas();
        model.addAttribute("consultas", consultas);
        return new ModelAndView("pacientes/listconsulpaciente", model);
    }


    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        repository.remove(id);
        return new ModelAndView("redirect:/pacientes/list");
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid Paciente paciente, BindingResult result){
        if (result.hasErrors()) {
            // Tratar os erros de validação
            return new ModelAndView ("/pacientes/form");
        }

        repository.save(paciente);
        return new ModelAndView("redirect:/pacientes/list");
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid Paciente paciente, BindingResult result) {
        if (result.hasErrors()) {
            // Tratar os erros de validação
            return new ModelAndView("/pacientes/form");
        }

        repository.update(paciente);
        return new ModelAndView("redirect:/pacientes/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("paciente", repository.findPacienteById(id));
        return new ModelAndView("/pacientes/form", model);
    }

    @GetMapping("/buscar")
    public String buscarPacientesPorNome(@RequestParam("nome") String nome, Model model) {
        List<Paciente> pacientes = repository.findPacientesByNome(nome);
        model.addAttribute("pacientes", pacientes);
        return "/pacientes/list";
    }

    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */


}
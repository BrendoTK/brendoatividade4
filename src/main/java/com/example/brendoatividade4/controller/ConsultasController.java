package com.example.brendoatividade4.controller;

import com.example.brendoatividade4.model.entity.Consulta;
import com.example.brendoatividade4.model.entity.Consulta;
import com.example.brendoatividade4.model.entity.Medico;
import com.example.brendoatividade4.model.entity.Paciente;
import com.example.brendoatividade4.model.repository.ConsultaRepository;
import com.example.brendoatividade4.model.repository.MedicoRepository;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Transactional
@Controller
@RequestMapping("consultas")
public class ConsultasController {

    @Autowired
    ConsultaRepository repository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;



    /**
     * @param consulta necessário devido utilizar no form.html o th:object que faz referência ao objeto esperado no controller.
     * @return
     */
    // Mapeamento para exibir o formulário de consulta
    @GetMapping("/form")
    public String form(Consulta consulta, Model model) {
        // Consulta consulta = new Consulta(); // Não é necessário criar uma nova instância de Consulta

        // Busque a lista de pacientes e médicos diretamente dos repositórios
        List<Paciente> pacientes = pacienteRepository.findAll();
        List<Medico> medicos = medicoRepository.findAll();

//        model.addAttribute("consulta", consulta);
        model.addAttribute("pacientes", pacientes);
        model.addAttribute("medicos", medicos);

        return "/consultas/form"; // Renderize a página do formulário de consulta
    }



    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("consultas", repository.consultas());
        return new ModelAndView("/consultas/list", model);
    }




    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        repository.remove(id);
        return new ModelAndView("redirect:/consultas/list");
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid Consulta consulta, BindingResult result, ModelMap model){
        if (result.hasErrors()) {

            // Busque a lista de pacientes e médicos diretamente dos repositórios
            List<Paciente> pacientes = pacienteRepository.findAll();
            List<Medico> medicos = medicoRepository.findAll();

//        model.addAttribute("consulta", consulta);
            model.addAttribute("pacientes", pacientes);
            model.addAttribute("medicos", medicos);
            // Tratar os erros de validação
            return new ModelAndView("/consultas/form");
        }

        repository.save(consulta);
        return new ModelAndView("redirect:/consultas/list");
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid Consulta consulta, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {

            // Busque a lista de pacientes e médicos diretamente dos repositórios
            List<Paciente> pacientes = pacienteRepository.findAll();
            List<Medico> medicos = medicoRepository.findAll();

//        model.addAttribute("consulta", consulta);
            model.addAttribute("pacientes", pacientes);
            model.addAttribute("medicos", medicos);
            // Tratar os erros de validação
            return new ModelAndView("/consultas/form");
        }

        repository.update(consulta);
        return new ModelAndView("redirect:/consultas/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        List<Paciente> pacientes = pacienteRepository.findAll();
        List<Medico> medicos = medicoRepository.findAll();


        model.addAttribute("pacientes", pacientes);
        model.addAttribute("medicos", medicos);
        model.addAttribute("consulta", repository.findConsultaById(id));
        return new ModelAndView("/consultas/form", model);
    }

    @GetMapping("/buscar")
    public String buscarConsultasPorData(@RequestParam("data") String data, Model model) {
        List<Consulta> consultas = repository.findConsultasByData(data);
        model.addAttribute("consultas", consultas);
        return "/consultas/list";
    }

    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */


}
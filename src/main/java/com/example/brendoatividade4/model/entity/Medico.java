package com.example.brendoatividade4.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

@Entity
@DiscriminatorValue("Medico")
public class Medico extends Pessoa {

    @NotBlank(message = "O crm do médico é obrigatório!")
    @Pattern(
            regexp = "^[A-Z]{2}/\\d{6}$",
            message = "Formato de CRM inválido. Use o formato UF/XXXXXX."
    )
    private String crm;

    @OneToMany(mappedBy = "medico")
    private List<Consulta> consultas;

    //getters e setters

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public List<Consulta> getConsultas(){
        return consultas;
    }

    public void setConsultasmedico(List<Consulta> consultas) {
        this.consultas = consultas;
    }


    @Override
    public String toString() {
        return "Dados{" +
                "crm='" + crm +
                ", consultas='" + consultas + '\'' +
                '}';
    }

}
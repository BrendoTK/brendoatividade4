package com.example.brendoatividade4.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

@Entity
@DiscriminatorValue("Paciente")
public class Paciente extends Pessoa {
    @NotBlank(message = "O telefone é obrigatório.")
    @Pattern(
            regexp = "^\\(\\d{2}\\)\\d{4,5}-\\d{4}$",
            message = "Formato de telefone inválido. Use o formato (DD)XXXX-XXXX ou (DD)XXXXX-XXXX."
    )
    private String telefone;



    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultas;

    //getters e setters
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Consulta> getConsultas(){
        return consultas;
    }

    @Override
    public String toString() {
        return "Dados{" +
                "telefone='" + telefone +
                ", consultas='" + consultas + '\'' +
                '}';
    }

}
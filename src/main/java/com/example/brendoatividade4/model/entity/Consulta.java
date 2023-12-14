package com.example.brendoatividade4.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Consulta{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A data da consulta é obrigatória!")
    @Pattern(
            regexp = "^(19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$",
            message = "Formato de data inválido. Use o formato yyyy-MM-dd (do ano 1900 até o ano 2099)."
    )
    private String data;

    @NotNull(message = "O valor da consulta é obrigatório!")
    private double valor;

    @NotBlank(message = "A observação da consulta é obrigatória!")
    @Size(max = 100, message = "Observação não pode ter mais de 100 letras.")
    private String observacao;

    @ManyToOne()
    @JoinColumn(name = "id_paciente")
    @OnDelete(action = OnDeleteAction.CASCADE) // Definindo ação de cascata
    private Paciente paciente;

    @ManyToOne()
    @JoinColumn(name = "id_medico")
    @OnDelete(action = OnDeleteAction.CASCADE) // Definindo ação de cascata
    private Medico medico;

    //getters e setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Medico getMedico() {
        return medico;
    }
    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "id=" + id +
                ", data='" + data +
                ", valor='" + valor +
                ", paciente='" + paciente +
                ", medico='" + medico +
                ", observacao='" + observacao + '\'' +
                '}';
    }

}
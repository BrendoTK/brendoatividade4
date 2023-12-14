package com.example.brendoatividade4.model.repository;
import com.example.brendoatividade4.model.entity.Paciente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class PacienteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public PacienteRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Transactional
    public void remove(Long id){
        Paciente p = entityManager.find(Paciente.class, id);
        entityManager.remove(p);
    }

    public Paciente findPacienteById(Long id) {
        return entityManager.find(Paciente.class, id);
    }

    public List<Paciente> pacientes() {
        return entityManager.createQuery("SELECT p FROM Paciente p", Paciente.class)
                .getResultList();
    }

    public List<Paciente> findAll() {
        return entityManager.createQuery("SELECT p FROM Paciente p", Paciente.class).getResultList();
    }

    public Paciente findById(Long id) {
        return entityManager.find(Paciente.class, id);
    }

    public void save(Paciente paciente){
        entityManager.persist(paciente);
    }

    //public void remove(Long id){
    //    Paciente m = entityManager.find(Paciente.class, id);
    //   entityManager.remove(m);
    //}

    public void update(Paciente paciente){
        entityManager.merge(paciente);
    }

    //Pesquisar paciente por nome
    public List<Paciente> findPacientesByNome(String nome) {
        return entityManager.createQuery(
                        "SELECT p FROM Paciente p WHERE LOWER(p.nome) LIKE LOWER(concat('%', :nome, '%'))",
                        Paciente.class)
                .setParameter("nome", nome)
                .getResultList();
    }
}
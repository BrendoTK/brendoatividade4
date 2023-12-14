package com.example.brendoatividade4.model.repository;
import com.example.brendoatividade4.model.entity.Consulta;
import com.example.brendoatividade4.model.entity.Medico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class MedicoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public MedicoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Transactional
    public void remove(Long id){
        Medico m = entityManager.find(Medico.class, id);
        entityManager.remove(m);
    }
    public Medico findMedicoById(Long id) {
        return entityManager.find(Medico.class, id);
    }

    public List<Medico> medicos() {
        return entityManager.createQuery("SELECT m FROM Medico m", Medico.class)
                .getResultList();
    }

    public List<Medico> findAll() {
        return entityManager.createQuery("SELECT m FROM Medico m", Medico.class).getResultList();
    }

    public Medico findById(Long id) {
        return entityManager.find(Medico.class, id);
    }

    public void save(Medico medico){
        entityManager.persist(medico);
    }

    //public void remove(Long id){
    //    Medico m = entityManager.find(Medico.class, id);
     //   entityManager.remove(m);
    //}

    public void update(Medico medico){
        entityManager.merge(medico);
    }

    //Pesquisar medico por nome
    public List<Medico> findMedicosByNome(String nome) {
        return entityManager.createQuery(
                        "SELECT m FROM Medico m WHERE LOWER(m.nome) LIKE LOWER(concat('%', :nome, '%'))",
                        Medico.class)
                .setParameter("nome", nome)
                .getResultList();
    }
}
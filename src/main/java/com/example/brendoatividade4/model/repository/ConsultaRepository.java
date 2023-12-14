package com.example.brendoatividade4.model.repository;
import com.example.brendoatividade4.model.entity.Consulta;
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
public class ConsultaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ConsultaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Transactional
    public void remove(Long id){
        Consulta m = entityManager.find(Consulta.class, id);
        entityManager.remove(m);
    }
    public Consulta findConsultaById(Long id) {
        return entityManager.find(Consulta.class, id);
    }

    public List<Consulta> consultas() {
        return entityManager.createQuery("SELECT m FROM Consulta m", Consulta.class)
                .getResultList();
    }

    public List<Consulta> findAll() {
        return entityManager.createQuery("SELECT m FROM Consulta m", Consulta.class).getResultList();
    }

    public Consulta findById(Long id) {
        return entityManager.find(Consulta.class, id);
    }

    public void save(Consulta consulta){
        entityManager.persist(consulta);
    }

    //public void remove(Long id){
    //    Consulta m = entityManager.find(Consulta.class, id);
    //   entityManager.remove(m);
    //}

    public void update(Consulta consulta){
        entityManager.merge(consulta);
    }

    //Pesquisar consulta por data
    public List<Consulta> findConsultasByData(String data) {
        return entityManager.createQuery(
                        "SELECT c FROM Consulta c WHERE LOWER(c.data) LIKE LOWER(concat('%', :data, '%'))",
                        Consulta.class)
                .setParameter("data", data)
                .getResultList();
    }
}
package no.hvl.dat107.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import no.hvl.dat107.entity.Ansatt;

public class AnsattDAO {

    private EntityManagerFactory emf;

    private Ansatt ansatt;
    public AnsattDAO() { emf = Persistence.createEntityManagerFactory("oblig3"); }


    public Ansatt findAnsattById(int id) {
        ansatt = null;
            EntityManager em = emf.createEntityManager();
        try {
            ansatt = em.find(Ansatt.class, id);
        } finally {
            em.close();
        }
        return ansatt;
    }

}

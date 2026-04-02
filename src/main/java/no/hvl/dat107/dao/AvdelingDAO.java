package no.hvl.dat107.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Avdeling;

import java.util.List;

public class AvdelingDAO {

    private EntityManagerFactory emf;
    private Avdeling avdeling;

    private List<Ansatt> ansatte;

    public AvdelingDAO() {
        emf = Persistence.createEntityManagerFactory("oblig3");
    }

    public Avdeling getAvdelingById(int id) {

        EntityManager em = emf.createEntityManager();

        try {
            avdeling = em.find(Avdeling.class, id);
        } finally {
            em.close();
        }

        return avdeling;
    }
}

package no.hvl.dat107.dao;

import jakarta.persistence.*;
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

    public List<Avdeling> getAllAvdelinger() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT a FROM Avdeling a", Avdeling.class).getResultList();
    }

    public void oppdaterSjef(int avdId, Ansatt nySjef) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Avdeling tmpAvd;

        try {
            tx.begin();

            tmpAvd = em.find(Avdeling.class, avdId);
            tmpAvd.setSjef(nySjef);

            tx.commit();

        } finally {
            em.close();

        }
    }

}

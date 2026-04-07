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

    /**
     * tar en integer og henter en avdeling som har lik id
     *
     * @param id -- id som blir søkt i avdeling
     * @return -- retunerer avdeling som passer id´en
     */
    public Avdeling getAvdelingById(int id) {

        EntityManager em = emf.createEntityManager();

        try {
            avdeling = em.find(Avdeling.class, id);
        } finally {
            em.close();
        }

        return avdeling;
    }

    /**
     * Søker alle avdelinger or returnerer de som en liste
     *
     * @return en List over alle avdelinger
     */
    public List<Avdeling> getAllAvdelinger() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT a FROM Avdeling a", Avdeling.class).getResultList();
    }

    /**
     * Tar inn en avdeling og ansatt og legger den ansatten som sjef over valgt avdeling
     *
     * @param avdId  -- id på avdeling
     * @param nySjef -- id på ansatt som skal være nye sjef over avdelingen
     */
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

    /**
     * Oppretter en ny avdeling
     *
     * @param avd -- Class Avdeling, blir lagt til i databasen
     */
    public void nyAvdeling(Avdeling avd) {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        try {

            tx.begin();

            em.persist(avd);

            tx.commit();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("\n -------- Raden du prøver å legge til finnes allerede! ----------");
        } finally {
            em.close();
        }
    }

}

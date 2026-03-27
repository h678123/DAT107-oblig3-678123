package no.hvl.dat107.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import no.hvl.dat107.entity.Ansatt;

import java.util.List;

public class AnsattDAO {

    private EntityManagerFactory emf;

    private Ansatt ansatt;


    public AnsattDAO() {
        emf = Persistence.createEntityManagerFactory("oblig3");
    }

    /**
     * Searches the database for an 'Ansatt' with the given id and return Ansatt object.
     *
     * @param ansatt_id to search for
     * @return Ansatt class instance
     */
    public Ansatt findAnsattById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            ansatt = em.find(Ansatt.class, id);
        } finally {
            em.close();
        }
        return ansatt;
    }

    // TODO ------------------------------------------------------------------------------
    // TODO ------------------------------------------------------------------------------

    public Ansatt findAnsattByBrukernavn(String user) {
        EntityManager em = emf.createEntityManager();
        // TODO -- FINN ANSATT OG SKRIV HAN UT VIA BRUKERNAVN. IKKE TA ALLE I EN LIST Å FILTER, DET SKAL GJØRES I DATABASEN.
        try {
            ansatt = (Ansatt) em.createNativeQuery("SELECT a FROM Ansatt" +
                    "WHERE a.brukernavn = :user");
        } finally {
            em.close();
        }
        return ansatt;
    }

    /*
     * Saves given 'Ansatt' as a new row into the database.
     * Note: id is not in the constructor because its serial in DB
     * @param ans class to save into DB
     * @throws IllegalArgumentException -> Ansatt already exist in DB
     */
    public void saveAnsatt(Ansatt ans) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {

            tx.begin();

            em.persist(ans);

            tx.commit();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("\n -------- Raden du prøver å legge til finnes allerede! ----------");
        } finally {
            em.close();
        }
    }

    /**
     * Remove an ansatt from the DB.
     *
     * @param id -- primary key for said ansatt
     */
    public void removeAnsattById(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Ansatt tmp;
        try {
            tx.begin();

            tmp = em.find(Ansatt.class, id);
            em.remove(tmp);

            tx.commit();

        } finally {
            em.close();
        }
    }

    /**
     * Set new manedslonn for single ansatt in DB
     *
     * @param id   -- primary key of ansatt
     * @param lonn -- new value of lonn
     */
    public void setManedsLonn(int id, int lonn) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Ansatt ans;
        try {
            tx.begin();

            ans = findAnsattById(id);
            ans.setManedsLonn(lonn);
            em.merge(ans);

            tx.commit();

        } finally {
            em.close();
        }
    }

    /**
     * Searches the DB for all rows in 'Ansatt' and returns them as List.
     *
     * @return List<Ansatt> liste
     */
    public List<Ansatt> getAllAnsatte() {

        EntityManager em = emf.createEntityManager();
        List<Ansatt> list;
        try {
            list = em.createQuery("SELECT a FROM Ansatt a", Ansatt.class).getResultList();
        } finally {
            em.close();
        }

        return list;

    }
}

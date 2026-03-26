package no.hvl.dat107.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import no.hvl.dat107.entity.Ansatt;

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
        ansatt = null;
        EntityManager em = emf.createEntityManager();
        try {
            ansatt = em.find(Ansatt.class, id);
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
        } catch (Exception e) {
            e.printStackTrace();
            //  throw new IllegalArgumentException("\n -------- Raden du prøver å legge til finnes allerede! ----------");
        } finally {
            em.close();
        }
    }

    /**
     * Remove
     *
     * @param ans
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
}

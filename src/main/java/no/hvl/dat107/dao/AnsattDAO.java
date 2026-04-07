package no.hvl.dat107.dao;

import jakarta.persistence.*;
import no.hvl.dat107.entity.Ansatt;

import java.util.List;

public class AnsattDAO {

    private EntityManagerFactory emf;

    private Ansatt ansatt;


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

    public AnsattDAO() {
        emf = Persistence.createEntityManagerFactory("oblig3");
    }

    /**
     * Searches the DB for an Ansatt with the given parameter and returns it.
     *
     * @param Ansatt.username
     * @return Ansatt object
     */
    public Ansatt findAnsattByBrukernavn(String user) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Ansatt> query = em.createQuery("SELECT a FROM Ansatt a" +
                    " WHERE a.brukernavn = :user", Ansatt.class);
            query.setParameter("user", user);
            ansatt = query.getSingleResult();
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
     * setter ny stilling til en ansatt
     *
     * @param id til ansatt
     * @param s  -- nye stillingsnavn
     */
    public void setStilling(int id, String s) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            ansatt = findAnsattById(id);
            ansatt.setStilling(s);
            em.merge(ansatt);

            tx.commit();
        } finally {
            em.close();
        }

    }

    /**
     * søker dabasen for alle rader 'Ansatt' og returnerer de som en List.
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

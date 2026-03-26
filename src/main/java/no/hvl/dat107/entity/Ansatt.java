package no.hvl.dat107.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(schema = "oblig3")
public class Ansatt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // FK
//    private int avdelingId;
    //   private int prosjektId;

    // attributter
    private String brukernavn;
    private String fornavn;
    private String etternavn;
    private LocalDate ansettelse_dato;
    private String stilling;
    private int manedsLonn;


    public Ansatt(String brukernavn, String fornavn, String etternavn, LocalDate ansettelse_dato, String stilling, int manedsLonn) {
        this.brukernavn = brukernavn;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.ansettelse_dato = ansettelse_dato;
        this.stilling = stilling;
        this.manedsLonn = manedsLonn;
    }

    public Ansatt() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return fornavn;
    }

    @Override
    public String toString() {
        return "Ansatt{" +
                "id = " + id + "\n" +
                "brukernavn = " + brukernavn + "\n" +
                "fornavn = " + fornavn + "\n" +
                "etternavn = " + etternavn + "\n" +
                "ansettelse_dato = " + ansettelse_dato + "\n" +
                "stilling = " + stilling + "\n" +
                "manedsLonn = " + manedsLonn + "\n" +
                '}';
    }
}

package no.hvl.dat107.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(schema = "oblig3")
public class Ansatt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // FK

    // avdeling
    @ManyToOne
    @JoinColumn(name = "avdeling_id")
    private Avdeling avdeling;

    //   private int prosjektId;

    // attributter
    private String brukernavn;
    private String fornavn;
    private String etternavn;
    private LocalDate ansettelse_dato;
    private String stilling;
    private int manedsLonn;

    public Avdeling getAvdeling() {
        return avdeling;
    }

    public void setAvdeling(Avdeling avdeling) {
        this.avdeling = avdeling;
    }


    public Ansatt(String brukernavn, String fornavn, String etternavn, LocalDate ansettelse_dato, String stilling, int manedsLonn, Avdeling avdeling) {
        this.brukernavn = brukernavn;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.ansettelse_dato = ansettelse_dato;
        this.stilling = stilling;
        this.manedsLonn = manedsLonn;
        this.avdeling = avdeling;
    }

    public Ansatt() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return fornavn;
    }

    public void setManedsLonn(int manedsLonn) {
        this.manedsLonn = manedsLonn;
    }

    public void setStilling(String stilling) {
        this.stilling = stilling;
    }

    @Override
    public String toString() {
        return "id = " + id + "\n" +
                "brukernavn = " + brukernavn + "\n" +
                "fornavn = " + fornavn + "\n" +
                "etternavn = " + etternavn + "\n" +
                "ansettelse_dato = " + ansettelse_dato + "\n" +
                "stilling = " + stilling + "\n" +
                "manedsLonn = " + manedsLonn;
    }

    public String showAnsattInfo() {
        return id + " " + fornavn + " " + etternavn + " " + stilling;
    }

    public String getBrukernavn() {
        return brukernavn;
    }
}

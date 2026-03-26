package no.hvl.dat107.entity;

import jakarta.persistence.*;

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
    private String ansettelse_dato;
    private String stilling;
    private int manedsLonn;

    public int getId() {
        return id;
    }

    public String getName() {
        return fornavn;
    }

}

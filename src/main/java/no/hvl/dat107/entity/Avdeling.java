package no.hvl.dat107.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Avdeling {

    @Id
    @Column(name = "avdeling_id")
    private int avdelingId;

    private String navn;

    @OneToMany(mappedBy = "avdeling", fetch = FetchType.EAGER)
    private List<Ansatt> ansatte;

    @OneToOne
    @JoinColumn(name = "sjef_ansatt_id")
    private Ansatt sjef;


    public Avdeling() {
    }

    public Avdeling(String navn, Ansatt sjef) {
        this.navn = navn;
        this.sjef = sjef;
    }

    public List<Ansatt> getAnsatte() {
        return ansatte;
    }

    public Ansatt getBoss() {
        return sjef;
    }

    public String getNavn() {
        return navn;
    }

    public void setSjef(Ansatt ans) {
        this.sjef = ans;
    }

    public int getId() {
        return avdelingId;
    }
}

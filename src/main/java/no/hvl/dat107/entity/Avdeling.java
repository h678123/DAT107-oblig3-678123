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

    public List<Ansatt> getAnsatte() {
        return ansatte;
    }


}

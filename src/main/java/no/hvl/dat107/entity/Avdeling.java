package no.hvl.dat107.entity;

import jakarta.persistence.*;

import java.util.List;

public class Avdeling {

    @Id
    private int avdelingId;

    private String navn;

    @OneToMany(mappedBy = "avdeling", fetch = FetchType.EAGER)
    @JoinColumn(name = "sjef_ansatt_id")
    private Ansatt sjef;


}

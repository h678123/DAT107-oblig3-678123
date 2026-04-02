package no.hvl.dat107;

import no.hvl.dat107.dao.AnsattDAO;
import no.hvl.dat107.dao.AvdelingDAO;
import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Avdeling;

import java.util.List;

public class Service {

    AnsattDAO ansattDAO = new AnsattDAO();
    AvdelingDAO avdelingDAO = new AvdelingDAO();

    public Service() {
    }

    public boolean sjekkOmAnsattErSjef(int ansId) {
        boolean funnet = false;
        Ansatt valgtAnsatt = ansattDAO.findAnsattById(ansId);
        List<Avdeling> alleAvdelinger = avdelingDAO.getAllAvdelinger();

        // sjekk om ansatt er allerede en sjef
        for (Avdeling a : alleAvdelinger) {
            System.out.println(a.getBoss().getName());
            if (a.getBoss() != null && a.getBoss().getId() == valgtAnsatt.getId()) {
                System.out.println("Denne ansatten er allerede en sjef!\n" +
                        "Endre sjefen til " + a.getNavn() + " før du kan endre avdeling til " + valgtAnsatt.getName());
                funnet = true;
            }
        }
        return funnet;
    }
}

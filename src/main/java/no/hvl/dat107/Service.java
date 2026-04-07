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

    /**
     * Sjekker om en ansatt er sjef i en hvilken som helst avdeling
     *
     * @param ansId -- id til ansatt
     * @return bolean om ansatt er sjef eller ikke
     */
    public boolean sjekkOmAnsattErSjef(int ansId) {
        boolean funnet = false;
        Ansatt valgtAnsatt = ansattDAO.findAnsattById(ansId);
        List<Avdeling> alleAvdelinger = avdelingDAO.getAllAvdelinger();

        // sjekk om ansatt er allerede en sjef
        for (Avdeling a : alleAvdelinger) {
            if (a.getBoss() != null && a.getBoss().getId() == valgtAnsatt.getId()) {
                System.out.println("Denne ansatten er allerede en sjef!\n" +
                        "Endre sjefen til " + a.getNavn() + " før du kan endre avdeling til " + valgtAnsatt.getName());
                funnet = true;
            }
        }
        return funnet;
    }
}

package no.hvl.dat107;

import no.hvl.dat107.dao.AnsattDAO;
import no.hvl.dat107.entity.Ansatt;
import org.eclipse.persistence.jpa.jpql.parser.LocalDateTime;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        LocalDate ansDato = LocalDate.now().minusDays(5);

        AnsattDAO ansattDAO = new AnsattDAO();

        Scanner sc = new Scanner(System.in);
        System.out.print("Hallaien \n" +
                "Hva vil du gjøre? \n" +
                "1  -- Se alle ansatte \n" +
                "2 -- Søk etter ansatt med brukernavn \n");
        String in = sc.next();

        switch (in) {
            case "1":
                System.out.println("Her er alle våre ansatte: ");
                List<Ansatt> l = ansattDAO.getAllAnsatte();
                System.out.println(l.size());
                for (Ansatt a : l) {
                    System.out.println(a.showFornavnEtternavn());
                }
                return;
            case "2":
                System.out.println("Hva er brukernavnet?");
                String inBruker = sc.next();
                System.out.println(ansattDAO.findAnsattByBrukernavn(inBruker).toString());

        }
/*
        // finn ansatt -- read
        Ansatt ansatt1 = ansattDAO.findAnsattById(2);
        System.out.println(ansatt1.getName());

        System.out.println("---------------");
        // legg til -- Create
        Ansatt tmpAns = new Ansatt("breen", "ish", "ishern", ansDato, "Pengteller", 100000);
        ansattDAO.saveAnsatt(tmpAns);

        // fjern -- remove
        ansattDAO.removeAnsattById(5);

        // endre -- update
        ansattDAO.setManedsLonn(2, 59000);
  */
    }
}

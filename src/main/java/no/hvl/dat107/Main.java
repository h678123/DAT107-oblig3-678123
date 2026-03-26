package no.hvl.dat107;

import no.hvl.dat107.dao.AnsattDAO;
import no.hvl.dat107.entity.Ansatt;
import org.eclipse.persistence.jpa.jpql.parser.LocalDateTime;

import java.sql.Time;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        LocalDate ansDato = LocalDate.now().minusDays(5);

        AnsattDAO ansattDAO = new AnsattDAO();


        // finn ansatt
        Ansatt ansatt1 = ansattDAO.findAnsattById(2);
        System.out.println(ansatt1.getName());

        System.out.println("---------------");
        // legg til
        Ansatt tmpAns = new Ansatt("breen", "ish", "ishern", ansDato, "Pengteller", 100000);
        ansattDAO.saveAnsatt(tmpAns);
        System.out.println(ansattDAO.findAnsattById(5).toString());

        // fjern -- later som jeg ikke har referanse oppe
        //  ansattDAO.removeAnsattById(5);

        // duplikat
        //    Ansatt ny = new Ansatt("enzenz", "Enzo", "Mendes", ansDato, "Utvikler", 60000);
        //   ansattDAO.saveAnsatt(ny);
    }
}

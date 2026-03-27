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
        LocalDate ansDato = LocalDate.now();

        AnsattDAO ansattDAO = new AnsattDAO();

        Scanner sc = new Scanner(System.in);

        String cmds = "1  -- Se alle ansatte \n" +
                "2 -- Søk etter ansatt med brukernavn \n" +
                "3 -- Endre stilling\n" +
                "4 -- Legg til ny ansatt";

        System.out.print("Hallaien \n" +
                "Hva vil du gjøre? \n" + cmds);

        boolean stop = false;
        while (!stop) {
            System.out.println(cmds + "\n");
            String in = sc.next();
            switch (in) {
                case "1": // SE ALLE ANSATTE
                    System.out.println("Her er alle våre ansatte: ");
                    List<Ansatt> l = ansattDAO.getAllAnsatte();
                    System.out.println(l.size());
                    for (Ansatt a : l) {
                        System.out.println(a.showAnsattInfo());
                    }
                    break;
                case "2": // SØK ETTER ANSATT MED BRUKERNAVN
                    System.out.println("Hva er brukernavnet?");
                    String inBruker = sc.next();
                    System.out.println(ansattDAO.findAnsattByBrukernavn(inBruker).toString());
                    break;

                case "3": // ENDRE STILLING MED BRUKERNAVN
                    System.out.println("Hvem sin stilling skal du endre?");
                    int ansId = Integer.parseInt(sc.next());

                    System.out.println("Hva heter den nye stillingen?");
                    String stIn = sc.next();
                    ansattDAO.setStilling(ansId, stIn);
                    break;
                case "4": // LEGG TIL ANSATT
                    // fornavn
                    System.out.println("Hva er fornavn?");
                    String fornavn = sc.next();
                    sc.nextLine();
                    // etternavn
                    System.out.println("Hva er etternavn?");
                    String etternavn = sc.nextLine();

                    // brukernavn format
                    String fNavn = String.valueOf(fornavn.charAt(0)).toLowerCase();
                    String eNavn = String.valueOf(etternavn.charAt(0)).toLowerCase();

                    //    sc.nextLine();
                    // stilling
                    System.out.println("Hva er stilling?");
                    String stilling = sc.nextLine();

                    // månedslønn
                    System.out.println("Hva er månedslønna?");
                    String manedslonn = sc.next();


                    ansattDAO.saveAnsatt(new Ansatt(fNavn + eNavn, fornavn,
                            etternavn, ansDato, stilling, Integer.parseInt(manedslonn)));
                    break;
                case "h": // HELP COMMAND
                    System.out.println(cmds);
                    break;
                case "q": // QUIT APP
                    System.out.println("Ha det bra!");
                    stop = true;
                    break;
                default:
                    System.out.println("Ugyldig input, tast 'h' for hjelp.");

            }
        }
    }
}

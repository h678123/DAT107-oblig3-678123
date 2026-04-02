package no.hvl.dat107;

import no.hvl.dat107.dao.AnsattDAO;
import no.hvl.dat107.dao.AvdelingDAO;
import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Avdeling;
import org.eclipse.persistence.jpa.jpql.parser.LocalDateTime;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        LocalDate ansDato = LocalDate.now();

        // DAO
        AnsattDAO ansattDAO = new AnsattDAO();
        AvdelingDAO avdDAO = new AvdelingDAO();

        Scanner sc = new Scanner(System.in);

        String cmds = "\n" +
                "1 -- Se alle ansatte \n" +
                "2 -- Søk etter ansatt med brukernavn \n" +
                "3 -- Endre en ansatt\n" +
                "4 -- Legg til ny ansatt\n" +
                "5 -- Finn ansatte med avdeling\n" +
                "6 -- Info om avdeling\n" +
                "7 -- Legg til en ny avdeling\n" +
                "h -- Hjelp\n" +
                "q -- Avslutt";

        String avdelinger =
                "Utvikling == 1 \n" +
                        "Salg == 2\n" +
                        "Testing == 3\n" +
                        "Diverse == 4";

        System.out.print("Hallaien \n" +
                "Hva vil du gjøre? \n");

        boolean stop = false;
        while (!stop) {
            System.out.println(cmds + "\n");
            String in = sc.next();
            switch (in) {
                // --------------------------- SE ALLE ANSATTE ----------------------------------
                case "1":
                    System.out.println("Her er alle våre ansatte: ");
                    List<Ansatt> l = ansattDAO.getAllAnsatte();
                    System.out.println(l.size());
                    for (Ansatt a : l) {
                        System.out.println(a.showAnsattInfo());
                    }
                    break;

                // -------------------------- SØK ETTER ANSATT MED BRUKERNAVN ------------------
                case "2":
                    System.out.println("Hva er brukernavnet?");
                    String inBruker = sc.next();
                    System.out.println(ansattDAO.findAnsattByBrukernavn(inBruker).toString());
                    break;

                // ---------------------------- ENDRE ANSATT INFO ------------------------------
                case "3":
                    System.out.println("Hvilken ansatt vil du gjøre endringer på?\n" +
                            "Skriv inn id ->");
                    int ansId = Integer.parseInt(sc.next());
                    System.out.println("Ansatten du har valgt er: \n" +
                            ansattDAO.findAnsattById(ansId).toString() + "\n" +
                            "Stemmer det? \n" +
                            "1 = ja, 0 = nei");
                    int valg = Integer.parseInt(sc.next());

                    if (valg != 1) { // bekreftelse
                        break;
                    }
                    System.out.println("Tast valg for hva du vil endre.\n" +
                            "1 = Stilling\n" +
                            "2 = Månedslønn\n" +
                            "3 = Avdeling" +
                            "4 = Gjør om til sjef");
                    int valg2 = Integer.parseInt(sc.next());
                    switch (valg2) {
                        // --------------- ENDRE STILLING
                        case 1:
                            System.out.println("Hva heter den nye stillingen?");
                            String stIn = sc.next();
                            ansattDAO.setStilling(ansId, stIn);
                            break;
                        // ----------------- ENDRE MÅNDESLØNN
                        case 2:
                            System.out.println("Hva skal den nye månedslønnen være? (ikke skriv med mellomrom)");
                            int nyMndLonn = Integer.parseInt(sc.next());
                            ansattDAO.setManedsLonn(ansId, nyMndLonn);
                            break;
                        // ------------------- ENDRE AVDELING
                        case 3:

                            boolean funnet = false;
                            Ansatt valgtAnsatt = ansattDAO.findAnsattById(ansId);
                            List<Avdeling> alleAvdelinger = avdDAO.getAllAvdelinger();

                            // sjekk om ansatt er allerede en sjef
                            for (Avdeling a : alleAvdelinger) {
                                System.out.println(a.getBoss().getName());
                                if (a.getBoss() != null && a.getBoss().getId() == valgtAnsatt.getId()) {
                                    System.out.println("Denne ansatten er allerede en sjef!\n" +
                                            "Endre sjefen til " + a.getNavn() + " før du kan endre avdeling til " + valgtAnsatt.getName());
                                    funnet = true;
                                    break;
                                }
                            }
                            if (funnet) {
                                break;
                            }

                            System.out.println("Hvilken avdeling vil du endre til?" + avdelinger);
                            Avdeling nyAvd = avdDAO.getAvdelingById(Integer.parseInt(sc.next()));

                            valgtAnsatt.setAvdeling(nyAvd);
                            System.out.println(valgtAnsatt.getName() + "sin nye avdeling ble " + valgtAnsatt.getAvdeling());

                            break;

                        case 4:
                            System.out.println("Hvilken avdeling skal vedkommende være sjef over?\n" + avdelinger);
                            Avdeling nyeAvd = avdDAO.getAvdelingById(Integer.parseInt(sc.next()));
                            avdDAO.oppdaterSjef(nyeAvd.getId(), ansattDAO.findAnsattById(ansId));
                            System.out.println("Da er det gjort!");
                            break;


                        default:
                            System.out.println("Ugyldig input -> tilbake til startsiden");
                            break;
                    }
                    break;

                // ----------------------------- LEGG TIL ANSATT -------------------------
                case "4":
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

                    // avdeling
                    System.out.println("Hvilken avdeling skal " + fornavn + " jobbe på?" + avdelinger);
                    Avdeling ansAvdeling = avdDAO.getAvdelingById(Integer.parseInt(sc.next()));

                    ansattDAO.saveAnsatt(new Ansatt(fNavn + eNavn, fornavn,
                            etternavn, ansDato, stilling, Integer.parseInt(manedslonn), ansAvdeling));
                    break;

                // --------------------- FINNE ANSATTE MED AVDELING ID ------------------------
                case "5":
                    System.out.println("Hva er id til avdelingen? \n" + avdelinger);
                    int avdId = Integer.parseInt(sc.next());
                    Avdeling avd = avdDAO.getAvdelingById(avdId);
                    for (Ansatt a : avd.getAnsatte()) {
                        System.out.println(a.showAnsattInfo());
                    }
                    break;

                // --------------------  INFO OM AVDELING --------------------------
                case "6":
                    System.out.println("Hvilken avdeling vil du ha info om?\n" + avdelinger);
                    int valgtAvdelingId = Integer.parseInt(sc.next());
                    Avdeling valgtAvdeling = avdDAO.getAvdelingById(valgtAvdelingId);
                    System.out.println("Avdelingsnavn: " + valgtAvdeling.getNavn() +
                            "\n Antall ansatte: " + valgtAvdeling.getAnsatte().size() +
                            "\n Sjef for avdelingen: \n" + valgtAvdeling.getBoss()
                    );
                    break;
                case "7":
                    System.out.println("Du er i ferd med å opprette en ny avdeling.\n" +
                            "Hva er navnet på den nye avdelingen?");
                    sc.nextLine();
                    String nyeNavnAvd = sc.nextLine();

                    System.out.println("Hvem skal være sjef på den nye avdelingen?\n" +
                            "Her er liste over våre ansatte: ");
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

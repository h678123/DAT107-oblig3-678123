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

        Service svc = new Service();

        Scanner sc = new Scanner(System.in);

        // enkel print av alle commandoer i appen
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

        // en hardcode av alle avdelinger, fikk ikke til stream
        // om du har en ide for meg der jeg printer alle en og en vil jeg gjerne vite det.
        String avdelinger =
                "Utvikling == 1 \n" +
                        "Salg == 2\n" +
                        "Testing == 3\n" +
                        "Diverse == 4";

        System.out.print("Hallaien \n" +
                "Hva vil du gjøre? \n");

        boolean stop = false; // brukes for å avslutte program
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
                    String inBruker = sc.next().toLowerCase();
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
                            "3 = Avdeling\n" +
                            "4 = Gjør om til sjef");
                    String valg2 = sc.next();
                    switch (valg2) {
                        // --------------- ENDRE STILLING
                        case "1":
                            System.out.println("Hva heter den nye stillingen?");
                            String stIn = sc.next();
                            ansattDAO.setStilling(ansId, stIn);
                            break;
                        // ----------------- ENDRE MÅNDESLØNN
                        case "2":
                            System.out.println("Hva skal den nye månedslønnen være? (ikke skriv med mellomrom)");
                            int nyMndLonn = Integer.parseInt(sc.next());
                            ansattDAO.setManedsLonn(ansId, nyMndLonn);
                            break;
                        // ------------------- ENDRE AVDELING
                        case "3":
                            if (!svc.sjekkOmAnsattErSjef(ansId)) {

                                Ansatt valgtAnsatt = ansattDAO.findAnsattById(ansId);

                                System.out.println("Hvilken avdeling vil du endre til?" + avdelinger);
                                Avdeling nyAvd = avdDAO.getAvdelingById(Integer.parseInt(sc.next()));

                                valgtAnsatt.setAvdeling(nyAvd);
                                System.out.println(valgtAnsatt.getName() + "sin nye avdeling ble " + valgtAnsatt.getAvdeling());
                            }
                            break;
                        // ------------------ SETT ANSATT SOM SJEF
                        case "4":
                            if (svc.sjekkOmAnsattErSjef(ansId)) {
                                System.out.println("Ansatt er allerede sjef!\n" +
                                        "Endre sjefen til avdelingen før du kan endre han til en annen!");
                            } else {
                                System.out.println("Hvilken avdeling skal vedkommende være sjef over?\n" + avdelinger);
                                Avdeling nyeAvd = avdDAO.getAvdelingById(Integer.parseInt(sc.next()));
                                avdDAO.oppdaterSjef(nyeAvd.getId(), ansattDAO.findAnsattById(ansId));
                                System.out.println("Da er det gjort!");
                            }
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
                // -------------------------- OPPRETT AVDELING ----------------
                case "7":
                    System.out.println("Du er i ferd med å opprette en ny avdeling.\n" +
                            "Hva er navnet på den nye avdelingen?");
                    sc.nextLine();
                    String nyeNavnAvd = sc.nextLine();

                    System.out.println("Hvem skal være sjef på den nye avdelingen?\n\n");
                    int nyeSjefId = Integer.parseInt(sc.next());

                    if (svc.sjekkOmAnsattErSjef(nyeSjefId)) {
                        break;
                    }
                    Ansatt endreAnsatt = ansattDAO.findAnsattById(nyeSjefId);
                    Avdeling nyeAvd = new Avdeling(nyeNavnAvd, endreAnsatt);
                    endreAnsatt.setAvdeling(nyeAvd);

                    avdDAO.nyAvdeling(nyeAvd);


                    System.out.println("Da er det gjort!");
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

package no.hvl.dat107;

import no.hvl.dat107.dao.AnsattDAO;
import no.hvl.dat107.entity.Ansatt;


public class Main
{
    public static void main( String[] args )
    {
        AnsattDAO ansattDAO = new AnsattDAO();

        Ansatt ansatt1 = ansattDAO.findAnsattById(2);
        System.out.println(ansatt1.getName());
    }
}

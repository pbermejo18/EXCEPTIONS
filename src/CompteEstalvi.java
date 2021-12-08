import Exceptions.BankAccountException;
import Exceptions.ClientAccountException;

import java.util.List;

import static Exceptions.BankAccountException.ACCOUNT_OVERDRAFT;
import static Exceptions.BankAccountException.ACCOUNT_ZERO_USER;
import static Exceptions.ClientAccountException.WRONG_DNI;

public class CompteEstalvi {
    private String numCompte;
    private double saldo;
    //Usuaris co-propietaris del compte
    private List<Client> llista_usuaris;

    public CompteEstalvi(String numCompte) {
        this.numCompte = numCompte;
        saldo = 0;
    }

    /**
     Afegeix un usuari d'aquest compte
     @param client
     @return quantitat d'usuaris que té el compte
     **/
    public int addUser(Client client) {
        llista_usuaris.add(client);
        return llista_usuaris.size();
    }

    /**
     Elimina un usuari d'aquest compte
     @param dni
     @return quantitat d'usuaris que té el compte
     @throws //BankAccountException
     **/
    public int removeUser(String dni) throws BankAccountException {
        if (llista_usuaris.size() >2){
            llista_usuaris.removeIf(u -> dni.equals(u.getDNI()));
        }else {
            throw new BankAccountException(ACCOUNT_ZERO_USER);
        }
        return llista_usuaris.size();
    }

    /**
     * Afegeix m diners al saldo
     * @param amount
     */
    public void ingressar(double amount) {
        saldo += amount;
    }

    /**
     * Treu m diners del compte si n'hi han suficient
     * @param m
     * @throws //BankAccountException
     */
    public void treure(double m) throws BankAccountException {
        if (saldo >= m){
            saldo -= m;
        }else {
            throw new BankAccountException(ACCOUNT_OVERDRAFT);
        }
    }

    public String getNumCompte() {
        return numCompte;
    }

    public double getSaldo() {
        return saldo;
    }

    public List<Client> getLlista_usuaris() {
        return llista_usuaris;
    }

    public static boolean verifyDNI(String dni) throws ClientAccountException {

        int DNI_Numeros = Integer.parseInt(dni.substring(0,8));
        String DNI_Letra = dni.substring(8,9);
        boolean correcto=false;
        String[] Letras= {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};

        int numero= DNI_Numeros %23;

        for (int i = 0; i < Letras.length; i++) {
            if (numero == i && Letras[i].equals(DNI_Letra)){
                correcto=true;
                break;
            }
        }
        if (!correcto){
            throw new ClientAccountException(WRONG_DNI);
        }
        return correcto;
    }
}
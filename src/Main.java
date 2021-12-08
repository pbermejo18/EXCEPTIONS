import Exceptions.BankAccountException;
import Exceptions.ClientAccountException;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion=0;

        while (!(opcion == 3)) {
            CompteEstalvi compteEstalvi = new CompteEstalvi("555555");
            System.out.println("Que deseas hacer ?");
            System.out.println("1. Añadir cliente");
            System.out.println("2. Sacar dinero");
            System.out.println("3. Eliminar cliente asociado");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Introduce los datos del titular:");
                    System.out.print("Nom: ");
                    String nom = scanner.next();
                    System.out.print("Cognom: ");
                    String cognom = scanner.next();
                    System.out.print("DNI: ");
                    String DNI = scanner.next();
                    System.out.println("El titular que quiere añadir es: "+ nom+" "+cognom+" "+DNI);
                    System.out.println();
                    Client client = new Client(nom, cognom, DNI);
                    try {
                        compteEstalvi.verifyDNI(client.getDNI());
                    } catch (ClientAccountException e) {
                        System.out.println(e);
                    }
                    try {
                        compteEstalvi.addUser(client);
                    } catch (Exception e){
                        System.out.println("ERROR");
                    }
                    break;

                case 2:
                    System.out.println("Ingrese la cantidad que quiere sacar");
                    System.out.println("Cantidad:");
                    int dineroSacar = scanner.nextInt();
                    try {
                       compteEstalvi.treure(dineroSacar);
                    } catch (BankAccountException e) {
                       System.out.println(e);
                    }
                       System.out.println("El saldo actual de la cuenta es: "+compteEstalvi.getSaldo());
                    break;

                case 3:
                    //System.out.println(compteEstalvi.getNumCompte());
                    //if (compteEstalvi.getLlista_usuaris() != null) {
                        System.out.println("DNI del cliente que quiere eliminar: ");
                        String dnieliminar = scanner.nextLine();
                        try {
                            compteEstalvi.removeUser(dnieliminar);
                        } catch (BankAccountException e) {
                            System.out.println(e);
                        }
                        System.out.println("Cliente eliminado correctamente!");
                    //}
                    break;

                default:
                    System.out.println("Las opciones posibles son 1,2,3");
                    break;
            }
        }
    }
}
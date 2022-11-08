package bin.Cliente;

import bin.DB.ConnDB;

import java.sql.SQLException;
import java.util.Scanner;

public class Requisitos {


    public void registo()  {

        try {
            ConnDB db = new ConnDB();

            Scanner sc = new Scanner(System.in);
            System.out.println("Introduza o seu username: ");
            String username = sc.next();
            System.out.println("Introduza o seu nome: ");
            String nome = sc.next();
            System.out.println("Introduza a sua password: ");
            String password = sc.next();

            db.registaNovoUtilizador(username, nome, password,0,0);
            System.out.println("Registo efetuado com Sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro no registo, o nome ou o username poderão estar já em uso, porfavor tente de novo");
            throw new RuntimeException(e);
        }

    }

    public boolean EfetuaLogin(){
        boolean flag = false;
        try {
            ConnDB db = new ConnDB();

            Scanner sc = new Scanner(System.in);
            System.out.println("Introduza o seu username: ");
            String username = sc.next();
            System.out.println("Introduza a sua password: ");
            String password = sc.next();

            System.out.println("Loging...!");
            flag = db.loginUtilizador(username, password);
            return flag;
        } catch (SQLException e) {
            System.out.println("Erro na ligação com a base de Dados (Efetua Login)");
            //throw new RuntimeException(e);
            return flag;
        }

    }

    public boolean EfetuaLoginAdmin(){
        boolean flag = false;
        try {
            ConnDB db = new ConnDB();

            Scanner sc = new Scanner(System.in);
            System.out.println("Introduza o seu username: ");
            String username = sc.next();
            System.out.println("Introduza a sua password: ");
            String password = sc.next();

            System.out.println("Loging...!");
            flag = db.loginAdmin(username, password);
            return flag;
        } catch (SQLException e) {
            System.out.println("Erro na ligação com a base de Dados (Efetua Login)");
            //throw new RuntimeException(e);
            return flag;
        }

    }
    public void registoDeAdmin()  {

        try {
            ConnDB db = new ConnDB();

            Scanner sc = new Scanner(System.in);
            String username = "admin";
            String nome = "admin";
            String password = "admin";

            db.registaNovoUtilizador(username, nome, password,1,0);
            System.out.println("Registo de administrador efetuado com Sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro no registo, o nome ou o username poderão estar já em uso, porfavor tente de novo");
            throw new RuntimeException(e);
        }

    }

    public void apagarUtilizador(int id){

        try {
            ConnDB db = new ConnDB();
            db.apagaUtilizador(id);
            System.out.println("Utilizador apagado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Nao foi possivel apagar o utilizador com id: "+ id +" !!!");
            throw new RuntimeException(e);
        }
    }

}

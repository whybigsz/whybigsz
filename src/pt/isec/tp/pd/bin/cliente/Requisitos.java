package pt.isec.tp.pd.bin.cliente;

import pt.isec.tp.pd.bin.db.ConnDB;

import java.sql.SQLException;
import java.util.Scanner;

public class Requisitos {


    public void registo() {

        try {
            ConnDB db = new ConnDB();

            Scanner sc = new Scanner(System.in);
            System.out.println("Introduza o seu username: ");
            String username = sc.next();
            System.out.println("Introduza o seu nome: ");
            String nome = sc.next();
            System.out.println("Introduza a sua password: ");
            String password = sc.next();

            db.registaNovoUtilizador(username, nome, password, 0, 0);


            System.out.println("Registo efetuado com Sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro no registo, o nome ou o username poderão estar já em uso, porfavor tente de novo");
            throw new RuntimeException(e);
        }

    }


   /* public int getID(String username) {
        int ID = 0;
        try {
            ConnDB db = new ConnDB();
            ID = db.getID(username);
            if (ID == -1) {
                System.out.println("Username não existe!!");
                return -1;
            } else {
                return ID;
            }
        } catch (SQLException e) {
            System.out.println("Erro na ligação com a base de Dados (getID()");
            //throw new RuntimeException(e);
            return -1;
        }
    }*/

    public int EfetuaLogin() {
        int ID;
        boolean flag;
        try {
            ConnDB db = new ConnDB();

            Scanner sc = new Scanner(System.in);
            System.out.println("Introduza o seu username: ");
            String username = sc.next();
            System.out.println("Introduza a sua password: ");
            String password = sc.next();

            System.out.println("******* Loging in! ********");
            flag = db.loginUtilizador(username, password);
            if(!flag){
                throw new SQLException();
            }else{
                ID = db.getID(username);
                return ID;
            }



        } catch (SQLException e) {
            System.out.println("Erro na ligação com a base de Dados (Efetua Login)");
            //throw new RuntimeException(e);
            return -1;
        }
    }

    public boolean EfetuaLoginAdmin() {
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

    public void registoDeAdmin() {

        try {
            ConnDB db = new ConnDB();

            String username = "admin";
            String nome = "admin";
            String password = "admin";

            db.registaNovoUtilizador(username, nome, password, 1, 0);
            System.out.println("Registo de administrador efetuado com Sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro no registo, o nome ou o username poderão estar já em uso, porfavor tente de novo");
            throw new RuntimeException(e);
        }

    }

    public void apagarUtilizador(int id) {

        try {
            ConnDB db = new ConnDB();
            db.apagaUtilizador(id);
            System.out.println("Utilizador apagado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Nao foi possivel apagar o utilizador com id: " + id + " !!!");
            throw new RuntimeException(e);
        }
    }

    public void editarUsername(int ID) throws SQLException {


        Scanner sc = new Scanner(System.in);
        ConnDB db = new ConnDB();
        if (ID != -1) {
            System.out.println("Qual é o seu novo username?");
            String novoUsername = sc.nextLine();
            db.editarUsername(ID, novoUsername);
        }
        else{
            System.out.println("Erro a encontrar este utilizador (editarUsername)");
        }

    }
    public void editarNome(int ID) throws SQLException {


        Scanner sc = new Scanner(System.in);
        ConnDB db = new ConnDB();
        if (ID != -1) {
            System.out.println("Qual é o seu novo nome?");
            String novoNome = sc.nextLine();
            db.editarNome(ID, novoNome);
        }  else{
            System.out.println("Erro a encontrar este utilizador (editarNome)");
        }

    }
    public void editarPassword(int ID) throws SQLException {


        Scanner sc = new Scanner(System.in);
        ConnDB db = new ConnDB();
        if (ID != -1) {
            System.out.println("Qual é a sua nova Password?");
            String novaPass = sc.nextLine();
            db.editarPassword(ID, novaPass);
        }  else{
            System.out.println("Erro a encontrar este utilizador (editarPassword)");
        }

    }

}

package bin.Cliente;


import bin.Servidor.Menu;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        Requisitos req = new Requisitos();
        Menu menu = new Menu();


        menu.startMenu(req);

    }


}
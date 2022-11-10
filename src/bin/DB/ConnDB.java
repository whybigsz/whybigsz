package bin.DB;

import java.sql.*;

public class ConnDB {

    //Project structure w/jar file
    public final String DATABASE_URL = "jdbc:sqlite:PD-2022-23-TP.db";
    public final Connection dbConn;


    public ConnDB() throws SQLException {
        dbConn = DriverManager.getConnection(DATABASE_URL);
    }

    public void close() throws SQLException {
        if (dbConn != null)
            dbConn.close();
    }

    public int getID(String username) {

        try {
            Statement statement = dbConn.createStatement();
            String sqlQuery = "Select id, username, nome, password, administrador, autenticado FROM utilizador";
            if (username != null) {
                sqlQuery += " WHERE username ='" + username + "'";
                ResultSet resultSet = statement.executeQuery(sqlQuery);
                if (!resultSet.next()) {
                    System.out.println("Erro no getID (username nao existe)");
                    statement.close();
                    throw new SQLException();
                } else {

                    int id = resultSet.getInt("id");
                    System.out.println("[" + id + "] ");
                    statement.close();
                    return id;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro no getID!");
            return -1;
        }

        return -1;
    }

    public void registaNovoUtilizador(String username, String nome, String password, int administrador, int autenticado) throws SQLException {
        Statement statement = dbConn.createStatement();

        String sqlQuery = "INSERT INTO utilizador VALUES (NULL,'" + username + "','" + nome + "','" + password + "','" + administrador + "','" + autenticado + "')";
        statement.executeUpdate(sqlQuery);
        statement.close();
    }


    public boolean loginUtilizador(String username, String password) {

        try {
            Statement statement = dbConn.createStatement();
            String sqlQuery = "SELECT id, username, nome, password, administrador, autenticado  FROM utilizador";
            if (username != null && password != null) {
                sqlQuery += " WHERE username = '" + username + "' AND password = '" + password + "'";
                ResultSet resultSet = statement.executeQuery(sqlQuery);
                if (!resultSet.next()) {
                    System.out.println("Login Incorreto!");
                    throw new SQLException();
                } else {
                    int admin = resultSet.getInt("administrador");
                    //System.out.println("[" + id + "] " + user + " " + pass + " " + admin);
                    if (admin == 1) {
                        System.out.println("Tentativa de entrada como admin detetada!");
                        statement.close();
                        return false;
                    } else {

                        System.out.println("Login Efetuado Com Sucesso!");
                        statement.close();
                        return true;
                    }
                }

            }
        } catch (SQLException e) {
            System.out.println("Excecao lançada (login)");
            return false;
        }

        return false;
    }


    public void apagaUtilizador(int id) throws SQLException {
        Statement statement = dbConn.createStatement();

        String sqlQuery = "DELETE FROM utilizador WHERE id=" + id;
        statement.executeUpdate(sqlQuery);
        statement.close();
    }

    public boolean loginAdmin(String username, String password) {
        try {
            Statement statement = dbConn.createStatement();
            String sqlQuery = "SELECT id, username, nome, password, administrador, autenticado  FROM utilizador";
            if (username.equals("admin") && password.equals("admin")) {
                sqlQuery += " WHERE username = '" + username + "' AND password = '" + password + "'";
                ResultSet resultSet = statement.executeQuery(sqlQuery);
                if (!resultSet.next()) {
                    System.out.println("Login Incorreto!");
                    throw new SQLException();
                } else {
                    /*int id = resultSet.getInt("id");
                    String user = resultSet.getString("username");
                    String pass = resultSet.getString("password");*/
                    int admin = resultSet.getInt("administrador");
                    //System.out.println("[" + id + "] " + user + " " + pass + " " + admin);

                    resultSet.close();

                    if (admin == 1) {
                        System.out.println("Bem Vindo, Está agora ligado na conta de Administrador!!");
                        statement.close();
                        return true;
                    } else {

                        System.out.println("Login na conta de Administrador falhada!");
                        statement.close();
                        return false;
                    }

                }

            } else {
                System.out.println("Credencias de Administrador Erradas");
                statement.close();
            }

        } catch (SQLException e) {
            System.out.println("Excecao lançada (login)");
            return false;
        }

        return false;
    }


    public void editarUsername(int id, String username) throws SQLException {

            Statement statement = dbConn.createStatement();
            String sqlQuery = "UPDATE utilizador SET username='" + username + "' WHERE id=" + id;
            statement.executeUpdate(sqlQuery);
            System.out.println("Username alterado com sucesso!");
            statement.close();


    }
    public void editarNome(int id, String nome) throws SQLException {

        Statement statement = dbConn.createStatement();
        String sqlQuery = "UPDATE utilizador SET nome='" + nome + "' WHERE id=" + id;
        statement.executeUpdate(sqlQuery);
        System.out.println("Nome alterado com sucesso!");
        statement.close();


    }
    public void editarPassword(int id, String password) throws SQLException {

        Statement statement = dbConn.createStatement();
        String sqlQuery = "UPDATE utilizador SET password='" + password + "' WHERE id=" + id;
        statement.executeUpdate(sqlQuery);
        System.out.println("Password alterada com sucesso!");
        statement.close();


    }

    public void listEspetaculos(String whereDescricao) throws SQLException {
        Statement statement = dbConn.createStatement();

        String sqlQuery = "SELECT id, descricao, tipo, data_hora, duracao, local, localidade, pais, classificacao_etaria, visivel FROM espetaculo";
        if (whereDescricao != null)
            sqlQuery += " WHERE descricao like '%" + whereDescricao + "%'";

        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String descricao = resultSet.getString("descricao");
            String tipo = resultSet.getString("tipo");
            Date data_hora = resultSet.getDate("data_hora");
            int duracao = resultSet.getInt("duracao");
            String local = resultSet.getString("local");
            String localidade = resultSet.getString("localidade");
            String pais = resultSet.getString("pais");
            String classificacao_etaria = resultSet.getString("classificacao_etaria");
            int visivel = resultSet.getInt("visivel");
            System.out.println("[" + id + "] " + descricao + tipo + "[" + data_hora + "]" + duracao + local + localidade + pais + classificacao_etaria + "(" + visivel + ")");
        }

        resultSet.close();
        statement.close();
    }

    // DATAFORMAT: YYYY-MM-DD HH:mm:ss.sss
    public void insereEspetaculo(String descricao, String tipo, Date data_hora, int duracao, String local, String localidade, String pais, String classificacao_etaria, int visivel) throws SQLException {
        Statement statement = dbConn.createStatement();

        String sqlQuery = "INSERT INTO espetaculo VALUES (NULL,'" + descricao + "','" + tipo + "','" + data_hora + "','" + duracao + "','" + local + "','" + localidade + "','" + classificacao_etaria + "','" + visivel + ")";
        statement.executeUpdate(sqlQuery);
        statement.close();
    }

    public void updateUser(int id, String name, String birthdate) throws SQLException {
        Statement statement = dbConn.createStatement();

        String sqlQuery = "UPDATE users SET name='" + name + "', " +
                "BIRTHDATE='" + birthdate + "' WHERE id=" + id;
        statement.executeUpdate(sqlQuery);
        statement.close();
    }


    public void deleteUser(int id) throws SQLException {
        Statement statement = dbConn.createStatement();

        String sqlQuery = "DELETE FROM users WHERE id=" + id;
        statement.executeUpdate(sqlQuery);
        statement.close();
    }


}

package bin.Servidor;

import bin.Cliente.Requisitos;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {



    public void startMenu(Requisitos requisitos) throws SQLException {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        //MENU INICIAL
        while (flag) {
            System.out.println("\nBem Vindo á Reserva e Compra de Bilhetes!");
            System.out.println("1- Registar");
            System.out.println("2- Login");
            System.out.println("3- Login Admin");
            System.out.println("4- Sair");
            System.out.print("Escolha o que pretende fazer: ");
            int opcao = sc.nextInt();
            boolean verifica = true;

            switch (opcao) {
                //REGISTO DE NOVOS UTILIZADORES
                case 1:
                    System.out.println("Registo de novos utilizadores");
                    requisitos.registo();
                    break;
                //LOGIN DE UTILIZADORES
                case 2:
                    int ID = requisitos.EfetuaLogin();
                    if(ID != -1){
                        while(verifica){

                            //MENU DE UTILIZADORES
                            System.out.println("\nBem Vindo!");
                            System.out.println("1- Editar Dados de Registo");
                            System.out.println("2- Consulta de Reservas pagas");
                            System.out.println("3- Consulta e pesquisa de espetáculos");
                            System.out.println("4- Seleção de um espetáculo" +
                                    "Visualização dos lugares disponiveis e respetivos preços" +
                                    "Seleção dos lugares pretendidos" +
                                    "Caso exista alteração dos lugares disponiveis a informação visualizada deve ser automaticamente atualizada" +
                                    "Submissão/validação de um pedido de reserva "
                                    + "Se for concluido com sucesso deve proceder ao seu pagamento, se a op falhar os lugares voltam a ficar disponiveis");
                            System.out.println("5- Logout");
                            System.out.print("\nO que pretende fazer?");
                            int opcao2 =sc.nextInt();

                            switch (opcao2) {
                                case 1 -> {
                                    boolean flag2 = true;
                                    while (flag2) {
                                        //MENU DE EDIÇÃO DE REGISTOS DO UTILIZADOR
                                        System.out.println("1- Alterar username");
                                        System.out.println("2- Alterar nome");
                                        System.out.println("3- Alterar password");
                                        System.out.println("4- Voltar");
                                        int opcao3 = sc.nextInt();

                                        switch (opcao3) {
                                            case 1 -> requisitos.editarUsername(ID);
                                            case 2 -> requisitos.editarNome(ID);
                                            case 3 -> requisitos.editarPassword(ID);
                                            case 4 -> flag2 = false;
                                        }
                                    }
                                }
                                case 5 -> {
                                    requisitos.EfetuaLogout(ID);
                                    verifica = false;
                                }
                            }
                        }
                    }
                    break;

                //LOGIN DE ADMINS
                case 3:
                    verifica = requisitos.EfetuaLoginAdmin();
                    if (verifica) {
                        while(verifica){
                            System.out.println("\nBem Vindo Admin!");
                            System.out.println("Inserçao de espetaculos no sistema" +
                                    "caso seja inserido com sucesso apenas o administrador pode ver, até que altere esta condição" +
                                    "Eliminar um espetáculo desde que nao exista nenhuma reserva paga associada");
                            System.out.println("5- Logout");
                            System.out.print("\nO que pretende fazer?");
                            int opcao2 =sc.nextInt();
                            switch (opcao2) {
                                case 1:

                                    break;
                                case 5:
                                    verifica = false;
                            }
                        }
                    }
                    break;
                //SAIR DO MENU
                case 4:
                    System.out.println("Obrigado pela visita!");
                    flag = false;
                    // APAGAR UTILIZADORES/ADMINS
                case 5:
                    System.out.println("Que utilizador quer apagar (ID)");
                    requisitos.apagarUtilizador(sc.nextInt());
                    break;
                //REGISTAR ADMINS
                case 6:
                    requisitos.registoDeAdmin();
                    break;
            }
        }
    }


}

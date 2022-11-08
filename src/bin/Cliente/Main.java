package bin.Cliente;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Requisitos requisitos = new Requisitos();
        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        //teste

        while (flag == true) {
            System.out.println("\nBem Vindo á Reserva e Compra de Bilhetes!");

            System.out.println("1- Registar");
            System.out.println("2- Login");
            System.out.println("3- Login Admin");
            System.out.println("4- Sair");
            System.out.print("Escolha o que pretende fazer: ");
            int opcao = sc.nextInt();
            boolean verifica;
            switch (opcao) {
                case 1:
                    System.out.println("Registo de novos utilizadores");
                    requisitos.registo();
                    break;
                case 2:
                    verifica = requisitos.EfetuaLogin();
                    if (verifica == true) {
                        while(verifica){

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
                                case 1:

                                    break;
                                case 5:
                                    verifica = false;
                            }
                        }
                    }

                    break;
                case 3:
                    verifica = requisitos.EfetuaLoginAdmin();
                    if (verifica == true) {
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
                case 4:
                    System.out.println("Obrigado pela visita!");
                    return;
                case 5:
                    System.out.println("Que utilizador quer apagar (ID)");
                    requisitos.apagarUtilizador(sc.nextInt());
                    break;
                case 6:
                    requisitos.registoDeAdmin();
                    break;
            }
        }


    }
}
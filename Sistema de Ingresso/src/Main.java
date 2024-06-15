import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BackPart main = new BackPart();
        Scanner sc = new Scanner(System.in);
        System.out.println("*****************************");
        System.out.println("******* Teatro Guará ********");
        System.out.println("*****************************");
        System.out.println(" ");

        boolean connect = true;
        while (connect) {
            try {
                System.out.println("Escolha uma opção:");
                System.out.println("1 - Comprar Ingresso");
                System.out.println("2 - Checar estatísticas");
                System.out.println("3 - Sair");

                int opcao = sc.nextInt();

                if (opcao == 1 || opcao == 2 || opcao == 3) {
                    switch (opcao) {
                        case 1 -> {
                            main.checaCPF();
                            main.teatro();
                            if (BackPart.limiteLugares(main.getPecaEscol(), main.getHoraEscol(), main.getPolEscol(), main.getQuantLug())) {
                                BackPart.guardaDados(main.getCpfClient(), main.getPecaEscol(), main.getHoraEscol(), main.getPolEscol(), main.getQuantLug());
                                System.out.println("Compra realizada com sucesso!");
                            }
                        }
                        case 2 -> {
                            BackPart.estatisticaVendas(BackPart.pecaUm, BackPart.pecaDos, BackPart.pecaTres);
                            BackPart.estatisticaLucroMedio(BackPart.pecaUm, BackPart.pecaDos, BackPart.pecaTres);
                            BackPart.estatisticaNumeroPoltrona(BackPart.pecaUm, BackPart.pecaDos, BackPart.pecaTres);
                            BackPart.estatisticaPecaSessaoLucro(BackPart.pecaUm, BackPart.pecaDos, BackPart.pecaTres);
                        }
                        case 3 -> {
                            System.out.println("Teatro Guará agradece a preferência");
                            connect = false;
                        }
                    }
                } else {
                    System.out.println("Digite um valor válido");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                sc.next(); // Limpa o buffer do scanner
            } catch (Exception e) {
                System.out.println("Ocorreu um erro: " + e.getMessage());
            }
        }
        sc.close();
    }
}

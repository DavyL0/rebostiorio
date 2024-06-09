import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BackPart main = new BackPart();
        Scanner sc = new Scanner(System.in);
        System.out.println("*****************************");
        System.out.println("******* Teatro Guará ********");
        System.out.println("*****************************");
        System.out.println(" ");

        while (true){
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Comprar Ingresso");
            System.out.println("2 - Checar estastisticas ");
            System.out.println("3 - Sair");
            int opcao = sc.nextInt();

            switch (opcao){
                case 1 -> {
                    main.checaCPF();
                    main.teatro();
                    BackPart.compraIngressos(main.getCpfClient(), main.getPecaEscol(), main.getHoraEscol(), main.getPolEscol(), main.getQuantLug());
                    BackPart.guardaDados(main.getCpfClient(), main.getPecaEscol(), main.getHoraEscol(), main.getPolEscol(), main.getQuantLug());
                    }
                    case 2 -> {
                    BackPart.estatisticaVendas(BackPart.pecaUm, BackPart.pecaDos, BackPart.pecaTres);
                    BackPart.estatisticaLucroMedio(BackPart.pecaUm, BackPart.pecaDos, BackPart.pecaTres);
                    BackPart.estatisticaNumeroPoltrona(BackPart.pecaUm, BackPart.pecaDos, BackPart.pecaTres);
                    BackPart.estatisticaPecaSessaoLucro(BackPart.pecaUm, BackPart.pecaDos, BackPart.pecaTres);
                    }

                    case 3 -> {
                        System.out.println("Teatro Guará agradece a preferencia");
                        break;
                    }

                }
            }

        }
    }


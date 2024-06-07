import java.util.Objects;
import java.util.Scanner;

public class BackPart {
        //Variaveis Globais
        private String cpfClient;
        private int pecaEscol;
        private String horaEscol;
        private String polEscol;
        private int quantLug;

        // Função que cria informações do Usuário
        private String checaCPF() {
            //variaveis e classes
            Scanner sc = new Scanner(System.in);
            String cpfCliente;
            while (true) {
                int contadorError = 0;
                //Referencia de CPF´s invalidos
                String[] cpfNaoValidos = {"00000000000", "11111111111", "22222222222", "33333333333", "44444444444", "55555555555",
                        "66666666666", "77777777777", "88888888888", "99999999999"};
                //digita cpf
                System.out.println("Coloque seu CPF (Apenas Números) :");
                cpfCliente = sc.next();

                //valida cpf
                if (cpfCliente.length() != 11) {
                    System.out.println("CPF deve ter 11 dígitos. Por favor, tente novamente!");
                    continue;
                }

                for (String cpfNaoValido : cpfNaoValidos) {
                    if (Objects.equals(cpfCliente, cpfNaoValido)) {
                        contadorError++;
                    }
                }
                if (contadorError == 0) {
                    break;
                }
                System.out.println("CPF invalido, por favor digite novamente!");
            }
            this.cpfClient = cpfCliente;
            return cpfCliente;
        }

        private void teatro() {
            Scanner sc = new Scanner(System.in);

            System.out.println("Qual peça você quer assistir 1, 2 ou 3");
            int pecaTeatro = sc.nextInt();
            while (pecaTeatro < 1 || pecaTeatro > 3) {
                System.out.println("Opção inválida! Qual peça você quer assistir 1, 2 ou 3");
                pecaTeatro = sc.nextInt();
            }

            System.out.println("Qual horário você deseja assistir: Manhã(M), Tarde(T), Noite(N)");
            String horarioPeca = sc.next().toUpperCase();
            while (!horarioPeca.equals("M") && !horarioPeca.equals("T") && !horarioPeca.equals("N")) {
                System.out.println("Horário inválido! Escolha: Manhã(M), Tarde(T), Noite(N)");
                horarioPeca = sc.next().toUpperCase();
            }

            System.out.println("Selecione sua poltrona: Plateia A (PA), Plateia B (PB), Frisa (FA), Camarote (CA), Balcão Nobre (BN)");
            String poltrona = sc.next().toUpperCase();
            while (!poltrona.equals("PA") && !poltrona.equals("PB") && !poltrona.equals("FA") &&
                    !poltrona.equals("CA") && !poltrona.equals("BN")) {
                System.out.println("Poltrona inválida! Selecione: Plateia A (PA), Plateia B (PB), Frisa (FA), Camarote (CA), Balcão Nobre (BN)");
                poltrona = sc.next().toUpperCase();
            }

            System.out.println("Quantos lugares você deseja comprar");
            int quantLugar = sc.nextInt();
            while (quantLugar <= 0) {
                System.out.println("Quantidade inválida! Quantos lugares você deseja comprar");
                quantLugar = sc.nextInt();
            }

            this.pecaEscol = pecaTeatro;
            this.horaEscol = horarioPeca;
            this.polEscol = poltrona;
            this.quantLug = quantLugar;
        }

        //Realiza compra de ingressos
        private static void compraIngressos(String cpfCliente, int pecaEscolhida, String horarioEscolhido, String poltronaEscolhida, int quantLugar) {
            double valorTotal = 0;
            switch (poltronaEscolhida) {
                case "PA" -> valorTotal += 40;
                case "PB" -> valorTotal += 60;
                case "CA" -> valorTotal += 80;
                case "FA" -> valorTotal += 120;
                case "BN" -> valorTotal += 250;
            }
            valorTotal = valorTotal * quantLugar;
            //resultado compra
            System.out.println(cpfCliente + " você escolheu a peça " + pecaEscolhida + " no horário " + horarioEscolhido +
                    " e " + quantLugar + " lugares nas poltronas " + poltronaEscolhida + " valor total " + valorTotal);
        }

        //Guarda Dados das Pecas
        private static void guardaDados(String cpfCliente, int pecaEscolhida, String horarioEscolhido, String poltronaEscolhida) {
            String[][] pecaUm = new String[10][3]; // Assuming size 10 for demonstration
            String[][] pecaDos = new String[10][3];
            String[][] pecaTres = new String[10][3];
            int c = 0;

            if (pecaEscolhida == 1) {
                pecaUm[c][0] = cpfCliente;
                pecaUm[c][1] = horarioEscolhido;
                pecaUm[c][2] = poltronaEscolhida;
            } else if (pecaEscolhida == 2) {
                pecaDos[c][0] = cpfCliente;
                pecaDos[c][1] = horarioEscolhido;
                pecaDos[c][2] = poltronaEscolhida;
            } else if (pecaEscolhida == 3) {
                pecaTres[c][0] = cpfCliente;
                pecaTres[c][1] = horarioEscolhido;
                pecaTres[c][2] = poltronaEscolhida;
            }

            for (int i = 0; i < pecaUm.length; i++) {
                for (int j = 0; j < pecaUm[i].length; j++) {
                    System.out.printf("%s\t", pecaUm[i][j]);
                }
                System.out.println("");
            }
        }

        public static void main(String[] args) {
            BackPart main = new BackPart();
            String cpfCliente = main.checaCPF();
            main.teatro();
            compraIngressos(main.getCpfClient(), main.getPecaEscol(), main.getHoraEscol(), main.getPolEscol(), main.getQuantLug());
            guardaDados(main.getCpfClient(), main.getPecaEscol(), main.getHoraEscol(), main.getPolEscol());
        }

    private int getQuantLug() {
        return quantLug;
    }

    private String getPolEscol() {
        return polEscol;
    }

    private String getHoraEscol() {
        return horaEscol;
    }

    private int getPecaEscol() {
        return pecaEscol;
    }

    private String getCpfClient() {
        return cpfClient;
    }
}



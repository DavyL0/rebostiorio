import jdk.swing.interop.SwingInterOpUtils;

import java.sql.SQLOutput;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    // Função que cria informações do Usuário
    private static String checaCPF() {
        //variaveis e classes
        Scanner sc = new Scanner(System.in);
        String cpfCliente;
        while (true) {
            int contadorError = 0;
            //Referencia de CPF´s invalidos
            String cpfNaoValidos[] = {"00000000000", "11111111111", "22222222222", "33333333333", "44444444444", "55555555555",
                    "66666666666", "77777777777", "88888888888", "99999999999"};
            //digita cpf
            System.out.println("Coloque seu CPF (Apenas Números) :");
            cpfCliente = sc.next();

            //valida cpf
            for (int i = 0; i < cpfNaoValidos.length; i++) {
                if (Objects.equals(cpfCliente, cpfNaoValidos[i])) {
                    contadorError++;
                }
            }
            if (contadorError == 0) {
                break;
            }
            System.out.println("CPF invalido, por favor digite novamente!");
        }
        return cpfCliente;
    }
    private static void teatro(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Qual peça vc quer assistir 1, 2 ou 3");
        int pecaTeatro = sc.nextInt();
        System.out.println("Qual horario você deseja assistir:  Manhã(M), Tarde(T), Noite(N)");
        String horarioPeca = sc.next();
        System.out.println("Selecione sua poltrona: Plateia A (PA), Plateia B (PB), Frisa (FA), Camarote (CA), Balcão Nobre (BN)");
        String poltrona = sc.next();
        System.out.println("Quantos lugares você deseja comprar");
        int quantLugar = sc.nextInt();
    }
    //Realiza compra de ingressos
    private static void  compraIngressos(String cpfCliente, int pecaEscolhida, String horarioEscolhido, String poltronaEscolhida, int quantLugar){
        double valorTotal = 0;
        if()
        //resultado compra
        System.out.println(cpfCliente + " você escolheu a peça "+ pecaEscolhida + " no horario "+ horarioEscolhido +
                " e "+quantLugar+ " nas poltronas "+ poltronaEscolhida+ " valor total "+valorTotal);
    }
    //Guarda Dados das Pecas
    private static void guardaDados(String cpfCliente, int pecaEscolhida, String horarioEscolhido, String poltronaEscolhida){
        String[][] pecaUm  = new String[1][1];
        String[][] pecaDos = {};
        String[][] pecaTres  = {};
        int c = 0;

        if (pecaEscolhida == 1 ){
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

        for(int i = 0; i < pecaUm.length; i++) {
            for(int j = 0; j < pecaUm[i].length;j++){
                System.out.printf("%d\t", pecaUm[i][j]);
            }
            System.out.println("");
        }


    }

    public static void main(String[] args) {
        //checaCPF();

    }


}
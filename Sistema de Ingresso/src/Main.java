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
        byte horarioPeca = sc.nextByte();
        System.out.println("Selecione sua poltrona: Plateia A (PA), Plateia B (PB), Frisa (FA), Camarote (CA), Balcão Nobre (BN)");
        byte poltrona = sc.nextByte();
    }

    public static void main(String[] args) {
        checaCPF();
    }


}
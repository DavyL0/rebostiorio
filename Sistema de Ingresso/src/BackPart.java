import java.util.Objects;
import java.util.Scanner;

public class BackPart {
    // Variáveis Globais
    private String cpfClient;
    private int pecaEscol;
    private String horaEscol;
    private String polEscol;
    private int quantLug;
    static String[][] pecaUm = new String[100][4]; // Presumindo tamanho 100 para exemplo
    static String[][] pecaDos = new String[100][4]; // Presumindo tamanho 100 para exemplo
    static String[][] pecaTres = new String[100][4]; // Presumindo tamanho 100 para exemplo
    //cada linha dessa matriz é a quantidade de lugares de cada horario
    static int[][] pecaLugaresUm = {{25,100,30,50,50},{25,100,30,50,50},{25,100,30,50,50}};
    static int[][] pecaLugaresDos = {{25,100,30,50,50},{25,100,30,50,50},{25,100,30,50,50}};
    static int[][] pecaLugaresTres = {{25,100,30,50,50},{25,100,30,50,50},{25,100,30,50,50}};
    public BackPart(String cpfCliente, int pecaEscolhida, String horarioEscolhido, String poltronaEscolhida, int quantLugar) {
        this.cpfClient = cpfCliente;
        this.pecaEscol = pecaEscolhida;
        this.horaEscol = horarioEscolhido;
        this.polEscol = poltronaEscolhida;
        this.quantLug = quantLugar;
    }

    public BackPart() {
    }

    String checaCPF() {
        Scanner sc = new Scanner(System.in);
        String cpfCliente;
        while (true) {
            System.out.println("Coloque seu CPF (Apenas Números) :");
            cpfCliente = sc.next();

            if (cpfCliente.length() != 11 || !cpfCliente.matches("\\d+")) {
                System.out.println("CPF deve ter 11 dígitos numéricos. Por favor, tente novamente!");
                continue;
            }

            if (isInvalidCPF(cpfCliente)) {
                System.out.println("CPF inválido, por favor digite novamente!");
            } else {
                break;
            }
        }
        this.cpfClient = cpfCliente;
        return cpfCliente;
    }

    private boolean isInvalidCPF(String cpfCliente) {
        String[] cpfNaoValidos = {"00000000000", "11111111111", "22222222222", "33333333333", "44444444444",
                "55555555555", "66666666666", "77777777777", "88888888888", "99999999999"};
        for (String cpfNaoValido : cpfNaoValidos) {
            if (Objects.equals(cpfCliente, cpfNaoValido)) {
                return true;
            }
        }
        return false;
    }

    void teatro() {
        Scanner sc = new Scanner(System.in);

        this.pecaEscol = getInputInt("Qual peça você quer assistir 1, 2 ou 3", 1, 3, sc);
        this.horaEscol = getInputString("Qual horário você deseja assistir: Manhã(M), Tarde(T), Noite(N)",
                new String[]{"M", "T", "N"}, sc);
        this.polEscol = getInputString("Selecione sua poltrona: Plateia A (PA), Plateia B (PB), Frisa (FA), Camarote (CA), Balcão Nobre (BN)",
                new String[]{"PA", "PB", "FA", "CA", "BN"}, sc);
        this.quantLug = getInputInt("Quantos lugares você deseja comprar", 1, Integer.MAX_VALUE, sc);
    }

    private int getInputInt(String prompt, int min, int max, Scanner sc) {
        int value;
        while (true) {
            System.out.println(prompt);
            if (sc.hasNextInt()) {
                value = sc.nextInt();
                if (value >= min && value <= max) {
                    break;
                }
            } else {
                sc.next(); // clear the invalid input
            }
            System.out.println("Entrada inválida! Por favor, tente novamente.");
        }
        return value;
    }

    private String getInputString(String prompt, String[] validOptions, Scanner sc) {
        String value;
        while (true) {
            System.out.println(prompt);
            value = sc.next().toUpperCase();
            for (String option : validOptions) {
                if (option.equals(value)) {
                    return value;
                }
            }
            System.out.println("Entrada inválida! Por favor, tente novamente.");
        }
    }

    public static boolean limiteLugares(int pecaEscolhida, String horarioEscolhido, String poltronaEscolhida, int quantLugar) {
        int[][] pecaLugares;

        switch (pecaEscolhida) {
            case 1 -> pecaLugares = pecaLugaresUm;
            case 2 -> pecaLugares = pecaLugaresDos;
            case 3 -> pecaLugares = pecaLugaresTres;
            default -> {
                System.out.println("Peça inválida.");
                return false;
            }
        }

        int horarioIndex;
        switch (horarioEscolhido) {
            case "M" -> horarioIndex = 0;
            case "T" -> horarioIndex = 1;
            case "N" -> horarioIndex = 2;
            default -> {
                System.out.println("Horário inválido.");
                return false;
            }
        }

        int poltronaIndex;
        switch (poltronaEscolhida) {
            case "PA" -> poltronaIndex = 0;
            case "PB" -> poltronaIndex = 1;
            case "FA" -> poltronaIndex = 2;
            case "CA" -> poltronaIndex = 3;
            case "BN" -> poltronaIndex = 4;
            default -> {
                System.out.println("Poltrona inválida.");
                return false;
            }
        }

        if (pecaLugares[poltronaIndex][horarioIndex] >= quantLugar) {
            pecaLugares[poltronaIndex][horarioIndex] -= quantLugar;
            return true;
        } else {
            System.out.println("Não há lugares disponíveis suficientes.");
            return false;
        }
    }

    static void compraIngressos(String cpfCliente, int pecaEscolhida, String horarioEscolhido, String poltronaEscolhida, int quantLugar) {
        double valorTotal = 0;
        switch (poltronaEscolhida) {
            case "PA" -> valorTotal += 40;
            case "PB" -> valorTotal += 60;
            case "CA" -> valorTotal += 80;
            case "FA" -> valorTotal += 120;
            case "BN" -> valorTotal += 250;
        }
        valorTotal = valorTotal * quantLugar;
        System.out.println(cpfCliente + " você escolheu a peça " + pecaEscolhida + " no horário " + horarioEscolhido +
                " e " + quantLugar + " lugares nas poltronas " + poltronaEscolhida + " valor total " + valorTotal);
    }

    static void guardaDados(String cpfCliente, int pecaEscolhida, String horarioEscolhido, String poltronaEscolhida, int quantLugar) {
        String[][] pecaSelecionada;
        switch (pecaEscolhida) {
            case 1 -> pecaSelecionada = pecaUm;
            case 2 -> pecaSelecionada = pecaDos;
            case 3 -> pecaSelecionada = pecaTres;
            default -> throw new IllegalArgumentException("Peça inválida: " + pecaEscolhida);
        }

        for (int i = 0; i < pecaSelecionada.length; i++) {
            if (pecaSelecionada[i][0] == null) {
                pecaSelecionada[i][0] = cpfCliente;
                pecaSelecionada[i][1] = horarioEscolhido;
                pecaSelecionada[i][2] = poltronaEscolhida;
                pecaSelecionada[i][3] = String.valueOf(quantLugar);
                break;
            }
        }

        // Imprime a matriz armazenada para verificação
        System.out.println("Dados armazenados para a peça " + pecaEscolhida + ":");
        for (String[] registro : pecaSelecionada) {
            if (registro[0] != null) {
                System.out.printf("CPF: %s, Horário: %s, Poltrona: %s, Quantidade: %s%n", registro[0], registro[1], registro[2], registro[3]);
            }
        }
    }

    static void estatisticaVendas(String[][] pecaUm, String[][] pecaDos, String[][] pecaTres){
        //variaveis de totais
            int somaPecaUm = 0;
            int somaPecaDos = 0;
            int somaPecaTres = 0;

        for (String[] strings : pecaUm) {
            if (strings[3] != null) {
                somaPecaUm += Integer.parseInt(strings[3]);
            }
        }
        for (String[] pecaDo : pecaDos) {
            if (pecaDo[3] != null) {
                somaPecaDos += Integer.parseInt(pecaDo[3]);
            }
        }
        for (String[] pecaTre : pecaTres) {
            if (pecaTre[3] != null) {
                somaPecaTres += Integer.parseInt(pecaTre[3]);
            }
        }
        //verifica qual foi a peça mais vendida e menos vendida
        if (somaPecaTres > somaPecaDos){
            if(somaPecaTres > somaPecaUm){
                System.out.println("A peça três foi a mais vendida");
                if (somaPecaDos > somaPecaUm){
                    System.out.println("A peça um foi a menos vendida ");
                }else {
                    System.out.println("A peça dois foi a menos vendida");
                }
            }else{
                System.out.println("A peça um foi a mais vendida");
                System.out.println("A peça dois foi a menos vendida");
            }
        } else if (somaPecaDos > somaPecaUm) {
                System.out.println("A peça dois foi a mais vendida");
                if (somaPecaTres > somaPecaUm){
                    System.out.println("A peça um foi a menos vendida ");
                }else{
                    System.out.println("A peça três foi a menos vendida");
                }
        }else {
            System.out.println("A peça um foi a mais vendida");
            System.out.println("A peça três foi a menos vendida");

        }
    }

    static void estatisticaLucroMedio(String[][] pecaUm, String[][] pecaDos, String[][] pecaTres){
        // Variáveis de totais
        int somaPecaUm = 0;
        int somaPecaDos = 0;
        int somaPecaTres = 0;

        for (String[] pecaU : pecaUm) {
            if (pecaU[2] != null) {
                switch (pecaU[2]) {
                    case "PA" -> {
                        if (pecaU[3] != null) somaPecaUm += 40 * Integer.parseInt(pecaU[3]);
                    }
                    case "PB" -> {
                        if (pecaU[3] != null) somaPecaUm += 60 * Integer.parseInt(pecaU[3]);
                    }
                    case "CA" -> {
                        if (pecaU[3] != null) somaPecaUm += 80 * Integer.parseInt(pecaU[3]);
                    }
                    case "FA" -> {
                        if (pecaU[3] != null) somaPecaUm += 120 * Integer.parseInt(pecaU[3]);
                    }
                    case "BN" -> {
                        if (pecaU[3] != null) somaPecaUm += 250 * Integer.parseInt(pecaU[3]);
                    }
                }
            }
        }

        for (String[] pecaDo : pecaDos) {
            if (pecaDo[2] != null) {
                switch (pecaDo[2]) {
                    case "PA" -> {
                        if (pecaDo[3] != null) somaPecaDos += 40 * Integer.parseInt(pecaDo[3]);
                    }
                    case "PB" -> {
                        if (pecaDo[3] != null) somaPecaDos += 60 * Integer.parseInt(pecaDo[3]);
                    }
                    case "CA" -> {
                        if (pecaDo[3] != null) somaPecaDos += 80 * Integer.parseInt(pecaDo[3]);
                    }
                    case "FA" -> {
                        if (pecaDo[3] != null) somaPecaDos += 120 * Integer.parseInt(pecaDo[3]);
                    }
                    case "BN" -> {
                        if (pecaDo[3] != null) somaPecaDos += 250 * Integer.parseInt(pecaDo[3]);
                    }
                }
            }
        }

            for (String[] pecaTre : pecaTres) {
                if (pecaTre[2] != null) {
                    switch (pecaTre[2]) {
                        case "PA" -> {
                            if (pecaTre[3] != null) somaPecaTres += 40 * Integer.parseInt(pecaTre[3]);
                        }
                        case "PB" -> {
                            if (pecaTre[3] != null) somaPecaTres += 60 * Integer.parseInt(pecaTre[3]);
                        }
                        case "CA" -> {
                            if (pecaTre[3] != null) somaPecaTres += 80 * Integer.parseInt(pecaTre[3]);
                        }
                        case "FA" -> {
                            if (pecaTre[3] != null) somaPecaTres += 120 * Integer.parseInt(pecaTre[3]);
                        }
                        case "BN" -> {
                            if (pecaTre[3] != null) somaPecaTres += 250 * Integer.parseInt(pecaTre[3]);
                        }
                    }
                }
            }
        System.out.println("O lucro médio da peça um foi R$ " + somaPecaUm);
        System.out.println("O lucro médio da peça dois foi R$ " + somaPecaDos);
        System.out.println("O lucro médio da peça três foi R$ "+ somaPecaTres);

        //verifica qual foi a peça mais lucrativa
        if (somaPecaTres > somaPecaDos){
            if(somaPecaTres > somaPecaUm){
                System.out.println("A peça três foi a mais lucrativa");
                if (somaPecaDos > somaPecaUm){
                    System.out.println("A peça um foi a menos lucrativa ");
                }else {
                    System.out.println("A peça dois foi a menos lucrativa");
                }
            }else{
                System.out.println("A peça um foi a mais lucrativa");
                System.out.println("A peça dois foi a menos lucrativa");
            }
        } else if (somaPecaDos > somaPecaUm) {
            System.out.println("A peça dois foi a mais lucrativa");
            if (somaPecaTres > somaPecaUm){
                System.out.println("A peça um foi a menos lucrativa ");
            }else{
                System.out.println("A peça três foi a menos lucrativa");
            }
        }else {
            System.out.println("A peça um foi a mais lucrativa");
            System.out.println("A peça três foi a menos lucrativa");

        }
    }

    static void estatisticaNumeroPoltrona(String[][] pecaUm, String[][] pecaDos, String[][] pecaTres){
        int somaManha = 0;
        int somaTarde = 0;
        int somaNoite = 0;

        for (String[] pecaU : pecaUm) {
            if (pecaU[1] != null) {
                switch (pecaU[1]){
                    case "M"-> {
                        if (pecaU[3] != null) somaManha ++;
                    }
                    case "T"-> {
                        if (pecaU[3] != null)somaTarde ++;
                    }
                    case "N"-> {
                        if (pecaU[3] != null)somaNoite ++;
                    }
                }
            }
        }
        for (String[] pecaDo : pecaDos) {
            if (pecaDo[1] != null) {
                switch (pecaDo[1]){
                    case "M"-> {
                        if (pecaDo[3] != null) somaManha ++;
                    }
                    case "T"-> {
                        if (pecaDo[3] != null)somaTarde ++;
                    }
                    case "N"-> {
                        if (pecaDo[3] != null)somaNoite ++;
                    }
                }
            }
        }
        for (String[] pecaTre : pecaTres) {
            if (pecaTre[1] != null) {
                switch (pecaTre[1]){
                    case "M"-> {
                        if (pecaTre[3] != null) somaManha ++;
                    }
                    case "T"-> {
                        if (pecaTre[3] != null)somaTarde ++;
                    }
                    case "N"-> {
                        if (pecaTre[3] != null)somaNoite ++;
                    }
                }
            }
        }
        //verifica sessão mais vista
        if (somaNoite > somaTarde){
            if(somaNoite > somaManha){
                System.out.println("A sessão da noite foi a mais ocupada");
                if (somaTarde > somaManha){
                    System.out.println("A sessão da manhã foi a menos ocupada ");
                }else {
                    System.out.println("A sessão da tarde foi a menos ocupada");
                }
            }else{
                System.out.println("A sessão da manhã  foi a mais ocupada");
                System.out.println("A sessão da tarde foi a menos ocupada");
            }
        } else if (somaTarde > somaManha) {
            System.out.println("A sessão da tarde foi a mais ocupada");
            if (somaNoite > somaManha){
                System.out.println("A sessão da manhã foi a menos ocupada ");
            }else{
                System.out.println("A sessão da noite foi a menos ocupada");
            }
        }else {
            System.out.println("A sessão da manhã  foi a mais ocupada");
            System.out.println("A sessão da noite foi a menos ocupada");

        }
    }

    static void estatisticaPecaSessaoLucro(String[][] pecaUm, String[][] pecaDos, String[][] pecaTres){
        int lucroPecaUm = 0;
        String horaPecaUm = "";
        int lucroPecaDos = 0;
        String horaPecaDos = "";
        int lucroPecaTres = 0;
        String horaPecaTres = "";
        int defPecaUm = 0;
        String horaDefPecaUm = "";
        int defPecaDos = 0;
        String horaDefPecaDos = "";
        int defPecaTres = 0;
        String horaDefPecaTres = "";



        for (String[] strings : pecaUm) {
            int manha = 0;
            int tarde = 0;
            int noite = 0;

            if (strings[1] != null) {
                switch (strings[1]){
                    case "M"->{
                        if (strings[2] != null) {
                            switch (strings[2]){
                                case "PA"-> {
                                    if (strings[3] != null){
                                        manha += 40* Integer.parseInt(strings[3]);
                                    }
                                }
                                case "PB"-> {
                                    if (strings[3] != null) {
                                        manha += 60 * Integer.parseInt(strings[3]);
                                    }
                                }
                                case "CA"-> {
                                    if (strings[3] != null) {
                                        manha += 80 * Integer.parseInt(strings[3]);
                                    }
                                }
                                case "FA"-> {
                                    if (strings[3] != null) {
                                        manha += 120 * Integer.parseInt(strings[3]);
                                    }
                                }
                                case "BN"-> {
                                    if (strings[3] != null) {
                                    manha +=250* Integer.parseInt(strings[3]);
                                    }
                                }
                            }
                        }

                    }
                    case "T"-> {
                        if (strings[2] != null) {
                            switch (strings[2]){
                                case "PA"-> {
                                    if (strings[3] != null){
                                        tarde += 40* Integer.parseInt(strings[3]);
                                    }
                                }
                                case "PB"-> {
                                    if (strings[3] != null) {
                                        tarde += 60 * Integer.parseInt(strings[3]);
                                    }
                                }
                                case "CA"-> {
                                    if (strings[3] != null) {
                                        tarde += 80 * Integer.parseInt(strings[3]);
                                    }
                                }
                                case "FA"-> {
                                    if (strings[3] != null) {
                                        tarde += 120 * Integer.parseInt(strings[3]);
                                    }
                                }
                                case "BN"-> {
                                    if (strings[3] != null) {
                                        tarde +=250* Integer.parseInt(strings[3]);
                                    }
                                }
                            }
                        }
                    }
                    case "N"->{
                        if (strings[2] != null) {
                            switch (strings[2]){
                                case "PA"-> {
                                    if (strings[3] != null){
                                        noite += 40* Integer.parseInt(strings[3]);
                                    }
                                }
                                case "PB"-> {
                                    if (strings[3] != null) {
                                        noite += 60 * Integer.parseInt(strings[3]);
                                    }
                                }
                                case "CA"-> {
                                    if (strings[3] != null) {
                                        noite += 80 * Integer.parseInt(strings[3]);
                                    }
                                }
                                case "FA"-> {
                                    if (strings[3] != null) {
                                        noite += 120 * Integer.parseInt(strings[3]);
                                    }
                                }
                                case "BN"-> {
                                    if (strings[3] != null) {
                                        noite +=250* Integer.parseInt(strings[3]);
                                    }
                                }
                            }
                        }
                    }
                }
                //verifica os horarios mais e menos lucrativos da peça
                if(noite > tarde){
                    if (noite > manha){
                        lucroPecaUm = noite;
                        horaPecaUm = "noite";
                        defPecaUm = manha;
                        horaDefPecaUm = "manhã";
                    }else{
                        lucroPecaUm = manha;
                        horaPecaUm = "manhã";
                        defPecaUm = tarde;
                        horaDefPecaUm = "tarde";
                    }
                }else if (tarde > manha) {
                    lucroPecaUm = tarde;
                    horaPecaUm = "tarde";

                    defPecaUm = manha;
                    horaDefPecaUm = "manhã";
                }else{
                    lucroPecaUm = manha;
                    horaPecaUm = "manhã";
                    if(tarde > noite){
                        defPecaUm = noite;
                        horaDefPecaUm = "noite";
                    }else{
                        defPecaUm = tarde;
                        horaDefPecaUm = "tarde";
                    }
                }
            }
        }
        for (String[] strings : pecaDos) {
            int manha = 0;
            int tarde = 0;
            int noite = 0;
            if (strings[1] != null) {
                switch (strings[1]){
                    case "M"->{
                        if (strings[2] != null) {
                            switch (strings[2]){
                                case "PA"-> {
                                    if (strings[3] != null){
                                        manha += 40* Integer.parseInt(strings[3]);
                                    }
                                }
                                case "PB"-> {
                                    if (strings[3] != null) {
                                        manha += 60 * Integer.parseInt(strings[3]);
                                    }
                                }
                                case "CA"-> {
                                    if (strings[3] != null) {
                                        manha += 80 * Integer.parseInt(strings[3]);
                                    }
                                }
                                case "FA"-> {
                                    if (strings[3] != null) {
                                        manha += 120 * Integer.parseInt(strings[3]);
                                    }
                                }
                                case "BN"-> {
                                    if (strings[3] != null) {
                                        manha +=250* Integer.parseInt(strings[3]);
                                    }
                                }
                            }
                        }

                    }
                    case "T"-> {
                        if (strings[2] != null) {
                            switch (strings[2]){
                                case "PA"-> {
                                    if (strings[3] != null){
                                        tarde += 40* Integer.parseInt(strings[3]);
                                    }
                                }
                                case "PB"-> {
                                    if (strings[3] != null) {
                                        tarde += 60 * Integer.parseInt(strings[3]);
                                    }
                                }
                                case "CA"-> {
                                    if (strings[3] != null) {
                                        tarde += 80 * Integer.parseInt(strings[3]);
                                    }
                                }
                                case "FA"-> {
                                    if (strings[3] != null) {
                                        tarde += 120 * Integer.parseInt(strings[3]);
                                    }
                                }
                                case "BN"-> {
                                    if (strings[3] != null) {
                                        tarde +=250* Integer.parseInt(strings[3]);
                                    }
                                }
                            }
                        }
                    }
                    case "N"->{
                        if (strings[2] != null) {
                            switch (strings[2]){
                                case "PA"-> {
                                    if (strings[3] != null){
                                        noite += 40* Integer.parseInt(strings[3]);
                                    }
                                }
                                case "PB"-> {
                                    if (strings[3] != null) {
                                        noite += 60 * Integer.parseInt(strings[3]);
                                    }
                                }
                                case "CA"-> {
                                    if (strings[3] != null) {
                                        noite += 80 * Integer.parseInt(strings[3]);
                                    }
                                }
                                case "FA"-> {
                                    if (strings[3] != null) {
                                        noite += 120 * Integer.parseInt(strings[3]);
                                    }
                                }
                                case "BN"-> {
                                    if (strings[3] != null) {
                                        noite +=250* Integer.parseInt(strings[3]);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if(noite > tarde){
                if (noite > manha){
                    lucroPecaDos = noite;
                    horaPecaDos = "noite";
                    defPecaDos = manha;
                    horaDefPecaDos = "manhã";
                }else{
                    lucroPecaDos = manha;
                    horaPecaDos = "manhã";
                    defPecaDos = tarde;
                    horaDefPecaDos = "tarde";
                }
            }else if (tarde > manha) {
                lucroPecaDos = tarde;
                horaPecaDos = "tarde";

                defPecaDos = manha;
                horaDefPecaDos = "manhã";
            }else{
                lucroPecaDos = manha;
                horaPecaDos = "manhã";
                if(tarde > noite){
                    defPecaDos = noite;
                    horaDefPecaDos = "noite";
                }else{
                    defPecaDos = tarde;
                    horaDefPecaDos = "tarde";
                }
            }
        }
        for (String[] strings : pecaTres) {
            int manha = 0;
            int tarde = 0;
            int noite = 0;
            if (strings[1] != null) {
                switch (strings[1]){
                    case "M"->{
                        if (strings[2] != null) {
                            switch (strings[2]){
                                case "PA"-> {
                                    if (strings[3] != null){
                                        manha += 40* Integer.parseInt(strings[3]);
                                    }
                                }
                                case "PB"-> {
                                    if (strings[3] != null) {
                                        manha += 60 * Integer.parseInt(strings[3]);
                                    }
                                }
                                case "CA"-> {
                                    if (strings[3] != null) {
                                        manha += 80 * Integer.parseInt(strings[3]);
                                    }
                                }
                                case "FA"-> {
                                    if (strings[3] != null) {
                                        manha += 120 * Integer.parseInt(strings[3]);
                                    }
                                }
                                case "BN"-> {
                                    if (strings[3] != null) {
                                        manha +=250* Integer.parseInt(strings[3]);
                                    }
                                }
                            }
                        }

                    }
                    case "T"-> {
                        if (strings[2] != null) {
                            switch (strings[2]){
                                case "PA"-> {
                                    if (strings[3] != null){
                                        tarde += 40* Integer.parseInt(strings[3]);
                                    }
                                }
                                case "PB"-> {
                                    if (strings[3] != null) {
                                        tarde += 60 * Integer.parseInt(strings[3]);
                                    }
                                }
                                case "CA"-> {
                                    if (strings[3] != null) {
                                        tarde += 80 * Integer.parseInt(strings[3]);
                                    }
                                }
                                case "FA"-> {
                                    if (strings[3] != null) {
                                        tarde += 120 * Integer.parseInt(strings[3]);
                                    }
                                }
                                case "BN"-> {
                                    if (strings[3] != null) {
                                        tarde +=250* Integer.parseInt(strings[3]);
                                    }
                                }
                            }
                        }
                    }
                    case "N"->{
                        if (strings[2] != null) {
                            switch (strings[2]){
                                case "PA"-> {
                                    if (strings[3] != null){
                                        noite += 40* Integer.parseInt(strings[3]);
                                    }
                                }
                                case "PB"-> {
                                    if (strings[3] != null) {
                                        noite += 60 * Integer.parseInt(strings[3]);
                                    }
                                }
                                case "CA"-> {
                                    if (strings[3] != null) {
                                        noite += 80 * Integer.parseInt(strings[3]);
                                    }
                                }
                                case "FA"-> {
                                    if (strings[3] != null) {
                                        noite += 120 * Integer.parseInt(strings[3]);
                                    }
                                }
                                case "BN"-> {
                                    if (strings[3] != null) {
                                        noite +=250* Integer.parseInt(strings[3]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if(noite > tarde){
                if (noite > manha){
                    lucroPecaTres = noite;
                    horaPecaTres = "noite";
                    defPecaTres = manha;
                    horaDefPecaTres = "manhã";
                }else{
                    lucroPecaTres = manha;
                    horaPecaTres = "manhã";
                    defPecaTres = tarde;
                    horaDefPecaTres = "tarde";
                }
            }else if (tarde > manha) {
                lucroPecaTres = tarde;
                horaPecaTres = "tarde";

                defPecaTres = manha;
                horaDefPecaTres = "manhã";
            }else{
                lucroPecaTres = manha;
                horaPecaTres = "manhã";
                if(tarde > noite){
                    defPecaTres = noite;
                    horaDefPecaTres = "noite";
                }else{
                    defPecaTres = tarde;
                    horaDefPecaTres = "tarde";
                }
            }
        }
        //verifica qual foi a sessão e peça mais lucrativa
        if(lucroPecaTres > lucroPecaDos){
            if (lucroPecaTres > lucroPecaUm){
                System.out.println("A peça três na parte da "+ horaPecaTres +" foi a mais lucrativa");
            }else{
                System.out.println("A peça um na parte da "+ horaPecaUm +" foi a mais lucrativa");
            }
        } else if (lucroPecaDos > lucroPecaUm) {
            System.out.println("A peça dois na parte da "+ horaPecaDos +" foi a mais lucrativa");
        }else{
            System.out.println("A peça um na parte da "+ horaPecaUm +" foi a mais lucrativa");
        }

        //verifica qual foi a sessão e peça menos lucrativa
        if(defPecaTres < defPecaDos){
            if (defPecaTres < defPecaUm){
                System.out.println("A peça três na parte da "+ horaDefPecaTres +" foi a menos lucrativa");
            }else{
                System.out.println("A peça um na parte da "+ horaDefPecaUm +" foi a menos lucrativa");
            }
        } else if (defPecaDos < defPecaUm) {
            System.out.println("A peça dois na parte da "+ horaDefPecaDos +" foi a menos lucrativa");
        }else{
            System.out.println("A peça um na parte da "+ horaDefPecaUm +" foi a menos lucrativa");
        }

    }
    public int getQuantLug() {
        return quantLug;
    }

    public String getPolEscol() {
        return polEscol;
    }

    public String getHoraEscol() {
        return horaEscol;
    }

    public int getPecaEscol() {
        return pecaEscol;
    }

    public String getCpfClient() {
        return cpfClient;
    }
}

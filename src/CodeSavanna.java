import java.util.Scanner;
import java.io.FileNotFoundException;

public class CodeSavanna {

    // ============================== MENU CLIENTE ==============================
    public static void menuCliente(String[][] matrizClientes, String[][] matrizAnimais, String[][] matrizInteracoes) {

        Scanner input = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n\n-===== MENU CLIENTE CODE SAVANNA+ =====");
            System.out.println("1. Ver catálogo de animais por habitat");
            System.out.println("2. Ver atividades de um animal");
            System.out.println("3. Simular apadrinhamento de um animal");
            System.out.println("4. Jogo: adivinha a especial");
            System.out.println("0. Voltar");

            System.out.print("Opção: ");
            opcao = input.nextInt();

            switch (opcao) {

                case 1:
                    catalogoAnimaisHabitat(matrizAnimais);
                    break;

                case 2:
                    verAtividadesAnimal(matrizAnimais, matrizInteracoes, input);
                    break;

                case 3:
                    simularApadrinhamento(matrizAnimais, input);
                    break;

                case 4:
                    break;

                case 0:
                    break;

                default:
                    System.out.println("\nOpção Inválida");
                    break;
            }

        } while (opcao != 0);
    }

    // ======================= CATÁLOGO DE ANIMAIS POR HABITAT =======================
    public static void catalogoAnimaisHabitat(String[][] matrizAnimais) {

        int total = matrizAnimais.length;
        String[] nomes = new String[total];
        String[] especies = new String[total];
        String[] habitats = new String[total];

        for (int i = 0; i < total; i++) {
            nomes[i] = matrizAnimais[i][1];
            especies[i] = matrizAnimais[i][2];
            habitats[i] = matrizAnimais[i][3];
        }

        for (int i = 0; i < total - 1; i++) {
            for (int j = 0; j < total - 1 - i; j++) {
                if (habitats[j].compareTo(habitats[j + 1]) > 0) {

                    String tempHab = habitats[j];
                    habitats[j] = habitats[j + 1];
                    habitats[j + 1] = tempHab;

                    String tempNome = nomes[j];
                    nomes[j] = nomes[j + 1];
                    nomes[j + 1] = tempNome;

                    String tempEsp = especies[j];
                    especies[j] = especies[j + 1];
                    especies[j + 1] = tempEsp;
                }
            }
        }

        String habitatAtual = "";
        boolean primeiro = true;

        for (int i = 0; i < total; i++) {

            if (primeiro || !habitats[i].equals(habitatAtual)) {
                habitatAtual = habitats[i];
                System.out.println("\n*** " + habitatAtual + " ***");
                primeiro = false;
            }

            System.out.println("- " + nomes[i] + " (" + especies[i] + ")");
        }
    }

    // ====================== ATIVIDADES (ESPETÁCULOS / ALIMENTAÇÕES) ======================
    public static void verAtividadesAnimal(String[][] matrizAnimais, String[][] matrizInteracoes, Scanner input) {

        System.out.print("\nId do animal (ex: A01): ");
        String idAnimalProcurado = input.next();

        String nomeAnimal = null;
        String especieAnimal = null;

        for (int i = 0; i < matrizAnimais.length; i++) {
            if (matrizAnimais[i][0].equalsIgnoreCase(idAnimalProcurado)) {
                nomeAnimal = matrizAnimais[i][1];
                especieAnimal = matrizAnimais[i][2];
                break;
            }
        }

        if (nomeAnimal == null) {
            System.out.println("\nNão existe nenhum animal com o id " + idAnimalProcurado + ".");
            return;
        }

        System.out.println("\nAtividades do animal " + nomeAnimal + " (" + especieAnimal + "):\n");

        String[] nomesEspetaculos = new String[matrizInteracoes.length];
        int[] contagensEspetaculos = new int[matrizInteracoes.length];
        int qtdEspetaculos = 0;

        String[] nomesAlimentacoes = new String[matrizInteracoes.length];
        int[] contagensAlimentacoes = new int[matrizInteracoes.length];
        int qtdAlimentacoes = 0;

        for (int i = 0; i < matrizInteracoes.length; i++) {

            if (matrizInteracoes[i][3].equalsIgnoreCase(idAnimalProcurado)) {

                String tipo = matrizInteracoes[i][1];
                String nomeEvento = matrizInteracoes[i][2];

                if (tipo.equalsIgnoreCase("ESPETACULO")) {

                    int pos = -1;
                    for (int j = 0; j < qtdEspetaculos; j++) {
                        if (nomesEspetaculos[j].equalsIgnoreCase(nomeEvento)) {
                            pos = j;
                            break;
                        }
                    }

                    if (pos == -1) {
                        nomesEspetaculos[qtdEspetaculos] = nomeEvento;
                        contagensEspetaculos[qtdEspetaculos] = 1;
                        qtdEspetaculos++;
                    } else {
                        contagensEspetaculos[pos]++;
                    }

                } else if (tipo.equalsIgnoreCase("ALIMENTACAO")) {

                    int pos = -1;
                    for (int j = 0; j < qtdAlimentacoes; j++) {
                        if (nomesAlimentacoes[j].equalsIgnoreCase(nomeEvento)) {
                            pos = j;
                            break;
                        }
                    }

                    if (pos == -1) {
                        nomesAlimentacoes[qtdAlimentacoes] = nomeEvento;
                        contagensAlimentacoes[qtdAlimentacoes] = 1;
                        qtdAlimentacoes++;
                    } else {
                        contagensAlimentacoes[pos]++;
                    }
                }
            }
        }

        System.out.println("ESPETÁCULOS:");
        if (qtdEspetaculos == 0) {
            System.out.println("- (sem registos)");
        } else {
            for (int i = 0; i < qtdEspetaculos; i++) {
                System.out.println("- " + nomesEspetaculos[i] + " (" + contagensEspetaculos[i] + " vezes)");
            }
        }

        System.out.println("\nALIMENTAÇÕES:");
        if (qtdAlimentacoes == 0) {
            System.out.println("- (sem registos)");
        } else {
            for (int i = 0; i < qtdAlimentacoes; i++) {
                System.out.println("- " + nomesAlimentacoes[i] + " (" + contagensAlimentacoes[i] + " vezes)");
            }
        }
    }

    // ========================== SIMULAR APADRINHAMENTO ==========================
    public static void simularApadrinhamento(String[][] matrizAnimais, Scanner input) {

        input.nextLine();

        System.out.print("\nNome do cliente: ");
        String nomeCliente = input.nextLine();

        System.out.print("Email: ");
        String email = input.nextLine();

        System.out.print("Id do animal (ex: A01): ");
        String idAnimalProcurado = input.nextLine();

        String nomeAnimal = null;
        String especieAnimal = null;
        String habitatAnimal = null;

        for (int i = 0; i < matrizAnimais.length; i++) {
            if (matrizAnimais[i][0].equalsIgnoreCase(idAnimalProcurado)) {
                nomeAnimal = matrizAnimais[i][1];
                especieAnimal = matrizAnimais[i][2];
                habitatAnimal = matrizAnimais[i][3];
                break;
            }
        }

        if (nomeAnimal == null) {
            System.out.println("\nNão existe nenhum animal com o id " + idAnimalProcurado + ".");
            return;
        }

        System.out.print("Valor mensal que pretende pagar (€): ");
        double valorMensal = input.nextDouble();

        String plano;
        if (valorMensal <= 25.0) {
            plano = "Apadrinhamento Simples";
        } else if (valorMensal <= 50.0) {
            plano = "Apadrinhamento Gold";
        } else {
            plano = "Apadrinhamento Diamond";
        }

        System.out.println("\nResumo do Apadrinhamento:\n");
        System.out.println("Padrinho : " + nomeCliente + " (" + email + ")");
        System.out.println("Animal   : " + nomeAnimal + " (" + especieAnimal + ") - " + habitatAnimal);
        System.out.println("Plano    : " + plano);
        System.out.printf("Valor    : %.2f €/mês%n", valorMensal);
    }

    // ============================== MENU ADMIN ==============================
    public static void menuAdmin(String[][] matrizClientes, String[][] matrizAnimais, String[][] matrizInteracoes) {

        Scanner input = new Scanner(System.in);
        int opcao;

        do {

            System.out.println("\n\n-*-*-*-*-*- MENU ADMIN CODESAVANNA-*-*-*-*-*-");
            System.out.println("1. Listar conteúdo dos ficheiros");
            System.out.println("2. Estatísticas gerais de interações");
            System.out.println("3. Receita total por tipo de interação");
            System.out.println("4. Animal mais popular");
            System.out.println("5. Top 3 espécies com mais apadrinhamentos");
            System.out.println("6. Listar padrinhos de um animal");
            System.out.println("7. Espetáculo mais rentável");
            System.out.println("8. Ranking de animais em perigo de extinção");
            System.out.println("9. Estatísticas por habitat");
            System.out.println("0. Voltar");

            System.out.print("Opção: ");
            opcao = input.nextInt();

            switch (opcao) {

                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 5:
                    break;

                case 6:
                    break;

                case 7:
                    break;

                case 8:
                    break;

                case 9:
                    break;

                case 0:
                    break;

                default:
                    System.out.println("\nOpção Inválida");
                    break;
            }

        } while (opcao != 0);
    }

    // ============================== MENU LOGIN ==============================
    public static void menuLogin(String[][] matrizClientes, String[][] matrizAnimais, String[][] matrizInteracoes) {

        Scanner input = new Scanner(System.in);

        int opcaoLogin;
        String username, password;

        do {

            System.out.println("\n\n-*-*-*-*-*- MENU LOGIN -*-*-*-*-*-");
            System.out.println("1. ADMIN");
            System.out.println("2. CLIENTE");
            System.out.println("0. SAIR");

            System.out.print("Tipo de Utilizador: ");
            opcaoLogin = input.nextInt();

            switch (opcaoLogin) {

                case 1:
                    System.out.print("\nUsername: ");
                    username = input.next();

                    System.out.print("Password: ");
                    password = input.next();

                    if (username.equals("admin") && password.equals("code")) {
                        menuAdmin(matrizClientes, matrizAnimais, matrizInteracoes);
                    } else {
                        System.out.println("Login incorreto");
                    }
                    break;

                case 2:
                    menuCliente(matrizClientes, matrizAnimais, matrizInteracoes);
                    break;

                case 0:
                    System.out.println("\nObrigado! Volte sempre...");
                    break;

                default:
                    System.out.println("\nOpção Inválida");
                    break;
            }

        } while (opcaoLogin != 0);
    }

    // ============================== MAIN ==============================
    public static void main(String[] args) throws FileNotFoundException {

        String caminhoAnimais = "files/animais.csv";
        String caminhoClientes = "files/clientes.csv";
        String caminhoInteracoes = "files/interacoes.csv";

        String[][] matrizAnimais = UtilCSV.ficheiroParaMatriz(caminhoAnimais);
        String[][] matrizClientes = UtilCSV.ficheiroParaMatriz(caminhoClientes);
        String[][] matrizInteracoes = UtilCSV.ficheiroParaMatriz(caminhoInteracoes);

        menuLogin(matrizClientes, matrizAnimais, matrizInteracoes);
    }
}

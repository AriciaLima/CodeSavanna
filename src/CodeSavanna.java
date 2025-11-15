import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class CodeSavanna {

    public static void menuCliente(String caminhoClientes, String caminhoAnimais, String caminhoInteracoes) throws FileNotFoundException {

        Scanner input = new Scanner(System.in);

        int opcao;

        do {

            System.out.println("\n\n-===== MENU CLIENTE CODE SAVANNA+ =====");
            System.out.println("1. Ver catalogo de animais por habitat");
            System.out.println("2. Ver atividades de um animal (espetáculos e alimentações)");
            System.out.println("3. Simular apadrinhamento de um animal");
            System.out.println("4. Jogo: adivinha a especial");
            System.out.println("0. Voltar");

            System.out.print("Opção: ");
            opcao = input.nextInt();

            switch (opcao) {

                case 1: // Ver catálogo de animais por habitat
                    catalogoAnimaisHabitat(caminhoAnimais);
                    break;

                case 2: // Ver atividades de um animal (espetáculos e alimentações
                    break;

                case 3: // Simular apadrinhamento de um animal
                    break;

                case 4: // Encontrar amigos de zoo
                    break;

                case 0: // Voltar
                    break;

                default:
                    System.out.println("\nOpção Inválida");
                    break;
            }

        } while (opcao != 0);

    }

    public static void catalogoAnimaisHabitat(String caminhoAnimais) throws FileNotFoundException {

        File arquivo = new File(caminhoAnimais);

        // 1) Primeira leitura: contar registos válidos
        Scanner scanner = new Scanner(arquivo);

        if (scanner.hasNextLine()) scanner.nextLine(); // ignora cabeçalho

        int total = 0;
        while (scanner.hasNextLine()) {
            String[] colunas = scanner.nextLine().split(";");
            if (colunas.length >= 4) total++;
        }
        scanner.close();

        // 2) Segunda leitura: carregar dados nos arrays
        scanner = new Scanner(arquivo);

        if (scanner.hasNextLine()) scanner.nextLine(); // ignora cabeçalho

        String[] nomes = new String[total];
        String[] especies = new String[total];
        String[] habitats = new String[total];

        int index = 0;

        while (scanner.hasNextLine() && index < total) {
            String[] colunas = scanner.nextLine().split(";");
            if (colunas.length >= 4) {
                nomes[index] = colunas[1].trim();
                especies[index] = colunas[2].trim();
                habitats[index] = colunas[3].trim();
                index++;
            }
        }
        scanner.close();

        // 3) Ordenar pelo habitat (bubble sort)
        for (int i = 0; i < total - 1; i++) {
            for (int j = 0; j < total - 1 - i; j++) {
                if (habitats[j].compareTo(habitats[j + 1]) > 0) {

                    // troca habitat
                    String tempHab = habitats[j];
                    habitats[j] = habitats[j + 1];
                    habitats[j + 1] = tempHab;

                    // mantém arrays sincronizados
                    String tempNome = nomes[j];
                    nomes[j] = nomes[j + 1];
                    nomes[j + 1] = tempNome;

                    String tempEsp = especies[j];
                    especies[j] = especies[j + 1];
                    especies[j + 1] = tempEsp;
                }
            }
        }

        // 4) Imprimir agrupado por habitat
        String habitatAtual = "";
        boolean primeiro = true;

        for (int i = 0; i < total; i++) {
            String habitat = habitats[i];

            if (primeiro || !habitat.equals(habitatAtual)) {
                habitatAtual = habitat;
                System.out.println("\n*** " + habitatAtual + " ***");
                primeiro = false;
            }

            System.out.println("- " + nomes[i] + " (" + especies[i] + ")");
        }
    }



    public static void menuAdmin(String caminhoClientes, String caminhoAnimais, String caminhoInteracoes) {
        Scanner input = new Scanner(System.in);

        int opcao;

        do {

            System.out.println("\n\n-*-*-*-*-*- MENU ADMIN CODESAVANNA-*-*-*-*-*-");
            System.out.println("1. Listar conteúdo dos ficheiro");
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

                case 1: // Listar conteúdo dos ficheiro
                    break;

                case 2: // Estatísticas gerais de interações
                    break;

                case 3: // Receita total por tipo de interação
                    break;

                case 4: // Animal mais popular
                    break;

                case 5: // Top 3 espécies com mais apadrinhamentos
                    break;
                case 6: // Listar padrinhos de um animal
                    break;
                case 7: // Espetáculo mais rentável
                    break;
                case 8: // Ranking de animais em perigo de extinção
                    break;
                case 9: // Estatísticas por habitat
                    break;
                case 0: // Voltar
                    break;

                default:
                    System.out.println("\nOpção Inválida");
                    break;
            }

        } while (opcao != 0);
    }

    public static void menuLogin(String caminhoClientes, String caminhoAnimais, String caminhoInteracoes) throws FileNotFoundException {

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

                case 1: // ADMIN

                    System.out.print("\nUsername: ");
                    username = input.next();

                    System.out.print("Password: ");
                    password = input.next();

                    if (username.equals("admin") && password.equals("code")) {
                        // Login válido
                        menuAdmin(caminhoClientes,caminhoAnimais, caminhoInteracoes);
                    } else {
                        System.out.println("Login incorreto");
                    }

                    break;

                case 2: // CLIENTE
                    menuCliente(caminhoClientes, caminhoAnimais, caminhoInteracoes);
                    break;

                case 0: // SAIR
                    System.out.println("\nObrigado! Volte sempre...");
                    break;

                default:
                    System.out.println("\nOpção Inválida");
                    break;
            }

        } while (opcaoLogin != 0);
    }


    public static void main(String[] args) throws FileNotFoundException {
        String caminhoAnimais = "files/animais.csv";
        String caminhoClientes = "files/clientes.csv";
        String caminhoInteracoes = "files/interacoes.csv";


        menuLogin(caminhoClientes, caminhoAnimais, caminhoInteracoes);

    }
}






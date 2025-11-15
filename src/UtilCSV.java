import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UtilCSV {

    // Conta quantas colunas existem olhando só a linha do cabeçalho
    public static int contarColunas(String caminho) throws FileNotFoundException {
        File ficheiro = new File(caminho);
        Scanner sc = new Scanner(ficheiro);

        String linha = sc.nextLine();          // cabeçalho
        String[] linhaSeparada = linha.split(";");

        return linhaSeparada.length;
    }

    // Converte um CSV para matriz (sem cabeçalho)
    public static String[][] ficheiroParaMatriz(String caminho) throws FileNotFoundException {

        int totalLinhas = contarLinhasFicheiro(caminho) - 1;  // remove cabeçalho
        int totalColunas = contarColunas(caminho);

        String[][] matriz = new String[totalLinhas][totalColunas];

        File ficheiro = new File(caminho);
        Scanner sc = new Scanner(ficheiro);

        sc.nextLine(); // ignora cabeçalho

        int linhaAtual = 0;

        while (sc.hasNextLine()) {
            String[] partes = sc.nextLine().split(";");

            // cópia direta para a matriz
            for (int c = 0; c < totalColunas; c++) {
                matriz[linhaAtual][c] = partes[c];
            }

            linhaAtual++;
        }

        return matriz;
    }

    // Conta linhas do CSV (inclusive cabeçalho)
    public static int contarLinhasFicheiro(String caminho) throws FileNotFoundException {
        File ficheiro = new File(caminho);
        Scanner sc = new Scanner(ficheiro);

        int contador = 0;
        while (sc.hasNextLine()) {
            sc.nextLine();
            contador++;
        }
        return contador;
    }
}
public class UtilTable {

    private static String padRight(String texto, int largura) {
        if (texto == null) texto = "";
        if (texto.length() >= largura) return texto;

        StringBuilder sb = new StringBuilder(texto);
        while (sb.length() < largura) sb.append(' ');
        return sb.toString();
    }

    public static void imprimirTabela(String titulo, String[] cabecalhos, String[][] dados, int colunasUsadas) {

        int cols = colunasUsadas;
        int[] larguras = new int[cols];

        // Define largura mínima baseada nos cabeçalhos
        for (int c = 0; c < cols; c++) {
            larguras[c] = cabecalhos[c].length();
        }

        // Ajusta pelas linhas reais
        for (int i = 0; i < dados.length; i++) {
            for (int c = 0; c < cols; c++) {
                if (dados[i][c] != null && dados[i][c].length() > larguras[c]) {
                    larguras[c] = dados[i][c].length();
                }
            }
        }

        // Cria linha separadora
        StringBuilder sep = new StringBuilder();
        sep.append("+");
        for (int c = 0; c < cols; c++) {
            for (int k = 0; k < larguras[c] + 2; k++) sep.append("-");
            sep.append("+");
        }

        System.out.println();
        System.out.println(sep);

        // Título centralizado
        if (titulo != null && !titulo.equals("")) {
            int total = 1;
            for (int c = 0; c < cols; c++) total += larguras[c] + 3;

            int left = (total - titulo.length()) / 2;
            String linhaTitulo = String.format("|%" + left + "s%s%" + (total - left - titulo.length() - 1) + "s|",
                    "", titulo, "");
            System.out.println(linhaTitulo);
            System.out.println(sep);
        }

        // Cabeçalhos
        StringBuilder cab = new StringBuilder("|");
        for (int c = 0; c < cols; c++) {
            cab.append(" ").append(padRight(cabecalhos[c], larguras[c])).append(" |");
        }
        System.out.println(cab);
        System.out.println(sep);

        // Linhas
        for (int i = 0; i < dados.length; i++) {
            StringBuilder lin = new StringBuilder("|");
            for (int c = 0; c < cols; c++) {
                lin.append(" ").append(padRight(dados[i][c], larguras[c])).append(" |");
            }
            System.out.println(lin);
        }

        System.out.println(sep);
    }
}

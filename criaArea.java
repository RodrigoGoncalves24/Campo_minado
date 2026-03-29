
/// Class responsible to creating the total area and mark the mina's positions
import java.util.ArrayList;

public class criaArea {
    private static int sizeLine = 0;
    private static int sizeColum = 0;
    private static int quantMinas = 0;
    private static boolean[][] campo;
    private static char caracter;
    private static int x = 0;
    private static int y = 0;

    /// function to creat the field
    public static void criarCampo(ArrayList<String> arquivo) {
        for (int i = 0; i < arquivo.size(); i++) {
            String linha = arquivo.get(i);

            lerLinhas(linha, 0, i);

            /// According to the line, define variables that will be defined
            if( i == 0){
                campo = new boolean[sizeLine][sizeColum];
                System.out.println("Linhas: "+sizeLine);
                System.out.println("Colunas: "+sizeColum);
                System.out.println("Quantidade de minas no terreno: "+quantMinas);
            }else{
                System.out.println("X: "+x+" Y: "+y);
                /// With the coordinate, mark the local where there is a bomb
                campo[x][y] = true;
            }

        }

        /// Call the method that will be calculated the total area without mina
        criarCampo.criaCampoRefugiados(campo, sizeLine, sizeColum);
    }


    /// Read the line and define variables
    public static void lerLinhas(String linha, int tamanho, int controle){
        for (int i = 0; i < linha.length(); i++) {
            caracter = linha.charAt(i);

            if(caracter == ' '){
                tamanho = 0;
                continue;
            }else if (caracter == '0') {
                tamanho *= 10;
            } else if (tamanho != 0) {
                tamanho = tamanho * 10 + Integer.parseInt(String.valueOf(caracter));
            } else {
                tamanho = Integer.parseInt(String.valueOf(caracter));
            }

            /// define how variables will be defined
            if(controle == 0){
                if (i < 3) sizeLine = tamanho;
                else if (i >= 4 && i < 7) sizeColum = tamanho;
                else if (i >= 8) quantMinas = tamanho;
            }else{
                if (i > 0 && i <= 2) x = tamanho;
                else if(i >= 4 && i <= 5) y = tamanho;
            }

        }

    }
}

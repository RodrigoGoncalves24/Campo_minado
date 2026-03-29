import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.Scanner;

public class LerAquivoTexto {
    private static ArrayList<String> arquivo = new ArrayList<>();
    private static int tamanhoLinhas = 0;
    private static int tamanhoColunas = 0;
    private static int quantidadeMinhas = 0;
    private static boolean[][] campo;
    private static char c;
    private static int tamanho = 0;
    private static int x = 0;
    private static int y = 0;

    public void LerAquivo() {

        /// Get path to read the case
        String currDir = Paths.get("src/casos").toAbsolutePath().toString();
        String nameComplete = currDir + "\\" + "caso01.txt";
        Path path = Paths.get(nameComplete);

        String linha;

        ///  Using scanner to read
        try (Scanner sc = new Scanner(Files.newBufferedReader(path, StandardCharsets.UTF_8))) {
            while (sc.hasNextLine()) {
                linha = sc.nextLine();
                arquivo.add(linha);
            }


        } catch (IOException x) {
            System.err.format("Erro de E/S: %s%n", x);
        }

        System.out.println(arquivo);
        LerAquivoTexto.criarCampo(arquivo);
    }


    /// function to creat the field
    private static void criarCampo(ArrayList<String> arquivo) {
        for (int i = 0; i < arquivo.size(); i++) {
            String linha = arquivo.get(i);
            /// Ensure that de first line is to decide the matrix size
            if (i == 0) {
                for (int j = 0; j < linha.length(); j++) {
                    c = linha.charAt(j);

                    /// define how to calculate the matrix size
                    if (c == ' ') {
                        tamanho = 0; /// make that do not enter the 3 condition after define a variable
                        continue;
                    } else if (c == '0') {
                        tamanho *= 10;
                    } else if (tamanho != 0) {
                        tamanho = tamanho * 10 + Integer.parseInt(String.valueOf(c));
                    } else {
                        tamanho = Integer.parseInt(String.valueOf(c));
                    }

                    /// define with variable will be defined
                    if (j < 3) tamanhoLinhas = tamanho;
                    else if (j >= 4 && j < 7) tamanhoColunas = tamanho;
                    else if (j >= 8) quantidadeMinhas = tamanho;
                }
                /// Ensure that the matrix is initialized with the correct size
                campo = new boolean[tamanhoLinhas][tamanhoColunas];
                System.out.println("Linhas: "+tamanhoLinhas);
                System.out.println("Colunas: "+tamanhoColunas);

            }else{
                tamanho = 0;
                for (int j = 0; j < linha.length(); j++) {
                    c = linha.charAt(j);

                    if(c == ' '){
                        tamanho = 0;
                        continue;
                    }else if (c == '0') {
                        tamanho *= 10;
                    } else if (tamanho != 0) {
                        tamanho = tamanho * 10 + Integer.parseInt(String.valueOf(c));
                    } else {
                        tamanho = Integer.parseInt(String.valueOf(c));
                    }

                    if (j > 0 && j <= 2) x = tamanho;
                    else if(j >= 4 && j <= 5) y = tamanho;
                }
                System.out.println("X: "+x+" Y: "+y);
                /// With the coordinate, mark the local where there is a bomb
                campo[x][y] = true;
                System.out.println(campo[x][y]);


            }







        }
    }
}



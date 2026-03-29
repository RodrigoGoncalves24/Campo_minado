import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.Scanner;

public class LerAquivoTexto {
    private static ArrayList<String> arquivo = new ArrayList<>();
    private static int sizeLine = 0;
    private static int sizeColum = 0;
    private static int quantMinas = 0;
    private static boolean[][] campo;
    private static char caracter;
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

            leLinhas(linha, 0, i);


            /// According to the line, define variables that will be defined
            if( i == 0){
                campo = new boolean[sizeLine][sizeColum];
                System.out.println("Linhas: "+sizeLine);
                System.out.println("Colunas: "+sizeColum);
            }else{
                System.out.println("X: "+x+" Y: "+y);

                /// With the coordinate, mark the local where there is a bomb
                campo[x][y] = true;
                System.out.println(campo[x][y]);
            }

            // Ensure that de first line is to decide the matrix size
//            if (i == 0) {
//                for (int j = 0; j < linha.length(); j++) {
//                    c = linha.charAt(j);
//
//                    /// define how to calculate the matrix size
//                    if (c == ' ') {
//                        tamanho = 0; /// make that do not enter the 3 condition after define a variable
//                        continue;
//                    } else if (c == '0') {
//                        tamanho *= 10;
//                    } else if (tamanho != 0) {
//                        tamanho = tamanho * 10 + Integer.parseInt(String.valueOf(c));
//                    } else {
//                        tamanho = Integer.parseInt(String.valueOf(c));
//                    }
//
//                    /// define with variable will be defined
//                    if (j < 3) tamanhoLinhas = tamanho;
//                    else if (j >= 4 && j < 7) tamanhoColunas = tamanho;
//                    else if (j >= 8) quantidadeMinas = tamanho;
//                }
//                /// Ensure that the matrix is initialized with the correct size
//                campo = new boolean[tamanhoLinhas][tamanhoColunas];
//                System.out.println("Linhas: "+tamanhoLinhas);
//                System.out.println("Colunas: "+tamanhoColunas);
//
//            }else{
//                tamanho = 0;
//                for (int j = 0; j < linha.length(); j++) {
//                    c = linha.charAt(j);
//
//                    if(c == ' '){
//                        tamanho = 0;
//                        continue;
//                    }else if (c == '0') {
//                        tamanho *= 10;
//                    } else if (tamanho != 0) {
//                        tamanho = tamanho * 10 + Integer.parseInt(String.valueOf(c));
//                    } else {
//                        tamanho = Integer.parseInt(String.valueOf(c));
//                    }
//
//                    if (j > 0 && j <= 2) x = tamanho;
//                    else if(j >= 4 && j <= 5) y = tamanho;
//                }
//                System.out.println("X: "+x+" Y: "+y);
//                /// With the coordinate, mark the local where there is a bomb
//                campo[x][y] = true;
//                System.out.println(campo[x][y]);
//
//
//            }


        }
    }

    /// Read the line and define variables
    public static void leLinhas(String linha, int tamanho, int controle){
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



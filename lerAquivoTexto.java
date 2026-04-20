
/// Class responsible to read the file
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.Scanner;

public class lerAquivoTexto {
    private static ArrayList<String> arquivo = new ArrayList<>();


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

        criaArea.criarCampo(arquivo);
    }




}



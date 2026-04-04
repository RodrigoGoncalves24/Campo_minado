import java.util.ArrayList;

///  Class responsible to creating refugee camp according the total area and de mina's position
/// Posso marcar como False as áreas visitadas que não possuem mina
public class criarCampo {
    private static boolean[][] campoRefugiados;
    private static int sizeLineCampoRefugiado = 0;
    private static int sizeColumCampoRefugiado = 0;
    private static boolean[][] campo;
    private static int totalSizeLine = 0;
    private static int totalSizeColum = 0;
    private static int coordX = 0;
    private static int coordY = 0;
    private static int initX = 0;
    private static int initY = 0;
    private static ArrayList tamanhoCampos = new ArrayList<>();


    /// Receive the minefield
    public static void criaCampoRefugiados(boolean[][] areaCriada, int sizeLine, int sizeColum) {
        campo = areaCriada;
        totalSizeLine = sizeLine;
        totalSizeColum = sizeColum;
        percorreCampoColunaIndo(0);

    }

    /// Go colum going
    private static void percorreCampoColunaIndo(int comecoX) {
        System.out.println(" - >");
        for (int i = comecoX; i < totalSizeLine; i++) {
            for (int j = 0; j < totalSizeColum; j++) {
                if (campo[i][j]) {
                    coordX = i;
                    coordY = j - 1;
                    initX = j - 1;
                    break;
                }
            }
            if (coordX != 0 && coordY != 0)
                break; // EVITA QUE, MESMO APÓS ENCONTRAR UMA MINA, ELE SIGA O LOOP. AO ENCONTRAR A PRIMEIRA, ELE PARA
        }
        System.out.println("DEBUG: ACHEI UMA MINA!! " + coordX + ", " + (coordY + 1) + "\nSalvei as posições...\nX: " + coordX + "\nY: " + coordY);
        System.out.println("DEBUG: Percorrendo linha em x: " + coordX + ", y: " + coordY);
        percorreCampoDescendoLinha(coordX + 1, coordY);
    }

    /// Go line going
    private static void percorreCampoDescendoLinha(int currentLine, int currentColum) {
        System.out.println(" v ");
        initY = currentLine -1;
        for (int i = currentLine; i < totalSizeLine; i++) { //Mais um para não identificar a mina atual
            if (campo[i][currentColum]) {
                coordX = i - 1;
                break;
            }
        }
        System.out.println("DEBUG: ACHIE OUTRA MINA!! " + (coordX + 1) + ", " + coordY + ". \nGuardei novas coordenadas: \nX: " + coordX + " \nY: " + coordY);
        System.out.println("DEBUG: Percorrendo linha em x: " + coordX + ", y: " + coordY);
        percorreCampoColunaVoltando(coordX, coordY-1);
    }

    /// Go back colum
    private static void percorreCampoColunaVoltando(int currentLine, int currentColum) {
        System.out.println("<-");
        for (int i = currentColum; i > 0; i--) {
            if (campo[currentLine][i]) {
                coordY = i - 1;
                if (initX > coordY) initX = coordY; // Se o tamanho de linha percorrida é menor que o anterior, atribuir o menor como sendo o número de linhas do novo campo
                break;
            }
        }
        System.out.println("DEBUG: ACHIE MAIS UMA MINA!! " + coordX + ", " + (coordY + 1) + "\n. Coordenadas novas: \nX: " + coordX + "\nY: " + coordY);
        System.out.println("DEBUG: Percorrendo linha em x: " + coordX + ", y: " + coordY);
        percorreLinhaSubindo(currentLine, coordY - 1);
    }

    /// Go back line
    private static void percorreLinhaSubindo(int currentLine, int currentColum) {
        System.out.println("^");
        boolean fecheiCampo = true;
        for (int i = currentLine; i > initY; i--) { /// Sobe até a linha onde a primeira mina foi encontrada
            System.out.println("x : "+i);
            if (campo[i][currentColum]) {
                /// achei uma mina no meio do caminho, logo, não consigo fechar meu retângulo (descarta retângulo).
                fecheiCampo = false;
                break;
                /// Recomeça de uma nova posição
            }
        }
        if (fecheiCampo) {
            campoRefugiados = new boolean[initX][initY];
            System.out.println("\nACHEI UM CAMPO VÁLIDO, PERCORRENDO ELE PARA VER SE É SEGURO!");
            percorrerCampoRefugiados();
            ///  define a matriz do campo que achei e começa a percorrer o campo até achar bomba
            ///  se achar, define outro campo
            /// caso não, guarda o tamanho total dele e proucura outro campo (objetivo: achar o MAIOR CAMPO LIVRE DE BOMBA)
        }else{
            percorreCampoColunaIndo(initY+1); /// seguir da proxima coluna a qual a bomba foi achada
        }


    }

    /// Go into the new camp to find a bomb
    private static void percorrerCampoRefugiados(){
        boolean ahMina = false;
        for (int i = 0; i < initX; i++) {
            for (int j = 0; j < initY; j++) {
                if(campoRefugiados[i][j]){
                    ahMina = true;
                    break; /// descarta campo e começa novamente da posição inicial + 1
                }
            }
            if (ahMina) break;
        }
        if (!ahMina){
            int totalCampo = initX + initY;
            tamanhoCampos.add(totalCampo); /// Adiciona o tamanho máximo desse campo e vai percorrer para achar outros possiveis campos
            System.out.println("\nNÃO ACHEI NENHUMA MINA, TAMANHO DO CAMPO SALVO: "+totalCampo);
        }

    }
}

///  Class responsible to creating refugee camp according the total area and de mina's position
/// Posso marcar como False as áreas visitadas que não possuem mina
public class criarCampo {
    private static int[][] campoRefugiados;
    private static int sizeLineCampoRefugiado = 0;
    private static int sizeColumCampoRefugiado = 0;
    private static boolean[][] campo;
    private static int totalSizeLine = 0;
    private static int totalSizeColum = 0;
    private static int initX = 0;
    private static int initY = 0;


    /// Objetivo: percorrer o campo, contando o tamanho das linhas e, ao achar um de minas, volta uma posição
    /// Volta a posição e começa a percorrer  para baixo. Repetir o mesmo passo de cima
    /// Cn

    public static void criaCampoRefugiados(boolean[][] areaCriada, int sizeLine, int sizeColum) {
        campo = areaCriada;
        totalSizeLine = sizeLine;
        totalSizeColum = sizeColum;
        percorreCampoColunaIndo();

    }

    /// Go colum going
    private static void percorreCampoColunaIndo() {
        for (int i = 0; i < totalSizeLine; i++) {
            for (int j = 0; j < totalSizeColum; j++) {
                if (!campo[i][j]) {
                    sizeColumCampoRefugiado++;
                } else {
                    initX = i;
                    initY = j;
                    percorreCampoDescendoLinha(i, j);
                    break;
                }
            }

        }
    }

    /// Go line going
    private static void percorreCampoDescendoLinha(int currentLine, int currentColum) {
        currentLine += 1; //ir para a linha de baixo da qual comecei
        currentColum -= 1; //Começa da coluna anterior a minha
        for (int i = currentLine; i > 0; i--) {
            if (!campo[i][currentColum]){
                sizeLineCampoRefugiado++;
            }else{
                percorreCampoColunaVoltando(i, currentColum);
                break;
            }

        }

    }

    /// Go back colum
    private static void percorreCampoColunaVoltando(int currentLine, int currentColum){
        currentLine -= 1; // Pegando linha anterior a bomba
        currentColum -= 1;
        for (int i = currentColum; i > 0 ; i--) {
            if(campo[currentLine][i]) {
                percorreLinhaSubindo(currentLine, currentColum);
                break;
            }

        }
    }

    /// Go back line
    private static void percorreLinhaSubindo(int currentLine, int currentColum){
        currentLine -= 1;
        currentColum -= 1;
        /// Sobe até encontrar a coordenada inicial
        for (int i = currentLine; i >0 ; i--) {
            if(!campo[i][currentColum]){
                if(i == initX && currentColum == initY){ // Ao chegar na mesma coordenada que começou, define o tamanho do retângulo achado
                    sizeLineCampoRefugiado = i;
                    sizeColumCampoRefugiado =  currentColum;
                    break;
                }
            }else{
                /// Não conseguiu fechar o retangulo e vai precisar voltar uma posição da coluna e subir
                currentColum += 1;
            }
        }

    }
}

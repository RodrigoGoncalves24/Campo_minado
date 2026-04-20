import java.lang.reflect.Array;
import java.util.ArrayList;

public class criaCampoDivConq {

    /// Ideia principal: Após matrizes menores criadas, deleta aquelas que possuem mina e guarda apenas aquelas que estão livre
    /// Depois, define quem é vizinha de quem pra formar a área maior. Forma área/áreas e define qual a maior

    private static boolean[][] campoMinado;
    private static ArrayList<int[]> coordMinas;
    private static int alturaCampo;
    private static int baseCampo;

    /// Receive minefield
    public static void criaCampoDivConq(boolean[][] campo, int baseCampo, int alturaCampo, ArrayList<int[]> minas) {
        campoMinado = campo;
        coordMinas = minas;
        criaCampoDivConq.alturaCampo = alturaCampo;
        criaCampoDivConq.baseCampo = baseCampo;

        System.out.println("Valores guardados:\nAltura: " + alturaCampo + "\nBase: " + baseCampo);

        divideCampo();
    }

    /// Return mina coordinate
    private static int[] pegarCoordenadasMinas(int index) {
        return coordMinas.get(index);
    }

    /// Divide the minefield in smaller minefield
    private static void divideCampo() {
        int[] mina = pegarCoordenadasMinas(0);

        int x = mina[0];
        int y = mina[1];


        int area1 = procuraArea(0, x - 1, 0, alturaCampo - 1); //esquerda
        int area2 = procuraArea(x + 1, baseCampo - 1, 0, alturaCampo - 1); // direita
        int area3 = procuraArea(0, baseCampo - 1, 0, y - 1); // cima
        int area4 = procuraArea(0, baseCampo - 1, y + 1, alturaCampo - 1); //baixo

        acharMaiorArea(area1, area2, area3, area4);

    }

    private static void acharMaiorArea(int area1, int area2, int area3, int area4) {
        int maiorArea = Math.max(Math.max(area1, area2), Math.max(area3, area4));

        System.out.println("A maior área é de: " + maiorArea);

    }


    /// Search in the area for the maximum area without mine
    private static int procuraArea(int initX, int finalX, int initY, int finalY) {

        for (int i = 0; i < coordMinas.size(); i++) {
            int[] mina = coordMinas.get(i);

            if ((mina[0] >= initX && mina[0] <= finalX) && (mina[1] >= initY && mina[1] <= finalY)) {
                int area1 = procuraArea(initX, finalX, initY, mina[1] - 1); //cima
                int area2 = procuraArea(initX, finalX, mina[1] + 1, finalY); //baixo
                int area3 = procuraArea(initX, mina[0]-1, initY, finalY ); //esquerda
                int area4 = procuraArea(mina[0]+1, finalX, initY, finalY); //direita

                return Math.max(Math.max(area1, area2), Math.max(area3, area4));
            }

        }

        return ((finalX - initX) + 1) * ((finalY - initY) + 1);
    }

}

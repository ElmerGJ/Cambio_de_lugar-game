
public class testWinGame {

    Fichas[] fichas = new Fichas[9];
    int[] winOrder = new int[9];

    testWinGame(){
        for (int i = 0; i < fichas.length; i++) {
            fichas[i] = new Fichas(null);
            fichas[i].setIndex(i + 1);
        }

        winOrder[0] = 6;
        winOrder[1] = 7;
        winOrder[2] = 8;
        winOrder[3] = 9;
        winOrder[4] = 5;
        winOrder[5] = 1;
        winOrder[6] = 2;
        winOrder[7] = 3;
        winOrder[8] = 4;


        for (Fichas ficha : fichas) {
            if (ficha.getIndex() < blankIndex()) {
                System.out.println("Menores");
            } else if (ficha.getIndex() > blankIndex()) {
                System.out.println("Mayores");
            } else {
                System.out.println("IGUAL");
            }
        }
    }


    public int blankIndex(){
        return fichas[4].getIndex();
    }


    public static void main(String[] args) {
        new testWinGame();
    }

}

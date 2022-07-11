import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;



class Fichas extends JLabel{
    int index;

    public Fichas(ImageIcon icon) {
        super(icon);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}


class Points extends Point{
    int index;

    public Points(int x, int y) {
        super(x, y);
    }

    public void setIndex(int index) {
        this.index = index;
    }
}


public class CambioDeLugar extends JFrame{
    JPanel panelFichas = new JPanel();
    JPanel panelControl = new JPanel();
    Fichas[] fichas = new Fichas[9];
    Fichas[] fichasDEF = new Fichas[9];
    Points[] points = new Points[9];

    public CambioDeLugar(){
        initComponents();
    }


    public void initComponents(){
        JButton[] buttons = new JButton[4];
        JButton restart = new JButton();
        int initX;
        JLabel label = new JLabel("J U E G O   D E   F I C H A S");


        setLayout(null);
        setResizable(false);
        setTitle("Cambio de Lugar");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //region P A N E L   D E   F I C H A S//////////////////////////////////////////////////////////////////////////
        panelFichas.setBackground(new Color(30, 80, 120));
        panelFichas.setLayout(null);

        //Bucle para establecer los puntos donde apareceran las fichas
        initX = 4;
        for (int i = 0; i < points.length; i++) {
            points[i] = new Points(initX, 106);
            points[i].setIndex(i + 1);
            initX += 88;
        }


        //Bucle para rellenar el arreglo de Labels con imagenes de fichas
//        int j = 1; //Contador para nombre de ficha.
        for (int i = 0; i < fichas.length; i++) {
            fichas[i] = new Fichas(new ImageIcon(Objects.requireNonNull(getClass().getResource(String.join("", "img/", String.valueOf(i), ".png")))));
            fichas[i].setSize(88, 88);
            fichas[i].setLocation(points[i]);
            fichas[i].setIndex(i + 1);
            if (fichas[i].getLocation().equals(points[4])){
                fichas[i].setName("Ficha_BLANK");
            }
            else {
                for (int j = i; j < (i + 1); j++) {
                    fichas[i].setName("Ficha_" + (j + 1));
                    if (!(fichas[i].getName().charAt(6) == '1')){
                        if (!(((int)fichas[i].getName().charAt(6) - (int)fichas[i - 1].getName().charAt(6)) == 1)){
                            fichas[i].setName("Ficha_" + (j));
                        }
                    }
                }
            }
            panelFichas.add(fichas[i]);
        }
        System.arraycopy(fichas, 0, fichasDEF, 0, fichasDEF.length);

        label.setBounds(150 ,5, 500, 50);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Calibri", Font.PLAIN, 40));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panelFichas.add(label);
        add(panelFichas);

        restart.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("img/restart.png"))));
        restart.setBounds(750, 20, 30, 30);
        restart.addActionListener(e -> restarGame());
        panelFichas.add(restart);

        panelFichas.setBounds(0, 0, 800, 300);
        //endregion--------------------------------------------------------------------------------------------------



        //region P A N E L   D E   C O N T R O L E S ////////////////////////////////////////////////////////////////////
        panelControl.setBackground(Color.DARK_GRAY);
        panelControl.setLayout(null);

        //Bucle para generar los botones con sus respectivos iconos
        initX = 20;
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(String.join("", "img/btn", String.valueOf(i), ".png")))));
            buttons[i].setBounds(initX, 19, 175, 112);
            panelControl.add(buttons[i]);
            initX +=195;
        }


        add(panelControl);

        buttons[0].addActionListener(e -> moveFicha(-2));
        buttons[1].addActionListener(e -> moveFicha(-1));
        buttons[2].addActionListener(e -> moveFicha(1));
        buttons[3].addActionListener(e -> moveFicha(2));

        addKeyListener(keysListener());
        setFocusable(true);

        panelControl.setBounds(0, 300, 800, 150);
        //endregion--------------------------------------------------------------------------------------------------

        setSize(new Dimension(816, 488));
        setLocationRelativeTo(null);
    }


    private int getFichaBLANK(){
        int blank = 0, i = 0;
        while (i < fichas.length) {
            if (fichas[i].getName().equals("Ficha_BLANK")) {
                blank = i;
                break;
            }
            i++;
        }
        return blank;
    }



    /**
     Las fichas cambian de posición de acuerdo con los movimientos que hagamos, las posiciones son puntos (x, y) que se
     almacenan en un arreglo.
        1. Se crea una instancia temporal de Fichas
        2. Se establece la posición de la ficha en blanco según el movimiento que hagamos (L or R, 1 or 2 steps).
        3. Se establece la posición de la ficha que hemos intercambiado con la ficha en blanco, según el índice de la
            ficha en blanco.
        4. Se establece la

     */
    private void moveFicha(int espacios) {
        int blank = getFichaBLANK();
        try {
            Fichas saved;
            fichas[blank].setLocation(points[blank + espacios]); // 2
            fichas[blank + espacios].setLocation(points[blank]); // 3

            for (Fichas t : fichas){
                System.out.println("\n"+ t.getName());
            }

            saved = fichas[blank]; // 4
            fichas[blank] = fichas[blank + espacios]; // 5

            Object x = new Object();
            Object y = new Object();
            Object tempx;

            tempx = y;
            y = x;
            System.out.println("\n\nTEST...");
            System.out.println("X: " + x.toString());
            System.out.println("Y: " + y.toString());
            System.out.println("Y: " + tempx.toString());


            x = tempx;
            System.out.println("\n\nTEST...");
            System.out.println("X: " + x.toString());
            System.out.println("Y: " + y.toString());
            System.out.println("Y: " + tempx.toString());



            System.out.println("\n\nFicha Blank Name: " + fichas[blank].getName() + "\t\tDir:" + fichas[blank].hashCode());
            System.out.println("\n\nFicha Saved Name: " + saved.getName()  + "\t\tDir:" + saved.hashCode());

            System.out.println("\n\nTEST...");
            System.out.println("X: " + x.toString());
            System.out.println("Y: " + y.toString());

            fichas[blank + espacios] = saved; // 6

            System.out.println("\n\n");
            for (Fichas t : fichas){
                System.out.println("\n"+ t.getName());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            final Runnable runnable =
                (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
            if (runnable != null) runnable.run();
        }
        finally {
            requestFocusInWindow();
        }
    }


    private KeyListener keysListener(){
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_LEFT -> moveFicha(-1);
                    case KeyEvent.VK_RIGHT -> moveFicha(1);
                    case KeyEvent.VK_A -> moveFicha(-2);
                    case KeyEvent.VK_D -> moveFicha(2);
                }
                if (winGame()){
                    JOptionPane.showMessageDialog(null, "FELICIDADES HAZ GANADO", "Juego Terminado", JOptionPane.INFORMATION_MESSAGE);
                    restarGame();
                }
            }
        };
    }


    private void restarGame(){
        for (int i = 0; i < fichas.length; i++) {
            fichas[i] = fichasDEF[i];
            fichas[i].setLocation(points[i]);
        }
        requestFocusInWindow();
    }

    private boolean winGame(){
        int[] winOrder = new int[9];
        winOrder[0] = 6;
        winOrder[1] = 7;
        winOrder[2] = 8;
        winOrder[3] = 9;
        winOrder[4] = 5;
        winOrder[5] = 1;
        winOrder[6] = 2;
        winOrder[7] = 3;
        winOrder[8] = 4;

        for (int i = 0; i < fichas.length; i++) {
            if (!(fichas[i].getIndex() == winOrder[i])){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        new CambioDeLugar().setVisible(true);
    }
}




















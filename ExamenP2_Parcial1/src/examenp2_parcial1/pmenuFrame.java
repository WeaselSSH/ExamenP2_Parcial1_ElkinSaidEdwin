package examenp2_parcial1;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class pmenuFrame extends FrontEnd {
     public static PantallaDeCarga pantallaDeCargaInstancia;
     
    private final SistemaWonderland sistemaWonderland = new SistemaWonderland();

    private final JLabel etiquetaTitulo = new JLabel("Wonderland");
    private final JButton btnAnadir = new JButton("A�adir");
    private final JButton btnRentar = new JButton("Rentar");
    private final JButton btnCerrar = new JButton("Cerrar");
    private final String imagenFondo = "examenp2_parcial1/mainBackground.jpg";

    public pmenuFrame() {
        FrameConFondo(this, cargarFondo(imagenFondo));
        titulo1(etiquetaTitulo);
        JButton[] botones = {btnRentar, btnAnadir, btnCerrar};
        layoutBtn(botones);
        acciones();
        transicionSuave.fadeIn(this);
    }

    public void acciones() {
        btnCerrar.addActionListener(e -> System.exit(0));

        btnAnadir.addActionListener(e -> {
            String[] opciones = {"Pel�cula", "Juego"};
            int eleccion = JOptionPane.showOptionDialog(
                    this, 
                    "�Qu� tipo de �tem deseas a�adir?", 
                    "Seleccionar Tipo de �tem", 
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,     
                    opciones,   
                    opciones[0]
            );

            if (eleccion == 0) {
                transicionSuave.fadeOut(this, () -> new addMovieF(sistemaWonderland));
            } else if (eleccion == 1) {
                transicionSuave.fadeOut(this, () -> new addGameF(sistemaWonderland));
            }
        });

        btnRentar.addActionListener(e -> {
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            pantallaDeCargaInstancia = new PantallaDeCarga();
            pantallaDeCargaInstancia.setVisible(true);
            new pmenuFrame();
        });
    }
}

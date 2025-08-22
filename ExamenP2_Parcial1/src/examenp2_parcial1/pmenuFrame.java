package examenp2_parcial1;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class pmenuFrame extends FrontEnd {
    public static PantallaDeCarga pantallaDeCargaInstancia;
    private final SistemaWonderland sistemaWonderland = new SistemaWonderland();

    private final JLabel etiquetaTitulo = new JLabel("Wonderland");
    private final JButton btnAnadir = new JButton("Añadir Ítem");
    private final JButton btnRentar = new JButton("Rentar");
    private final JButton btnReportes = new JButton("Reportes");
    private final JButton btnCerrar = new JButton("Cerrar");
    private final String imagenFondo = "examenp2_parcial1/imagenes/main.jpeg";

    public pmenuFrame() {
        PantallaDeCarga pantalla  = new PantallaDeCarga();
        pantalla.setVisible(true);
        FrameConFondo(this, cargarFondo(imagenFondo));
        titulo1(etiquetaTitulo);
        JButton[] botones = {btnAnadir, btnRentar, btnReportes, btnCerrar};
        layoutBtn(botones);
        acciones();
        transicionSuave.fadeIn(this);
    }

    public void acciones() {
        btnCerrar.addActionListener(e -> System.exit(0));

        btnAnadir.addActionListener(e -> {
            String[] opciones = {"Película", "Juego"};
            int eleccion = JOptionPane.showOptionDialog(this, "¿Qué tipo de ítem deseas gestionar?", "Menú Añadir", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

            if (eleccion == 0) {
                transicionSuave.fadeOut(this, () -> new addMovieF(sistemaWonderland));
            } else if (eleccion == 1) {
                transicionSuave.fadeOut(this, () -> new addGameF(sistemaWonderland));
            }
        });

        btnRentar.addActionListener(e -> {
            transicionSuave.fadeOut(this, () -> new rentarFrame(sistemaWonderland));
        });
        
        btnReportes.addActionListener(e -> {
             transicionSuave.fadeOut(this, () -> new ReportesF(sistemaWonderland));
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
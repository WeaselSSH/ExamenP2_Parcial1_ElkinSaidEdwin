package examenp2_parcial1;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class pmenuFrame extends FrontEnd {
    public static PantallaDeCarga pantallaDeCargaInstancia;
    private static final SistemaWonderland sistemaWonderland = new SistemaWonderland();

    private final JLabel etiquetaTitulo = new JLabel("Wonderland");
    private final JButton btnAnadir    = new JButton("Añadir ítem");
    private final JButton btnRentar    = new JButton("Rentar");
    private final JButton btnReportes  = new JButton("Mostrar todo");
    private final JButton btnSubmenu   = new JButton("Ejecutar Submenu"); 
    private final JButton btnCerrar    = new JButton("Cerrar");
    private final String imagenFondo   = "examenp2_parcial1/imagenes/main.jpeg";

    public pmenuFrame() {
        FrameConFondo(this, cargarFondo(imagenFondo));
        titulo1(etiquetaTitulo);

        JButton[] botones = { btnAnadir, btnRentar, btnSubmenu, btnReportes, btnCerrar };
        layoutBtn(botones);

        acciones();
        transicionSuave.fadeIn(this);
    }

    public void acciones() {
        btnCerrar.addActionListener(e -> System.exit(0));

        btnAnadir.addActionListener(e -> {
            String[] opciones = { "Pelicula", "Juego" };
            int eleccion = JOptionPane.showOptionDialog(
                    this,
                    "Que deseas agregar?",
                    "Anadir item",
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

        btnRentar.addActionListener(e ->
            transicionSuave.fadeOut(this, () -> new rentarFrame(sistemaWonderland))
        );

        
        btnSubmenu.addActionListener(e -> {
            try {
                String sCodigo = JOptionPane.showInputDialog(
                        this,
                        "Codigo del juego para ejecutar el submenu:"
                );
                if (sCodigo == null) return;
                int codigo = Integer.parseInt(sCodigo.trim());

                RentItem item = sistemaWonderland.buscarItem(codigo);
                if (item == null) {
                    JOptionPane.showMessageDialog(this, "No existe un item con codigo " + codigo,
                            "No encontrado", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (!(item instanceof Game)) {
                    JOptionPane.showMessageDialog(this, "El codigo " + codigo + " no corresponde a un Juego",
                            "Tipo incorrecto", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                ((Game) item).submenu();

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Formato de codigo invalido.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al ejecutar el submenu: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnReportes.addActionListener(e ->
            transicionSuave.fadeOut(this, () -> new ReportesF(sistemaWonderland))
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            pantallaDeCargaInstancia = new PantallaDeCarga();
            pantallaDeCargaInstancia.setVisible(true);
            new pmenuFrame();
        });
    }
}
package examenp2_parcial1;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class pmenuFrame extends FrontEnd {
    public static PantallaDeCarga pantallaDeCargaInstancia;
    private final SistemaWonderland sistemaWonderland = new SistemaWonderland();

    private final JLabel etiquetaTitulo = new JLabel("Wonderland");
    private final JButton btnAnadir = new JButton("Añadir");
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
            String[] opciones = {"Película", "Juego"};
            int eleccion = JOptionPane.showOptionDialog(this, "¿Qué tipo de ítem deseas gestionar?", "Menú Añadir", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

            if (eleccion == 0) {
                transicionSuave.fadeOut(this, () -> new addMovieF(sistemaWonderland));
            } else if (eleccion == 1) {
                mostrarSubmenuJuego();
            }
        });

        btnRentar.addActionListener(e -> {});
    }

    private void mostrarSubmenuJuego() {
        String[] opcionesJuego = {"Crear Juego Nuevo", "Añadir Especificaciones a un Juego"};
        int eleccionJuego = JOptionPane.showOptionDialog(this, "¿Qué deseas hacer?", "Opciones de Juego", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcionesJuego, opcionesJuego[0]);

        if (eleccionJuego == 0) {
            transicionSuave.fadeOut(this, () -> new addGameF(sistemaWonderland));
        } else if (eleccionJuego == 1) {
            String codigoStr = JOptionPane.showInputDialog(this, "Ingresa el código del juego a modificar:", "Buscar Juego", JOptionPane.QUESTION_MESSAGE);
            try {
                if (codigoStr == null) return; 
                int codigo = Integer.parseInt(codigoStr);
                Game juegoExistente = sistemaWonderland.buscarJuego(codigo);

                if (juegoExistente != null) {
                    while (true) {
                        String spec = JOptionPane.showInputDialog(this, "Añade una especificación para \"" + juegoExistente.getNombreItem() + "\"\n(Deja vacío o cancela para terminar):");
                        if (spec == null || spec.trim().isEmpty()) {
                            break;
                        }
                        juegoExistente.especificacionesTecnicas.add(spec);
                    }
                    JOptionPane.showMessageDialog(this, "Especificaciones añadidas correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró ningún juego con el código " + codigo, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa un código numérico válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            pantallaDeCargaInstancia = new PantallaDeCarga();
            pantallaDeCargaInstancia.setVisible(true);
            new pmenuFrame();
        });
    }
}
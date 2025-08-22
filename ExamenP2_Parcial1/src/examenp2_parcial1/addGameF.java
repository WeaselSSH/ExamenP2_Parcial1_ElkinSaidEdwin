package examenp2_parcial1;

import com.toedter.calendar.JDateChooser;
import java.io.File;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class addGameF extends FrontEnd {

    private final SistemaWonderland sistemaWonderland;

    private final JTextField campoCodigo = new JTextField();
    private final JTextField campoNombre = new JTextField();
    private final JDateChooser selectorFecha = new JDateChooser();
    private final JTextField campoRutaImagen = new JTextField();
    private final JButton btnSeleccionarImagen = new JButton("Seleccionar...");
    private final JButton btnGuardar = new JButton("Guardar");
    private final JButton btnVolver = new JButton("Volver");

    public addGameF(SistemaWonderland sistema) {
        this.sistemaWonderland = sistema;
        
        FrameConFondo(this, cargarFondo("examenp2_parcial1/mainBackground.jpg"));
        titulo1(new JLabel("A�adir Nuevo Juego"));
        
        JPanel panelImagen = new JPanel();
        panelImagen.setOpaque(false);
        campoRutaImagen.setEditable(false);
        panelImagen.add(campoRutaImagen);
        panelImagen.add(btnSeleccionarImagen);

        String[] etiquetas = {"C�digo:", "Nombre:", "Fecha de Publicaci�n:", "Ruta Imagen:"};
        JComponent[] componentes = {campoCodigo, campoNombre, selectorFecha, panelImagen};
        JButton[] botones = {btnGuardar, btnVolver};
        
        crearPanelFormularioCentrado(new JPanel(), etiquetas, componentes, botones);

        acciones();
        transicionSuave.fadeIn(this);
    }

    private void acciones() {
        btnVolver.addActionListener(e -> transicionSuave.fadeOut(this, pmenuFrame::new));

        btnSeleccionarImagen.addActionListener(e -> {
            JFileChooser selectorArchivo = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Im�genes", "jpg", "png", "gif", "jpeg");
            selectorArchivo.setFileFilter(filtro);
            if (selectorArchivo.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                campoRutaImagen.setText(selectorArchivo.getSelectedFile().getAbsolutePath());
            }
        });

        btnGuardar.addActionListener(e -> {
            try {
                int codigo = Integer.parseInt(campoCodigo.getText());
                String nombre = campoNombre.getText();
                Calendar fecha = selectorFecha.getCalendar();
                String rutaImagen = campoRutaImagen.getText();

                if (nombre.trim().isEmpty() || fecha == null || rutaImagen.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor, llene todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Game nuevoJuego = new Game(codigo, nombre, rutaImagen);
                nuevoJuego.setfechaPublicacion(fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH) + 1, fecha.get(Calendar.DAY_OF_MONTH));
                
                sistemaWonderland.agregarJuego(nuevoJuego);
                
                JOptionPane.showMessageDialog(this, "�Juego guardado con �xito!", "�xito", JOptionPane.INFORMATION_MESSAGE);
                
                int opcionSpecs = JOptionPane.showConfirmDialog(this, "�Deseas a�adir especificaciones t�cnicas ahora?", "Especificaciones T�cnicas", JOptionPane.YES_NO_OPTION);
                
                if (opcionSpecs == JOptionPane.YES_OPTION) {
                    while (true) {
                        String spec = JOptionPane.showInputDialog(this, "A�ade una especificaci�n (o cancela para terminar):");
                        if (spec == null || spec.trim().isEmpty()) {
                            break;
                        }
                        nuevoJuego.especificacionesTecnicas.add(spec);
                    }
                }
                
                campoCodigo.setText("");
                campoNombre.setText("");
                selectorFecha.setCalendar(null);
                campoRutaImagen.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El c�digo debe ser un n�mero v�lido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
package examenp2_parcial1;

import com.toedter.calendar.JDateChooser;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class addMovieF extends FrontEnd {

    private final SistemaWonderland sistemaWonderland;
    
    private final JTextField campoCodigo = new JTextField();
    private final JTextField campoNombre = new JTextField();
    private final JTextField campoPrecio = new JTextField();
    private final JDateChooser selectorFecha = new JDateChooser();
    private final JButton btnGuardar = new JButton("Guardar");
    private final JButton btnVolver = new JButton("Volver");

    // CONSTRUCTOR CORREGIDO: Acepta el sistema como parámetro
    public addMovieF(SistemaWonderland sistema) {
        this.sistemaWonderland = sistema; // Guardamos la referencia al sistema
        
        FrameConFondo(this, cargarFondo("examenp2_parcial1/mainBackground.jpg"));
        titulo1(new JLabel("Añadir Nueva Película"));
        
        // El resto del código para construir la ventana...
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridy = 0;

        crearCampoFormulario(panel, gbc, "Código:", campoCodigo);
        crearCampoFormulario(panel, gbc, "Nombre:", campoNombre);
        crearCampoFormulario(panel, gbc, "Precio de Renta:", campoPrecio);
        crearCampoFormulario(panel, gbc, "Fecha de Estreno:", selectorFecha);

        JButton[] botones = {btnGuardar, btnVolver};
        layoutBtn(botones);
        
        GridBagConstraints frameGbc = new GridBagConstraints();
        frameGbc.gridy = 1;
        frameGbc.insets = new Insets(0, 0, 120, 0);
        getContentPane().add(panel, frameGbc);

        acciones();
        transicionSuave.fadeIn(this);
    }

    private void acciones() {
        btnVolver.addActionListener(e -> transicionSuave.fadeOut(this, pmenuFrame::new));

        btnGuardar.addActionListener(e -> {
            try {
                int codigo = Integer.parseInt(campoCodigo.getText());
                String nombre = campoNombre.getText();
                double precio = Double.parseDouble(campoPrecio.getText());
                Calendar fecha = selectorFecha.getCalendar();

                if (nombre.trim().isEmpty() || fecha == null) {
                    JOptionPane.showMessageDialog(this, "Por favor, llene todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Movie nuevaPelicula = new Movie(codigo, nombre, precio, null);
                nuevaPelicula.setFechaEstreno(fecha);
                
                // Usamos el método de SistemaWonderland para añadir la película
                sistemaWonderland.agregarPelicula(nuevaPelicula);
                
                JOptionPane.showMessageDialog(this, "¡Película guardada con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
                campoCodigo.setText("");
                campoNombre.setText("");
                campoPrecio.setText("");
                selectorFecha.setCalendar(null);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Código y Precio deben ser números válidos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
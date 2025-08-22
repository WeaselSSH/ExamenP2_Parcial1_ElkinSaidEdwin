package examenp2_parcial1;

import com.toedter.calendar.JDateChooser;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class addMovieF extends FrontEnd {

    private final SistemaWonderland sistemaWonderland;
    
    private final JTextField campoCodigo = new JTextField();
    private final JTextField campoNombre = new JTextField();
    private final JTextField campoPrecio = new JTextField();
    private final JDateChooser selectorFecha = new JDateChooser();
    private final JTextField campoRutaImagen = new JTextField();
    private final JButton btnSeleccionarImagen = new JButton("...");
    private final JButton btnGuardar = new JButton("Guardar");
    private final JButton btnVolver = new JButton("Volver");

    public addMovieF(SistemaWonderland sistema) {
        this.sistemaWonderland = sistema;
        
        FrameConFondo(this, cargarFondo("examenp2_parcial1/imagenes/main.jpeg"));
        titulo1(new JLabel("Añadir Nueva Película"));
        
        selectorFecha.setDate(new Date());

        JPanel panelImagen = new JPanel();
        campoRutaImagen.setEditable(false);
        panelImagen.add(campoRutaImagen);
        panelImagen.add(btnSeleccionarImagen);
        
        String[] etiquetas = {"Código:", "Nombre:", "Precio de Renta:", "Fecha de Estreno:", "Imagen:"};
        JComponent[] componentes = {campoCodigo, campoNombre, campoPrecio, selectorFecha, panelImagen};
        JButton[] botones = {btnGuardar, btnVolver};
        
        crearPanelFormularioCentrado(new JPanel(), etiquetas, componentes, botones);
        
        acciones();
        transicionSuave.fadeIn(this);
    }

    private void acciones() {
        btnVolver.addActionListener(e -> transicionSuave.fadeOut(this, pmenuFrame::new));

        btnSeleccionarImagen.addActionListener(e -> {
            JFileChooser selectorArchivo = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imágenes", "jpg", "png", "gif", "jpeg");
            selectorArchivo.setFileFilter(filtro);
            if (selectorArchivo.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File archivo = selectorArchivo.getSelectedFile();
                campoRutaImagen.setText(archivo.getAbsolutePath());
            }
        });

        btnGuardar.addActionListener(e -> {
            try {
                int codigo = Integer.parseInt(campoCodigo.getText());
                String nombre = campoNombre.getText();
                double precio = Double.parseDouble(campoPrecio.getText());
                Calendar fecha = selectorFecha.getCalendar();
                String rutaImagen = campoRutaImagen.getText();

                if (nombre.trim().isEmpty() || fecha == null || rutaImagen.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor, llene todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (sistemaWonderland.codigoExiste(codigo)) {
                    JOptionPane.showMessageDialog(this, "El código " + codigo + " ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Movie nuevaPelicula = new Movie(codigo, nombre, precio, rutaImagen, fecha);
                sistemaWonderland.agregarItem(nuevaPelicula);
                
                campoCodigo.setText("");
                campoNombre.setText("");
                campoPrecio.setText("");
                selectorFecha.setDate(new Date());
                campoRutaImagen.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Código y Precio deben ser números válidos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
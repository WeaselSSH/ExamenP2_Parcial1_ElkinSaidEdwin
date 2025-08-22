package examenp2_parcial1;

import com.toedter.calendar.JDateChooser;
import static examenp2_parcial1.FrontEnd.BORDER_COLOR;
import static examenp2_parcial1.FrontEnd.DETALLE_AZUL;
import static examenp2_parcial1.FrontEnd.FONDO_SECUNDARIO;
import static examenp2_parcial1.FrontEnd.FONT_CAMPO;
import static examenp2_parcial1.FrontEnd.FONT_ETIQUETA;
import static examenp2_parcial1.FrontEnd.TEXTO_BLANCO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class addMovieF extends FrontEnd {

    private final SistemaWonderland sistemaWonderland;
    private final JTextField campoCodigo = new JTextField(20);
    private final JTextField campoNombre = new JTextField(20);
    private final JTextField campoPrecio = new JTextField(20);
    private final JTextField campoCopias = new JTextField(20);

  
    private final JTextField campoRuta   = new JTextField(20);

    private final JDateChooser dcEstreno = new JDateChooser();
    private final JButton btnBuscarImagen = new JButton("Buscar imagen");
    private final JButton btnGuardar = new JButton("Guardar");
    private final JButton btnLimpiar = new JButton("Limpiar");
    private final JButton btnVolver  = new JButton("Volver");

    private final JLabel preview = new JLabel();
    private String rutaImagenSeleccionada = null;

    public addMovieF(SistemaWonderland sistema) {
        this.sistemaWonderland = sistema;

        FrameConFondo(this, cargarFondo("examenp2_parcial1/imagenes/main.jpeg"));
        JLabel titulo = new JLabel("Anadir Nueva Pelicula");
        titulo1(titulo);
        campoRuta.setEditable(false);

        JPanel panelContenido = new JPanel(new BorderLayout());
        panelContenido.setOpaque(false);

       
        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setOpaque(false);
        GridBagConstraints gbcCentral = new GridBagConstraints();
        gbcCentral.insets = new Insets(20, 20, 20, 20);
        gbcCentral.anchor = GridBagConstraints.CENTER;

      
        JPanel panelFormulario = crearPanelFormulario();
        gbcCentral.gridx = 0;
        panelCentral.add(panelFormulario, gbcCentral);

        
        JPanel panelPreview = crearPanelPreview();
        gbcCentral.gridx = 1;
        panelCentral.add(panelPreview, gbcCentral);

     
        JPanel barraInferior = new JPanel();
        barraInferior.setOpaque(false);
        barraInferior.setBorder(new EmptyBorder(10, 0, 20, 0));
        disBoton(btnVolver);
        barraInferior.add(btnVolver);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(titulo, BorderLayout.NORTH);
        getContentPane().add(panelCentral, BorderLayout.CENTER);
        getContentPane().add(barraInferior, BorderLayout.SOUTH);

        acciones();
        transicionSuave.fadeIn(this);
    }

    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(true);
        panel.setBackground(new Color(45, 45, 58, 220));
        panel.setBorder(BorderFactory.createLineBorder(DETALLE_AZUL, 2));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 0; agregarCampo(panel, gbc, "Codigo:", campoCodigo);
        gbc.gridy = 1; agregarCampo(panel, gbc, "Nombre:", campoNombre);
        gbc.gridy = 2; agregarCampo(panel, gbc, "Precio de Renta:", campoPrecio);
        gbc.gridy = 3; agregarCampo(panel, gbc, "Copias Disponibles:", campoCopias);
        
        gbc.gridy = 4; agregarCampo(panel, gbc, "Buscar Imagen:", filaSoloBotonImagen(btnBuscarImagen));
        gbc.gridy = 5; configurarDateChooser(dcEstreno); agregarCampo(panel, gbc, "Fecha de Estreno:", dcEstreno);

        gbc.gridy = 6; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        JPanel panelBotonesForm = new JPanel();
        panelBotonesForm.setOpaque(false);
        disBoton(btnGuardar);
        disBoton(btnLimpiar);
        panelBotonesForm.add(btnGuardar);
        panelBotonesForm.add(btnLimpiar);
        panel.add(panelBotonesForm, gbc);

        return panel;
    }

    private JPanel crearPanelPreview() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setOpaque(false);

        JLabel lblPrev = new JLabel("Vista previa");
        lblPrev.setFont(FONT_ETIQUETA);
        lblPrev.setForeground(TEXTO_BLANCO);
        lblPrev.setHorizontalAlignment(JLabel.CENTER);

        preview.setHorizontalAlignment(JLabel.CENTER);
        preview.setPreferredSize(new Dimension(300, 420));
        preview.setBorder(BorderFactory.createDashedBorder(DETALLE_AZUL, 5, 5));

        panel.add(lblPrev, BorderLayout.NORTH);
        panel.add(preview, BorderLayout.CENTER);
        return panel;
    }

    private JPanel filaSoloBotonImagen(JButton boton) {
        disBotonInline(boton);
        JPanel p = new JPanel();
        p.setOpaque(false);
        p.add(boton);
        return p;
    }

    private void agregarCampo(JPanel panel, GridBagConstraints gbc, String etiqueta, JComponent componente) {
        JLabel label = new JLabel(etiqueta);
        label.setFont(FONT_ETIQUETA);
        label.setForeground(TEXTO_BLANCO);

        if (componente instanceof JTextField) {
            JTextField textField = (JTextField) componente;
            textField.setFont(FONT_CAMPO);
            textField.setPreferredSize(new Dimension(350, 40));
            textField.setCaretColor(TEXTO_BLANCO);
            textField.setBackground(FONDO_SECUNDARIO);
            textField.setForeground(TEXTO_BLANCO);
            textField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(BORDER_COLOR, 1),
                    new EmptyBorder(5, 10, 5, 10)
            ));
        }

        gbc.gridx = 0; gbc.anchor = GridBagConstraints.EAST; panel.add(label, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST; panel.add(componente, gbc);
    }

    
    private void estilizarDateChooser(JDateChooser chooser) {
        JComponent comp = (JComponent) chooser.getDateEditor().getUiComponent();
        comp.setFont(FONT_CAMPO);
        comp.setForeground(TEXTO_BLANCO);
        comp.setBackground(FONDO_SECUNDARIO);
        comp.setOpaque(true);
        comp.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1),
                new EmptyBorder(5, 10, 5, 10)
        ));
        if (comp instanceof JTextField) {
            JTextField tf = (JTextField) comp;
            tf.setCaretColor(TEXTO_BLANCO);
            tf.setDisabledTextColor(TEXTO_BLANCO);
            tf.setSelectionColor(DETALLE_AZUL);
            tf.setSelectedTextColor(Color.WHITE);
        }
    }

    private void configurarDateChooser(JDateChooser dateChooser) {
        dateChooser.setDate(new Date());
        dateChooser.setDateFormatString("yyyy/MM/dd");
        dateChooser.setPreferredSize(new Dimension(350, 40));
        dateChooser.setFont(FONT_CAMPO);

        
        estilizarDateChooser(dateChooser);
        dateChooser.getDateEditor().addPropertyChangeListener("date",
                evt -> estilizarDateChooser(dateChooser));
        dateChooser.addPropertyChangeListener("enabled",
                evt -> estilizarDateChooser(dateChooser));
    }

    private void acciones() {
        btnGuardar.addActionListener(e -> guardarPelicula());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnVolver.addActionListener(e -> transicionSuave.fadeOut(this, pmenuFrame::new));
        btnBuscarImagen.addActionListener(e -> seleccionarImagen());
    }

    private void seleccionarImagen() {
        JFileChooser ch = new JFileChooser();
        ch.setDialogTitle("Selecciona una imagen");
        ch.setFileFilter(new FileNameExtensionFilter("Imagenes", "jpg", "jpeg", "png", "gif"));
        if (ch.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = ch.getSelectedFile();
            rutaImagenSeleccionada = f.getAbsolutePath();
          
            actualizarPreview(rutaImagenSeleccionada);
        }
    }

    private void actualizarPreview(String ruta) {
        try {
            if (ruta == null || ruta.isEmpty()) {
                preview.setIcon(null); return;
            }
            ImageIcon icon = new ImageIcon(ruta);
            Image img = icon.getImage().getScaledInstance(
                    preview.getPreferredSize().width,
                    preview.getPreferredSize().height,
                    Image.SCALE_SMOOTH
            );
            preview.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            preview.setIcon(null);
        }
    }

    private void guardarPelicula() {
        try {
            int codigo = Integer.parseInt(campoCodigo.getText().trim());
            String nombre = campoNombre.getText().trim();
            double precio = Double.parseDouble(campoPrecio.getText().trim());
            int copias = Integer.parseInt(campoCopias.getText().trim());

            if (nombre.isEmpty() || rutaImagenSeleccionada == null || rutaImagenSeleccionada.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios, incluyendo la imagen.", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (copias < 0) {
                JOptionPane.showMessageDialog(this, "El numero de copias no puede ser negativo.", "Datos invalidos", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (sistemaWonderland.buscarItem(codigo) != null) {
                JOptionPane.showMessageDialog(this, "El codigo " + codigo + " ya existe.", "Codigo duplicado", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (dcEstreno.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha de estreno.", "Validacion", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Calendar cal = dcEstreno.getCalendar();
            Movie nueva = new Movie(codigo, nombre, precio, rutaImagenSeleccionada, cal);
            nueva.setCantidadCopias(copias);
            sistemaWonderland.agregarItem(nueva);

            int resp = JOptionPane.showConfirmDialog(this, "Pelicula agregada correctamente. Desea agregar otra?", "Exito", JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                limpiarCampos();
            } else {
                transicionSuave.fadeOut(this, pmenuFrame::new);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Verifica formatos: codigo, precio y copias deben ser numeros validos.", "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se pudo guardar la pelicula: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        campoCodigo.setText("");
        campoNombre.setText("");
        campoPrecio.setText("");
        campoCopias.setText("");

        rutaImagenSeleccionada = null;
        preview.setIcon(null);
        dcEstreno.setDate(new Date());
        
        estilizarDateChooser(dcEstreno);
        campoCodigo.requestFocus();
    }
}
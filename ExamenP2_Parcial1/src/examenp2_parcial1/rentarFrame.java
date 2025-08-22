package examenp2_parcial1;

import static examenp2_parcial1.FrontEnd.BORDER_COLOR;
import static examenp2_parcial1.FrontEnd.DETALLE_AZUL;
import static examenp2_parcial1.FrontEnd.FONT_BOTON;
import static examenp2_parcial1.FrontEnd.FONT_CAMPO;
import static examenp2_parcial1.FrontEnd.FONT_ETIQUETA;
import static examenp2_parcial1.FrontEnd.TEXTO_BLANCO;
import static examenp2_parcial1.FrontEnd.TEXTO_GRIS;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

public class rentarFrame extends FrontEnd {

    private final SistemaWonderland sistemaWonderland;

    private final JTextField campoCodigo = new JTextField(20);
    private final JButton btnBuscar = new JButton("Buscar");
    private final JButton btnVolver = new JButton("Volver");
    private final JPanel panelResultado = new JPanel(new BorderLayout(20, 20));

    public rentarFrame(SistemaWonderland sistema) {
        this.sistemaWonderland = sistema;

        FrameConFondo(this, cargarFondo("examenp2_parcial1/imagenes/main.jpeg"));
        titulo1(new JLabel("Rentar Item"));

        campoCodigo.setPreferredSize(new Dimension(240, 36));
        campoCodigo.setForeground(TEXTO_BLANCO);
        campoCodigo.setCaretColor(TEXTO_BLANCO);
        campoCodigo.setBackground(new Color(28, 28, 36));
        campoCodigo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1, true),
                new EmptyBorder(6, 10, 6, 10)
        ));

        JPanel panelCentro = new JPanel(new GridBagLayout());
        panelCentro.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);

        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setOpaque(false);
        String[] etiquetas = {"Codigo del Item:"};
        JComponent[] componentes = {campoCodigo};
        JButton[] botones = {btnBuscar};
        crearPanelFormularioCentrado(panelBusqueda, etiquetas, componentes, botones);
        panelCentro.add(panelBusqueda, gbc);

        gbc.gridy = 1;
        panelResultado.setOpaque(false);
        panelResultado.setPreferredSize(new Dimension(900, 500));
        panelCentro.add(panelResultado, gbc);

        JPanel barraInferior = new JPanel();
        barraInferior.setOpaque(false);
        barraInferior.setBorder(new EmptyBorder(10, 0, 16, 0));
        disBoton(btnVolver);
        barraInferior.add(btnVolver);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelCentro, BorderLayout.CENTER);
        getContentPane().add(barraInferior, BorderLayout.SOUTH);

        acciones();

        transicionSuave.fadeIn(this);
        campoCodigo.requestFocusInWindow();
    }

    private void acciones() {
        btnVolver.addActionListener(e -> transicionSuave.fadeOut(this, pmenuFrame::new));
        btnBuscar.addActionListener(e -> buscarItem());
        campoCodigo.addActionListener(e -> buscarItem());
    }

    private void buscarItem() {
        try {
            int codigo = Integer.parseInt(campoCodigo.getText().trim());
            RentItem item = sistemaWonderland.buscarItem(codigo);
            panelResultado.removeAll();

            if (item != null) {
                if (item.getCantidadCopias() > 0) {
                    mostrarInfoItem(item);
                } else {
                    JOptionPane.showMessageDialog(
                        this,
                        "No hay copias disponibles para rentar el item: " + item.getNombreItem(),
                        "Sin Stock",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "Item con codigo " + codigo + " no encontrado.",
                    "Busqueda fallida",
                    JOptionPane.ERROR_MESSAGE
                );
            }
            panelResultado.revalidate();
            panelResultado.repaint();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Por favor, ingrese un codigo numerico valido.",
                "Error de formato",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void mostrarInfoItem(RentItem item) {
        JPanel panelContenedorItem = new JPanel(new BorderLayout(15, 15));
        panelContenedorItem.setOpaque(true);
        panelContenedorItem.setBackground(new Color(45, 45, 58, 220));
        panelContenedorItem.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(DETALLE_AZUL, 2, true),
            new EmptyBorder(20, 20, 20, 20)
        ));

        ImageIcon originalIcon = item.item;
        Image scaledImage = originalIcon.getImage().getScaledInstance(300, 420, Image.SCALE_SMOOTH);
        JLabel etiquetaImagen = new JLabel(new ImageIcon(scaledImage));
        etiquetaImagen.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1, true));
        panelContenedorItem.add(etiquetaImagen, BorderLayout.WEST);

        JPanel panelDerecho = new JPanel(new BorderLayout(10, 10));
        panelDerecho.setOpaque(false);

        JLabel tituloItem = new JLabel(item.getNombreItem());
        tituloItem.setFont(FONT_ETIQUETA.deriveFont(Font.BOLD, 32f));
        tituloItem.setForeground(TEXTO_BLANCO);
        tituloItem.setBorder(new EmptyBorder(0, 0, 15, 0));
        panelDerecho.add(tituloItem, BorderLayout.NORTH);

        JTextArea areaInfo = new JTextArea(item.toString());
        if (item instanceof Game) {
            areaInfo.append("\n\n--- Especificaciones ---\n");
            areaInfo.append(((Game) item).listEspecificaciones(0));
        }
        areaInfo.setEditable(false);
        areaInfo.setFont(FONT_CAMPO.deriveFont(16f));
        areaInfo.setOpaque(false);
        areaInfo.setForeground(TEXTO_GRIS);
        areaInfo.setLineWrap(true);
        areaInfo.setWrapStyleWord(true);

        JScrollPane scrollInfo = new JScrollPane(areaInfo);
        scrollInfo.setOpaque(false);
        scrollInfo.getViewport().setOpaque(false);
        scrollInfo.setBorder(null);
        panelDerecho.add(scrollInfo, BorderLayout.CENTER);

        JPanel panelRenta = new JPanel(new GridBagLayout());
        panelRenta.setOpaque(false);
        GridBagConstraints gbcRenta = new GridBagConstraints();
        gbcRenta.insets = new Insets(10, 5, 10, 5);

        JLabel labelDias = new JLabel("Dias:");
        labelDias.setFont(FONT_ETIQUETA.deriveFont(18f));
        labelDias.setForeground(TEXTO_BLANCO);
        gbcRenta.gridx = 0; panelRenta.add(labelDias, gbcRenta);

        SpinnerNumberModel model = new SpinnerNumberModel(1, 1, 30, 1);
        JSpinner spinnerDias = new JSpinner(model);
        spinnerDias.setFont(FONT_CAMPO.deriveFont(16f));
        spinnerDias.setPreferredSize(new Dimension(70, 32));
        gbcRenta.gridx = 1; panelRenta.add(spinnerDias, gbcRenta);

        
        JButton btnRentar = new JButton("Rentar");
        disBoton(btnRentar);
        btnRentar.setPreferredSize(new Dimension(200, 38));
        gbcRenta.gridx = 2; panelRenta.add(btnRentar, gbcRenta);

        JLabel etiquetaTotal = new JLabel("Total: Lps. 0.00");
        etiquetaTotal.setFont(FONT_BOTON.deriveFont(18f));
        etiquetaTotal.setForeground(new Color(88, 222, 145));
        gbcRenta.gridy = 1; gbcRenta.gridx = 0; gbcRenta.gridwidth = 3;
        panelRenta.add(etiquetaTotal, gbcRenta);

        
        btnRentar.addActionListener(e -> {
            int dias = (int) spinnerDias.getValue();
            double total = item.pagoRenta(dias);
            etiquetaTotal.setText("Total: Lps. " + String.format("%.2f", total));

            int respuesta = JOptionPane.showConfirmDialog(
                this, 
                "El total a pagar es Lps. " + String.format("%.2f", total) + ".\nConfirmar la renta?",
                "Confirmar Renta", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );

            if (respuesta == JOptionPane.YES_OPTION) {
                item.setCantidadCopias(item.getCantidadCopias() - 1);
                JOptionPane.showMessageDialog(
                    this, 
                    "Item rentado con exito\nCopias restantes: " + item.getCantidadCopias(), 
                    "Renta Exitosa", 
                    JOptionPane.INFORMATION_MESSAGE
                );
                
                panelResultado.removeAll();
                panelResultado.revalidate();
                panelResultado.repaint();
                campoCodigo.setText("");
                campoCodigo.requestFocusInWindow();
            }
        });

        panelDerecho.add(panelRenta, BorderLayout.SOUTH);
        panelContenedorItem.add(panelDerecho, BorderLayout.CENTER);
        panelResultado.add(panelContenedorItem, BorderLayout.CENTER);
    }
}
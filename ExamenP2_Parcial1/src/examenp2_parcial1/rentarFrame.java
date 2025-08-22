package examenp2_parcial1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
    private final JTextField campoCodigo = new JTextField(15);
    private final JButton btnBuscar = new JButton("Buscar");
    private final JButton btnVolver = new JButton("Volver al Menú");
    private final JPanel panelInfo = new JPanel(new BorderLayout(20, 20));

    public rentarFrame(SistemaWonderland sistema) {
        this.sistemaWonderland = sistema;
        FrameConFondo(this, cargarFondo("examenp2_parcial1/imagenes/main.jpeg"));
        titulo1(new JLabel("Rentar Ítem"));

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBusqueda.setOpaque(false);
        JLabel labelCodigo = new JLabel("Código del Ítem:");
        labelCodigo.setFont(FONT_ETIQUETA);
        labelCodigo.setForeground(TEXTO_BLANCO);
        panelBusqueda.add(labelCodigo);
        campoCodigo.setFont(FONT_CAMPO);
        panelBusqueda.add(campoCodigo);
        disBoton(btnBuscar);
        panelBusqueda.add(btnBuscar);
        
        panelInfo.setOpaque(false);
        panelInfo.setBorder(new EmptyBorder(20, 50, 20, 50));

        JPanel panelContenedor = new JPanel(new BorderLayout(10, 10));
        panelContenedor.setOpaque(false);
        panelContenedor.add(panelBusqueda, BorderLayout.NORTH);
        panelContenedor.add(panelInfo, BorderLayout.CENTER);
        
        JPanel panelVolver = new JPanel();
        panelVolver.setOpaque(false);
        disBoton(btnVolver);
        panelVolver.add(btnVolver);
        panelContenedor.add(panelVolver, BorderLayout.SOUTH);

        GridBagConstraints gbcFrame = new GridBagConstraints();
        gbcFrame.gridy = 1;
        getContentPane().add(panelContenedor, gbcFrame);

        acciones();
        transicionSuave.fadeIn(this);
    }

    private void acciones() {
        btnVolver.addActionListener(e -> transicionSuave.fadeOut(this, pmenuFrame::new));

        btnBuscar.addActionListener(e -> {
            try {
                int codigo = Integer.parseInt(campoCodigo.getText());
                RentItem item = sistemaWonderland.buscarItem(codigo);
                panelInfo.removeAll();

                if (item != null) {
                    mostrarInfoItem(item);
                } else {
                    JOptionPane.showMessageDialog(this, "Ítem no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                panelInfo.revalidate();
                panelInfo.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Código no válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void mostrarInfoItem(RentItem item) {
        ImageIcon originalIcon = item.item;
        Image scaledImage = originalIcon.getImage().getScaledInstance(300, 420, Image.SCALE_SMOOTH);
        JLabel etiquetaImagen = new JLabel(new ImageIcon(scaledImage));
        panelInfo.add(etiquetaImagen, BorderLayout.WEST);

        JTextArea areaInfo = new JTextArea(item.toString());
        if (item instanceof Game) {
            areaInfo.append("\n\n--- Especificaciones ---\n");
            areaInfo.append(((Game) item).listEspecificaciones(0));
        }
        areaInfo.setEditable(false);
        areaInfo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        areaInfo.setOpaque(false);
        areaInfo.setForeground(TEXTO_BLANCO);
        areaInfo.setLineWrap(true);
        areaInfo.setWrapStyleWord(true);
        areaInfo.setBorder(new EmptyBorder(10,10,10,10));
        JScrollPane scrollInfo = new JScrollPane(areaInfo);
        scrollInfo.setOpaque(false);
        scrollInfo.getViewport().setOpaque(false);
        scrollInfo.setBorder(null);
        panelInfo.add(scrollInfo, BorderLayout.CENTER);

        JPanel panelRenta = new JPanel(new GridBagLayout());
        panelRenta.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel labelDias = new JLabel("Días de Renta:");
        labelDias.setFont(FONT_ETIQUETA);
        labelDias.setForeground(TEXTO_BLANCO);
        gbc.gridy = 0; gbc.gridx = 0; panelRenta.add(labelDias, gbc);
        
        gbc.gridx = 1; 
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, 30, 1);
        JSpinner spinnerDias = new JSpinner(spinnerModel);
        spinnerDias.setFont(FONT_CAMPO);
        panelRenta.add(spinnerDias, gbc);

        gbc.gridx = 2;
        JButton btnCalcular = new JButton("Calcular Renta");
        disBoton(btnCalcular);
        panelRenta.add(btnCalcular, gbc);
        
        gbc.gridy = 1; gbc.gridx = 0; gbc.gridwidth = 3;
        JLabel etiquetaTotal = new JLabel("Total a Pagar: Lps. 0.00");
        etiquetaTotal.setFont(new Font("Segoe UI", Font.BOLD, 28));
        etiquetaTotal.setForeground(new Color(88, 222, 145));
        panelRenta.add(etiquetaTotal, gbc);

        btnCalcular.addActionListener(e -> {
            int dias = (int) spinnerDias.getValue();
            double total = item.pagoRenta(dias);
            etiquetaTotal.setText("Total a Pagar: Lps. " + String.format("%.2f", total));
        });

        panelInfo.add(panelRenta, BorderLayout.SOUTH);
    }
}
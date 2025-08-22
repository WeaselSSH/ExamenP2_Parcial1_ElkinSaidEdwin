package examenp2_parcial1;

import static examenp2_parcial1.FrontEnd.DETALLE_AZUL;
import static examenp2_parcial1.FrontEnd.FONDO_SECUNDARIO;
import static examenp2_parcial1.FrontEnd.TEXTO_BLANCO;
import static examenp2_parcial1.FrontEnd.TEXTO_GRIS;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class ReportesF extends FrontEnd {

    private final SistemaWonderland sistemaWonderland;

    public ReportesF(SistemaWonderland sistema) {
        this.sistemaWonderland = sistema;

        if (sistemaWonderland.getItems().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay items para mostrar en el reporte.", "Reporte Vacio", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new pmenuFrame();
            transicionSuave.fadeIn(new pmenuFrame());
            return;
        }
        
        FrameConFondo(this, cargarFondo("examenp2_parcial1/imagenes/main.jpeg"));
        titulo1(new JLabel("Reporte de Items"));

        JPanel panelGrid = new JPanel(new GridLayout(0, 4, 20, 20));
        panelGrid.setOpaque(false);
        panelGrid.setBorder(new EmptyBorder(20, 20, 20, 20));

        for (RentItem item : sistemaWonderland.getItems()) {
            panelGrid.add(crearTarjetaItem(item));
        }

        JScrollPane scrollPane = new JScrollPane(panelGrid);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        GridBagConstraints gbcFrame = new GridBagConstraints();
        gbcFrame.gridy = 1;
        gbcFrame.weightx = 1;
        gbcFrame.weighty = 1;
        gbcFrame.fill = GridBagConstraints.BOTH;
        getContentPane().add(scrollPane, gbcFrame);
        
        JButton btnVolver = new JButton("Volver al Menu");
        disBoton(btnVolver);
        btnVolver.addActionListener(e -> transicionSuave.fadeOut(this, pmenuFrame::new));
        
        JPanel panelVolver = new JPanel();
        panelVolver.setOpaque(false);
        panelVolver.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelVolver.add(btnVolver);
        
        GridBagConstraints gbcVolver = new GridBagConstraints();
        gbcVolver.gridy = 2;
        getContentPane().add(panelVolver, gbcVolver);
        
        transicionSuave.fadeIn(this);
    }

    private JPanel crearTarjetaItem(RentItem item) {
        JPanel tarjeta = new JPanel(new BorderLayout(10, 10));
        tarjeta.setBackground(FONDO_SECUNDARIO);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(DETALLE_AZUL, 2),
            item.getNombreItem()
        );
        titledBorder.setTitleFont(new Font("Segoe UI", Font.BOLD, 18));
        titledBorder.setTitleColor(TEXTO_BLANCO);
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(titledBorder, new EmptyBorder(10, 10, 10, 10)));

        ImageIcon originalIcon = item.item;
        Image scaledImage = originalIcon.getImage().getScaledInstance(200, 280, Image.SCALE_SMOOTH);
        JLabel etiquetaImagen = new JLabel(new ImageIcon(scaledImage));
        etiquetaImagen.setHorizontalAlignment(SwingConstants.CENTER);
        tarjeta.add(etiquetaImagen, BorderLayout.NORTH);
        
        JTextArea areaInfo = new JTextArea(item.toString());
        areaInfo.setEditable(false);
        areaInfo.setOpaque(false);
        areaInfo.setForeground(TEXTO_GRIS);
        areaInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        areaInfo.setLineWrap(true);
        areaInfo.setWrapStyleWord(true);
        tarjeta.add(areaInfo, BorderLayout.CENTER);
        
        return tarjeta;
    }
}
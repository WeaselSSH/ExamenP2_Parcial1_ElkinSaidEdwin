package examenp2_parcial1;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FrontEnd extends JFrame {

    protected static final Color FONDO_PRINCIPAL = new Color(24, 26, 33);
    protected static final Color FONDO_SECUNDARIO = new Color(35, 39, 42);
    protected static final Color DETALLE_AZUL = new Color(114, 137, 218);
    protected static final Color TEXTO_BLANCO = new Color(255, 255, 255);
    protected static final Color TEXTO_GRIS = new Color(188, 193, 199);
    protected static final Color BORDER_COLOR = new Color(68, 85, 132);

    protected static final Font FONT_TITULO = new Font("Segoe UI Semibold", Font.BOLD, 80);
    protected static final Font FONT_ETIQUETA = new Font("Segoe UI", Font.BOLD, 22);
    protected static final Font FONT_CAMPO = new Font("Segoe UI", Font.PLAIN, 20);
    protected static final Font FONT_BOTON = new Font("Segoe UI Semibold", Font.BOLD, 24);
    
    public FrontEnd() {}

    public void FrameConFondo(JFrame frame, Image bg) {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setContentPane(new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(FONDO_PRINCIPAL);
                g.fillRect(0, 0, getWidth(), getHeight());
                if (bg != null) {
                    g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
                }
            }
        });
    }

    public Image cargarFondo(String imagen) {
        try {
            java.net.URL imageUrl = getClass().getClassLoader().getResource(imagen);
            if (imageUrl == null) return null;
            return ImageIO.read(imageUrl);
        } catch (IOException ex) {
            return null;
        }
    }

    public void titulo1(JLabel titleLabel) {
        titleLabel.setFont(FONT_TITULO);
        titleLabel.setForeground(TEXTO_BLANCO);
        titleLabel.setBackground(new Color(45, 45, 58, 220));
        titleLabel.setOpaque(true);
        titleLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(DETALLE_AZUL, 2), BorderFactory.createEmptyBorder(20, 45, 20, 45)));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.insets = new Insets(50, 20, 50, 20);
        gbc.anchor = GridBagConstraints.NORTH;
        getContentPane().add(titleLabel, gbc);
    }
    
    public void disBoton(JButton btn) {
        btn.setFont(FONT_BOTON);
        btn.setPreferredSize(new Dimension(300, 60));
        btn.setForeground(TEXTO_BLANCO);
        btn.setBackground(DETALLE_AZUL);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn.setFocusPainted(false);

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(DETALLE_AZUL.brighter());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(DETALLE_AZUL);
            }
        });
    }

    public void layoutBtn(JButton[] botones) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        for (JButton btn : botones) {
            disBoton(btn);
            JPanel container = new JPanel(new GridBagLayout());
            container.setOpaque(false);
            container.add(btn);
            panel.add(container);
            panel.add(Box.createRigidArea(new Dimension(0, 20)));
        }
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 1;
        getContentPane().add(panel, gbc);
    }
    
    public void crearPanelFormularioCentrado(JPanel panelContenedor, String[] etiquetas, JComponent[] componentes, JButton[] botones) {
        panelContenedor.setOpaque(true);
        panelContenedor.setBackground(new Color(45, 45, 58, 220));
        panelContenedor.setBorder(BorderFactory.createLineBorder(DETALLE_AZUL, 2));
        panelContenedor.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 0; i < etiquetas.length; i++) {
            JLabel label = new JLabel(etiquetas[i]);
            label.setFont(FONT_ETIQUETA);
            label.setForeground(TEXTO_BLANCO);
            
            JComponent component = componentes[i];
            component.setFont(FONT_CAMPO);
            component.setPreferredSize(new Dimension(350, 40));

            if(component instanceof JTextField){
                JTextField textField = (JTextField) component;
                textField.setCaretColor(TEXTO_BLANCO);
                textField.setBackground(FONDO_SECUNDARIO);
                textField.setForeground(TEXTO_BLANCO);
                Border padding = BorderFactory.createEmptyBorder(5, 10, 5, 10);
                textField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1), padding));
            } else if (component instanceof JPanel) {
                 component.setOpaque(false);
                 for(Component subComp : component.getComponents()){
                     if(subComp instanceof JTextField) subComp.setPreferredSize(new Dimension(220, 40));
                 }
            }
            
            gbc.gridy = i;
            gbc.gridx = 0;
            gbc.anchor = GridBagConstraints.EAST;
            panelContenedor.add(label, gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST;
            panelContenedor.add(component, gbc);
        }

        JPanel panelBotones = new JPanel();
        panelBotones.setOpaque(false);
        for (JButton boton : botones) {
            disBoton(boton);
            panelBotones.add(boton);
        }
        
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 15, 10, 15);
        panelContenedor.add(panelBotones, gbc);
        
        GridBagConstraints gbcFrame = new GridBagConstraints();
        gbcFrame.gridy = 1;
        getContentPane().add(panelContenedor, gbcFrame);
    }
    
public void disBotonInline(JButton btn) {
    btn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
    btn.setPreferredSize(new Dimension(220, 40)); 
    btn.setForeground(TEXTO_BLANCO);
    btn.setBackground(DETALLE_AZUL);
    btn.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12)); 
    btn.setFocusPainted(false);
    btn.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override public void mouseEntered(java.awt.event.MouseEvent e) { btn.setBackground(DETALLE_AZUL.brighter()); }
        @Override public void mouseExited (java.awt.event.MouseEvent e) { btn.setBackground(DETALLE_AZUL); }
    });
}

}
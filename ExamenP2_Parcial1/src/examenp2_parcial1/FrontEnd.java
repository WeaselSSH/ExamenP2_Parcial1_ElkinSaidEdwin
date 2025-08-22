package examenp2_parcial1;

import java.awt.Color;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrontEnd extends JFrame {

    private static final Color BACKGROUND_COMPONENTS = new Color(45, 45, 58);
    private static final Color BUTTON_HOVER_BACKGROUND = new Color(30, 32, 42);
    private static final Color TEXT_COLOR = new Color(236, 236, 234);
    private static final Color BORDER_COLOR = new Color(68, 85, 132);
    private static final Color BORDER_HOVER_COLOR = new Color(40, 50, 70);
    private static final Font FONT_LABEL = new Font("Georgia", Font.BOLD, 22);
    private static final Font FONT_FIELD = new Font("Georgia", Font.PLAIN, 20);

    public FrontEnd() {}

    public void FrameConFondo(JFrame frame, Image bg) {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setContentPane(new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bg != null) {
                    g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
                } else {
                    g.setColor(new Color(28, 28, 36));
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        });
    }

    public Image cargarFondo(String imagen) {
        try {
            java.net.URL imageUrl = getClass().getClassLoader().getResource(imagen);
            if (imageUrl == null) {
                JOptionPane.showMessageDialog(null, "No se pudo encontrar la imagen de fondo: " + imagen, "Error de Recurso", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            return ImageIO.read(imageUrl);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la imagen de fondo.", "Error de Carga", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return null;
        }
    }

    public void titulo1(JLabel titleLabel) {
        titleLabel.setFont(new Font("Georgia", Font.BOLD, 85));
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setBackground(BACKGROUND_COMPONENTS);
        titleLabel.setOpaque(true);
        titleLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(BORDER_COLOR, 3), BorderFactory.createEmptyBorder(20, 45, 20, 45)));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.insets = new Insets(60, 20, 70, 20);
        gbc.anchor = GridBagConstraints.NORTH;
        getContentPane().add(titleLabel, gbc);
    }

    public void disBoton(JButton[] btn) {
        Font buttonFont = new Font("Georgia", Font.BOLD, 28);
        Dimension buttonSize = new Dimension(300, 60);
        for (JButton b : btn) {
            b.setFont(buttonFont);
            b.setPreferredSize(buttonSize);
            b.setMinimumSize(buttonSize);
            b.setMaximumSize(buttonSize);
            b.setForeground(TEXT_COLOR);
            b.setBackground(BACKGROUND_COMPONENTS);
            b.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 3));
            b.setFocusPainted(false);
            b.setContentAreaFilled(true);
            b.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    b.setBackground(BUTTON_HOVER_BACKGROUND);
                    b.setBorder(BorderFactory.createLineBorder(BORDER_HOVER_COLOR, 4));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    b.setBackground(BACKGROUND_COMPONENTS);
                    b.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 3));
                }
            });
        }
    }

    public void layoutBtn(JButton[] btn) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        disBoton(btn);
        for (int i = 0; i < btn.length; i++) {
            JPanel buttonContainer = new JPanel(new GridBagLayout());
            buttonContainer.setOpaque(false);
            buttonContainer.add(btn[i]);
            panel.add(buttonContainer);
            if (i < btn.length - 1) {
                panel.add(Box.createRigidArea(new Dimension(0, 20)));
            }
        }
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 1;
        getContentPane().add(panel, gbc);
    }
    
    public void crearPanelFormularioCentrado(JPanel panelContenedor, String[] etiquetas, JComponent[] componentes, JButton[] botones) {
        panelContenedor.setOpaque(true);
        panelContenedor.setBackground(new Color(45, 45, 58, 220));
        panelContenedor.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 3));
        panelContenedor.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 0; i < etiquetas.length; i++) {
            JLabel label = new JLabel(etiquetas[i]);
            label.setFont(FONT_LABEL);
            label.setForeground(TEXT_COLOR);
            
            JComponent component = componentes[i];
            component.setFont(FONT_FIELD);
            component.setPreferredSize(new Dimension(350, 40));

            if(component instanceof JTextField){
                ((JTextField)component).setCaretColor(TEXT_COLOR);
                ((JTextField)component).setBackground(new Color(30, 32, 42));
                ((JTextField)component).setForeground(TEXT_COLOR);
                ((JTextField)component).setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(BORDER_COLOR, 2),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)
                ));
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
        disBoton(botones);
        for (JButton boton : botones) {
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
}

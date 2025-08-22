package examenp2_parcial1;

import java.awt.GridBagConstraints;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author saidn
 */
public class PantallaDeCarga extends FrontEnd
{
    public PantallaDeCarga() {
        FrameConFondo(this, null);

        JLabel tituloFondo = new JLabel("Wonderland");
        tituloFondo.setFont(new java.awt.Font("Georgia", java.awt.Font.BOLD, 80));
        tituloFondo.setForeground(new java.awt.Color(60, 63, 80));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        getContentPane().add(tituloFondo, gbc);

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
}
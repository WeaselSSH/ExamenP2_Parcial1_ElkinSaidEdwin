/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examenp2_parcial1;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author saidn
 */
public class pmenuFrame extends FrontEnd {
     public static PantallaDeCarga pantallaDeCargaInstancia;

    private final JLabel titleLabel = new JLabel("JAVA TICKET");
    private final JButton btnLogin = new JButton("Login");
    private final JButton btnCerrar = new JButton("Cerrar");
    private final String imagen = "/proyectocal/imagenes/prueba1.jpg";

    public pmenuFrame() {
        FrameConFondo(this, cargarFondo(imagen));
        titulo1(titleLabel);
        JButton[] botones = {btnLogin, btnCerrar};
        layoutBtn(botones);
        acciones();
        transicionSuave.fadeIn(this);
    }

    public void acciones() {
        btnCerrar.addActionListener(e -> System.exit(0));

        btnLogin.addActionListener(e -> {
        });
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            pantallaDeCargaInstancia = new PantallaDeCarga();
            pantallaDeCargaInstancia.setVisible(true);

            new pmenuFrame();
        });
    }
}

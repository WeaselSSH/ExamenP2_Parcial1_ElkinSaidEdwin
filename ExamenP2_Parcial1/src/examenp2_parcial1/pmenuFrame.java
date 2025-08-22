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

    private final JLabel titleLabel = new JLabel("Wonderland");
    private final JButton btnAdd = new JButton("Añadir");
     private final JButton btnRentar = new JButton("Rentar");
    private final JButton btnCerrar = new JButton("Cerrar");
    private final String imagen = "/examenp2_parcial1/mainBackground.jpg";

    public pmenuFrame() {
        FrameConFondo(this, cargarFondo(imagen));
        titulo1(titleLabel);
        JButton[] botones = {btnRentar, btnAdd,btnCerrar};
        layoutBtn(botones);
        acciones();
        transicionSuave.fadeIn(this);
    }

    public void acciones() {
        btnCerrar.addActionListener(e -> System.exit(0));
 btnAdd.addActionListener(e -> System.exit(0));
  btnRentar.addActionListener(e -> System.exit(0));
        
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            pantallaDeCargaInstancia = new PantallaDeCarga();
            pantallaDeCargaInstancia.setVisible(true);

            new pmenuFrame();
        });
    }
}

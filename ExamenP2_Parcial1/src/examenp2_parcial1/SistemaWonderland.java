package examenp2_parcial1;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class SistemaWonderland {
    private static ArrayList<RentItem> items = new ArrayList<>();

    public boolean codigoExiste(int codigo) {
        for (RentItem item : items) {
            if (item.getCodigoItem() == codigo) {
                return true;
            }
        }
        return false;
    }

    public void agregarItem(RentItem item) {
        if (!codigoExiste(item.getCodigoItem())) {
            items.add(item);
            JOptionPane.showMessageDialog(null, "Item agregado con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "El codigo " + item.getCodigoItem() + " ya existe.", "Error de Duplicado", JOptionPane.ERROR_MESSAGE);
        }
    }

    public RentItem buscarItem(int codigo) {
        for (RentItem item : items) {
            if (item.getCodigoItem() == codigo) {
                return item;
            }
        }
        return null;
    }

    public ArrayList<RentItem> getItems() {
        return items;
    }
}
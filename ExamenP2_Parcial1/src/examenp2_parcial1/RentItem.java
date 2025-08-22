package examenp2_parcial1;

import javax.swing.ImageIcon;

public abstract class RentItem {

    int codigoItem;
    String nombreItem;
    double precioRenta;
    int cantidadCopias;
    ImageIcon item;
    
    public RentItem(int codigoItem, String nombreItem, double precioRenta) {
        this.cantidadCopias = 0;
    }
    
    @Override
    public String toString() {
        return "Código item: " + codigoItem
                + "\n Nombre item: " + nombreItem
                + "\n Precio renta: " + precioRenta
                + "\n Cantidad copias: " + cantidadCopias;
    }
    
    public abstract double pagoRenta(int dias);

    public int getCodigoItem() {
        return codigoItem;
    }

    public String getNombreItem() {
        return nombreItem;
    }

    public double getPrecioRenta() {
        return precioRenta;
    }

    public int getCantidadCopias() {
        return cantidadCopias;
    }
    
}


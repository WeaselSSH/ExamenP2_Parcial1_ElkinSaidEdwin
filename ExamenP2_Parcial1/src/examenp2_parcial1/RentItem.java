package examenp2_parcial1;

import javax.swing.ImageIcon;

public abstract class RentItem {

    protected int codigoItem;
    protected String nombreItem;
    protected double precioRenta;
    protected int cantidadCopias;
    protected ImageIcon item;

    public RentItem(int codigoItem, String nombreItem, double precioRenta, String path) {
        this.codigoItem = codigoItem;
        this.nombreItem = nombreItem;
        this.precioRenta = precioRenta;
        this.cantidadCopias = 0; 
        this.item = new ImageIcon(path);
    }

    @Override
    public String toString() {
        return "Codigo item: " + codigoItem
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

    public void setCantidadCopias(int cantidadCopias) {
        if (cantidadCopias >= 0) {
            this.cantidadCopias = cantidadCopias;
        }
    }
}
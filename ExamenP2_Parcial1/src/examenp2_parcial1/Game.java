package examenp2_parcial1;

import java.util.ArrayList;
import java.util.Calendar;

public class Game extends RentItem implements MenuActions {
    Calendar fechaPublicacion;
    ArrayList<String> especificacionesTecnicas;
    
    public Game(int codigoItem, String nombreItem, double precioRenta) {
        super(codigoItem, nombreItem, precioRenta);
        
        this.fechaPublicacion = Calendar.getInstance();
        this.especificacionesTecnicas = new ArrayList<>();
    }
    
    @Override
    public String toString() {
        return super.toString() + "Fecha de publicación: " + fechaPublicacion;
    }
    
    @Override
    public double pagoRenta(int dias) {
        
    }
}

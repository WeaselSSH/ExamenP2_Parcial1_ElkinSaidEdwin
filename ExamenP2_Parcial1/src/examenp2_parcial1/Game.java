package examenp2_parcial1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Game extends RentItem implements MenuActions {

    Calendar fechaPublicacion;
    ArrayList<String> especificacionesTecnicas;

    public Game(int codigoItem, String nombreItem,String path) {
        super(codigoItem, nombreItem, 20.0, path);
        this.fechaPublicacion = Calendar.getInstance();
        this.especificacionesTecnicas = new ArrayList<>();
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaTexto = sdf.format(fechaPublicacion.getTime());
        
        return super.toString() + "Fecha de publicacion: " + fechaTexto;
    }

    @Override
    public double pagoRenta(int dias) {
        return dias * 20;
    }

    public void setfechaPublicacion(int year, int month, int day) {
        this.fechaPublicacion.set(year, month - 1, day);
    }

    public String listEspecificaciones(int i) {
        if (especificacionesTecnicas.isEmpty()) {
            return "NO SE HAN AGREGADO ESPECIFICACIONES";
        }
        if (i >= especificacionesTecnicas.size()) {
            return "";
        }
        return especificacionesTecnicas.get(i) + " \n " + listEspecificaciones(i + 1);
    }

    public void ejecutarOpcion(int opcion) {

    }

}

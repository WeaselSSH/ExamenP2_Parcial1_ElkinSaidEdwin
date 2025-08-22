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
        return super.toString() + "Fecha de publicacion: " + fechaPublicacion;
    }
    
    @Override
    public double pagoRenta(int dias) {
     return dias*20;
    }
   public void setfechaPublicacion(int year, int month,int day){
   this.fechaPublicacion.set(year, month,day);
   }
    public String listEspecificaciones(int i){
    if(especificacionesTecnicas.isEmpty()){
    return "NO SE HAN AGREGADO ESPECIFICACIONES";
    }
    if(i>especificacionesTecnicas.size()){
    return "";
    }
    return especificacionesTecnicas.get(i)+" \n "+listEspecificaciones(i+1);
    }
  public void ejecutarOpcion(int opcion){
  
  }

}

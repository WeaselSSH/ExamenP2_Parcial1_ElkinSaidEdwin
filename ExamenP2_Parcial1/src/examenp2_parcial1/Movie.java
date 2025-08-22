package examenp2_parcial1;

import java.util.Calendar;

public class Movie extends RentItem {

    
    public static final String ESTADO_ESTRENO = "ESTRENO";
    public static final String ESTADO_NORMAL  = "NORMAL";

    
    private Calendar fechaEstreno;  

    
    public Movie(int codigoItem, String nombreItem, double precioRenta, String imagenPath) {
        super(codigoItem, nombreItem, precioRenta, imagenPath);
        this.fechaEstreno = Calendar.getInstance();
    }

  
    public Calendar getFechaEstreno() {
        Calendar copia = Calendar.getInstance();
        copia.setTime(this.fechaEstreno.getTime());
        return copia;
    }

    public void setFechaEstreno(Calendar fechaEstreno) {
        if (fechaEstreno == null) {
            this.fechaEstreno = Calendar.getInstance();
        } else {
            this.fechaEstreno = Calendar.getInstance();
            this.fechaEstreno.setTime(fechaEstreno.getTime());
        }
    }
   
    public String getEstado() {
        Calendar hoy = Calendar.getInstance();
        Calendar limiteEstreno = Calendar.getInstance();
        limiteEstreno.setTime(this.fechaEstreno.getTime());
        limiteEstreno.add(Calendar.MONTH, 3); 

        if (hoy.compareTo(limiteEstreno) <= 0) {
            return ESTADO_ESTRENO;
        } else {
            return ESTADO_NORMAL;
        }
    }

    
    public double pagoRenta(int dias) {
        if (dias <= 0) return 0.0;

        double base = precioRenta * dias;
        double recargo = 0.0;

        if (getEstado().equals(ESTADO_ESTRENO)) {
            if (dias > 2) {
                recargo = (dias - 2) * 50;
            }
        } else { 
            if (dias > 5) {
                recargo = (dias - 5) * 30;
            }
        }

        return base + recargo;
    }

    
    @Override
    public String toString() {
        return super.toString()
                + "\n Estado: " + getEstado()
                + "\n- Movie";
    }
}

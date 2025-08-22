package examenp2_parcial1;

import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends RentItem implements MenuActions {

    Calendar fechaPublicacion;
    ArrayList<String> especificacionesTecnicas;

    public Game(int codigoItem, String nombreItem, String path) {
        super(codigoItem, nombreItem, 20.0, path);
        this.fechaPublicacion = Calendar.getInstance();
        this.especificacionesTecnicas = new ArrayList<>();
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaTexto = sdf.format(fechaPublicacion.getTime());
        return super.toString() + "\nFecha de publicacion: " + fechaTexto + "\n- PS3 GAME";
    }

    @Override
    public double pagoRenta(int dias) {
        return dias * precioRenta;
    }

    public void setfechaPublicacion(int year, int month, int day) {
        this.fechaPublicacion.set(year, month - 1, day);
    }

    public String listEspecificaciones(int i) {
        if (especificacionesTecnicas.isEmpty()) {
            return "No hay especificaciones técnicas agregadas.";
        }
        if (i >= especificacionesTecnicas.size()) {
            return "";
        }
        return "- " + especificacionesTecnicas.get(i) + "\n" + listEspecificaciones(i + 1);
    }

    @Override
    public void submenu() {
        String[] opciones = {"Actualizar Fecha de Publicación", "Agregar Especificación", "Ver Especificaciones", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(null, "Submenú para: " + getNombreItem(), "Submenú de Juego", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
        if(eleccion != 3) {
            ejecutarOpcion(eleccion);
        }
    }

    @Override
    public void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 0:
                JDateChooser dateChooser = new JDateChooser();
                dateChooser.setDate(new Date());
                JPanel panelFecha = new JPanel();
                panelFecha.add(dateChooser);
                int result = JOptionPane.showConfirmDialog(null, panelFecha, "Seleccione la nueva fecha de publicación", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    Calendar nuevaFecha = dateChooser.getCalendar();
                    setfechaPublicacion(nuevaFecha.get(Calendar.YEAR), nuevaFecha.get(Calendar.MONTH) + 1, nuevaFecha.get(Calendar.DAY_OF_MONTH));
                    JOptionPane.showMessageDialog(null, "Fecha actualizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case 1:
                String spec = JOptionPane.showInputDialog("Ingrese la nueva especificación técnica:");
                if (spec != null && !spec.trim().isEmpty()) {
                    especificacionesTecnicas.add(spec);
                    JOptionPane.showMessageDialog(null, "Especificación agregada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case 2:
                JOptionPane.showMessageDialog(null, listEspecificaciones(0), "Especificaciones de " + getNombreItem(), JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }
}

package examenp2_parcial1;

import java.util.ArrayList;

public class SistemaWonderland {
    private static ArrayList<Movie> peliculas = new ArrayList<>();
    private static ArrayList<Game> juegos = new ArrayList<>();

    public void agregarPelicula(Movie pelicula) {
        peliculas.add(pelicula);
    }

    public ArrayList<Movie> getPeliculas() {
        return peliculas;
    }
    
    public Movie buscarPelicula(int codigo) {
        for (Movie pelicula : peliculas) {
            if (pelicula.getCodigoItem() == codigo) {
                return pelicula;
            }
        }
        return null;
    }

    public void agregarJuego(Game juego) {
        juegos.add(juego);
    }

    public ArrayList<Game> getJuegos() {
        return juegos;
    }

    public Game buscarJuego(int codigo) {
        for (Game juego : juegos) {
            if (juego.getCodigoItem() == codigo) {
                return juego;
            }
        }
        return null;
    }
}
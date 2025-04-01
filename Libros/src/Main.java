import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Persona {
    String nombre;
    List<Prestamo> prestamos;

    Persona(String nombre){
        this.nombre = nombre;
        this.prestamos = new ArrayList<>();
    }

    void tomarPrestado (Libro libro){
        if (!libro.estaPrestado()){
            Prestamo prestamo = new Prestamo(this, libro);
            prestamos.add(prestamo);
            libro.marcarComoPrestado();
            System.out.println(nombre + " ha tomado el libro: " + libro.getTitulo() + " prestado.");
        } else {
            System.out.println("El libro '" + libro.getTitulo() + "' ya está prestado.");
        }
    }

    public void devolverLibro(Libro libro) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getLibro().equals(libro)) {
                prestamos.remove(prestamo);
                libro.devolver();
                System.out.println(nombre + " ha devuelto el libro: " + libro.getTitulo());
                return;
            }
        }
        System.out.println(nombre + " no tiene registrado este libro en sus préstamos.");
    }
}

class Prestamo {
    Persona persona;
    Libro libro;
    Date fechaPrestamo;

    Prestamo(Persona persona, Libro libro) {
        this.persona = persona;
        this.libro = libro;
        this.fechaPrestamo = new Date();
    }

    Libro getLibro() {
        return libro;
    }

    @Override
    public String toString() {
        return "Préstamo{" +
                "usuario=" + persona +
                ", libro=" + libro.getTitulo() +
                ", fechaPrestamo=" + fechaPrestamo +
                '}';
    }
}

class Libro {
    String titulo;
    String autor;
    boolean prestado;

    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.prestado = false;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean estaPrestado() {
        return prestado;
    }

    public void marcarComoPrestado() {
        this.prestado = true;
    }

    public void devolver() {
        this.prestado = false;
    }
}

public class Main {
    public static void main(String[] args) {
        Persona persona = new Persona("Carlos");
        Libro libro1 = new Libro("1984", "George Owell");
        Libro libro2 = new Libro("Fahreinheit 451", "Ray BRadbury");

        persona.tomarPrestado(libro1);
        persona.tomarPrestado(libro1);

        persona.devolverLibro(libro1);
        persona.devolverLibro(libro2);

    }
}
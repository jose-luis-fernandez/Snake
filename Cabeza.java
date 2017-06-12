import javafx.scene.paint.*;

public class Cabeza extends ParteDelCuerpo
{

    private int velocidadEnX;
    private int velocidadEnY;
    private String direccion;

    public Cabeza(double x, double y)
    {
        super(x, y);
        this.setFill(Color.GREEN);
    }

    public void cambiarDireccionALaDerecha()
    {
        velocidadEnX = 20;
        velocidadEnY = 0;
        direccion = "derecha";
    }

    public void cambiarDireccionALaIzquierda()
    {
        velocidadEnX = -20;
        velocidadEnY = 0;
        direccion = "izquierda";
    }

    public void cambiarDireccionHaciaArriba()
    {
        velocidadEnY = -20;
        velocidadEnX = 0;
        direccion = "arriba";
    }

    public void cambiarDireccionHaciaAbajo()
    {
        velocidadEnY = 20;
        velocidadEnX = 0;
        direccion = "abajo";
    }

    public void moverEnX()
    {
        this.setTranslateX(this.getTranslateX() + velocidadEnX);
    }

    public void moverEnY()
    {
        this.setTranslateY(this.getTranslateY() + velocidadEnY);
    }

    public String getDireccion()
    {
        return direccion;
    }

}
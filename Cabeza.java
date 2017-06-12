import javafx.scene.paint.*;

public class Cabeza extends ParteDelCuerpo
{

    private int velocidadEnX;
    private int velocidadEnY;
    private String direccion;
    private double anchoEscena;
    private double altoEscena;

    public Cabeza(double x, double y, double anchoEscena, double altoEscena)
    {
        super(x, y);
        this.setFill(Color.GREEN);
        this.anchoEscena = anchoEscena;
        this.altoEscena = altoEscena;
        direccion = "";
        velocidadEnX = 20;
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
        if(direccion.equals("izquierda") && this.getBoundsInParent().getMinX() <= 0){
            this.setTranslateX(anchoEscena);
        }
        else if(direccion.equals("derecha") && this.getBoundsInParent().getMaxX() >= anchoEscena){
            this.setTranslateX(1);
        }
        this.setTranslateX(this.getTranslateX() + velocidadEnX);
    }

    public void moverEnY()
    {
        if(direccion.equals("arriba") && this.getBoundsInParent().getMinY() <= 0){
            this.setTranslateY(altoEscena);
        }
        else if(direccion.equals("abajo") && this.getBoundsInParent().getMaxY() >= altoEscena){
            this.setTranslateY(1);
        }
        this.setTranslateY(this.getTranslateY() + velocidadEnY);
    }

    public String getDireccion()
    {
        return direccion;
    }

}
import javafx.scene.paint.*;
import javafx.scene.image.Image;

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
        Image spriteCabeza = new Image("cabeza_derecha.png");
        this.setFill(new ImagePattern(spriteCabeza, 0, 0, 1, 1, true));
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
        Image spriteCabeza = new Image("cabeza_derecha.png");
        this.setFill(new ImagePattern(spriteCabeza, 0, 0, 1, 1, true));
    }

    public void cambiarDireccionALaIzquierda()
    {
        velocidadEnX = -20;
        velocidadEnY = 0;
        direccion = "izquierda";
        Image spriteCabeza = new Image("cabeza_izquierda.png");
        this.setFill(new ImagePattern(spriteCabeza, 0, 0, 1, 1, true));
    }

    public void cambiarDireccionHaciaArriba()
    {
        velocidadEnY = -20;
        velocidadEnX = 0;
        direccion = "arriba";
        Image spriteCabeza = new Image("cabeza_arriba.png");
        this.setFill(new ImagePattern(spriteCabeza, 0, 0, 1, 1, true));
    }

    public void cambiarDireccionHaciaAbajo()
    {
        velocidadEnY = 20;
        velocidadEnX = 0;
        direccion = "abajo";
        Image spriteCabeza = new Image("cabeza_abajo.png");
        this.setFill(new ImagePattern(spriteCabeza, 0, 0, 1, 1, true));
    }

    public void moverEnX()
    {
        
        if(direccion.equals("izquierda") && this.getBoundsInParent().getMinX() <= 0){
            this.setTranslateX(anchoEscena);
        }
        else if(direccion.equals("derecha") && this.getBoundsInParent().getMaxX() >= anchoEscena){
            this.setTranslateX(-20);
        }
        this.setTranslateX(this.getTranslateX() + velocidadEnX);
    }

    public void moverEnY()
    {
        if(direccion.equals("arriba") && this.getBoundsInParent().getMinY() <= 60){
            this.setTranslateY(altoEscena);
        }
        else if(direccion.equals("abajo") && this.getBoundsInParent().getMaxY() >= altoEscena){
            this.setTranslateY(40);
        }
        this.setTranslateY(this.getTranslateY() + velocidadEnY);
    }

    public String getDireccion()
    {
        return direccion;
    }

}
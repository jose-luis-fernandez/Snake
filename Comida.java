import javafx.scene.shape.Rectangle;
import javafx.scene.paint.*;
import javafx.scene.image.Image;

public class Comida extends Rectangle
{
    
    private static final int ANCHO = 20;
    private static final int ALTO = 20;
    
    public Comida(double x, double y)
    {
        super(x, y, ANCHO, ALTO);
        Image manzana = new Image("manzana3.png");
        this.setFill(new ImagePattern(manzana, 0, 0, 1, 1, true));
    }
}
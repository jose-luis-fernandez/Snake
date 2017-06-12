import javafx.scene.shape.Rectangle;
import javafx.scene.paint.*;

public class Comida extends Rectangle
{
    
    private static final int ANCHO = 20;
    private static final int ALTO = 20;
    
    public Comida(double x, double y)
    {
        super(x, y, ANCHO, ALTO);
        this.setFill(Color.RED);
    }
}
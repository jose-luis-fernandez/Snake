import javafx.scene.shape.Rectangle;

public class ParteDelCuerpo extends Rectangle
{
    
    private static final int ANCHO = 20;
    private static final int ALTO = 20;
    
    public ParteDelCuerpo(double x, double y)
    {
        super();
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.setWidth(ANCHO);
        this.setHeight(ALTO);
    }
}
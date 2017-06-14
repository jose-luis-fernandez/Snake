import javafx.scene.paint.*;
import javafx.scene.image.Image;

public class Cola extends ParteDelCuerpo
{
    
    private Image spriteCuerpo = new Image("cuerpo.png");
    
    public Cola(double x, double y)
    {
        super(x, y);
        this.setFill(new ImagePattern(spriteCuerpo, 0, 0, 1, 1, true));
    }
    
}
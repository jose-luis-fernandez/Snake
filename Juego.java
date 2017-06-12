import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.paint.*;

public class Juego extends Application
{
    
    private ArrayList<ParteDelCuerpo> serpiente = new ArrayList<ParteDelCuerpo>();
    private ArrayList<Comida> comida = new ArrayList<Comida>();
    
    public static void main(String[] args){
        launch(args);
    }
    
    @Override
    public void start(Stage escenario)
    {
        Group contenedor = new Group();
        Scene escena = new Scene(contenedor, 710, 710);
        escenario.setScene(escena);
        
        Cabeza cabeza = new Cabeza(345, 345);
        serpiente.add(cabeza);
        contenedor.getChildren().add(cabeza);
        
        Cola cola1 = new Cola(serpiente.get(serpiente.size() - 1).getTranslateX() + 20, serpiente.get(serpiente.size() - 1).getTranslateY());
        serpiente.add(cola1);
        contenedor.getChildren().add(cola1);
        
        escenario.show();
        
        Random aleatoriox = new Random();
        Random aleatorioy = new Random();
        
        
        Timeline timeline = new Timeline();
        KeyFrame keyframe = new KeyFrame(Duration.seconds(0.6), event -> {
            
            int i = (serpiente.size() - 1);
            while(i > 0){
                serpiente.get(i).setTranslateX(serpiente.get(i - 1).getTranslateX());
                serpiente.get(i).setTranslateY(serpiente.get(i - 1).getTranslateY());
                i--;
            }
            cabeza.moverEnX();
            cabeza.moverEnY();
            
            if(comida.size() == 0){
                int x = aleatoriox.nextInt(690);
                int y = aleatorioy.nextInt(690);
                Comida comida1 = new Comida(x, y);
                comida.add(comida1);
                contenedor.getChildren().add(comida1);
            }
            
            if(comida.size() != 0){
                if(serpiente.get(0).getBoundsInParent().intersects(comida.get(0).getBoundsInParent())){
                    comida.get(0).setFill(Color.TRANSPARENT);
                    comida.remove(0);
                    
                    Cola cola = new Cola(serpiente.get(serpiente.size() - 1).getTranslateX(), serpiente.get(serpiente.size() - 1).getTranslateY());
                    serpiente.add(cola);
                    contenedor.getChildren().add(cola);
                    
                }
                
            }
            
        });
        
        timeline.getKeyFrames().add(keyframe);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        
        escena.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.RIGHT) {
                    cabeza.cambiarDireccionALaDerecha();
                }
                else if (event.getCode() == KeyCode.LEFT) {
                    cabeza.cambiarDireccionALaIzquierda();
                }
                else if (event.getCode() == KeyCode.UP) {
                    cabeza.cambiarDireccionHaciaArriba();
                }
                else if (event.getCode() == KeyCode.DOWN) {
                    cabeza.cambiarDireccionHaciaAbajo();
                }
            });
        
    }
}
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
import javafx.scene.control.Label;
import javafx.scene.shape.Shape;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Juego extends Application
{
    
    private ArrayList<ParteDelCuerpo> serpiente = new ArrayList<ParteDelCuerpo>();
    private ArrayList<Comida> comida = new ArrayList<Comida>();
    private int puntos = 0;
    private int vidas = 5;
    private ArrayList<Rectangle> vidas1 = new ArrayList<Rectangle>();
    
    public static void main(String[] args){
        launch(args);
    }
    
    @Override
    public void start(Stage escenario)
    {
        Group contenedor = new Group();
        Scene escena = new Scene(contenedor, 640, 640);
        escenario.setScene(escena);
        
        Image fondo = new Image("hierba.png");
        ImageView imagen = new ImageView();
        imagen.setImage(fondo);
        imagen.setTranslateY(60);
        contenedor.getChildren().add(imagen);
        
        Cabeza cabeza = new Cabeza(100, 320, escena.getWidth(), escena.getHeight());
        serpiente.add(cabeza);
        contenedor.getChildren().add(cabeza);
        
        Cola cola1 = new Cola(serpiente.get(serpiente.size() - 1).getTranslateX(), serpiente.get(serpiente.size() - 1).getTranslateY());
        serpiente.add(cola1);
        contenedor.getChildren().add(cola1);
        
        escenario.show();
        
        Label vidas = new Label();
        vidas.setLayoutX(50);
        vidas.setLayoutY(20);
        vidas.setText("Vidas: ");
        contenedor.getChildren().add(vidas);
        
        Image spriteCorazon = new Image("corazon.png");
        
        Rectangle corazon = new Rectangle();
        corazon.setTranslateX(100);
        corazon.setTranslateY(20);
        corazon.setWidth(25);
        corazon.setHeight(25);
        corazon.setFill(new ImagePattern(spriteCorazon, 0, 0, 1, 1, true));
        vidas1.add(corazon);
        contenedor.getChildren().add(corazon);
        
        for(int v = 0; v < 4; v++){
            Rectangle corazon2 = new Rectangle();
            corazon2.setTranslateX(vidas1.get(v).getTranslateX() + 30);
            corazon2.setTranslateY(20);
            corazon2.setWidth(25);
            corazon2.setHeight(25);
            corazon2.setFill(new ImagePattern(spriteCorazon, 0, 0, 1, 1, true));
            vidas1.add(corazon2);
            contenedor.getChildren().add(corazon2);
        }
        
        
        
        Label puntuacion = new Label();
        puntuacion.setLayoutX(escena.getWidth() - 100);
        puntuacion.setLayoutY(20);
        puntuacion.setText("Puntos: " + puntos);
        contenedor.getChildren().add(puntuacion);
        
        Random aleatoriox = new Random();
        Random aleatorioy = new Random();
        
        
        Timeline timeline = new Timeline();
        KeyFrame keyframe = new KeyFrame(Duration.seconds(0.1), event -> {
            
            int i = (serpiente.size() - 1);
            while(i > 0){
                serpiente.get(i).setTranslateX(serpiente.get(i - 1).getTranslateX());
                serpiente.get(i).setTranslateY(serpiente.get(i - 1).getTranslateY());
                i--;
            }
            cabeza.moverEnX();
            cabeza.moverEnY();
            
            //Si no hay comida se genera en un lugar aleatorio de la escena
            if(comida.size() == 0){
                boolean libre = true;
                boolean anadido = false;
                
                while(libre && !anadido){
                    int x = aleatoriox.nextInt(620);
                    int y = aleatorioy.nextInt(620);
                    Comida comida1 = new Comida(x, y);
                    for(int j = 0; j < serpiente.size(); j++){
                        if(serpiente.get(j).getBoundsInParent().intersects(comida1.getBoundsInParent()) || y < 60){
                            libre = false;
                        }
                    }
                    
                    if(libre){
                        comida.add(comida1);
                        contenedor.getChildren().add(comida1);
                        anadido = true;
                    }
                }
                
            }
            
            //Si la cabeza de la serpiente choca con la comida la comida desapareze y la serpiente crece
            if(comida.size() != 0){
                if(serpiente.get(0).getBoundsInParent().intersects(comida.get(0).getBoundsInParent())){
                    comida.get(0).setFill(Color.TRANSPARENT);
                    comida.remove(0);
                    
                    Cola cola = new Cola(serpiente.get(serpiente.size() - 1).getTranslateX(), serpiente.get(serpiente.size() - 1).getTranslateY());
                    serpiente.add(cola);
                    contenedor.getChildren().add(cola);
                    
                    puntos++;
                    puntuacion.setText("Puntos: " + puntos);
                    
                }
                
            }
            
            //Si la cabeza de la serpiente choca con la cola se resta una vida, si las vidas son 0 se acaba el juego
            for(int k = 1; k < serpiente.size(); k++){
                Shape interseccion = Shape.intersect(cabeza,serpiente.get(k));
                
                if(interseccion.getBoundsInParent().getWidth() != -1){
                    while(serpiente.size() > 2){
                        serpiente.get(serpiente.size() - 1).setFill(Color.TRANSPARENT);
                        serpiente.remove(serpiente.size() - 1);
                    }
                    this.vidas--;
                    vidas1.get(vidas1.size() - 1).setFill(Color.TRANSPARENT);
                    vidas1.remove(vidas1.size() - 1);
                }
                
                if(this.vidas == 0){
                    Image gameOver = new Image("game_over.png");
                    ImageView imagenGameOver = new ImageView();
                    imagenGameOver.setImage(gameOver);
                    imagenGameOver.setTranslateX(192);
                    imagenGameOver.setTranslateY(192);
                    contenedor.getChildren().add(imagenGameOver);
                    timeline.stop();
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
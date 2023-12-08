package com.carRace;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;


public class Main extends Application {
    //baseX and baseY are used to draw the car,step is how fast the car is
    int baseX=0,baseY=150,step=1;

    @Override
    public void start(Stage stage) {
        //gui is the main container for all the gui
        BorderPane gui = new BorderPane();
        //carTrack is where the car is drawn and where it moves
        Pane carTrack = new Pane();
        gui.setCenter(carTrack);
        //create the race button
        Button race = new Button("Race");
        BorderPane.setMargin(race, new Insets(10));
        BorderPane.setAlignment(race, Pos.CENTER);
        gui.setBottom(race);
        //create a scene with a defined size
        Scene scene = new Scene(gui, 500, 193);
        //create rectangle for the car body and an other for the car roof
        Rectangle body = new Rectangle(baseX,baseY-20,50,10);
        Rectangle roof = new Rectangle(baseX+20,baseY-30,10,10);
        //create 2 Circles for the caw wheels
        Circle circle1 = new Circle(baseX+15, baseY-5, 5);
        Circle circle2 = new Circle(baseX+35, baseY-5, 5);
        //create 2 triangles for the car's front and back glass
        Polygon triangle1 = new Polygon();
        triangle1.getPoints().addAll(0.0, 10.0,10.0, 0.0,10.00, 10.0, 0.0);
        triangle1.setLayoutX(baseX+10);
        triangle1.setLayoutY(baseY-30);
        Polygon triangle2 = new Polygon();
        triangle2.getPoints().addAll(0.0, 0.0,0.0, 10.0,10.00, 10.0, 0.0);
        triangle2.setLayoutX(baseX+30);
        triangle2.setLayoutY(baseY-30);
        //set colors for the car parts
        triangle1.setFill(Color.web("2C9CFF"));
        triangle2.setFill(Color.web("2C9CFF"));
        roof.setFill(Color.web("2C9CFF"));
        body.setFill(Color.web("2CD4D4"));
        //add the parts of the car together
        carTrack.getChildren().addAll(body,roof,circle1,circle2,triangle1,triangle2);
        //create a buttom border for the car to move on
        carTrack.setStyle("-fx-font-weight: bold;-fx-border-style:hidden hidden solid hidden;-fx-border-width: 4;-fx-border-color: black;");
        stage.setTitle("Racing car simulation");
        stage.setScene(scene);
        //stop the user from resizing the window
        stage.setResizable(false);
        stage.show();
        //create a timeline to move the car
        Timeline t = new Timeline(new KeyFrame(Duration.millis(50),new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                //set the speed limitation
                if(step<0)
                    step=0;
                else if(step>10)
                    step=10;
                //move the baseX
                baseX+=step;
                //move all the components to the new location
                triangle1.setLayoutX(baseX+10);
                triangle2.setLayoutX(baseX+30);
                circle1.setLayoutX(baseX);
                circle2.setLayoutX(baseX);
                body.setX(baseX);
                roof.setX(baseX+20);
                //if the carr hits the border move it to the beginning
                if ((body.getX()+body.getWidth()) >= (scene.getWidth())) {
                    baseX = 0;
                }
            }
        }));
        //on button press move the car
        race.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                t.play();
            }
        });
        // on button release stop the car
        race.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                t.stop();
            }
        });

        t.setCycleCount(Timeline.INDEFINITE);
        //when a key in the keyboard is pressed
        scene.setOnKeyPressed(event ->{
            //if the key is up increase the speed
            if (event.getCode() == KeyCode.UP)
                step++;
            //if the key is up reduce the speed
            if (event.getCode() == KeyCode.DOWN)
                step--;
        });
    }
    public static void main(String[] args) {
        launch();
    }
}
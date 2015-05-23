package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sun.jvm.hotspot.HelloWorld;


public class Main extends Application {
    class Delta {double x,y;}

    final Delta dragDelta = new Delta();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane canvas = new Pane();
        canvas.setStyle("-fx-background-color: black;");
        canvas.setPrefSize(200,200);
        Circle circle = new Circle(50, Color.BLUE);
        circle.relocate(20, 20);
        final Rectangle rectangle = new Rectangle(100,100, Color.RED);
        rectangle.relocate(100,100);

        rectangle.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dragDelta.x = rectangle.getLayoutX() - event.getSceneX();
                dragDelta.y = rectangle.getLayoutY() - event.getSceneY();
                rectangle.setCursor(Cursor.MOVE);
            }
        });
        rectangle.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rectangle.setCursor(Cursor.HAND);
            }
        });
        rectangle.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rectangle.setLayoutX(event.getSceneX() + dragDelta.x);
                rectangle.setLayoutY(event.getSceneY() + dragDelta.y);
            }
        });
        rectangle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rectangle.setCursor(Cursor.HAND);
            }
        });

        final boolean b = canvas.getChildren().addAll(circle, rectangle);
        Scene scene = new Scene(canvas, 500, 600);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

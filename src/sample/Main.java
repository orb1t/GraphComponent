package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import jp.iboy.component.graph.layout.GraphPaneBuilder;
import jp.iboy.component.graph.model.Edge;
import jp.iboy.component.graph.model.Node;

import java.util.ArrayList;
import java.util.List;


public class Main extends Application {
    class Delta {double x,y;}

    final Delta dragDelta = new Delta();
    final Pane canvas = new Pane();


    @Override
    public void start(Stage primaryStage) throws Exception{
        Node node1 = new Node("node1", 50, 50, 100, 100);
        Node node2 = new Node("node2", 50, 50, 100, 200);
        Node node3 = new Node("node3", 50, 50, 200, 200);

        Edge edge1 = new Edge(node1, node2);
        Edge edge2 = new Edge(node1, node3);

        List<Node> nodes = new ArrayList<>();
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);

        List<Edge> edges = new ArrayList<>();
        edges.add(edge1);
        edges.add(edge2);

        Scene scene = new Scene(new GraphPaneBuilder(nodes, edges).build(), 500, 600);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

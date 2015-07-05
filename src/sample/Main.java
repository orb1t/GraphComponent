package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jp.iboy.component.graph.layout.GraphPaneBuilder;
import jp.iboy.component.graph.model.UndirectedEdge;
import jp.iboy.component.graph.model.RectangleNode;

import java.util.ArrayList;
import java.util.List;


public class Main extends Application {
    class Delta {double x,y;}

    final Delta dragDelta = new Delta();
    final Pane canvas = new Pane();


    @Override
    public void start(Stage primaryStage) throws Exception{
        RectangleNode node1 = new RectangleNode("node1", 50, 50, 100, 100);
        RectangleNode node2 = new RectangleNode("node2", 50, 50, 100, 200);
        RectangleNode node3 = new RectangleNode("node3", 50, 50, 200, 200);

        UndirectedEdge edge1 = new UndirectedEdge(node1, node2);
        UndirectedEdge edge2 = new UndirectedEdge(node1, node3);

        List<RectangleNode> nodes = new ArrayList<>();
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);

        List<UndirectedEdge> edges = new ArrayList<>();
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

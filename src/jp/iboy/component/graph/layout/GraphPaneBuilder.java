package jp.iboy.component.graph.layout;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import jp.iboy.component.graph.model.UndirectedEdge;
import jp.iboy.component.graph.model.RectangleNode;

import java.util.List;

/**
 * グラフを表示するためのpaneを作成する. <br/>
 * new GraphPaneBuilder().build()を用いてpaneを作成することができ、
 * pane上では、コンストラクタで与えたnodeとedgeが無向グラフとして表示される.
 *
 * @author  I-BOY
 */
public class GraphPaneBuilder {
    private List<? extends Node> nodes;
    private List<? extends UndirectedEdge> edges;
    private static Slider slider = new Slider(0, 300, 100);

    public static Slider getSlider() {
        return slider;
    }

    /**
     * コンストラクタ.
     * @param nodes 描画したいnode
     * @param edges 描画したいedge
     */
    public GraphPaneBuilder(List<? extends Node> nodes, List<? extends UndirectedEdge> edges) {
        this.nodes = nodes;
        this.edges = edges;
        slider.setOrientation(Orientation.VERTICAL);
        slider.setLayoutX(10);
        slider.setLayoutY(10);
    }

    public void addContextMenu(final Pane canvas, final Node node, final ContextMenu menu) {
        node.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.SECONDARY)) {
                menu.show(canvas, event.getScreenX(), event.getScreenY());
                event.consume();
            }
        });
        canvas.setOnMouseClicked(event -> menu.hide());
    }

    /**
     * 与えられた情報を元に、グラフを表示するためのJavaFX用のPaneを生成する.<br/>
     * @return グラフが描画されたPane
     */
    public Pane build() {
        final Pane canvas = new Pane();
        nodes.forEach((node) -> addContextMenu(canvas, node, RectangleNode.getContextMenu()));

        canvas.getChildren().addAll(edges);
        canvas.getChildren().addAll(nodes);

        DoubleProperty property = slider.valueProperty();
        DoubleBinding binding = property.divide(100);
        canvas.scaleXProperty().bind(binding);
        canvas.scaleYProperty().bind(binding);

        return new Pane(canvas, slider);
    }
}

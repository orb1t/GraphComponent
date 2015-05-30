package jp.iboy.component.graph.layout;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Orientation;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import jp.iboy.component.graph.model.Edge;
import jp.iboy.component.graph.model.Node;

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
    private List<? extends Edge> edges;
    private Slider slider = new Slider(0, 300, 100);

    /**
     * コンストラクタ.
     * @param nodes 描画したいnode
     * @param edges 描画したいedge
     */
    public GraphPaneBuilder(List<? extends Node> nodes, List<? extends Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
        slider.setOrientation(Orientation.VERTICAL);
        slider.setLayoutX(10);
        slider.setLayoutY(10);
    }

    /**
     * nodeのコンテキストメニューの操作を行う.<br/>
     * ここで操作を行わないと、コンテキストメニューを閉じるなどの操作が難しくなるので、
     * ここで行う.
     * @param node コンテキストメニューを追加するnode (基本的にすべてのnode)
     */
    private void addEventListener(final Pane canvas, final Node node) {
        node.setOnMouseClicked((event) -> {
            if (event.getButton().equals(MouseButton.SECONDARY)) {
                Node.getContextMenu().show(canvas, event.getScreenX(), event.getScreenY());
                event.consume();
            }
        });
        canvas.setOnMouseClicked((event) -> {Node.getContextMenu().hide();});
    }

    /**
     * 与えられた情報を元に、グラフを表示するためのJavaFX用のPaneを生成する.<br/>
     * @return グラフが描画されたPane
     */
    public Pane build() {
        final Pane canvas = new Pane();
        nodes.forEach((node) -> addEventListener(canvas, node));

        canvas.getChildren().addAll(edges);
        canvas.getChildren().addAll(nodes);

        DoubleProperty property = slider.valueProperty();
        DoubleBinding binding = property.divide(100);
        canvas.scaleXProperty().bind(binding);
        canvas.scaleYProperty().bind(binding);

        return new Pane(canvas, slider);
    }
}

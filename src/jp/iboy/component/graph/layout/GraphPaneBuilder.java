package jp.iboy.component.graph.layout;

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

    private Pane canvas;

    /**
     * コンストラクタ.
     * @param nodes 描画したいnode
     * @param edges 描画したいedge
     */
    public GraphPaneBuilder(List<? extends Node> nodes, List<? extends Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    /**
     * nodeのコンテキストメニューの操作を行う.<br/>
     * ここで操作を行わないと、コンテキストメニューを閉じるなどの操作が難しくなるので、
     * ここで行う.
     * @param node コンテキストメニューを追加するnode (基本的にすべてのnode)
     */
    private void addEventListener(final Node node) {
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
        canvas = new Pane();
        nodes.forEach((node) -> addEventListener(node));
        canvas.getChildren().addAll(edges);
        canvas.getChildren().addAll(nodes);
        return canvas;
    }
}

package jp.iboy.component.graph.model;

import javafx.scene.shape.Line;

/**
 * グラフコンポーネントでnode間のedgeを表すクラス.<br/>
 * このコンポーネントでは、このクラスもしくは、このクラスを継承したクラスをedgeとして扱う.
 *
 * @author I-BOY
 */
public class UndirectedEdge extends Line {
    /**
     * コンストラクタ. <br>
     * 引数に指定したnode間を結ぶedgeを作成する.
     * 作成されたedgeはnodeが移動されても良いように、nodeにbindされる.
     * @param source edgeの起点となるnode
     * @param target edgeの終点となるnode
     */
    public UndirectedEdge(RectangleNode source, RectangleNode target) {
        startXProperty().bind(source.layoutXProperty().add(source.getWidth()/2));
        startYProperty().bind(source.layoutYProperty().add(source.getHeight()/2));
        endXProperty().bind(target.layoutXProperty().add(target.getWidth()/2));
        endYProperty().bind(target.layoutYProperty().add(target.getHeight()/2));
    }
}

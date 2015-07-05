package jp.iboy.component.graph.model;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import jp.iboy.component.graph.layout.GraphPaneBuilder;

/**
 * グラフコンポーネントで表示されるnodeを表すクラス.<br/>
 * このコンポーネントでは、このクラスもしくは、このクラスを継承したクラスをnodeとして扱う.
 *
 * @author I-BOY
 */
public class RectangleNode extends Group {
    private double dragBaseX;
    private double dragBaseY;
    private double positionX;
    private double positionY;

    private Rectangle rectangle;

    private static ContextMenu contextMenu = new ContextMenu();

    /**
     * コンストラクタ.
     * @param label　nodeのラベル
     * @param width nodeの幅
     * @param height　nodeの高さ
     * @param locationX nodeのx座標
     * @param locationY nodeのy座標
     */
    public RectangleNode(String label, double width, double height, double locationX, double locationY) {
        this.rectangle = new Rectangle(width,height, Color.RED);
        Text text = new Text(label);
        text.setLayoutX(10);
        text.setLayoutY((int) height / 2);
        this.setLayoutX(locationX);
        this.setLayoutY(locationY);

        this.getChildren().addAll(this.rectangle, text);
        this.relocate(locationX, locationY);

        this.setOnMousePressed(event -> {
            positionX = this.getLayoutX();
            positionY = this.getLayoutY();
            dragBaseX = event.getSceneX();
            dragBaseY = event.getSceneY();
            this.setCursor(Cursor.MOVE);
        });
        this.setOnMouseDragged( (event) -> {
            double scale = GraphPaneBuilder.getSlider().getValue() / 100;
            this.setLayoutX(positionX + (event.getSceneX() - dragBaseX) / scale);
            this.setLayoutY(positionY + (event.getSceneY() - dragBaseY) / scale);
        });

    }


    /**
     * 右クリックした時のコンテキストメニューの追加.
     * @param item 追加したいメニュー
     */
    public static void addContextMenu(MenuItem item) {
        contextMenu.getItems().add(item);
    }

    /**
     * 右クリックしたときのコンテキストメニューの追加.
     * @param index メニューを追加したい位置
     * @param item 追加したいメニュー
     */
    public static void addContextMenu(int index, MenuItem item) {
        contextMenu.getItems().add(index, item);
    }

    /**
     * 右クリックした時のコンテキストメニューの追加.
     * @param items 追加したいアイテム
     */
    public static void addContextMenus(MenuItem... items) {
        contextMenu.getItems().addAll(items);
    }

    /**
     * nodeのコンテキストメニューの取得.
     * @return コンテキストメニュー
     */
    public static ContextMenu getContextMenu() {
        return contextMenu;
    }

    /**
     * nodeの幅の取得.
     * @return nodeの幅
     */
    public double getWidth() {
        return rectangle.getWidth();
    }

    /**
     * nodeの高さの取得.
     * @return nodeの高さ
     */
    public double getHeight() {
        return rectangle.getHeight();
    }

}

package sample.file.util;

import javafx.stage.FileChooser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yohei on 15/07/05.
 */
public class ExtensionFilterFactory {
    public static FileChooser.ExtensionFilter generateExtensionFilter(String descriptor, String... extensions) {
        return new FileChooser.ExtensionFilter(descriptor, extensions);
    }


}

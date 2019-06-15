

package com.golong.commlib.encrypt.base;

import java.io.Closeable;
import java.io.IOException;

public class CloseUtils {
    public static void close(Closeable closeable) {
        if (null == closeable) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

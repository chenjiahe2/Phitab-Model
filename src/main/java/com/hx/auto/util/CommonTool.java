package com.hx.auto.util;

public class CommonTool {

    /**
     * 判断是否为空
     */
    public static boolean checkNotNull(Object o) {
        boolean b = false;
        if (o != null && !"".equals(o)&&!"undefind".equals(o)) {
            b = true;
        }
        return b;
    }

	
}

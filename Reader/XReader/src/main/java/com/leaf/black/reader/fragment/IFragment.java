/**
 * @version: 1.0
 * @author: yefufeng@foxmail.com
 */

package com.leaf.black.reader.fragment;

public interface IFragment {
    void onEnter(Object data);

    void onLeave();

    void onBack();

    void onBackWithData(Object data);

    boolean processBackPressed();
}

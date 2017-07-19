/**
 * @version: 1.0
 * @author: yefufeng@foxmail.com
 */
package com.leaf.black.reader.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leaf.black.reader.R;

public class PlanFragment extends XFragment{
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base_item, container, false);
    }
}

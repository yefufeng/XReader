/**
 * @version: 1.0
 * @author: yefufeng@foxmail.com
 */
package com.leaf.black.reader.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leaf.black.reader.service.AlarmNoiserReciever;

public class XFragment extends Fragment implements IFragment,IComponentContainer{
    protected Object mDataIn;
    private boolean mFirstResume = true;

    private LifeCycleComponentManager mLifeCycleComponentManager = new LifeCycleComponentManager();

    @Override
    public void addComponent(ILifeCycleComponent component) {
        mLifeCycleComponentManager.addComponent(component);
    }

    @Override
    public void onEnter(Object data) {
        mDataIn = data;
    }

    @Override
    public void onLeave() {
        mLifeCycleComponentManager.onBecomesTotallyInvisible();
    }

    @Override
    public void onBack() {
        mLifeCycleComponentManager.onBecomesVisibleFromTotallyInvisible();
    }

    @Override
    public void onBackWithData(Object data) {
        mLifeCycleComponentManager.onBecomesVisibleFromTotallyInvisible();
    }

    @Override
    public boolean processBackPressed() {
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!mFirstResume) {
            onBack();
        }
        if (mFirstResume) {
            mFirstResume = false;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        onLeave();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLifeCycleComponentManager.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    protected void callRoutineService() {
        Intent serviceIntent = new Intent(getContext(), AlarmNoiserReciever.class);
        getContext().sendBroadcast(serviceIntent, null);
    }
}

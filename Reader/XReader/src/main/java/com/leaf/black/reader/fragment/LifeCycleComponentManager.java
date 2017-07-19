/**
 * @version: 1.0
 * @author: yefufeng@foxmail.com
 */
package com.leaf.black.reader.fragment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class LifeCycleComponentManager implements IComponentContainer {

    private HashMap<String, ILifeCycleComponent> mComponentList;

    public static void tryAddComponentToContainer
            (ILifeCycleComponent component, Object matrixContainer) {
        tryAddComponentToContainer(component, matrixContainer, true);
    }

    public static boolean tryAddComponentToContainer
            (ILifeCycleComponent component, Object matrixContainer, boolean throwEx) {
        if (matrixContainer instanceof IComponentContainer) {
            ((IComponentContainer) matrixContainer).addComponent(component);
            return true;
        } else {
            if (throwEx) {
                throw new IllegalArgumentException("componentContainerContext should implements IComponentContainer");
            }
            return false;
        }
    }

    public void addComponent(ILifeCycleComponent component) {
        if (component != null) {
            if (mComponentList == null) {
                mComponentList = new HashMap();
            }
            mComponentList.put(component.toString(), component);
        }
    }

    public void onBecomesVisibleFromTotallyInvisible() {

        if (mComponentList == null) {
            return;
        }

        Iterator<Entry<String, ILifeCycleComponent>> it = mComponentList.entrySet().iterator();
        while (it.hasNext()) {
            ILifeCycleComponent component = it.next().getValue();
            if (component != null) {
                component.onBecomesVisibleFromTotallyInvisible();
            }
        }
    }

    public void onBecomesTotallyInvisible() {
        if (mComponentList == null) {
            return;
        }
        Iterator<Entry<String, ILifeCycleComponent>> it = mComponentList.entrySet().iterator();
        while (it.hasNext()) {
            ILifeCycleComponent component = it.next().getValue();
            if (component != null) {
                component.onBecomesTotallyInvisible();
            }
        }
    }

    public void onBecomesPartiallyInvisible() {
        if (mComponentList == null) {
            return;
        }
        Iterator<Entry<String, ILifeCycleComponent>> it = mComponentList.entrySet().iterator();
        while (it.hasNext()) {
            ILifeCycleComponent component = it.next().getValue();
            if (component != null) {
                component.onBecomesPartiallyInvisible();
            }
        }
    }

    public void onBecomesVisibleFromPartiallyInvisible() {
        if (mComponentList == null) {
            return;
        }
        Iterator<Entry<String, ILifeCycleComponent>> it = mComponentList.entrySet().iterator();
        while (it.hasNext()) {
            ILifeCycleComponent component = it.next().getValue();
            if (component != null) {
                component.onBecomesVisible();
            }
        }
    }

    public void onDestroy() {
        if (mComponentList == null) {
            return;
        }
        Iterator<Entry<String, ILifeCycleComponent>> it = mComponentList.entrySet().iterator();
        while (it.hasNext()) {
            ILifeCycleComponent component = it.next().getValue();
            if (component != null) {
                component.onDestroy();
            }
        }
    }
}

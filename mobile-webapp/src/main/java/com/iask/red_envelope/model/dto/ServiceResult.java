package com.iask.red_envelope.model.dto;

import org.craigq.common.logger.LogMgr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuqiang on 15-9-1.
 *
 * @author wuqiang
 */
public class ServiceResult<T> {
    private T result;
    private List<Runnable> callbacks;
    private boolean isCallbackInvoked = false;  // callbacks是否已经被执行过了

    public ServiceResult() {
    }

    public ServiceResult(T result) {
        this.result = result;
    }

    public ServiceResult(T result, List<Runnable> callbacks) {
        this(result);
        this.callbacks = callbacks;
    }

    public ServiceResult(T result, Runnable callback) {
        this(result);
        if (callback != null) {
            List<Runnable> _callbacks = new ArrayList<Runnable>(1);
            _callbacks.add(callback);
            this.callbacks = _callbacks;
        }
    }

    public static ServiceResult build(String json) {
        return new ServiceResult(json);
    }

    public boolean isCallbackInvoked() {
        return isCallbackInvoked;
    }

    public void setIsCallbackInvoked(boolean isCallbackInvoked) {
        this.isCallbackInvoked = isCallbackInvoked;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public List<Runnable> getCallbacks() {
        return callbacks;
    }

    public void setCallbacks(List<Runnable> callbacks) {
        this.callbacks = callbacks;
    }

    public void addCallback(Runnable callback) {
        if (callback == null) {
            return;
        }
        if (this.callbacks == null) {
            this.callbacks = new ArrayList<Runnable>(1);
        }
        this.callbacks.add(callback);
    }

    /**
     * 执行所有回调代码(只能执行一次, 后续多次调用, 将不会执行callback)
     * <p/>
     * 并同时返回result
     */
    public T invokeCallbackGetResult() {
        if (!isCallbackInvoked) {
            isCallbackInvoked = true;
            if (callbacks != null && callbacks.size() > 0) {
                int size = callbacks.size();
                for (int i = 0; i < size; i++) {
                    Runnable runnable = callbacks.get(i);
                    try {
                        runnable.run();
                    } catch (Exception e) {
                        LogMgr.getLogger().error("ServiceResult callbacks[" + i + "].run() error", e);
                    }
                }
            }
        }
        return this.result;
    }
}

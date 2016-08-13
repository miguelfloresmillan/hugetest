package com.mgl.test.hugetest.services.utils;

public interface ServiceCallback<T> {
    void onResponse(T response);

    void onFailure(Exception e);
}

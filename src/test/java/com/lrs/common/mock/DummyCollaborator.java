package com.lrs.common.mock;

import java.util.Collections;

public class DummyCollaborator {
    public static int ERROR_CODE = 1;

    public DummyCollaborator() {
        // empty
    }

    public void doSomethingAsynchronously(final DummyCallback callback) {
        new Thread(() -> {
            try {
                Thread.sleep(5000);
                callback.onSuccess(Collections.EMPTY_LIST);
            } catch (InterruptedException e) {
                callback.onFail(ERROR_CODE);
                e.printStackTrace();
            }
        }).start();
    }
}

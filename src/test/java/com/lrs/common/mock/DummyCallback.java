package com.lrs.common.mock;

import java.util.List;

/**
 * 异步测试示例接口
 *
 * @author Swedish-li
 */
public interface DummyCallback {
    public void onSuccess(List<String> result);

    public void onFail(int code);
}

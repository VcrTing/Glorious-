package com.glorious.common.mvc;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Optional;

/**
 * 操作消息提醒
 *
 * @author qiong
 */
public class AjaxResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";
    /**
     * 返回内容
     */
    public static final String MSG_TAG = "msg";
    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";
    /**
     * 扩展数据
     */
    public static final String EXTRA_TAG = "extra";

    /**
     * 将 存好的 code 返回为 HttpStatus
     */
    public Optional<HttpStatus> resolveCode() {
        Optional<Object> ops = Optional.ofNullable(super.get(CODE_TAG));
        return ops.map(o -> Integer.parseInt(o.toString())).map(HttpStatus::resolve);
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
     */
    public AjaxResult() {
        super.put(CODE_TAG, HttpStatus.NOT_FOUND);
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    public AjaxResult(HttpStatus code, String msg) {
        super.put(CODE_TAG, code.value());
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public AjaxResult(HttpStatus code, String msg, Object data) {
        super.put(CODE_TAG, code.value());
        super.put(MSG_TAG, msg);
        if (data != null) super.put(DATA_TAG, data);
    }

    /**
     * 方便链式调用
     *
     * @param key   键
     * @param value 值
     * @return 数据对象
     */
    @Override
    public AjaxResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
    /**
     * 加入扩充数据
     *
     * @param value 值
     * @return 数据对象
     */
    public AjaxResult putExtra(Object value) {
        super.put(EXTRA_TAG, value);
        return this;
    }


    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static AjaxResult success() {
        return AjaxResult.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static AjaxResult success(Object data) {
        return AjaxResult.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static AjaxResult success(String msg) {
        return AjaxResult.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static AjaxResult success(String msg, Object data) {
        return new AjaxResult(HttpStatus.OK, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static AjaxResult error() {
        return AjaxResult.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static AjaxResult error(HttpStatus code, String msg) {
        return new AjaxResult(code, msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static AjaxResult error(String msg) {
        return AjaxResult.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static AjaxResult error(String msg, Object data) {
        return new AjaxResult(HttpStatus.BAD_REQUEST, msg, data);
    }

    /**
     * 返回消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static AjaxResult def(HttpStatus code, String msg, Object data) {
        return new AjaxResult(code, msg, data);
    }

    /**
     * 返回成功与否的消息
     *
     * @param isOk 是否成功
     * @param data 数据
     * @return 警告消息
     */
    public static AjaxResult restfull(boolean isOk, Object data) {
        return isOk ? AjaxResult.success(data) : AjaxResult.error("网络波动，操作失败", data);
    }
}

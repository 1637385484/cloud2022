package com.jx.springCloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LDW
 * @date 2022/3/23 18:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult {

    private Integer code;
    private String message;
    private Map<String, Object> data;

    public CommonResult(Integer code, String message) {
        this(code, message, new HashMap<>());
    }

    /*
        添加数据
     */
    public CommonResult add(String key, Object value) {
        data.put(key, value);
        return this;
    }
}

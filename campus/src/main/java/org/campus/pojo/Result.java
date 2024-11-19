package org.campus.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private String status; // SUCCESS or FAIL
    private String message; // 返回的信息
    private T data; // 泛型数据
}

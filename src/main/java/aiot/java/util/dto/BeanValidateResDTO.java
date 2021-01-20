package aiot.java.util.dto;

import lombok.Data;

@Data
public class BeanValidateResDTO {

    // 欄位
    private String field;

    // 不符合的規則
    private String ruleName;

    // 規則值
    private String ruleValue;

    // 訊息
    private String message;
}

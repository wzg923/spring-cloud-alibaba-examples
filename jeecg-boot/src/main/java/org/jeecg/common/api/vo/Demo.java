package org.jeecg.common.api.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Transient;

/**
 * @ClassName Demo
 * @Description TODO
 * @Author Administrator
 * @Date 2019/06/04 14:37
 * Version 1.0
 **/
@Data
public class Demo {
    @JsonIgnore
    private String id;
    @JSONField(serialize = false)
    private String name;

    private String address;
}

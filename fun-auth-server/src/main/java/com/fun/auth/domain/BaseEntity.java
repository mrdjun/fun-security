package com.fun.auth.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity 基类
 *
 * @author DJun
 */
@Getter
@Setter
@ToString
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 时间戳 */
    private Date createTime;
    private Date updateTime;
    /** 0-禁用 1-正常 */
    private String status;
    /** 创建者 */
    private String createBy;
    /** 更新者 */
    private String updateBy;
    /** 备注 */
    private String remark;

}

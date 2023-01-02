package com.grpn.demo.vo.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author Zijian Liao
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleUpdateReq extends RoleReq {

    @NotNull
    private Long id;
}

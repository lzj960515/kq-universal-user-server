package com.grpn.demo.vo.req;

import lombok.Data;

/**
 * @author Zijian Liao
 * @since 1.0.0
 */
@Data
public class PageReq {

    private int current = 1;

    private int size = 10;
}
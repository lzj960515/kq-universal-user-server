package com.grpn.demo.service;

import com.kqinfo.universal.test.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Zijian Liao
 * @since 1.0.0
 */
@Rollback
@Transactional
public class DemoServiceTest extends BaseTest {



    @Test
    public void createTest(){
//        Assertions.assertNotNull(result);
    }

}
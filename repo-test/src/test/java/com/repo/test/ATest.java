package com.repo.test;

import com.primeton.ft.bpm.repo.core.service.api.RepoResourceService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author weibing@primeton.com
 * @date 2019/11/5 11:42
 */
public class ATest extends AbstractServiceTest{

    @Test
    public void name() {
        System.out.println(123);
    }
    @Autowired
    private RepoResourceService service;

    @Test
    public void name1() {
        System.out.println(service);
    }
}
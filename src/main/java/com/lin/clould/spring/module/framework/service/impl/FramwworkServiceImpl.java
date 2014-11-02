package com.lin.clould.spring.module.framework.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lin.clould.spring.module.framework.service.FrameworkService;

@Service("frameworkService")
public class FramwworkServiceImpl implements FrameworkService {

	@Resource(name="frameworkDAO")
	FrameworkDAO frameworkDAO;

}

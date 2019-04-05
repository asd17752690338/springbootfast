package com.technology.springboot.hotDeploy.demo5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleHotDeployImpl extends AbstractHotDeploy {
    private Logger LOG = LoggerFactory.getLogger(SimpleHotDeployImpl.class.getName());

    @Override
    public void exec() {
            try {
                Class<?> testClass = Class.forName(Test.class.getName());
                Test test = (Test) testClass.newInstance();
                test.aa();
            } catch (Exception e) {
                LOG.debug("{}",e.getMessage());
            }

    }




}

package com.common.framework.common;

import com.common.framework.interfaces.IMobileUIElements;
import com.common.framework.interfaces.IWebUIElements;
import com.common.framework.utils.AppBaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BasePage extends AppBaseUtil {

    @Autowired
    public IWebUIElements iWebUIElements;

    @Autowired
    public IMobileUIElements iMobileUIElements;

}

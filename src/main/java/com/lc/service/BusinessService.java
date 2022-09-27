package com.lc.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lc 2022/9/15 2:36 PM
 */
public interface BusinessService {

    void chxBankExport(HttpServletRequest request, HttpServletResponse response) throws IOException;

}

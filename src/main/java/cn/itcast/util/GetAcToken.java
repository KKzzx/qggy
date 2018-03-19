package cn.itcast.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import cn.itcast.jk.domain.AcToken;
import cn.itcast.jk.service.WXPayService;

public class GetAcToken {
    /**
     * 最多循环取5次access_token，但仍然有可能为空
     *
     * @param session
     * @param wxPayService
     * @return
     */
    public static String getAcToken(HttpSession session,
                                    WXPayService wxPayService) {
        String access_token = null;
        int t = 0;
        while (t < 5 && access_token == null) {
            access_token = realGetAcToken(session.getServletContext(),
                    wxPayService);
            t++;
        }
        return access_token;
    }

    private static String realGetAcToken(ServletContext servletContext,
                                         WXPayService wxPayService) {
        AcToken acToken = (AcToken) servletContext.getAttribute("AcToken");
        if (acToken == null) {
            acToken = wxPayService.getACCESS_TOKEN();
            servletContext.setAttribute("AcToken", acToken);
        } else if (acToken != null && !acToken.isValid()) {
            acToken = wxPayService.getACCESS_TOKEN();
            servletContext.setAttribute("AcToken", acToken);
        }
        return acToken.getAccess_token();
    }
}

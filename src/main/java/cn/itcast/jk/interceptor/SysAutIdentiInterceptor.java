package cn.itcast.jk.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.jk.domain.SysUser;
import cn.itcast.jk.service.SysUserService;
import cn.itcast.jk.vo.SysUserVO;
import cn.itcast.qg.wxpay.CodeUtils;
import cn.itcast.qg.wxpay.MyWxPayConfig;

public class SysAutIdentiInterceptor implements HandlerInterceptor {

    @Resource
    SysUserService sysUserService;

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

        HttpSession session = request.getSession();
        SysUserVO user = (SysUserVO) session.getAttribute("sysUserVO");
        System.out.println(request.getRequestURI());
        if (user != null)
            return true;
        else {
            String code = request.getParameter("code");
            System.out.println("code=" + code);
            if (code != null && code.length() > 0) {
                Map<String, String> map = CodeUtils.GetOpenidAndAccessTokenFromCode(code);
                // 需要获取openid；
                String openid = map.get("openid");
                Map<String, String> paramap = new HashMap<>();
                paramap.put("openid", openid);
                List<SysUser> sys = sysUserService.find(paramap);
                if (sys != null && sys.size() > 0) {
                    SysUserVO sysUserVO = sysUserService.view(sys.get(0).getId());
                    session.setAttribute("sysUserVO", sysUserVO);

                    return true;
                } else {
                    System.out.println("去注册");
                    if (request.getRequestURL().indexOf("/phone/sys/basicinfo/sysuser/tocreate.action") >= 0) {
                        request.setAttribute("userOpenid", openid);
                        return true;
                    }

                    request.getRequestDispatcher("/Sysregister.jsp").forward(request, response);
                    return false;
                }

            } else {

                String url = MyWxPayConfig.OAUTH_AUTHORIZE_URL + "appid=" + MyWxPayConfig.APPID + "&redirect_uri=" + request.getRequestURL() + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
                //request.getRequestDispatcher(url).forward(request, response);
                response.sendRedirect(url);
                System.out.println(url);
                return false;

            }
        }
    }

}

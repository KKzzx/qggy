package cn.itcast.jk.interceptor;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.jk.domain.Student;
import cn.itcast.jk.service.StudentService;
import cn.itcast.qg.wxpay.CodeUtils;
import cn.itcast.qg.wxpay.MyWxPayConfig;

public class MainInterceptor implements HandlerInterceptor {

    @Resource
    StudentService studentService;

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
        Student user = (Student) session.getAttribute("user");
        System.out.println(request.getRequestURI());
        if (user != null) {
            System.out.println("已经登录");
            return true;
        } else {
            String code = request.getParameter("code");
            System.out.println("code=" + code);
            if (code != null && code.length() > 0) {
                Map<String, String> map = CodeUtils.GetOpenidAndAccessTokenFromCode(code);
                // 需要获取openid；
                String openid = map.get("openid");
                if (openid != null) {
                    Student student = studentService.get(openid);
                    if (student != null) {
                        System.out.println("自动登录");
                        session.setAttribute("user", student);
                        return true;
                    } else {
                        return true;
                    }
                } else {
                    String url = MyWxPayConfig.OAUTH_AUTHORIZE_URL + "appid=" + MyWxPayConfig.APPID + "&redirect_uri="
                            + request.getRequestURL()
                            + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
                    //request.getRequestDispatcher(url).forward(request, response);
                    response.sendRedirect(url);
                    System.out.println(url);
                    return false;
                }

            } else {
                String url = MyWxPayConfig.OAUTH_AUTHORIZE_URL + "appid=" + MyWxPayConfig.APPID + "&redirect_uri="
                        + request.getRequestURL() + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
                //request.getRequestDispatcher(url).forward(request, response);
                response.sendRedirect(url);
                System.out.println(url);
                return false;

            }

        }

    }

}

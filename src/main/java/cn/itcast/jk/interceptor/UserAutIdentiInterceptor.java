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

public class UserAutIdentiInterceptor implements HandlerInterceptor {

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
        //拦截黑名单用户，如果用户状态是4，拦截重定向到error页面
        if (user != null && 4 == user.getState()) {
            response.sendRedirect("/error.html");
            return false;
        }
        System.out.println(request.getRequestURI());
        if (user != null) {
            if (request.getRequestURL().indexOf("/phone/user/basicinfo/student/tocreate.action") >= 0) {
                request.getRequestDispatcher("/phone/user/main.action").forward(request, response);
                return false;
            }
            if (request.getRequestURL().indexOf("/phone/user/basicinfo/teacher/tocreateteacher.action") >= 0) {
                if (user.getState() == 1) {
                    request.getRequestDispatcher("/phone/user/becomedaoshi.action").forward(request, response);
                    return false;
                } else if (user.getState() == 0) {
                    request.getRequestDispatcher("/WEB-INF/pages/basicinfo/teacher/shenqingsucess.jsp").forward(request, response);
                    return false;

                } else {
                    request.getRequestDispatcher("/WEB-INF/pages/basicinfo/teacher/teacherpass.jsp").forward(request, response);
                    return false;

                }
            }
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
                        if (student.getState() == 4){
                            response.sendRedirect("/error.html");
                            return false;
                        }
                        System.out.println("自动登录");
                        session.setAttribute("user", student);
                        if (request.getRequestURL().indexOf("/phone/user/basicinfo/student/tocreate.action") >= 0) {
                            request.getRequestDispatcher("/phone/user/main.action").forward(request, response);
                            return false;
                        }
                        if (request.getRequestURL()
                                .indexOf("/phone/user/basicinfo/teacher/tocreateteacher.action") >= 0) {
                            if (student.getState() == 1) {
                                request.getRequestDispatcher("/phone/user/becomedaoshi.action").forward(request,
                                        response);
                                return false;
                            } else if (student.getState() == 0) {
                                request.getRequestDispatcher("/WEB-INF/pages/basicinfo/teacher/shenqingsucess.jsp").forward(request, response);
                                return false;

                            } else {
                                request.getRequestDispatcher("/WEB-INF/pages/basicinfo/teacher/teacherpass.jsp").forward(request, response);
                                return false;

                            }
                        }
                        return true;
                    } else {
                        if (request.getRequestURL().indexOf("/phone/user/basicinfo/student/tocreate.action") >= 0
                                || request.getRequestURL()
                                .indexOf("/phone/user/basicinfo/teacher/tocreateteacher.action") >= 0) {
                            request.setAttribute("userOpenid", openid);
                            return true;
                        }
                        System.out.println("去注册");

                        if (request.getParameter("id") != null) {
                            request.setAttribute("id", request.getParameter("id"));
                        }
                        request.getRequestDispatcher("/Userregister.jsp").forward(request, response);
                        return false;
                    }
                } else {
                    String id = request.getParameter("id");
                    String url = "";
                    if (id != null) {
                        url = MyWxPayConfig.OAUTH_AUTHORIZE_URL + "appid=" + MyWxPayConfig.APPID + "&redirect_uri="
                                + request.getRequestURL() + "?id=" + id
                                + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
                    } else {

                        url = MyWxPayConfig.OAUTH_AUTHORIZE_URL + "appid=" + MyWxPayConfig.APPID + "&redirect_uri="
                                + request.getRequestURL()
                                + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
                    }
                    // request.getRequestDispatcher(url).forward(request,
                    // response);
                    response.sendRedirect(url);
                    System.out.println(url);
                    return false;
                }

            } else {
                System.out.println("触发code");
                String id = request.getParameter("id");
                String url = "";
                if (id != null) {
                    url = MyWxPayConfig.OAUTH_AUTHORIZE_URL + "appid=" + MyWxPayConfig.APPID + "&redirect_uri="
                            + request.getRequestURL() + "?id=" + id
                            + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
                } else {

                    url = MyWxPayConfig.OAUTH_AUTHORIZE_URL + "appid=" + MyWxPayConfig.APPID + "&redirect_uri="
                            + request.getRequestURL()
                            + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
                }
                // request.getRequestDispatcher(url).forward(request, response);
                response.sendRedirect(url);
                System.out.println(url);
                return false;

            }

        }

    }

}

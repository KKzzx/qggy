package cn.itcast.jk.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.jk.vo.SysUserVO;

public class LoginInterceptor implements HandlerInterceptor {

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
        SysUserVO sysUserVO = (SysUserVO) session.getAttribute("sysUserVO");
//		System.out.println(request.getRequestURL());
//		System.out.println(request.getContextPath());
        if (sysUserVO != null)
            return true;
        else {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return false;
        }
    }

}

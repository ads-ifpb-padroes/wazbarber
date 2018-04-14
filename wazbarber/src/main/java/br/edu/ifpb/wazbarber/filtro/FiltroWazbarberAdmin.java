package br.edu.ifpb.wazbarber.filtro;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jozimar
 */
//@WebFilter(filterName = "FiltroWazbarberAdmin", urlPatterns = {"/areaadmin.xhtml"})
//public class FiltroWazbarberAdmin implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response,
//            FilterChain chain)
//            throws IOException, ServletException {
//
//        HttpSession session = ((HttpServletRequest) request).getSession();
//
//        Object admin = (Object) session.getAttribute("admin");
//
//        if (admin == null) {
//            ((HttpServletResponse) response).sendRedirect("loginadmin.xhtml");
//        } else {
//            chain.doFilter(request, response);
//        }
//    }
//
//    @Override
//    public void destroy() {
//    }
//
//}

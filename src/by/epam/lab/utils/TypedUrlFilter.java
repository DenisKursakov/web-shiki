package by.epam.lab.utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.FilterChain;

@WebFilter({"/start", "/result"})
public class TypedUrlFilter implements Filter {
    private static final String HEADER = "referer";

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String referer = httpRequest.getHeader(HEADER);
        if (referer == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath());
            return;
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void destroy() {
    }

}

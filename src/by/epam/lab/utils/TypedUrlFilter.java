package by.epam.lab.utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/prog/*")
public class TypedUrlFilter implements Filter {
    private static final String HEADER = "referer";

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String referer = httpRequest.getHeader(HEADER);
        if (referer == null) {
            String[] urlPath = httpRequest.getRequestURI().split(ConstantsJSP.SLASH);
            System.out.println(httpRequest.getRequestURI());
            String url = ConstantsJSP.PROG_URL_FOR_FILTER + urlPath[urlPath.length - 1];
            System.out.println(url);
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) {
    }

    public void destroy() {
    }

}

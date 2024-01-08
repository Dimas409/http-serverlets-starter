package http.socket.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebFilter(value = "/*",
            initParams = {
                    @WebInitParam(name = "param1", value = "paramValue")
            },
            dispatcherTypes = DispatcherType.REQUEST
)
public class CharsetFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        chain.doFilter(request, response);
        System.out.println();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}

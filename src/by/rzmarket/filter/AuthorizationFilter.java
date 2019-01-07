package by.rzmarket.filter;

import by.rzmarket.entity.Role;
import by.rzmarket.entity.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter(servletNames = {"allUsers", "adminChangeUser", "processOrder", "reservedOrder", "finishedOrder", "addCountProduct"})
public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (isUserAdmin(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect("/index.jsp");
        }
    }

    public boolean isUserAdmin(ServletRequest servletRequest) {
        User user = (User) ((HttpServletRequest) servletRequest).getSession().getAttribute("currentUser");
        return Objects.nonNull(user) && Role.ADMIN.getName().equals(user.getRole().getName());
    }

}

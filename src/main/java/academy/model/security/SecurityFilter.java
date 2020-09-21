package academy.model.security;

import java.io.IOException;

import javax.servlet.DispatcherType;
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import academy.model.pojo.Feedback;
import academy.model.pojo.User;

@WebFilter(dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ERROR }, urlPatterns = { "/views/private/*" })

public class SecurityFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger("appAcademy-log");

    public void init(FilterConfig fConfig) throws ServletException {

	LOGGER.info("Security filter initited");

    }

    public void destroy() {

	LOGGER.info("Security filter destroyed");

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

	// Important: It must be parsed from ServletRequest to HttpServletRequest
	HttpServletRequest httpRequest = (HttpServletRequest) request;
	HttpServletResponse httpResponse = (HttpServletResponse) response;

	// Get user values from ession
	User userLogin = (User) httpRequest.getSession().getAttribute("userLoginDetails");

	// Get the root path of the app
	String rootPath = httpRequest.getContextPath();

	// Creating new feedback object
	Feedback feedback = new Feedback();

	// Get user session
	HttpSession session = httpRequest.getSession();

	if (userLogin == null) { // User not logged or session expired -> Go to login

	    // Create feedback
	    feedback = new Feedback("danger", "You are not logged or not authorized");
	    session.setAttribute("feedback", feedback);

	    httpResponse.sendRedirect(rootPath + "/views/login.jsp"); // Absolute path

	} else {

	    // Everything ok -> Keep moving
	    chain.doFilter(request, response);

	}

    }

}

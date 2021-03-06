package net.ramso.dita.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ramso.dita.security.LoginBean;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(description = "Se validan todas las paginas", urlPatterns = { "/*" })
public class LoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest req = (HttpServletRequest) request;
		final HttpServletResponse res = (HttpServletResponse) response;

		final LoginBean loginBean = (LoginBean) req.getSession().getAttribute(
				"loginBean");

		final String urlStr = req.getRequestURL().toString().toLowerCase();
		noProteger(urlStr);
		if (noProteger(urlStr)) {
			chain.doFilter(request, response);
			return;
		}

		if ((loginBean == null) || !loginBean.estaLogeado()) {
			res.sendRedirect(req.getContextPath() + "/login.xhtml");
			return;
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	private boolean noProteger(String urlStr) {

		if (urlStr.endsWith("login.xhtml")) {
			return true;
		}
		if (urlStr.indexOf("/javax.faces.resource/") != -1) {
			return true;
		}
		return false;
	}

}

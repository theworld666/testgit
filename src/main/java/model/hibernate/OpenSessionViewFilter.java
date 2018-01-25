package model.hibernate;

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

import org.hibernate.SessionFactory;

@WebFilter(urlPatterns = { "/*" })
public class OpenSessionViewFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		try {
			sessionFactory.getCurrentSession().beginTransaction();
			chain.doFilter(request, response);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			chain.doFilter(request, response);
		}

	}

	private FilterConfig filterConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void destroy() {

	}

}

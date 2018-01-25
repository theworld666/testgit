package model.hibernate;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.hibernate.SessionFactory;

@WebListener
public class SessionFactoryListener implements ServletContextListener {

	private SessionFactory sessionFactory;
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
	    System.out.println("session begin");
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		if(sessionFactory!=null) {
			sessionFactory.close();
			System.out.println("session close");
		}
	}
}

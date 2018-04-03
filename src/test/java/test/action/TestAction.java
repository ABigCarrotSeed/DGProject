package test.action;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import test.domain.User;
import test.utils.HibernateUtils;

@Service
@Transactional
public class TestAction extends ActionSupport implements ModelDriven<User>{
	@Resource
	private SessionFactory sessionFactory;
	private User user;
	
	public User getModel() {
		// TODO Auto-generated method stub
		if(user==null){
			user = new User();
		}
		return user;
	}
	
	public String testAction(){
//		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query= session.createQuery("from User");
		List<User> list = query.list();
	//	transaction.commit();
		user = list.get(0);
		ActionContext.getContext().getValueStack().push(user);
		ActionContext.getContext().getSession().put("user", user);
		
		return "sss";
	}
}

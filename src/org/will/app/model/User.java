package org.will.app.model;

import java.util.List;

import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.will.app.db.DBServiceProvider;


@Entity
@Table(name="user_inf")
public class User
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Integer isuid;
	
	private String name;
	
	private String actor;
	
	private String smiscount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIsuid() {
		return isuid;
	}

	public void setIsuid(Integer isuid) {
		this.isuid = isuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public static User findUser(List<User> users,String id)
	{

		for (User u : users)
		{
			//getIsuid()返回的是integer对象，id是String型对象，就算值一样，但是由于类型不同，系统还是认为不同，特别注意，equals对比不同的对象不报错
			if(u.getIsuid().toString().equals(id))
			{
				return u;
			}
			
		}
		
		return null;
	}
	
	public static List<User> getAllUsers()
	{
		
		SessionFactory sf = DBServiceProvider.getSessionFactoryVersion5();
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();		
		@SuppressWarnings("unchecked")
		List<User> p =  sess.createQuery("select u from User u").getResultList();		
		tx.commit();
		sess.close();
		
		return p;
	}

	public String getActor()
	{
		return actor;
	}

	public void setActor(String actor)
	{
		this.actor = actor;
	}

	public String getSmiscount()
	{
		return smiscount;
	}

	public void setSmiscount(String smiscount) {
		this.smiscount = smiscount;
	}











	
}

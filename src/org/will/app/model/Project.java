package org.will.app.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.will.app.db.DBServiceProvider;

@Entity
@Table(name="project_inf")
public class Project
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String pkey;
	
	private String pname;
	
	private String pleader;
	
	public static List<Project> getAllProjectInDB()
	{
		
		SessionFactory sf = DBServiceProvider.getSessionFactoryVersion5();
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();		
		@SuppressWarnings("unchecked")
		List<Project> projects =  sess.createQuery("select p from Project p").getResultList();		
		tx.commit();
		sess.close();		
		return projects;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPkey() {
		return pkey;
	}

	public void setPkey(String pkey) {
		this.pkey = pkey;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPleader() {
		return pleader;
	}

	public void setPleader(String pleader) {
		this.pleader = pleader;
	}
	
}

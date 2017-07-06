package org.will.app.db;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyComponentPathImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class DBServiceProvider
{
	static StandardServiceRegistry standardRegistry = null;
	static SessionFactory sessionFactory = null;
	
	
	private DBServiceProvider()
	{
	
	}
	
	public static SessionFactory getSessionFactoryVersion5()
	{
		
		if(standardRegistry == null)
		{
			StandardServiceRegistry standardRegistry = 	
				new StandardServiceRegistryBuilder().configure().build();
			Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder()
				.applyImplicitNamingStrategy(ImplicitNamingStrategyComponentPathImpl.INSTANCE).build();
		
			sessionFactory = metadata.getSessionFactoryBuilder().build();
		}
		
		return sessionFactory;
		
	}
	
	public static void closeSession()
	{
		sessionFactory.close();
		sessionFactory = null;
	}
	

}

package my.projects.contactbook.server.dao;

import java.util.List;
import my.projects.contactbook.shared.model.Contact;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

@Repository
public class ContactBookDAO {

	@Autowired
	private HibernateTemplate hibernate;
	LocalSessionFactoryBean lsfb;

	
	public Contact get(Long id) {
		return hibernate.get(Contact.class,id);
	}
		
	public void delete(Contact entity) {
		hibernate.delete(entity);
	}
	
	public Long insert(Contact entity) {
		return (Long) hibernate.save(entity);
	}
	
	
	public void update(Contact entity) {
		 hibernate.update(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<Contact> list() {
		//runSchemaGeneration();
		String queryStr = "SELECT c FROM Contact c order by c.id";
		System.out.println(queryStr);
		Query query = getCurrentSession().createQuery(queryStr);
		return (List<Contact>)query.list();
	}
	
	public int listSize() {
		String queryStr = "SELECT c FROM Contact c order by c.id";
		System.out.println(queryStr);
		Query query = getCurrentSession().createQuery(queryStr);
		return query.list().size();
	}
	
	@SuppressWarnings("unchecked")
	public List<Contact> listByQuery(String q,int pageNum) {
		Query query;
		q=q.trim();
		
		String queryStr = "SELECT c FROM Contact c where lower(c.name) like '%"+q.toLowerCase()+"%'  order by c.id";
		System.out.println(queryStr);
		query = getCurrentSession().createQuery(queryStr);
		
		
		return (List<Contact>)query.list();
	}

	
	private Session getCurrentSession() {
		return hibernate.getSessionFactory().getCurrentSession();
	}

	     
	public void runSchemaGeneration() {
	  SchemaExport export 
	    = new SchemaExport(lsfb.getConfiguration());
	  export.setOutputFile("import.sql");
	  export.setDelimiter(";");
	  export.execute(true, false, false, true);
	}
}





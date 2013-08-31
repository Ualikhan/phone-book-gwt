package my.projects.contactbook.client;

import java.util.List;
import my.projects.contactbook.shared.model.Contact;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("myservices/contactBookService")
public interface GreetingService extends RemoteService {
	//public Contact get(Long id);
	public List<Contact> getContactList(int pageNum);
	public List<Contact> getContactListByQuery(String query,int pageNum);
	public void update(Contact contact);
	public Long insert(Contact contact);
	public void delete(Contact contact);	
	public int getContactListSize();
	public Contact get(Long id);
	
}

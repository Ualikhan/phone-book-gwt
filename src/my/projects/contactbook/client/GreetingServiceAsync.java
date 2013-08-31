package my.projects.contactbook.client;

import java.util.List;
import my.projects.contactbook.shared.model.Contact;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {

	void delete(Contact contact, AsyncCallback<Void> callback);

	void get(Long id, AsyncCallback<Contact> callback);

	void getContactList(int pageNum, AsyncCallback<List<Contact>> callback);

	void update(Contact contact, AsyncCallback<Void> callback);
	
	void insert(Contact contact, AsyncCallback<Long> callback);

	void getContactListByQuery(String query, int pageNum,
			AsyncCallback<List<Contact>> callback);
	void getContactListSize(AsyncCallback<Integer> callback);


}

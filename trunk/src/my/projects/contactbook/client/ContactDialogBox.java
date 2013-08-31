package my.projects.contactbook.client;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import java.util.Locale;

import javax.swing.text.DefaultFormatter;

import my.projects.contactbook.shared.FieldVerifier;
import my.projects.contactbook.shared.model.Contact;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.shared.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;

public class ContactDialogBox extends DialogBox implements ClickHandler {
	 
	DialogBox current;
	Grid phonesPanel;
	InlineLabel birthdayLabel;
	DateBox birthday;
	InlineLabel nameLabel;
	TextBox name;
	InlineLabel addressLabel;
	TextBox address;
	InlineLabel phoneLabel;
	TextBox phone;
	Button okButton;
	Button closeButton;
	HorizontalPanel bottomPanel;
	FlexTable flexTable;
    int dialogX;
    String errorName="Enter valid name!";
    String errorBirthday="Enter valid birthday!";
    String errorAddress="Enter valid address!";
	
    
		  public ContactDialogBox(String action) {
			  setText("Add contact");
			  current=this;
			  birthdayLabel=new InlineLabel("Birthday");
			  birthday=new DateBox();
			  nameLabel=new InlineLabel("Name");
			  name=new TextBox();
			  addressLabel=new InlineLabel("Address");
			  address=new TextBox();
			  phonesPanel=new Grid(0, 5);
			  phoneLabel=new InlineLabel("Phone");
			  phone=new TextBox();
			  
			  okButton = new Button("OK");
			  closeButton = new Button("Close", this);
			  bottomPanel=new HorizontalPanel();
			  flexTable = new FlexTable();
			  dialogX=getPopupLeft();
			  
			  name.addClickHandler(this);
			  DateTimeFormat format = DateTimeFormat.getFormat("dd/MM/yyyy");

			  birthday.setFormat(new DateBox.DefaultFormat(format));
			  phone.addClickHandler(this);
			  nameLabel.setStyleName("nameLabel",true);
			  birthdayLabel.setStyleName("nameLabel",true);
			  name.setStyleName("nameText",true);
			  birthday.setStyleName("birthdayText",true);
			  phoneLabel.setStyleName("phonesLabel",true);
				
			  if(action.equals("add")){
			
			
		    okButton.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					// TODO Auto-generated method stub
					boolean validName=true;
					boolean validBirthday=true;
					boolean validAddress=true;
					final List<Contact> list = ContactBook.dataProvider.getList();
					final Contact c=new Contact();
					if(FieldVerifier.isValidName(name.getValue()) && !name.getValue().equals(errorName))
					c.setName(name.getValue());
					else validName=false;
					
					c.setAddress(address.getValue());
					c.setBirthday(birthday.getValue());
				    
					c.setNumber(phone.getValue());
					if(validName && validBirthday){
			        ContactBook.service.insert(c, new AsyncCallback<Long>() {
						
						@Override
						public void onSuccess(Long result) {
							c.setId(result);
							list.add(c);
							ContactBook.addNewContact();
							ContactBook.table.redraw();
						}
						
						@Override
						public void onFailure(Throwable caught) {
							Window.alert(caught.getMessage());
						}
					});
			        hide();
					}
					
					else if(!validName){
						name.setValue("Enter valid name!");
						name.setStyleName("errorField");
						
					}
					}
				
			});
		    
			  
		    //bottomPanel.setStyleName("bottomPanel");
		    //okButton.setStyleName("okButtonDialog");
		    //closeButton.setStyleName("closeButtonDialog");
		    DOM.setElementAttribute(bottomPanel.getElement(), "id", "bottomPanel");
		    DOM.setElementAttribute(okButton.getElement(), "id", "okButtonDialog");
		    DOM.setElementAttribute(closeButton.getElement(), "id", "closeButtonDialog");
		    
		    bottomPanel.add(okButton);
		    bottomPanel.add(closeButton);
		    
		    flexTable.setWidget(0, 0, nameLabel);
		    flexTable.setWidget(0, 1, name);
		    flexTable.setWidget(1, 0, birthdayLabel);
		    flexTable.setWidget(1, 1, birthday);
		    flexTable.setWidget(2, 0, addressLabel);
		    flexTable.setWidget(2, 1, address);
		    flexTable.setWidget(3, 0, phoneLabel);
		    flexTable.setWidget(3, 1, phone);
		    flexTable.setWidget(4, 0, phonesPanel);
		    flexTable.setWidget(5, 0, bottomPanel);
		    
		   
			  
		    
		    flexTable.setStyleName("panel flexTable");
		    flexTable.getFlexCellFormatter().setColSpan(4, 0, 3);
		    flexTable.getFlexCellFormatter().setColSpan(5, 0, 3);
		    
		    for (int i = 0; i < flexTable.getRowCount(); i++) {
		        for (int j = 0; j < flexTable.getCellCount(i); j++) {
		            if ((j % 2) == 0) {
		                flexTable.getFlexCellFormatter()
		                         .setStyleName(i, j, "tableCell-even");
		            } else {
		                flexTable.getFlexCellFormatter()
		                         .setStyleName(i, j, "tableCell-odd");
		            }
		        }
		    }

		    setWidget(flexTable);
		  }

			  
			  else if(action.equals("edit")){
				    setText("Edit contact");
				    
				    List<Contact> list = ContactBook.dataProvider.getList();

				    for(Contact c:list)
				    	if(c.getId()==ContactBook.selectedIndex){
				    		birthday.setValue(c.getBirthday());
				    		name.setValue(c.getName());
				    		address.setValue(c.getAddress());
				    		phone.setValue(c.getNumber());
				    	}
				    
				   
				    
				    okButton.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							// TODO Auto-generated method stub
							List<Contact> list = ContactBook.dataProvider.getList();
							boolean validName=true;
							boolean validBirthday=true;
							
							for(Contact c:list)
						    	if(c.getId()==ContactBook.selectedIndex){
						    if(FieldVerifier.isValidName(name.getValue()) && !name.getValue().equals(errorName))		
						    	c.setName(name.getValue());
						    else validName=false;
						    c.setAddress(address.getValue());
						    c.setBirthday(birthday.getValue());
							c.setNumber(phone.getValue());
							
								
						    if(validName && validBirthday){
						        
						    	ContactBook.service.update(c, new AsyncCallback<Void>() {

									@Override
									public void onFailure(Throwable caught) {
										// TODO Auto-generated method stub
										Window.alert(caught.getMessage());
									}

									@Override
									public void onSuccess(Void result) {
										// TODO Auto-generated method stub
										ContactBook.table.redraw();
										
									}
								});	
						    
						    hide();
						    }
						    else if(!validName){
								name.setValue("Enter valid name!");
								name.setStyleName("errorField");
									
						    }
							
						    	}
						}
					});
				    
				    bottomPanel.add(okButton);
				    bottomPanel.add(closeButton);
				    
				    flexTable.setWidget(0, 0, nameLabel);
				    flexTable.setWidget(0, 1, name);
				    flexTable.setWidget(1, 0, birthdayLabel);
				    flexTable.setWidget(1, 1, birthday);
				    flexTable.setWidget(2, 0, addressLabel);
				    flexTable.setWidget(2, 1, address);
				    
				    flexTable.setWidget(3, 0, phoneLabel);
				    flexTable.setWidget(3, 1, phone);
				    flexTable.setWidget(4, 0, phonesPanel);
				    flexTable.setWidget(5, 0,bottomPanel);
				    
				  
				    //bottomPanel.setStyleName("bottomPanel");
				    //okButton.setStyleName("okButtonDialog");
				    //closeButton.setStyleName("closeButtonDialog");
				    DOM.setElementAttribute(bottomPanel.getElement(), "id", "bottomPanel");
				    DOM.setElementAttribute(okButton.getElement(), "id", "okButtonDialog");
				    DOM.setElementAttribute(closeButton.getElement(), "id", "closeButtonDialog");
				    
				    
				    flexTable.setStyleName("panel flexTable");
				    flexTable.getFlexCellFormatter().setColSpan(4, 0, 3);
				    flexTable.getFlexCellFormatter().setColSpan(5, 0, 3);
				    
				    for (int i = 0; i < flexTable.getRowCount(); i++) {
				        for (int j = 0; j < flexTable.getCellCount(i); j++) {
				            if ((j % 2) == 0) {
				                flexTable.getFlexCellFormatter()
				                         .setStyleName(i, j, "tableCell-even");
				            } else {
				                flexTable.getFlexCellFormatter()
				                         .setStyleName(i, j, "tableCell-odd");
				            }
				        }
				    }
				    
				    
				    setWidget(flexTable);
				  }
			  
		  }
		  public void setNumberText(Object ldata,String num){
			  for(int i=0;i<phonesPanel.getRowCount();i++){
				  if(phonesPanel.getWidget(i, 1).getLayoutData().equals(ldata)){
					TextBox number = (TextBox) phonesPanel.getWidget(i,1);
			  		number.setValue(num);
				  }
		  }
		  }
		  public void setStyle(){
			  for(int i=0;i<phonesPanel.getRowCount();i++){
				  
					Button sender =  (Button) phonesPanel.getWidget(i,4);
			  		sender.setStyleName("chooseNumberButton");
				  
		  }
			  current.setPopupPosition(getPopupLeft()+200, getPopupTop());
		  }
		 
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			if(event.getSource()==closeButton)
			hide();
			
			if(event.getSource()==name)
				if(name.getValue().equals(errorName)){
					name.setValue("");
					name.removeStyleName("errorField");
						
				}
			
			if(event.getSource()==birthday)
				if(birthday.getValue().equals(errorBirthday)){
					birthday.removeStyleName("errorField");
			
				}
		}
		 
		}


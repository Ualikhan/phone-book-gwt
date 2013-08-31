package my.projects.contactbook.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

public class ChoosePhoneNumberDialogBox extends DialogBox implements ClickHandler{
	
		  HorizontalPanel mainPanel;
		  InlineLabel countryLabel;
		  InlineLabel cityLabel;
		  InlineLabel numberLabel;
		  ListBox countryList;
		  ListBox cityList;
		  TextBox countryCode;
		  TextBox cityCode;
		  TextBox numberText;
		  Label infoLabel;
		  FlexTable table;
		  Button closeButton;
		  HorizontalPanel bottomPanel;
		  ContactDialogBox parent;
		  Object layoutData;
		  int maxScroll;
		  
		 
		  public ChoosePhoneNumberDialogBox(){
			  this.parent=(ContactDialogBox) parent;
			  countryLabel=new InlineLabel("Code of country");
			  cityLabel=new InlineLabel("Code of city");
			  numberLabel=new InlineLabel("Number");
			  mainPanel=new HorizontalPanel();
			  countryList=new ListBox();
			  countryCode=new TextBox();
			  cityCode=new TextBox();
			  numberText=new TextBox();
			  infoLabel=new Label();
			  table=new FlexTable();
			  cityList=new ListBox();
			  maxScroll=0;
			  bottomPanel=new HorizontalPanel();
			  closeButton = new Button();
		
			  countryCode.setMaxLength(4);
			  cityCode.setMaxLength(7);
			  table.setWidget(0, 0, countryLabel);
			  table.setWidget(0, 1, cityLabel);
			  table.setWidget(0, 2, numberLabel);
			  table.setWidget(1, 0, countryList);
			  table.setWidget(1, 1, cityList);
			  table.setWidget(1, 2, infoLabel);
			  table.setWidget(2, 0, countryCode);
			  table.setWidget(2, 1, cityCode);
			  table.setWidget(2, 2, numberText);
			  
			  countryLabel.setStyleName("countryLabel");
			  cityLabel.setStyleName("cityLabel");
			  numberLabel.setStyleName("numberLabel");
			  countryList.setStyleName("countryList");
			  cityList.setStyleName("cityList");
			  countryCode.setStyleName("countryCode",true);
			  cityCode.setStyleName("cityCode",true);
			  numberText.setStyleName("numberText",true);
			  closeButton.addClickHandler(this);
			  closeButton.setStyleName("closeButtonPhone");
			  mainPanel.add(closeButton);
			  mainPanel.add(table);
			  mainPanel.setHeight("100%");

			  setWidget(mainPanel);
			 
		  }

		public void setArgument1(Object layoutData) {
			// TODO Auto-generated constructor stub
			this.layoutData=layoutData;
		}
		public void setArgument2(DialogBox parent) {
			// TODO Auto-generated constructor stub
		this.parent=(ContactDialogBox) parent;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			if(event.getSource()==closeButton){
				String phoneNum="";
				if(countryCode.getValue()!=null)
					phoneNum+=countryCode.getValue();
				if(cityCode.getValue()!=null)
					phoneNum+=cityCode.getValue();
				if(numberText.getValue()!=null)
					phoneNum+=numberText.getValue();
				this.parent.setNumberText(layoutData,phoneNum);
				this.parent.setStyle();
				hide();
			
			}
		}
}

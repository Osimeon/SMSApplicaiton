package com.mw.sms.ui.forms;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.mw.sms.dbms.Contacts;
import com.mw.sms.dbms.DBMS;
import com.mw.sms.ui.SMSApplication;
import com.mw.sms.ui.images.Loader;


@SuppressWarnings("serial")
public class ContactsForm extends javax.swing.JPanel {
    public ContactsForm() {
        initComponents();
    }
                       
    private void initComponents(){
        jScrollPane1 = new javax.swing.JScrollPane();
        tblContacts = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnCreate = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        contacts = new DefaultTableModel(){
        	@Override
        	public boolean isCellEditable(int row, int column){
        		return column == 3;
        	}
        };
        
        DBMS db = new DBMS();
        contacts.addColumn("Contact ID");
        contacts.addColumn("Contact Name");
        contacts.addColumn("Phone Number");
        
        //Looping through all contacts
        List<Contacts> mContacts = db.getContacts();
        for(Contacts contact: mContacts){
        	String [] tmp = {String.valueOf(contact.getID()), contact.getContactName(), contact.getPhoneNumber()};
        	contacts.addRow(tmp);
        }
        
        btnCreate.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				JInternalFrame frame = new JInternalFrame("Create New Contact", false, true, false, true);
				CreateContactForm conForm = new CreateContactForm(frame);
				Container container = frame.getContentPane();
				container.add(new JScrollPane(conForm), BorderLayout.CENTER);
				frame.setSize(350, 150);
				int offset = 30 * SMSApplication.getDesktop().getAllFrames().length;
				frame.setLocation(offset, offset);
				
				SMSApplication.getDesktop().add(frame);
				frame.setFrameIcon(new ImageIcon(Loader.load("createcontact.png")));
				frame.setVisible(true);
			}
        	
        });
        
        btnDelete.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tblContacts.getSelectedRow();
				int col = tblContacts.getSelectedColumn();
				Object data = (Object)tblContacts.getValueAt(row, col);
                int conid = Integer.valueOf(data.toString());
                DBMS db = new DBMS();
                Contacts con = db.getContact(conid);
                int selectedOption = JOptionPane.showConfirmDialog(null, "Delete " + con.getContactName() + "?", "Delete Contact", JOptionPane.YES_NO_OPTION);
                if(selectedOption == JOptionPane.YES_OPTION){
                	db.deleteContact(con);
                	contacts.removeRow(tblContacts.getSelectedRow());
                }
			}
        	
        });
        
        btnEdit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tblContacts.getSelectedRow();
				int col = tblContacts.getSelectedColumn();
				Object data = (Object)tblContacts.getValueAt(row, col);
                int conid = Integer.valueOf(data.toString());
                DBMS db = new DBMS();
                Contacts con = db.getContact(conid);
                JInternalFrame frame = new JInternalFrame("Edit Contact", false, true, false, true);
				EditContact conForm = new EditContact(frame);
				conForm.setId(String.valueOf(con.getID()));
				conForm.setContact(con.getContactName());
				conForm.setPhone(con.getPhoneNumber());
				Container container = frame.getContentPane();
				container.add(new JScrollPane(conForm), BorderLayout.CENTER);
				frame.setSize(400, 210);
				int offset = 30 * SMSApplication.getDesktop().getAllFrames().length;
				frame.setLocation(offset, offset);
				
				SMSApplication.getDesktop().add(frame);
				frame.setFrameIcon(new ImageIcon(Loader.load("createcontact.png")));
				frame.setVisible(true);
			}        	
        });
        
        tblContacts.setModel(contacts);
        jScrollPane1.setViewportView(tblContacts);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnCreate.setText("Create");

        btnEdit.setText("Edit");
        btnEdit.setToolTipText("");

        btnDelete.setText("Delete");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCreate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCreate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDelete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }
    
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblContacts;
    private DefaultTableModel contacts;                 
}

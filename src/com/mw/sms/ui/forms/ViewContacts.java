package com.mw.sms.ui.forms;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mw.sms.dbms.Contacts;
import com.mw.sms.dbms.DBMS;

@SuppressWarnings("serial")
public class ViewContacts extends javax.swing.JPanel{
	CreateMessageForm mThis;
	
    public ViewContacts(CreateMessageForm host) {
        initComponents();
        mThis = host;
    }
    
    private void initComponents(){

        jScrollPane1 = new javax.swing.JScrollPane();
        tblContacts = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
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

        List<Contacts> mContacts = db.getContacts();
        for(Contacts contact: mContacts){
        	String [] tmp = {String.valueOf(contact.getID()), contact.getContactName(), contact.getPhoneNumber()};
        	contacts.addRow(tmp);
        }
        
        tblContacts.setModel(contacts);
        
        tblContacts.addMouseListener(new MouseAdapter(){
        	@Override
        	public void mouseClicked(MouseEvent e){
        		if(e.getClickCount() == 2){
        			JTable target = (JTable) e.getSource();
        			int row = target.getSelectedRow();
                    int col = target.getSelectedColumn();
                    Object data = (Object)target.getValueAt(row, col);
                    int conid = Integer.valueOf(data.toString());
                    DBMS db = new DBMS();
                    Contacts con = db.getContact(conid);
                    mThis.setPhone(con.getPhoneNumber());
                    mThis.setContact(con.getContactName());
                    mThis.getDialog().dispose();
        		}
        	}
        });
        
        jScrollPane1.setViewportView(tblContacts);

        jLabel1.setText("Search Contact:");
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearch)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblContacts;
    private javax.swing.JTextField txtSearch;
    private DefaultTableModel contacts;
}
package com.mw.sms.ui.forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import com.mw.sms.dbms.Contacts;
import com.mw.sms.dbms.DBMS;

@SuppressWarnings("serial")
public class CreateContactForm extends javax.swing.JPanel{
	JInternalFrame mFrame;
	
    public CreateContactForm(JInternalFrame f){
        initComponents();
        mFrame = f;
    }
                          
    private void initComponents(){
        lblContact = new javax.swing.JLabel();
        lblPhone = new javax.swing.JLabel();
        txtContactName = new javax.swing.JTextField();
        txtMobilePhone = new javax.swing.JTextField();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        lblContact.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblContact.setText("Contact Name:");

        lblPhone.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPhone.setText("Phone Number:");

        btnOk.setText("Ok");

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                btnCancelActionPerformed(evt);
            }
        });
        
        btnOk.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				DBMS db = new DBMS();
				if(txtContactName.getText().isEmpty() && txtMobilePhone.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Please Fill All Details Before Saving!", "Blank Fields", JOptionPane.ERROR_MESSAGE);
				}
				else{
					Contacts con = new Contacts();
					con.setContactName(txtContactName.getText());
					con.setPhoneNumber(txtMobilePhone.getText());
					db.insertContact(con);
					JOptionPane.showMessageDialog(null, "Contact Was Successfully Created!");
					txtContactName.setText("");
					txtMobilePhone.setText("");
				}
			}
        	
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblContact, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtContactName))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPhone)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMobilePhone)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                                .addComponent(btnCancel)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContact)
                    .addComponent(txtContactName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPhone)
                    .addComponent(txtMobilePhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk)
                    .addComponent(btnCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }                       

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {
    	mFrame.dispose();
    }
                    
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOk;
    private javax.swing.JLabel lblContact;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JTextField txtContactName;
    private javax.swing.JTextField txtMobilePhone;                  
}

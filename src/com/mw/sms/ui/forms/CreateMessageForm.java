package com.mw.sms.ui.forms;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.mw.sms.ui.SMSApplication;
import com.mw.sms.ui.images.Loader;

@SuppressWarnings("serial")
public class CreateMessageForm extends javax.swing.JPanel {
	CreateMessageForm mInstance;
	
    public CreateMessageForm(){
        initComponents();
        mInstance = this;
    }
    
    private void initComponents() {

        lblTo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtMessage = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btnSend = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        txtPhoneNumber = new javax.swing.JTextField();
        btnBrowse = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtContact = new javax.swing.JTextField();
        txtContact.setEditable(false);
        
        lblTo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTo.setText("To:");

        jLabel1.setText("Message:");

        txtMessage.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        txtMessage.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        txtMessage.setViewportView(jTextArea1);

        btnSend.setText("Send");

        btnCancel.setText("Cancel");

        btnBrowse.setText("...");
        
        btnBrowse.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event){
				ViewContacts conForm = new ViewContacts(mInstance);
				int offset = 30 * SMSApplication.getDesktop().getAllFrames().length;
				condiag = new JDialog();
				condiag.setLocationRelativeTo(null);
				condiag.setTitle("Select A Contact");
				condiag.add(conForm);
				condiag.pack();
				condiag.setModal(true);
				condiag.setLocation(offset, offset);
				Image img = Loader.load("viewcontacts.jpg");
				condiag.setIconImage(img);
				condiag.setVisible(true);
			}        	
        });

        jLabel2.setText("Contact Name:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(lblTo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel))
                    .addComponent(txtMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtPhoneNumber)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtContact))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTo)
                    .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBrowse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSend)
                    .addComponent(btnCancel))
                .addContainerGap())
        );
    }
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSend;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblTo;
    private javax.swing.JTextField txtContact;
    private javax.swing.JScrollPane txtMessage;
    private javax.swing.JTextField txtPhoneNumber;
    
    JDialog condiag;
    
    public JDialog getDialog(){
    	return condiag;
    }
    
    public void setPhone(String phone){
    	this.txtPhoneNumber.setText(phone);
    	this.txtPhoneNumber.setEditable(false);
    }
    
    public void setContact(String contact){
    	this.txtContact.setText(contact);
    }
}
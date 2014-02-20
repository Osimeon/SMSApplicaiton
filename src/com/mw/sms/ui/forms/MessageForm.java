package com.mw.sms.ui.forms;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mw.sms.dbms.Contacts;
import com.mw.sms.dbms.DBMS;
import com.mw.sms.dbms.Messages;
import com.mw.sms.ui.SMSApplication;
import com.mw.sms.ui.images.Loader;

@SuppressWarnings("serial")
public class MessageForm extends javax.swing.JPanel {
    public MessageForm() {
        initComponents();
    }
    
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblInbox = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnInbox = new javax.swing.JButton();
        btnOutbox = new javax.swing.JButton();        
        messages = new DefaultTableModel(){
        	@Override
        	public boolean isCellEditable(int row, int column){
        		return column == 3;
        	}
        };
        
        DBMS db = new DBMS();
        messages.addColumn("Message ID");
        messages.addColumn("Phone Number");
        messages.addColumn("Message");
        messages.addColumn("Received");
        messages.addColumn("Status");
        
        List<Messages> mMessages = db.getMessages("INBOX");
        for(Messages message : mMessages){
        	Contacts con = db.getContact(message.getContact());
        	String[] tmp = {String.valueOf(message.getMessageId()), con.getPhoneNumber(), message.getMessage(), message.getReceived(), message.getStatus()};
        	messages.addRow(tmp);
        }
        
        tblInbox.setModel(messages);
        
        tblInbox.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if(e.getClickCount() == 2){
        			JTable target = (JTable) e.getSource();
        			int row = target.getSelectedRow();
                    int col = target.getSelectedColumn();
                    Object data = (Object)target.getValueAt(row, col);
                    int conid = Integer.valueOf(data.toString());
                    DBMS db = new DBMS();
                    Messages msg = db.getMessage(conid);
                    Contacts con = db.getContact(msg.getContact());
                    
                    //System.out.println("Object Clicked Is: " + data.toString());
                    
        			JInternalFrame frame = new JInternalFrame(con.getContactName() + ", Date - " + msg.getReceived(), false, true, false, true);			
        			ViewMessage msgForm = new ViewMessage();
        			
        			msgForm.setPhone(con.getPhoneNumber());
        			msgForm.setContact(con.getContactName());
        			msgForm.setType(msg.getMsgType());
        			msgForm.setMessage(msg.getMessage());
        			
        			Container container = frame.getContentPane();
        			container.add(new JScrollPane(msgForm), BorderLayout.CENTER);
        			frame.setFrameIcon(new ImageIcon(Loader.load("messageinbox.jpg")));
        			frame.setSize(420, 460);

        			int offset = 30 * SMSApplication.getDesktop().getAllFrames().length;
        			frame.setLocation(offset, offset);
        			
        			SMSApplication.getDesktop().add(frame);
        			frame.setVisible(true);
        		}
        	}
        });
        
        tblInbox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblInbox.setSelectionBackground(new java.awt.Color(255, 255, 204));
        tblInbox.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(tblInbox);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnInbox.setText("Inbox");

        btnOutbox.setText("Outbox");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnInbox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOutbox, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnInbox)
                .addGap(18, 18, 18)
                .addComponent(btnOutbox)
                .addContainerGap(365, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }

    private javax.swing.JButton btnInbox;
    private javax.swing.JButton btnOutbox;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblInbox;
    private DefaultTableModel messages; 
}

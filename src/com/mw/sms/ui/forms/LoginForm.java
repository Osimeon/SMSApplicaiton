package com.mw.sms.ui.forms;


@SuppressWarnings("serial")
public class LoginForm extends javax.swing.JPanel{
   public LoginForm(){
       initComponents();
   }
   
   private void initComponents(){
       lblUsername = new javax.swing.JLabel();
       lblPassword = new javax.swing.JLabel();
       txtUsername = new javax.swing.JTextField();
       txtPassword = new javax.swing.JPasswordField();
       btnOk = new javax.swing.JButton();
       btnCancel = new javax.swing.JButton();

       lblUsername.setText("Username");

       lblPassword.setText("Password");

       txtUsername.setToolTipText("Enter Password");

       txtPassword.setToolTipText("Enter Password");

       btnOk.setText("Ok");

       btnCancel.setText("Cancel");

       javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
       this.setLayout(layout);
       layout.setHorizontalGroup(
           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addGap(19, 19, 19)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addComponent(lblUsername)
                   .addComponent(lblPassword))
               .addGap(18, 18, 18)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addComponent(txtUsername)
                   .addComponent(txtPassword)
                   .addGroup(layout.createSequentialGroup()
                       .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                       .addComponent(btnCancel)))
               .addContainerGap())
       );
       layout.setVerticalGroup(
           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addGap(17, 17, 17)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(lblUsername)
                   .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGap(18, 18, 18)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(lblPassword)
                   .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGap(18, 18, 18)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(btnOk)
                   .addComponent(btnCancel))
               .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
       );
   }
   private javax.swing.JButton btnCancel;
   private javax.swing.JButton btnOk;
   private javax.swing.JLabel lblPassword;
   private javax.swing.JLabel lblUsername;
   private javax.swing.JPasswordField txtPassword;
   private javax.swing.JTextField txtUsername;
}

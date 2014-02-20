package com.mw.sms.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import com.mw.sms.ui.forms.ContactsForm;
import com.mw.sms.ui.forms.CreateContactForm;
import com.mw.sms.ui.forms.CreateMessageForm;
import com.mw.sms.ui.forms.MessageForm;
import com.mw.sms.ui.images.Loader;

@SuppressWarnings("serial")
public class SMSApplication extends javax.swing.JFrame{
    public SMSApplication(){    	
    	desktopPane = new JDesktopPane(){
			ImageIcon mImage = new ImageIcon(Loader.load("background.jpg"));
			Image img = mImage.getImage();
			
			@Override
            protected void paintComponent(Graphics grphcs) {
                super.paintComponent(grphcs);
                grphcs.drawImage(img, 0, 0, null);
            }

            @Override
            public Dimension getPreferredSize() {
            	return null;
            }
		};
		
		mDesktop = desktopPane;
		
		JLabel statusLabel = new JLabel(System.getProperty("user.name"));
        statusLabel.setBorder(BorderFactory.createEtchedBorder());
		
		JPanel fileToolPanel = new JPanel();
    	fileToolPanel.setBorder(BorderFactory.createEtchedBorder());
    	fileToolPanel.setLayout(new BorderLayout());
    	JToolBar mToolBar = new JToolBar();
    	mToolBar.add(new CreateSMSAction());
    	mToolBar.addSeparator();
    	mToolBar.add(new LoadSMSBoxAction());
    	mToolBar.addSeparator();
    	mToolBar.add(new CreateNewContactAction());
    	mToolBar.addSeparator();
    	mToolBar.add(new LoadViewBoxAction());
    	fileToolPanel.add(mToolBar, BorderLayout.CENTER);		
		
		Container container = getContentPane();		
		container.add(desktopPane);    	
		container.add(fileToolPanel, BorderLayout.NORTH);
		container.add(statusLabel, BorderLayout.SOUTH);
		setLookAndFeel();
        initComponents();
        setIconImage(Loader.load("logo.png"));
    }
    
    public static JDesktopPane getDesktop(){
		if(mDesktop != null){
			return mDesktop;
		}
		else{
			return null;
		}
	}

    private void initComponents(){
        mnuMainMenu = new javax.swing.JMenuBar();        
        mnuFile = new javax.swing.JMenu();
        mnuMessages = new javax.swing.JMenu();
        mnuContacts = new javax.swing.JMenu();       
        
        mnuMessages.add(new CreateSMSAction());
        mnuMessages.add(new LoadSMSBoxAction());
        
        mnuContacts.add(new CreateNewContactAction());
        mnuContacts.add(new LoadViewBoxAction());
        
        mnuFile.add(new QuitApplication());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SMSApplication");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);

        mnuFile.setText("File");  

        mnuMainMenu.add(mnuFile);

        mnuMessages.setText("Messages");

        mnuMainMenu.add(mnuMessages);

        mnuContacts.setText("Contacts");

        mnuMainMenu.add(mnuContacts);

        setJMenuBar(mnuMainMenu);
    }
    
    private void setLookAndFeel() {
		try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch(Exception ex){
            System.out.println("Look & Feel exception");
        }
	}

    public static void main(String args[]){
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                new SMSApplication().setVisible(true);
            }
        });
    }
    
    /**
     * Create SMS Action
     **/
    private class CreateSMSAction extends AbstractAction{
    	
    	public CreateSMSAction(){
			putValue(Action.NAME, "Create");
			putValue(Action.SHORT_DESCRIPTION, "Create New SMS");
			putValue(Action.SMALL_ICON, new ImageIcon(Loader.load("writemessage.jpg")));
		}
    	
		@Override
		public void actionPerformed(ActionEvent event){
			JInternalFrame frame = new JInternalFrame("New SMS", false, true, false, true);
			CreateMessageForm msgForm = new CreateMessageForm();
			
			Container container = frame.getContentPane();
			container.add(new JScrollPane(msgForm), BorderLayout.CENTER);
			frame.setSize(450, 330);

			int offset = 30 * getDesktop().getAllFrames().length;
			frame.setLocation(offset, offset);
			
			getDesktop().add(frame);
			frame.setFrameIcon(new ImageIcon(Loader.load("writemessage.jpg")));
			frame.setVisible(true);
		}
    	
    }
    
    /**
     * Load Messages Box
     **/
    private class LoadSMSBoxAction extends AbstractAction{
    	
    	public LoadSMSBoxAction(){
			putValue(Action.NAME, "SMS");
			putValue(Action.SHORT_DESCRIPTION, "View SMS Box");
			putValue(Action.SMALL_ICON, new ImageIcon(Loader.load("messageinbox.jpg")));
		}
    	
		@Override
		public void actionPerformed(ActionEvent event){
			JInternalFrame frame = new JInternalFrame("SMS", false, true, false, true);			
			MessageForm msgForm = new MessageForm();
			Container container = frame.getContentPane();
			container.add(new JScrollPane(msgForm), BorderLayout.CENTER);
			frame.setSize(800, 500);

			int offset = 30 * getDesktop().getAllFrames().length;
			frame.setLocation(offset, offset);
			
			getDesktop().add(frame);
			frame.setFrameIcon(new ImageIcon(Loader.load("messageinbox.jpg")));
			frame.setVisible(true);
		}
    	
    }
    
    /**
     * Create New Contact
     **/
    private class CreateNewContactAction extends AbstractAction{
    	
    	public CreateNewContactAction(){
			putValue(Action.NAME, "Create");
			putValue(Action.SHORT_DESCRIPTION,"Create New Contact");
			putValue(Action.SMALL_ICON, new ImageIcon(Loader.load("createcontact.png")));
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			JInternalFrame frame = new JInternalFrame("Create New Contact", false, true, false, true);
			CreateContactForm conForm = new CreateContactForm(frame);
			Container container = frame.getContentPane();
			container.add(new JScrollPane(conForm), BorderLayout.CENTER);
			frame.setSize(350, 150);
			int offset = 30 * getDesktop().getAllFrames().length;
			frame.setLocation(offset, offset);
			
			getDesktop().add(frame);
			frame.setFrameIcon(new ImageIcon(Loader.load("createcontact.png")));
			frame.setVisible(true);
		}
    	
    }
    
    /**
     * View Contacts
     **/
    private class LoadViewBoxAction extends AbstractAction{
    	
    	public LoadViewBoxAction(){
			putValue(Action.NAME, "View");
			putValue(Action.SHORT_DESCRIPTION,"View Contacts");
			putValue(Action.SMALL_ICON, new ImageIcon(Loader.load("viewcontacts.jpg")));
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			JInternalFrame frame = new JInternalFrame("View Contacts", false, true, false, true);
			ContactsForm conForm = new ContactsForm();
			Container container = frame.getContentPane();
			container.add(new JScrollPane(conForm), BorderLayout.CENTER);
			frame.setSize(570, 500);

			int offset = 30 * getDesktop().getAllFrames().length;
			frame.setLocation(offset, offset);
			
			getDesktop().add(frame);
			frame.setFrameIcon(new ImageIcon(Loader.load("viewcontacts.jpg")));
			frame.setVisible(true);
		}
    	
    }
    
    /**
     * Quit Application
     **/
    private class QuitApplication extends AbstractAction{
    	
    	public QuitApplication(){
    		putValue(Action.NAME, "Exit");
			putValue(Action.SHORT_DESCRIPTION,"Exit Application");
    	}
    	
		@Override
		public void actionPerformed(ActionEvent event) {
			int selectedOption = JOptionPane.showConfirmDialog(null, "Are You Sure You Want To Exit?", "Exit Application", JOptionPane.YES_NO_OPTION);
			if(selectedOption == JOptionPane.YES_OPTION){
				System.exit(1);
			}
		}
    	
    }
    
    private javax.swing.JMenu mnuContacts;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JMenuBar mnuMainMenu;
    private javax.swing.JMenu mnuMessages;
    
    JDesktopPane desktopPane;
    static JDesktopPane mDesktop;
}
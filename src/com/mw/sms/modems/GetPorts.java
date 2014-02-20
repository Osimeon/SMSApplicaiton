package com.mw.sms.modems;

import gnu.io.CommPortIdentifier;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ListIterator;

public class GetPorts {
    ArrayList<CommPortIdentifier> portNames = new ArrayList<CommPortIdentifier>();
	private final Logger log = new Logger(this.getClass());
        Modems modem;
        
        
        public GetPorts(){
            getPorts();
            detectedPorts();
            
        }
    @SuppressWarnings("unchecked")
	public void getPorts(){
            log.trace("Refreshing detectors...");
            Enumeration<CommPortIdentifier> ports = CommPortIdentifier.getPortIdentifiers();
            while(ports.hasMoreElements()) {
                CommPortIdentifier port = ports.nextElement();
                if(port.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                    log.info("Beginning detection for serial port: " + port.getName());
                    portNames.add(port);
                }
            }
        }
    
    @SuppressWarnings("rawtypes")
	public void detectedPorts(){
        ListIterator iterator = portNames.listIterator();
        while(iterator.hasNext()){
            CommPortIdentifier port = (CommPortIdentifier) iterator.next();            
            modem = new Modems(port);
        }
    }
    
    @SuppressWarnings("unused")
	public static void main(String[]args){
        GetPorts ports = new GetPorts();
    }
}

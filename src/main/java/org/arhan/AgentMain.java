package org.arhan;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.instrument.Instrumentation;
import java.lang.management.ManagementFactory;

public class AgentMain {
  
	/**
	 * Installs the transformation service
	 * @param args None supported
	 * @param inst The instrumentation instance
	 * @throws Exception thrown on any error
	 */
	public static void agentmain (String args, Instrumentation inst) throws Exception {
		System.out.println("Installing AgentMain...");
		TransformerService ts = new TransformerService(inst);
		ObjectName on = new ObjectName("transformer:service=DemoTransformer");
		// Could be a different MBeanServer. If so, pass a JMX Default Domain Name in agentArgs
		MBeanServer server = ManagementFactory.getPlatformMBeanServer();
		server.registerMBean(ts, on);
		// Set this property so the installer knows we're already here
		System.setProperty("demo.agent.installed", "true");		
		System.out.println("AgentMain Installed");
	}

}

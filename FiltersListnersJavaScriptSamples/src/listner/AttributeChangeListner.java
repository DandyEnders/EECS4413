package listner;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class Counter
 * it counts how many times an attribute is updated ( created or changed)
 * currently it only checks the events with "firstName" 
 *
 */
@WebListener
public class AttributeChangeListner implements ServletRequestAttributeListener {
	int counter=0;

    /**
     * Default constructor. 
     */
    public AttributeChangeListner() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletRequestAttributeListener#attributeRemoved(ServletRequestAttributeEvent)
     */
    public void attributeRemoved(ServletRequestAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletRequestAttributeListener#attributeAdded(ServletRequestAttributeEvent)
     */
    public void attributeAdded(ServletRequestAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    	handleEvent(arg0);
    }

	/**
     * @see ServletRequestAttributeListener#attributeReplaced(ServletRequestAttributeEvent)
     */
    public void attributeReplaced(ServletRequestAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    	handleEvent(arg0);
    }
    
    void handleEvent(ServletRequestAttributeEvent event) {
    	if (event.getName().equals("firstName")){
    		/*
    		 * if the attribute firstName is changed then we record it
    		 * we save the counter name in a context attribute..
    		 */
    			counter++;
    			event.getServletContext().setAttribute("count", counter);
    	
    }
    }
	
}

package listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class CreditListener
 *
 */
@WebListener
public class CreditListener implements ServletRequestAttributeListener {
	int nSearches;
    /**
     * Default constructor. 
     */
    public CreditListener() {
        // TODO Auto-generated constructor stub
    	this.nSearches = 0;
    }

	/**
     * @see ServletRequestAttributeListener#attributeRemoved(ServletRequestAttributeEvent)
     */
    public void attributeRemoved(ServletRequestAttributeEvent event)  { 
    	if (event.getName().equals("credit")) {
    		System.out.println("Listener credit: " + nSearches);
    		nSearches += 1;
    		event.getServletContext().setAttribute("nSearches", nSearches);
    	}
    }

	/**
     * @see ServletRequestAttributeListener#attributeAdded(ServletRequestAttributeEvent)
     */
    public void attributeAdded(ServletRequestAttributeEvent event)  { 
    	if (event.getName().equals("credit")) {
    		System.out.println("Listener credit: " + nSearches);
    		nSearches += 1;
    		event.getServletContext().setAttribute("nSearches", nSearches);
    	}
    }

	/**
     * @see ServletRequestAttributeListener#attributeReplaced(ServletRequestAttributeEvent)
     */
    public void attributeReplaced(ServletRequestAttributeEvent event)  { 
    	if (event.getName().equals("credit")) {
    		System.out.println("Listener credit: " + nSearches);
    		nSearches += 1;
    		event.getServletContext().setAttribute("nSearches", nSearches);
    	}
    }
	
}

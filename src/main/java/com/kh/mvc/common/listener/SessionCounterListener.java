package com.kh.mvc.common.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionCounterListener
 *
 */

/**
 * @WebListener
 * -web.xml에 listner등록 처리
 *
 */
@WebListener
public class SessionCounterListener implements HttpSessionListener {

		private static int activeSessions;
    /**
     * Default constructor. 
     */
    public SessionCounterListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
    	activeSessions++;
    	System.out.println("[sessionCreated] 현재 세션수 :" + activeSessions);
    	
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	activeSessions--;
    	System.out.println("[sessionCreated] 현재 세션수 :" + activeSessions);
    }
	
}

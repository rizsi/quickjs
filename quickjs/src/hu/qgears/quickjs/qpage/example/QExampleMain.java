package hu.qgears.quickjs.qpage.example;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.session.HashSessionIdManager;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;

import hu.qgears.quickjs.qpage.QPageManager;

/**
 * Executable main class that opens a Jetty web server and handles QPage based web applications
 * within it.
 */
public class QExampleMain extends AbstractHandler
{
	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
		
        // Specify the Session ID Manager
        HashSessionIdManager idmanager = new HashSessionIdManager();
        server.setSessionIdManager(idmanager);

        // Sessions are bound to a context.
        ContextHandler context = new ContextHandler("/");
        server.setHandler(context);

        // Create the SessionHandler (wrapper) to handle the sessions
        HashSessionManager manager = new HashSessionManager();
        SessionHandler sessions = new SessionHandler(manager);
        sessions.addEventListener(QPageManager.createSessionListener());
        context.setHandler(sessions);
        sessions.setHandler(new QExampleMain());
        server.start();
        server.join();
	}

	@Override
	public void handle(String target, final Request baseRequest, HttpServletRequest request, 
			final HttpServletResponse response)
			throws IOException, ServletException {
		switch(target)
		{
		case "/01":
			new QPageHandler(QExample01.class).handle(target, baseRequest, request, response);
			break;
		case "/02":
			new QPageHandler(QExample02.class).handle(target, baseRequest, request, response);
			break;
		case "/03":
			new QPageHandler(QExample03.class).handle(target, baseRequest, request, response);
			break;
		default:
			new QPageHandler(Index.class).handle(target, baseRequest, request, response);
			break;
		}
	}
}

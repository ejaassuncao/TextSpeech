package br.com.ewa.sse;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

public class GerenciadorThreads {

	private static final String SESSION_THREAD = "SESSION_THREAD";
	private Thread currentThread;
	private ServletContext session;
	Map<String, Long> lista;

	// private String painelId;

	public GerenciadorThreads(ServletContext session, Thread currentThread) {
		// this.painelId = painelId;
		this.session = session;
		this.currentThread = currentThread;
		this.lista = new HashMap<String, Long>();
	}

	public void validaThread(String painelId) throws Exception {
		long sessionThreadId = this.getSessionThreadId(painelId);
		DestroyThread(sessionThreadId);
		session.setAttribute(painelId, this.currentThread.getId());
		monitorarThread(painelId);
	}

	public void monitorarThread(String painelId) {
		if (!lista.containsKey(painelId)) {
			lista.put(painelId, this.currentThread.getId());
			session.setAttribute(SESSION_THREAD, lista);
		}
	}

	public void destroy(String painelId) throws Exception {
		long sessionThreadId = this.getSessionThreadId(painelId);
		DestroyThread(sessionThreadId);
		System.out
				.println("**************thread destruida: " + sessionThreadId);
	}

	@SuppressWarnings("unchecked")
	public void destroyAll() throws Exception {
		if (session.getAttribute(SESSION_THREAD) != null) {
			lista = (HashMap<String, Long>) session
					.getAttribute(SESSION_THREAD);
			for (long sessionThreadId : lista.values()) {
				System.out.println("*********thread all destruida: "
						+ sessionThreadId);
				DestroyThread(sessionThreadId);
			}
		}
	}

	private long getSessionThreadId(String painelId) {
		Object valorSessao = session.getAttribute(painelId);
		return (valorSessao == null) ? 0 : (Long) valorSessao;
	}

	private void DestroyThread(long sessionThreadId) throws Exception {
		if (sessionThreadId > 0) {
			Set<Thread> setOfThread = Thread.getAllStackTraces().keySet();
			for (Thread thread : setOfThread) {
				if (thread.getId() == sessionThreadId) {
					thread.interrupt();
					break;
				}
			}
		}
	}
}

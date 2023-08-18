package br.com.abaco.sse;

import java.io.InputStream;

public interface IConnectHost {
	public void disconnect();
	public int getGetResponseCode();
	public InputStream getInputStream();
}

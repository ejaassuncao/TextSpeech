package br.com.ewa.commons;

import javax.servlet.http.HttpServletRequest;

public class UrlHost
{
  private String contextPath;
  private String localName;
  private String localAddr;
  private int localPort;
  private String pathInfo;
  private String protocol;
  private String queryString;
  private String remoteAddr;
  private String remoteHost;
  private int remotePort;
  private String remoteUser;
  private String requestURI;
  private String scheme;
  private int serverPort;
  private String servletPath;
  private String serverName;
  private String urlbase;
  private HttpServletRequest request;
  private final String NOME_PARAMETRO_HOST = "url-path-application";
  
  public UrlHost(HttpServletRequest request, Caminho caminho)
  {
    this.request = request;
    this.contextPath = request.getContextPath();
    this.localName = request.getLocalName();
    this.localAddr = request.getLocalAddr();
    this.localPort = request.getLocalPort();
    this.pathInfo = request.getPathInfo();
    this.protocol = request.getProtocol();
    this.queryString = request.getQueryString();
    this.remoteAddr = request.getRemoteAddr();
    this.remoteHost = request.getRemoteHost();
    this.remotePort = request.getRemotePort();
    this.remoteUser = request.getRemoteUser();
    this.requestURI = request.getRequestURI();
    this.scheme = request.getScheme();
    this.serverName = request.getServerName();
    this.serverPort = request.getServerPort();
    this.servletPath = request.getServletPath();
    
    this.urlbase = caminho.getParm("url-path-application");
  }
  
  public String getIP()
  {
    String ipAddress = this.request.getHeader("X-FORWARDED-FOR");
    if (ipAddress == null) {
      ipAddress = this.request.getRemoteAddr();
    }
    return ipAddress;
  }
  
  public String getBaseUrl()
  {
    return this.urlbase;
  }
  
  public String getContextPath()
  {
    return this.contextPath;
  }
  
  public String getLocalName()
  {
    return this.localName;
  }
  
  public String getLocalAddr()
  {
    return this.localAddr;
  }
  
  public int getLocalPort()
  {
    return this.localPort;
  }
  
  public String getPathInfo()
  {
    return this.pathInfo;
  }
  
  public String getProtocol()
  {
    return this.protocol;
  }
  
  public String getQueryString()
  {
    return this.queryString;
  }
  
  public String getRemoteAddr()
  {
    return this.remoteAddr;
  }
  
  public String getRemoteHost()
  {
    return this.remoteHost;
  }
  
  public int getRemotePort()
  {
    return this.remotePort;
  }
  
  public String getRemoteUser()
  {
    return this.remoteUser;
  }
  
  public String getRequestURI()
  {
    return this.requestURI;
  }
  
  public String getScheme()
  {
    return this.scheme;
  }
  
  public int getServerPort()
  {
    return this.serverPort;
  }
  
  public String getServletPath()
  {
    return this.servletPath;
  }
  
  public String getServerName()
  {
    return this.serverName;
  }
  
  public String getParameter(String string)
  {
    return this.request.getParameter(string);
  }
}

package br.com.abaco.commons;

public class Paramentro
{
  private String host;
  private String parmExecute;
  private String parmId;
  private String parmEvent;
  private boolean parmDestroy;
  private boolean parmDestroyAll;
  private String IP;
  
  public Paramentro(UrlHost urlHost)
    throws Exception
  {
    if ((urlHost.getParameter("execute") == null) || 
      (urlHost.getParameter("execute").isEmpty())) {
      throw new Exception(
        "parametro execute n?o informado na chamada sse");
    }
    this.parmExecute = urlHost.getParameter("execute").trim();
    this.parmId = urlHost.getParameter("id").trim();
    this.parmEvent = urlHost.getParameter("event").trim();
    this.parmDestroy = (this.parmExecute.equalsIgnoreCase("destroy"));
    this.parmDestroyAll = (this.parmExecute.equalsIgnoreCase("destroyAll"));
    this.IP = urlHost.getIP();
    
    this.host = 
    
      (urlHost.getBaseUrl() + "servlet/" + this.parmExecute + "?" + this.parmId + "," + this.IP + "," + this.parmEvent);
  }
  
  public String getHost()
  {
    return this.host;
  }
  
  public String getParmExecute()
  {
    return this.parmExecute;
  }
  
  public String getParmId()
  {
    return this.parmId;
  }
  
  public String getParmEvent()
  {
    return this.parmEvent;
  }
  
  public boolean isDestroy()
  {
    return this.parmDestroy;
  }
  
  public boolean isDestroyAll()
  {
    return this.parmDestroyAll;
  }
}

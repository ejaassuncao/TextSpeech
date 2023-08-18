package br.com.abaco.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletContext;

public class Caminho
{
  private ServletContext servletContext;
  private Properties props;
  
  public Caminho(ServletContext servletContext)
    throws IOException
  {
    this.servletContext = servletContext;
    
    File file = new File(getPathWebInf() + "onix.properties");
    this.props = new Properties();
    
    FileInputStream fis = new FileInputStream(file);
    this.props.load(fis);
  }
  
  public String getPathAplicacao()
  {
    String separador = File.separator;
    return this.servletContext.getRealPath("") + separador;
  }
  
  public String getPathWebInf()
  {
    String separador = File.separator;
    return this.servletContext.getRealPath("") + separador + "WEB-INF" + 
      File.separator;
  }
  
  public String getParm(String parm)
  {
    return this.props.getProperty(parm);
  }
}

import br.com.abaco.commons.Caminho;
import br.com.abaco.commons.Paramentro;
import br.com.abaco.commons.UrlHost;
import br.com.abaco.sse.GerenciadorThreads;
import br.com.abaco.sse.Resource;
import br.com.abaco.sse.RespostaDTO;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/ServletEventSource"})
public class ServletEventSource
  extends HttpServlet
{
  private static final long serialVersionUID = 1L;
  
  protected void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    response.setContentType("text/event-stream");
    response.setCharacterEncoding("UTF-8");
    response.setHeader("Cache-Control", 
      "private, no-store, no-cache, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0L);
    response.setHeader("Connection", "Keep-Alive");
    response.setHeader("Keep-Alive", "timeout=24h");
    response.setHeader("X-Accel-Buffering", "no");
    
    PrintWriter writer = null;
    Caminho caminho = new Caminho(getServletContext());
    UrlHost urlHost = new UrlHost(request, caminho);
    
    Paramentro parm = null;
    try
    {
      parm = new Paramentro(urlHost);
      
      GerenciadorThreads gerenciador = new GerenciadorThreads(
        super.getServletContext(), Thread.currentThread());
      gerenciador.validaThread(parm.getParmId());
      if (parm.isDestroy())
      {
        gerenciador.destroy(parm.getParmId());
        return;
      }
      if (parm.isDestroyAll())
      {
        gerenciador.destroyAll();
        return;
      }
      while (!Thread.currentThread().isInterrupted())
      {
        parm = new Paramentro(urlHost);
        
        gerenciador.monitorarThread(parm.getParmId());
        try
        {
          Resource resource = new Resource(parm.getHost(), request);
          
          String json = resource.getJSON();
          
          writer = response.getWriter();
          if (!json.isEmpty())
          {
            writer.print(RespostaDTO.Parse(parm.getParmEvent(), json));
            response.setStatus(resource.getStatusCode());
            response.flushBuffer();
            break;
          }
          writer.print(RespostaDTO.Parse(parm.getParmEvent(), "[]"));
          response.flushBuffer();
          Thread.sleep(3000L);
        }
        catch (Exception e)
        {
          if (writer != null) {
            writer.close();
          }
          Thread.currentThread().interrupt();
          System.out.println(e.getMessage());
          break;
        }
      }
    }
    catch (Exception e1)
    {
      System.out.println(e1.getMessage());
    }
  }
}

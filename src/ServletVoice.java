import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.abaco.commons.Arquivo;
import br.com.abaco.voice.ChamadaSenha;
import br.com.abaco.voice.MapaArquivos;
import br.com.abaco.voice.MergeMp3Builder;

@WebServlet("/ServletVoice")
public class ServletVoice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletVoice() {
		super();
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream outputStream = null;
		
		try {

			// https://text-to-speech-demo.ng.bluemix.net/api/synthesize?voice=pt-BR_IsabelaVoice&text=Senha<break time="500ms"/><prosody rate="-15%">{senha}</prosody>Comparecer ao guichê {guiche}. <break time="1000ms"/>&X-WDC-PL-OPT-OUT=0

			ChamadaSenha chamadaSenha = new ChamadaSenha(
					request.getParameter("senha"),
					request.getParameter("guiche"));

			MapaArquivos mapaArquivos = new MapaArquivos(this.getServletContext());
			MergeMp3Builder merge = new MergeMp3Builder(chamadaSenha,mapaArquivos);

			Arquivo file = new Arquivo(merge.getAbsFile());

			//cabeçalho
			response.setContentLength((int) file.length());
			response.setCharacterEncoding("UTF-8");
			response.setContentType("audio/mpeg");
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Connection", "Keep-Alive");
			
			//saida
			outputStream = response.getOutputStream();			
			outputStream.write(file.toBytes());						

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				outputStream.flush();
				outputStream.close();
			}				
		}
	}
}

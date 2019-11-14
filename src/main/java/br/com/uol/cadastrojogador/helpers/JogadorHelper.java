package br.com.uol.cadastrojogador.helpers;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.JDOMParseException;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import br.com.uol.cadastrojogador.service.GrupoService;

@Component
public class JogadorHelper {
	
	
	@Autowired
	private GrupoService grupoService;
	
	private final String USER_AGENT = "";
	private final String urlJson = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json";
	private final String urlXml = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml";

	public JogadorHelper() {
	}

	public List<String> requestVingadores() throws IOException {
		final StringBuilder to = enviaRequest(urlJson);
		final JsonElement root = new JsonParser().parse(to.toString());
		final JsonArray results = root.getAsJsonObject().get("vingadores").getAsJsonArray();
		final GrupoHttp  grupoHttp = new GrupoHttp();
		for (int i = 0; i < results.size(); i++) {
			final JsonElement element = results.get(i);
			if(grupoService.buscaPorCodNome(element.getAsJsonObject().get("codinome").getAsString())) {
				grupoHttp.addVingadores(element.getAsJsonObject().get("codinome").getAsString());
			}
		}
		return grupoHttp.getCodNomeVingadores();
	}
	
	public List<String> requestLiga() throws IOException {
		final StringBuilder response = enviaRequest(urlXml);
		final GrupoHttp  grupoHttp = new GrupoHttp();
		try {
			if(response.toString() != ""){
				final SAXBuilder saxBuilder = new SAXBuilder();
				final Document doc = saxBuilder.build(new StringReader(response.toString()));
				for(Element element : doc.getRootElement().getChild("codinomes").getChildren()) {
					if(grupoService.buscaPorCodNome(element.getValue())){
						grupoHttp.addLiga(element.getValue());

					}
				}
			}
			
		} catch(JDOMParseException e){
			//ignore
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return grupoHttp.getCodNomesLiga();
	}

	private StringBuilder enviaRequest(String url) throws IOException {
		final StringBuilder result = new StringBuilder();
		final URL obj = new URL(url);
		final HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		final  int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			final InputStream in = new BufferedInputStream(con.getInputStream());
			final BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = reader.readLine()) != null) {
				result.append(line + "\n");
			}
		}
		return result;
	}
}

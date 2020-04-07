package br.com.uol.cadastrojogador.service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import br.com.uol.cadastrojogador.dao.GrupoDao;

@Component
public class JogadorService {
	
	
	@Autowired
	private GrupoDao grupoDao;
	
	private final String USER_AGENT = "";
	private final String urlJson = "https://raw.githubusercontssssent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json";
	private final String urlXml = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml";

	public JogadorService() {}

	public List<Map<String, String>> requestVingadores() throws IOException {
		final StringBuilder to = enviaRequest(urlJson);
		final JsonElement root = new JsonParser().parse(to.toString());
		final JsonArray results = root.getAsJsonObject().get("vingadores").getAsJsonArray();
		final List<Map<String, String>> vingadores = new ArrayList<Map<String, String>>();
		for (JsonElement element : results) {
			final String hero = element.getAsJsonObject().get("codinome").getAsString();
			final Map<String, String> superHero = new HashMap<String, String>();
			if(grupoDao.buscaPorCodNome(hero)) {
				superHero.put("codName", hero);
			}
			vingadores.add(superHero);
		}
		return vingadores;
	}
	
	public List<Map<String, String>> requestLiga() throws IOException, JDOMException {
		final StringBuilder response = enviaRequest(urlXml);
		final List<Map<String, String>> liga = new ArrayList<Map<String, String>>();
		if(!response.toString().isEmpty()){
			final SAXBuilder saxBuilder = new SAXBuilder();
			final Document doc = saxBuilder.build(new StringReader(response.toString()));
			for(Element element : doc.getRootElement().getChild("codinomes").getChildren()) {
				final Map<String, String> superHero = new HashMap<String, String>();
				if(grupoDao.buscaPorCodNome(element.getValue())){
					superHero.put("codName", element.getValue());
				}
				liga.add(superHero);
			}
		}	
		return liga;
	}

	private StringBuilder enviaRequest(final String url) {
		try {
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
		}catch (IOException e) {
			// TODO: handle exception
			
			System.err.println("AQUUUUUUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
			System.err.println(e.getMessage());
			return new StringBuilder();
		}
		
	}
}

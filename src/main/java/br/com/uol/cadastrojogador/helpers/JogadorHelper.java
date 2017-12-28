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
	
	
	private GrupoHttp  grupoHttp;
	@Autowired
	private GrupoService grupoService;
	
	private static final String USER_AGENT = "";
	private static final String urlJson = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json";
	private static final String urlXml = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml";

	public JogadorHelper() {
		grupoHttp = new GrupoHttp();
		this.grupoHttp.setCodNomesLiga(new ArrayList<String>());
		this.grupoHttp.setCodNomeVingadores(new ArrayList<String>());
	}

	public List<String> requestVingadores() throws IOException {
		StringBuilder to = enviaRequest(urlJson);
		JsonElement root = new JsonParser().parse(to.toString());
		JsonArray results = root.getAsJsonObject().get("vingadores").getAsJsonArray();
		grupoHttp.getCodNomeVingadores().clear();
		for (int i = 0; i < results.size(); i++) {
			JsonElement element = results.get(i);
			if(grupoService.buscaPorCodNome(element.getAsJsonObject().get("codinome").getAsString())) {
				grupoHttp.addVingadores(element.getAsJsonObject().get("codinome").getAsString());
			}
		}
		System.out.println(grupoHttp.getCodNomeVingadores());
		return grupoHttp.getCodNomeVingadores();
	}
	
	public List<String> requestLiga() throws IOException {
		StringBuilder response = enviaRequest(urlXml);
			try {
				if(response.toString() != ""){
					SAXBuilder saxBuilder = new SAXBuilder();
					Document doc = saxBuilder.build(new StringReader(response.toString()));
					grupoHttp.getCodNomesLiga().clear();
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
			System.out.println(grupoHttp.getCodNomesLiga());
			return grupoHttp.getCodNomesLiga();
	}

	private StringBuilder enviaRequest(String url) throws IOException {
		StringBuilder result = new StringBuilder();

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();

		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) {
			InputStream in = new BufferedInputStream(con.getInputStream());

			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String line;
			while ((line = reader.readLine()) != null) {
				result.append(line + "\n");
			}

		}

		return result;
	}
}

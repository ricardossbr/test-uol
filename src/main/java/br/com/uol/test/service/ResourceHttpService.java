package br.com.uol.test.service;

import br.com.uol.test.dto.SuperHeroJson;
import br.com.uol.test.dto.SuperHeroXml;
import br.com.uol.test.exceptions.ResourceHttpIsNotAvailableException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMethod;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class ResourceHttpService {
	@Autowired
	private ObjectMapper mapper;
	private final XmlMapper xmlMapper = new XmlMapper();

	@Value("${url.json}")
	private String urlJson;
	@Value("${url.xml}")
	private String urlXml;

	public SuperHeroJson requestVingadores(){
		try{
			return this.mapper.readValue(this.requestByUrl(urlJson), SuperHeroJson.class);
		}catch (IOException | ResourceHttpIsNotAvailableException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public SuperHeroXml requestLiga(){
		try{
			return this.xmlMapper.readValue(requestByUrl(urlXml), SuperHeroXml.class);
		}catch (IOException | ResourceHttpIsNotAvailableException e){
			e.printStackTrace();
			return null;
		}
	}

	private String requestByUrl(String url) throws ResourceHttpIsNotAvailableException {
		try {
			final StringBuilder result = new StringBuilder();
			final URL obj = new URL(url);
			final HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod(RequestMethod.GET.name());
			con.setRequestProperty("User-Agent", "");
			final int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				final InputStream in = new BufferedInputStream(con.getInputStream());
				final BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				String line;
				while ((line = reader.readLine()) != null) {
					result.append(line + "\n");
				}
			}
			return result.toString();
		}catch (IOException e) {
			e.printStackTrace();
			throw new ResourceHttpIsNotAvailableException();
		}
	}
}

package br.com.uol.test.service;

import br.com.uol.test.dto.SuperHeroJson;
import br.com.uol.test.dto.SuperHeroXml;
import br.com.uol.test.service.ResourceHttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class ResourceHttpServiceTest {
    @InjectMocks
    private ResourceHttpService service;
    @Spy
    private ObjectMapper mapper;
    @Spy
    private XmlMapper xmlMapper;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(service, "urlJson", "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json");
        ReflectionTestUtils.setField(service, "urlXml", "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml");
    }

    @Test
    public void when_call_request_vingadores_should_be_ok(){
        final SuperHeroJson json = service.requestVingadores();
        assertNotNull(json);
        assertEquals(7 , json.getVingadores().size());
    }

    @Test
    public void when_call_request_liga_da_justica_should_be_ok() {
        final SuperHeroXml superHeroXml = service.requestLiga();
        assertNotNull(superHeroXml);
        assertEquals(6 , superHeroXml.getCodNames().size());
    }

    @Test
    public void when_call_request_liga_da_justica_should_be_erro() {
        ReflectionTestUtils.setField(service, "urlXml", "");
        final SuperHeroXml superHeroXml = service.requestLiga();
        assertNull(superHeroXml);
    }

    @Test
    public void when_call_request_vingadores_should_be_erro(){
        ReflectionTestUtils.setField(service, "urlJson", "");
        final SuperHeroJson json = service.requestVingadores();
        assertNull(json);
    }
}

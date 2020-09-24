package br.com.uol.cadastrojogador.cadastrojogador.service;

import br.com.uol.cadastrojogador.exceptions.NameIsNotAvailableException;
import br.com.uol.cadastrojogador.exceptions.ResourceHttpIsNotAvailableException;
import br.com.uol.cadastrojogador.model.GroupModel;
import br.com.uol.cadastrojogador.repository.GroupRepository;
import br.com.uol.cadastrojogador.service.GroupService;
import br.com.uol.cadastrojogador.service.ResourceHttpService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static br.com.uol.cadastrojogador.cadastrojogador.builder.GroupTestBuilder.*;
import static br.com.uol.cadastrojogador.cadastrojogador.builder.ResourceHttpTestBuilder.*;


@RunWith(MockitoJUnitRunner.class)
public class GroupServiceTest {
    @InjectMocks
    private GroupService groupService;
    @Mock
    private GroupRepository repository;
    @Mock
    private ResourceHttpService httpService;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void when_call_get_group_with_vingadores_should_be_ok() throws IOException, ResourceHttpIsNotAvailableException {
        when(this.repository.findByGroupName(anyString())).thenReturn(getListEmpty());
        when(this.httpService.requestVingadores()).thenReturn(getHeroJson());
        final GroupModel gpm = this.groupService.getGroup("VINGADORES");
        assertNotNull(gpm);
        assertEquals("VINGADORES", gpm.getGroupName());
        assertNotEquals("", gpm.getName());
    }

    @Test
    public void when_call_get_group_with_liga_da_justica_should_be_ok() throws IOException, ResourceHttpIsNotAvailableException {
        when(this.repository.findByGroupName(anyString())).thenReturn(getListEmpty());
        when(this.httpService.requestLiga()).thenReturn(getHeroXml());
        final GroupModel gpm = this.groupService.getGroup("LIGADAJUSTICA");
        assertNotNull(gpm);
        assertEquals("LIGADAJUSTICA", gpm.getGroupName());
        assertNotEquals("", gpm.getName());
    }

    @Test
    public void when_call_get_group_with_liga_da_justica_should_be_erro() throws IOException, ResourceHttpIsNotAvailableException {
        when(this.repository.findByGroupName(anyString())).thenReturn(getList());
        when(this.httpService.requestLiga()).thenReturn(getHeroXml());
        try{
            final GroupModel gpm = this.groupService.getGroup("LIGADAJUSTICA");
        }catch (NameIsNotAvailableException e){
            assertEquals("name is not available", e.getMessage());
        }
    }
}

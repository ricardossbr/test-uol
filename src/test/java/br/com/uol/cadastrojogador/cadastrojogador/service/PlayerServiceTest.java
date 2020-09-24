package br.com.uol.cadastrojogador.cadastrojogador.service;

import br.com.uol.cadastrojogador.exceptions.PlayerIsNotFoundException;
import br.com.uol.cadastrojogador.exceptions.ResourceHttpIsNotAvailableException;
import br.com.uol.cadastrojogador.model.PlayerModel;
import br.com.uol.cadastrojogador.repository.PlayerRepository;
import br.com.uol.cadastrojogador.service.GroupService;
import br.com.uol.cadastrojogador.service.PlayerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

import static br.com.uol.cadastrojogador.cadastrojogador.builder.PlayerTestBuilder.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceTest {
    @InjectMocks
    private PlayerService service;
    @Mock
    private PlayerRepository repository;
    @Mock
    private GroupService groupService;
    @Spy
    private ServletUriComponentsBuilder servletUriComponentsBuilder;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void when_call_get_players_should_be_ok(){
        when(this.repository.findAll()).thenReturn(getList());
        final ResponseEntity<Map<String, List<PlayerModel>>> players = this.service.getPlayers();
        final Map<String, List<PlayerModel>> response = players.getBody();
        assertNotNull(players);
        assertEquals( 200 , players.getStatusCodeValue());
        assertEquals(2 , response.size());
    }

    @Test
    public void when_call_get_player_should_be_ok(){
        when(this.repository.findById(anyLong())).thenReturn(getOptionalPlayer());
        final ResponseEntity<PlayerModel> player = this.service.getPlayer(anyLong());
        assertNotNull(player);
        assertEquals( 200 , player.getStatusCodeValue());
        assertEquals("Ricardo" , player.getBody().getName());
    }

   /* @Test
    public void when_call_save_player_should_be_ok() throws IOException, ResourceHttpIsNotAvailableException {
        when(this.repository.save(any())).thenReturn(getPlayer());
        when(ServletUriComponentsBuilder.fromContextPath(any())).then(new URI(""));
        final ResponseEntity player = this.service.save(getPlayerDTO());
        assertNotNull(player);
        assertEquals( 200 , player.getStatusCodeValue());
        assertEquals("http://localhost:8080/player/1" , player.getBody());
    }*/

    @Test
    public void when_call_alter_player_should_be_ok(){
        when(this.repository.save(any())).thenReturn(getPlayer());
        final ResponseEntity<PlayerModel> player = this.service.alterPlayer(getPlayer());
        assertNotNull(player);
        assertEquals( 200 , player.getStatusCodeValue());
        assertEquals("Ricardo" , player.getBody().getName());
    }

    @Test
    public void when_call_alter_player_should_be_error(){
        when(this.repository.save(any())).thenReturn(getPlayerEmpty());
        try{
            this.service.alterPlayer(getPlayer());
        }catch (EmptyResultDataAccessException e){
            assertEquals("Player is not found" , e.getMessage());
        }
    }

    @Test
    public void when_call_get_player_should_be_error(){
        when(this.repository.findById(anyLong())).thenReturn(getOptionalPlayerEmpty());
        try{
            this.service.getPlayer(anyLong());
        }catch (PlayerIsNotFoundException e){
            assertEquals("Player is not found" , e.getMessage());
        }
    }

    @Test
    public void when_call_delete_player_should_be_error(){
        doThrow(EmptyResultDataAccessException.class).when(this.repository).deleteById(anyLong());
        try{
            this.service.deletePlayer(anyLong());
        }catch (PlayerIsNotFoundException e){
            assertEquals("Player is not found" , e.getMessage());
        }
    }
}

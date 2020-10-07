package br.com.uol.test.service;

import br.com.uol.test.builder.PlayerTestBuilder;
import br.com.uol.test.enums.TeamEnum;
import br.com.uol.test.exceptions.*;
import br.com.uol.test.model.PlayerModel;
import br.com.uol.test.repository.PlayerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        when(this.repository.findAll()).thenReturn(PlayerTestBuilder.getList());
        final ResponseEntity<Map<TeamEnum, List<PlayerModel>>> players = this.service.getPlayers();
        final Map<TeamEnum, List<PlayerModel>> response = players.getBody();
        assertNotNull(players);
        assertEquals( 200 , players.getStatusCodeValue());
        assertEquals(2 , response.size());
    }

    @Test
    public void when_call_get_player_should_be_ok(){
        when(this.repository.findById(anyLong())).thenReturn(PlayerTestBuilder.getOptionalPlayer());
        final ResponseEntity<PlayerModel> player = this.service.getPlayer(anyLong());
        assertNotNull(player);
        assertEquals( 200 , player.getStatusCodeValue());
        assertEquals("Ricardo" , player.getBody().getName());
    }

    @Test
    public void when_call_save_player_should_be_ok() throws IOException, ResourceHttpIsNotAvailableException, TeamIsFullException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(this.repository.save(any())).thenReturn(PlayerTestBuilder.getPlayer());
        final ResponseEntity player = this.service.save(PlayerTestBuilder.getPlayerDTO());
        assertNotNull(player);
        assertEquals( 200 , player.getStatusCodeValue());
        assertEquals("http://localhost/1" , player.getBody().toString());
    }

    @Test
    public void when_call_alter_player_should_be_ok() throws GroupIsRequiredExcepetion, HeroInconsistentWithTeamException {
        when(this.repository.findById(any())).thenReturn(PlayerTestBuilder.getOptionalPlayer());
        when(this.groupService.getGroupByAlter(any())).thenReturn(PlayerTestBuilder.getGroupModel());
        when(this.repository.save(any())).thenReturn(PlayerTestBuilder.getPlayer());
        final ResponseEntity<PlayerModel> player = this.service.alterPlayer(PlayerTestBuilder.getPlayer());
        assertNotNull(player);
        assertEquals( 200 , player.getStatusCodeValue());
        assertEquals("Ricardo" , player.getBody().getName());
    }

    @Test
    public void when_call_save_player_dto_without_group_name_should_be_error() throws IOException, ResourceHttpIsNotAvailableException, TeamIsFullException {
        final ResponseEntity player = this.service.save(PlayerTestBuilder.getPlayerDTOWitoutGroupName());
        assertNotNull(player);
        assertEquals( 400 , player.getStatusCodeValue());
    }

    @Test
    public void when_call_save_player_with_null_should_be_error() throws TeamIsFullException {
        final ResponseEntity player = this.service.save(PlayerTestBuilder.getPlayerDTONull());
        assertNotNull(player);
        assertEquals( 400 , player.getStatusCodeValue());
    }

    @Test
    public void when_call_alter_player_empty_should_be_not_found() throws GroupIsRequiredExcepetion, HeroInconsistentWithTeamException {
        when(this.repository.findById(any())).thenReturn(PlayerTestBuilder.getOptionalPlayerEmpty());
        final ResponseEntity<PlayerModel> player = this.service.alterPlayer(PlayerTestBuilder.getPlayerEmpty());
        assertNotNull(player );
        assertEquals(404, player.getStatusCodeValue());
    }

    @Test
    public void when_call_alter_player_empty_should_be_error_group_is_required() throws HeroInconsistentWithTeamException {
        when(this.repository.findById(any())).thenReturn(PlayerTestBuilder.getOptionalPlayer());
        try{
            this.service.alterPlayer(PlayerTestBuilder.getPlayerWithoutGroup());
        }catch (GroupIsRequiredExcepetion e){
            assertEquals("Group is required" , e.getMessage());
        }
    }

    @Test
    public void when_call_alter_player_empty_should_be_error_hero_inconsistent() throws GroupIsRequiredExcepetion {
        when(this.repository.findById(any())).thenReturn(PlayerTestBuilder.getOptionalPlayer());
        try{
            this.service.alterPlayer(PlayerTestBuilder.getPlayerInconsistentGroup());
        }catch (HeroInconsistentWithTeamException e){
            assertEquals("Superhero is not available for this team" , e.getMessage());
        }
    }

    @Test
    public void when_call_get_player_should_be_error(){
        when(this.repository.findById(anyLong())).thenReturn(PlayerTestBuilder.getOptionalPlayerEmpty());
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

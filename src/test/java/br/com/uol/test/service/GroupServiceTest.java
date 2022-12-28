package br.com.uol.test.service;

import br.com.uol.test.builder.GroupTestBuilder;
import br.com.uol.test.enums.TeamEnum;
import br.com.uol.test.exceptions.HeroInconsistentWithTeamException;
import br.com.uol.test.exceptions.TeamIsFullException;
import br.com.uol.test.model.GroupModel;
import br.com.uol.test.repository.GroupRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;


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
    public void when_call_get_group_by_save_with_the_avengers_should_be_ok() throws TeamIsFullException {
        when(this.repository.findByTeam(any())).thenReturn(GroupTestBuilder.getListEmpty());
        when(this.httpService.requestTheAvengers()).thenReturn(GroupTestBuilder.getSuperHeroJson());
        final GroupModel group = this.groupService.getGroupBySave(TeamEnum.THE_AVENGERS);
        assertEquals("THE_AVENGERS", group.getTeam().name());
        assertNotEquals("", group.getSuperHero());
    }

    @Test
    public void when_call_get_group_by_save_with_justice_league_should_be_ok() throws TeamIsFullException {
        when(this.repository.findByTeam(any())).thenReturn(GroupTestBuilder.getListEmpty());
        when(this.httpService.requestJusticeLeague()).thenReturn(GroupTestBuilder.getHeroXml());
        final GroupModel gpm = this.groupService.getGroupBySave(TeamEnum.JUSTICE_LEAGUE);
        assertEquals("JUSTICE_LEAGUE", gpm.getTeam().name());
        assertNotEquals("", gpm.getSuperHero());
    }

    @Test
    public void when_call_get_group_by_alter_with_justice_league_should_be_ok() throws HeroInconsistentWithTeamException {
        when(this.repository.existsBySuperHero(any())).thenReturn(false);
        when(this.httpService.requestJusticeLeague()).thenReturn(GroupTestBuilder.getHeroXml());
        final GroupModel gpm = this.groupService.getGroupByAlter(GroupTestBuilder.getGroupModelXml());
        assertEquals("JUSTICE_LEAGUE", gpm.getTeam().name());
        assertNotEquals("", gpm.getSuperHero());
    }

    @Test
    public void when_call_get_group_by_alter_with_the_avengers_should_be_ok() throws HeroInconsistentWithTeamException {
        when(this.repository.existsBySuperHero(any())).thenReturn(false);
        when(this.httpService.requestTheAvengers()).thenReturn(GroupTestBuilder.getSuperHeroJson());
        final GroupModel gpm = this.groupService.getGroupByAlter(GroupTestBuilder.getGroupModelJson());
        assertEquals("THE_AVENGERS", gpm.getTeam().name());
        assertNotEquals("", gpm.getSuperHero());
    }

    @Test
    public void when_call_get_group_by_save_should_be_erro_superhero_not_available(){
        when(this.repository.findByTeam(any())).thenReturn(GroupTestBuilder.getList());
        when(this.httpService.requestJusticeLeague()).thenReturn(GroupTestBuilder.getHeroXml());
        try{
            final GroupModel gpm = this.groupService.getGroupBySave(TeamEnum.JUSTICE_LEAGUE);
        }catch (TeamIsFullException e){
            assertEquals("This team is full", e.getMessage());
        }
    }

    @Test
    public void when_call_get_group_should_be_erro_team_is_full(){
        when(this.repository.findByTeam(any())).thenReturn(GroupTestBuilder.getList());
        when(this.httpService.requestJusticeLeague()).thenReturn(GroupTestBuilder.getHeroXml());
        try{
            final GroupModel gpm = this.groupService.getGroupBySave(TeamEnum.JUSTICE_LEAGUE);
        }catch (TeamIsFullException e){
            assertEquals("This team is full", e.getMessage());
        }
    }
}

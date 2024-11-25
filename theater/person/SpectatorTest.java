package theater.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

import theater.TheaterSeating;
import theater.seating.Seat;

public class SpectatorTest {
    private static final int ROW_COUNT = 5;
    private static final int COL_COUNT = 5;
    private TheaterSeating theater;
    private Spectator spectator;

    @BeforeEach
    public void setup(){
        theater = new TheaterSeating(4,5);
        spectator = new Spectator("some name");
    }
    @Test
    public void testConstructorWithNullName(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Spectator("");
        });
    }
    
    @Test
    public void testTakeGift(){
        spectator = new Spectator("name");

        spectator.bookSpecificSeat(theater,0,1);

        assertTrue(spectator.takeGift());

        spectator.takeGift();
        assertFalse(spectator.getSeat().getHasGift());
        
        spectator.takeGift();
        assertFalse(spectator.getSeat().getHasGift());
    }

    @Test
    public void testBookAnySeat(){
        spectator = new Spectator("name");
        spectator.bookAnySeat(theater);
        assertNotNull(spectator.getSeat());
        assertTrue(spectator.getSeat().getIsOccupied());
    }
    @Test
    public void testBookSpecificSeat(){
        spectator = new Spectator("name");
        spectator.bookSpecificSeat(theater, 2, 2);
        Seat seat = spectator.getSeat();

        assertTrue(seat.getIsOccupied());
    }
    @Test
    public void testBookTailoredSeat(){
        spectator = new Spectator("name");
        spectator.bookTailoredSeat(theater);
        Seat tailoredSeat = spectator.getSeat();

        assertTrue(tailoredSeat.getIsOccupied());
        
    }
    @Test
    public void testGetName(){
        spectator = new Spectator("name");
        assertEquals("name", spectator.getName());
    }

    @Test
    public void testGetSeatInitialState(){
        spectator = new Spectator("name");
        assertNull(spectator.getSeat());
    }
}

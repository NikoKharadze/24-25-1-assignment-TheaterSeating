package theater;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import theater.seating.Seat;
import theater.seating.SeatType;

import static org.junit.jupiter.api.Assertions.*;

public class TheaterSeatingTest {
    private TheaterSeating theaterSeating;

    @BeforeEach
    public void setup() {
        theaterSeating = new TheaterSeating(4, 5);
    }

    @Test
    public void testInitialization() {
        assertEquals(4, theaterSeating.getN());
        assertEquals(5, theaterSeating.getM());

        assertEquals(SeatType.OT, theaterSeating.getSeatType(0, 0));
        assertEquals(SeatType.OT, theaterSeating.getSeatType(3, 4));
        assertEquals(SeatType.MT, theaterSeating.getSeatType(2, 2));
        assertEquals(SeatType.IT, theaterSeating.getSeatType(1, 3));
    }

    @ParameterizedTest
    @CsvSource({
        "4, 5, 10",
        "3, 3, 4",
        "6, 6, 18"
    })
    public void testGiftsInitialization(int rows, int cols, int expectedGifts) {
        TheaterSeating testTheater = new TheaterSeating(rows, cols);
        assertEquals(expectedGifts, testTheater.getAmountOfGifts());
    }

    @Test
    public void testBookSeat() {
        Seat firstBookedSeat = theaterSeating.bookSeat();
        assertNotNull(firstBookedSeat);
        assertTrue(theaterSeating.isBooked(0, 0));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> theaterSeating.bookSeat(0, 0));
        assertEquals("Seat is taken", exception.getMessage());
    }

    @Test
    public void testBookTailoredEmptySeat() {
        Seat tailoredSeat = theaterSeating.bookTailoredEmptySeat(SeatType.OT, true);
        assertNotNull(tailoredSeat);
        assertEquals(SeatType.OT, tailoredSeat.getSeatType());
        assertTrue(theaterSeating.isBooked(0, 0));
    }

    @Test
    public void testTotalTakenGifts() {
        int initialGifts = theaterSeating.getAmountOfGifts();
        assertEquals(initialGifts, theaterSeating.totalTakenGifts());

        theaterSeating.bookSeat(0,1);
        int remainingGifts = theaterSeating.totalTakenGifts();
        assertEquals(initialGifts - 1, remainingGifts);
    }

    @Test
    public void methodDecreaseGifts(){
        int initialGifts = theaterSeating.getAmountOfGifts();
        theaterSeating.decreaseGifts();
        assertEquals(initialGifts - 1, theaterSeating.totalTakenGifts());        
    }

    @Test
    public void testText() {
        theaterSeating.bookSeat(0, 0);
        theaterSeating.bookSeat(1, 2);
        theaterSeating.bookSeat(2, 4);

        StringBuilder sb = new StringBuilder();
        sb.append("[B] [A] [A] [A] [A] ");
        sb.append("\n");
        sb.append("[A] [A] [B] [A] [A] ");
        sb.append("\n");
        sb.append("[A] [A] [A] [A] [B] ");
        sb.append("\n");
        sb.append("[A] [A] [A] [A] [A] ");
        sb.append("\n");

        assertEquals(sb.toString(), theaterSeating.toString());
    }
}

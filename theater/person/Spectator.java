package theater.person;

import theater.TheaterSeating;
import theater.seating.Seat;
import theater.seating.SeatType;

public class Spectator {
    private final String name;
    private Seat seat;

    public Spectator(String name){
        if(name.length()==0 || name==null)throw new IllegalArgumentException("name is empty");
        this.name=name;
        seat=null;
    }

    public String getName(){return name;}
    public Seat getSeat(){return seat;}
    public boolean takeGift(){
        if(seat != null && seat.getHasGift()){
            seat.setHasGift(false);
            return true;
        }
        return false;
        
    }

    public void bookAnySeat(TheaterSeating theater){
        this.seat = theater.bookSeat();
        this.seat.setIsOccupied(true);
    }
    public void bookSpecificSeat(TheaterSeating theater, int row, int col){
        this.seat = theater.bookSeat(row, col);
        this.seat.setIsOccupied(true);
    }
    public void bookTailoredSeat(TheaterSeating theater){
        if (name.length() % 2 == 0){
            this.seat = theater.bookTailoredEmptySeat(SeatType.OT, true);
        }
        else if (name.length() < 3){
            this.seat = theater.bookTailoredEmptySeat(SeatType.OT,true);
        }
        else{
            this.seat = theater.bookTailoredEmptySeat(SeatType.MT,true);
        }
        this.seat.setIsOccupied(true);

    }
}

package theater.seating;
public class Seat{
    private final String id;
    private boolean hasGift;
    private final SeatType seatType;
    private boolean isOccupied;

    public Seat(String id, boolean hasGift, SeatType seatType){
        this.id = id;
        this.hasGift = hasGift;
        this.seatType = seatType;
        this.isOccupied = false;
    }

    public String getId(){return id;}
    public boolean getHasGift(){return hasGift;}
    public SeatType getSeatType(){return seatType;}
    public boolean getIsOccupied() {
        return isOccupied;
    }
    public void setIsOccupied(boolean occupied) {
        isOccupied = occupied;
    }
    
    public void setHasGift(boolean hasGift){this.hasGift=hasGift;}
    
    public boolean removeGift(){
        hasGift = false;
        return false;
    }

    @Override
    public String toString() {
        return String.format("Seat{id='%s', hasGift=%b, seatType=%s, isOccupied=%b}", 
                             id, hasGift, seatType, isOccupied);
    }
}
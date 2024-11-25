package theater;

import theater.seating.Seat;
import theater.seating.SeatType;

public class TheaterSeating{
    private int n;
    private int m;
    private Seat[][] seats;

    private boolean[][] bookedSeats;
    private int giftsTotal;

    public TheaterSeating(int n, int m){
        if(n < 0 || m < 0) throw new IllegalArgumentException ("Invalid Value");
        this.n = n;
        this.m = m;

        bookedSeats = new boolean[n][m];

        initSeating(n, m);
        giftsTotal = getAmountOfGifts();
    }

    public int getN(){return n;}
    public int getM(){return m;}

    public int getAmountOfGifts(){
        int gifts = 0;
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(seats[i][j].getHasGift()){gifts+=1;}
            }
        }

        return gifts;
    }

    public Seat[][] getSeats(){return seats;}

    public void decreaseGifts(){giftsTotal -= 1;}

    public int totalTakenGifts(){
        return giftsTotal;
    }

    private void initSeating(int n, int m){
        seats = new Seat[n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                SeatType st = null;
                if(i == 0 || i == n-1) st = SeatType.OT;
                else if(n % 2 == 1 && i == Math.floor(n / 2)+1) st = SeatType.MT;
                else if(n % 2 == 0 && ((i == (n / 2)+1) || (i == (n / 2)))) st = SeatType.MT;
                else st = SeatType.IT;
                if(i == 0 || i == n-1) seats[i][j] = new Seat(""+i+" "+j,(i+j)%2==1,st);
                else if(n % 2 == 1 && i == Math.floor(n / 2)+1) seats[i][j] = new Seat(""+i+" "+j,(i+j)%2==1,st);
                else if(n % 2 == 0 && ((i == (n / 2)+1) || (i == (n / 2)))) seats[i][j] = new Seat(""+i+" "+j,(i+j)%2==1,st);
                else seats[i][j] = new Seat(""+i+" "+j,(i+j)%2==1,st);
                bookedSeats[i][j] = false;
            }
        }
    }

    public Seat bookSeat(){
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(!bookedSeats[i][j]){
                    return bookSeat(i, j);
                }
            }
        }

        return null;
    }
    public Seat bookSeat(int i, int j){
        if(i >= n || j >= m) throw new IndexOutOfBoundsException("Index is out of range");
        if(bookedSeats[i][j]) throw new IllegalArgumentException("Seat is taken");
        bookedSeats[i][j] = true;

        if((i+j) % 2 == 1)
        { 
            decreaseGifts();
        }
        seats[i][j].setIsOccupied(true);
        return seats[i][j];
    }
    
    public Seat bookTailoredEmptySeat(SeatType type, boolean preferLeft){
        if(preferLeft){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    if(!bookedSeats[i][j] && seats[i][j].getSeatType() == type){
                        if((i+j) % 2 == 1)
                        {
                            decreaseGifts();
                        }
                        
                        bookedSeats[i][j]=true;
                        return seats[i][j];
                    }
                }
            }
        }else{
            for(int i = 0; i < n; i++){
                for(int j = m-1; j >= 0; j--){
                    if(!bookedSeats[i][j] && seats[i][j].getSeatType() == type){
                        if((i+j) % 2 == 1)
                        {
                            decreaseGifts();
                        }
                        bookedSeats[i][j]=true;
                        return seats[i][j];
                    }
                }
            }
        }
        return null;
    }

    public boolean isBooked(int i, int j){
        return bookedSeats[i][j];
    }

    public Seat getSeat(int i, int j){
        if(i >= n || j >= m) throw new IllegalArgumentException("index out of range");
        return seats[i][j];
    }
    public SeatType getSeatType(int i, int j){
        if(i >= n || j >= m) throw new IllegalArgumentException("index out of range");
        return seats[i][j].getSeatType();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(!bookedSeats[i][j]){
                    sb.append("[A] ");
                }else{
                    sb.append("[B] ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
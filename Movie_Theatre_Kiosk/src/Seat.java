public class Seat {
    char occupancy = '.';

    public Seat(){}

    public void setSeat(char c){
        occupancy = c;
    }
    public boolean getSeat(){
        if(occupancy == '.'){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public String toString(){
        String s = "" + occupancy;
        return s;
    }
}

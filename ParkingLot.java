import java.util.HashSet;

public class ParkingLot {

    private int emptySlots;
    private HashSet<Vehicle> vehicles;
    private Owner owner;
    private SecurityPersonnel security;

    ParkingLot(int emptySlots){
        this(emptySlots,null);
    }

    ParkingLot(int emptySlots, Owner owner) {
        this(emptySlots,owner,null);
    }

    ParkingLot(int emptySlots, Owner owner, SecurityPersonnel security){
        this.emptySlots = emptySlots;
        vehicles = new HashSet<>();
        this.owner = owner;
        this.security = security;
    }


    public void park(Vehicle car) throws ParkingLotException{
       if(isFull()){
           throw ParkingLotException.lotFullException();
       }
       if(vehicles.contains(car)) {
           throw ParkingLotException.alreadyParkedException();
       }
       vehicles.add(car);
       emptySlots--;
        if(isFull() && owner != null){
            owner.notifyParkingLotIsFull();
        }
    }

    public void unpark(Vehicle car) throws ParkingLotException{
        if(!vehicles.contains(car)) {
            throw ParkingLotException.alreadyUnparkedException();
        }
        vehicles.remove(car);
        emptySlots++;
        if(isAvailable() && owner != null && security != null) {
            security.notifyParkingSpaceAvailable();
        }
    }

    public boolean isFull(){
        return (emptySlots == 0);
    }
    public boolean isAvailable() { return (emptySlots != 0); }
}
public class ParkingLotException extends Exception{

    static final String LOT_FULL = "Parking Lot Is Full";
    static final String ALREADY_PARKED = "Vehicle already present";
    static final String ALREADY_UNPARKED = "Vehicle not present";

    private ParkingLotException(String message){
        super(message);
    }

    public static ParkingLotException lotFullException(){
        return new ParkingLotException(LOT_FULL);
    }
    public static ParkingLotException alreadyParkedException(){
        return new ParkingLotException(ALREADY_PARKED);
    }
    public static ParkingLotException alreadyUnparkedException(){
        return new ParkingLotException(ALREADY_UNPARKED);
    }

}


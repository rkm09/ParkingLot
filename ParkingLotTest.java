import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;

public class ParkingLotTest {
    ParkingLot parkingLot ;
    Vehicle car;

    @Before
    public void setUp(){
        car = new Car();
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldSuccessfullyPark() throws ParkingLotException{
        parkingLot = new ParkingLot(4);
        parkingLot.park(car);
    }

    @Test
    public void shouldSuccessfullyUnPark() throws ParkingLotException{
        parkingLot = new ParkingLot(2);
        parkingLot.park(car);
        parkingLot.park(new Car());
        parkingLot.unpark(car);
    }

    @Test
    public void shouldThrowAnExceptionIfParkingLotIsFull() throws ParkingLotException{
        expectedException.expect(ParkingLotException.class);
        expectedException.expectMessage(ParkingLotException.LOT_FULL);
        parkingLot = new ParkingLot(0);
        parkingLot.park(car);
    }

    @Test
    public void shouldThrowAnExceptionIfAlreadyParked() throws ParkingLotException{
        expectedException.expect(ParkingLotException.class);
        expectedException.expectMessage(ParkingLotException.ALREADY_PARKED);
        parkingLot = new ParkingLot(3);
        parkingLot.park(car);
        parkingLot.park(car);
    }

    @Test
    public void shouldThrowAnExceptionIfAlreadyUnparked() throws ParkingLotException{
        expectedException.expect(ParkingLotException.class);
        expectedException.expectMessage(ParkingLotException.ALREADY_UNPARKED);
        parkingLot =  new ParkingLot(0);
        parkingLot.unpark(car);
    }

    @Test
    public void shouldNotifyOwnerIfParkingLotIsFull() throws ParkingLotException{
        Owner mockOwner = mock(Owner.class);
        ParkingLot parkingLot = new ParkingLot(1,mockOwner);
        parkingLot.park(car);
        Mockito.verify(mockOwner).notifyParkingLotIsFull();
    }

    @Test
    public void shouldNotifySecurityIfParkingSpaceIsAvailable() throws ParkingLotException{
        SecurityPersonnel mockSecurity = mock(SecurityPersonnel.class);
        Owner mockOwner = mock(Owner.class);
        ParkingLot parkingLot = new ParkingLot(1,mockOwner,mockSecurity);
        parkingLot.park(car);
        parkingLot.unpark(car);
        Mockito.verify(mockSecurity).notifyParkingSpaceAvailable();
    }
}

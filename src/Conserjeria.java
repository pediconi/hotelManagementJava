import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Conserjeria {

    private Hotel hotel;

    public Conserjeria(Hotel hotel) {
        this.hotel = hotel;
    }




    public void refreshReservas() {   // este metodo se tendria q ejecutar siempre a diario para ir borrando las reservas que ya pasaron
        for (Reserva r : hotel.getReservas()) {
            if (r.getFin().isEqual(LocalDate.now())) {
                hotel.getReservas().remove(r);
            }
        }

    }




}

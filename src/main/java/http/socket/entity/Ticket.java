package http.socket.entity;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
@Value
@Builder
public class Ticket {
     Long id;
     String passengerNo;
     String passengerName;
     Long flightId;
     String seatNo;
     BigDecimal cost;
}

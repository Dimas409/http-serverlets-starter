package http.socket.service;

import http.socket.dao.TicketDao;
import http.socket.dto.TicketDto;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class TicketService {
    private static final TicketService INSTANCE = new TicketService();
    private final TicketDao ticketDao = TicketDao.getInstance();

    public static TicketService getInstance(){
        return INSTANCE;
    }
    public List<TicketDto> findAllByFlightId(Long flightId){
        return ticketDao.findAllByFlightId(flightId).stream()
                .map(ticket -> TicketDto.builder()
                                .id(ticket.getId())
                                .flightId(ticket.getFlightId())
                                .seatNo(ticket.getSeatNo())
                                .build()
                )
                .collect(toList());
    }
}

package http.socket.dao;

import http.socket.dto.FlightDto;
import http.socket.entity.Flight;
import http.socket.entity.FlightStatus;
import http.socket.util.ConnectionManager;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightDao implements Dao<Long, Flight> {
    public static final FlightDao INSTANCE = new FlightDao();
    public static final String FIND_ALL = """
            SELECT 
                id, 
                flight_no,
                departure_date,
                departure_airport_code,
                arrival_date,
                arrival_airport_code,
                aircraft_id,
                status
            FROM flight
            """;

    private FlightDao() {
    }

    @Override
    public List<Flight> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Flight> flights = new ArrayList<>();
            while (resultSet.next()){
                flights.add(buildFlight(resultSet));
            }
            return flights;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static FlightDao getInstance(){
        return INSTANCE;
    }

    private Flight buildFlight(ResultSet resultSet) {
        try {
            return Flight.builder()
                            .id(resultSet.getObject("id", Long.class))
                            .flightNo(resultSet.getObject("flight_no", String.class))
                            .departureDate(resultSet.getObject("departure_date", Timestamp.class).toLocalDateTime())
                            .departureAirportCode(resultSet.getObject("departure_airport_code", String.class))
                            .arrivalDate(resultSet.getObject("arrival_date", Timestamp.class).toLocalDateTime())
                            .arrivalAirportCode(resultSet.getObject("arrival_airport_code", String.class))
                            .aircraftId(resultSet.getObject("aircraft_id", Integer.class))
                            .status(FlightStatus.valueOf(resultSet.getObject("status", String.class)))
                            .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Flight> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(Flight entity) {

    }

    @Override
    public Flight save(Flight entity) {
        return null;
    }
}

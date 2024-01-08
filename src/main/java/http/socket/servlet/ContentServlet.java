package http.socket.servlet;

import http.socket.dto.FlightDto;
import http.socket.service.FlightService;
import http.socket.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static http.socket.util.UrlPath.CONTENT;
import static java.util.stream.Collectors.toMap;

@WebServlet(CONTENT)
public class ContentServlet extends HttpServlet {
    private final FlightService flightService = FlightService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<FlightDto> flightDtos = flightService.findAll();
        req.setAttribute("flights", flightDtos);
        req.getSession().setAttribute("flightsMap", flightDtos.stream()
                .collect(toMap(FlightDto::getId, FlightDto::getDescription)));


        req.getRequestDispatcher(JspHelper.getPath("content"))
                .forward(req, resp);
    }
}

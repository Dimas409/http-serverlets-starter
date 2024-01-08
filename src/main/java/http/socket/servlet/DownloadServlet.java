package http.socket.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import static http.socket.util.UrlPath.DOWNLOAD;

@WebServlet(DOWNLOAD)
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Disposition", "attachment; filename=\"filename.txt\"");
        resp.setContentType("application/json");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

//        Files.readAllBytes(Path.of("resources", "first.json"));
        try (OutputStream outputStream = resp.getOutputStream();
             InputStream inputStream = DownloadServlet.class.getClassLoader().getResourceAsStream("first.json")) {

            outputStream.write(inputStream.readAllBytes());
        }
    }
}

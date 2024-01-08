package http.socket.mapper;

public interface Mapper<F, T> {

    T mapFrom(F object);
}

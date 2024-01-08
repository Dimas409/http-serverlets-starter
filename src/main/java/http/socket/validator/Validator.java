package http.socket.validator;

public interface Validator<T>{
    ValidationResult isValid(T object);
}

package by.rzmarket.validator;

public interface Validator<T> {

    boolean isValid(T object, T object2);
}

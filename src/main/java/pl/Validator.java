package pl;

public interface Validator<T> {
   ErrorMap valid(T object);

}

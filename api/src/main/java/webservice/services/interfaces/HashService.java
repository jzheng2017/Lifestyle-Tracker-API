package webservice.services.interfaces;

public interface HashService {
    String encode(String plain);
    boolean valid(String raw, String encoded);
}

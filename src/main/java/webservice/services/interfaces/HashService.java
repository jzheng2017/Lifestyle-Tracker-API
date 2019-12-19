package webservice.services.interfaces;

public interface HashService {
    /**
     * Encode a plain text value
     *
     * @param plain text that has to be encoded
     * @return encoded value
     */
    String encode(String plain);

    /**
     * Validates whether the raw value is the same as the encoded value
     *
     * @param raw     unencoded value
     * @param encoded encoded value
     * @return whether the two values match
     */
    boolean valid(String raw, String encoded);
}

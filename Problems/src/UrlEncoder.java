import java.util.HashMap;
import java.util.Map;

public class UrlEncoder {
  Map<String, String> hashToUrl = new HashMap<>();

  public String encode(String longUrl) {
    int hash = longUrl.hashCode();
    hashToUrl.put(String.valueOf(hash), longUrl);
    return String.valueOf(hash);
  }

  public String decode(String shortUrl) {
    return hashToUrl.get(shortUrl);
  }
}
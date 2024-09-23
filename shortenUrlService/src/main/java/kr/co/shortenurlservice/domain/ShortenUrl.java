package kr.co.shortenurlservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
public class ShortenUrl {
    private String originalUrl;
    private String shortenUrlKey;
    private Long redirectCount;

    public ShortenUrl(String originalUrl, String shortenUrlKey) {
        this.originalUrl = originalUrl;
        this.shortenUrlKey = shortenUrlKey;
        this.redirectCount = 0L;
    }

    public static String generateShortenUrlKey(){
        String base56Characters = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";

        Random random = new Random();
        StringBuilder shortenUrlKey = new StringBuilder();

        for (int count = 0; count < 8; count++) {
            int base56CharactersIdx = random.nextInt(0,base56Characters.length());
            char base56Char = base56Characters.charAt(base56CharactersIdx);
            shortenUrlKey.append(base56Char);
        }
        return shortenUrlKey.toString();
    }
    public void increaseRedirectCount(){
        this.redirectCount+= this.redirectCount + 1;
    }
}

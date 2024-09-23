package kr.co.shortenurlservice.presentation.dto;

import kr.co.shortenurlservice.domain.ShortenUrl;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ShortenUrlInformationDto {
    private String originalUrl;
    private String shortenUrlKey;
    private Long redirectCount;

    public ShortenUrlInformationDto(ShortenUrl shortenUrl) {
        this.originalUrl = shortenUrl.getOriginalUrl();
        this.shortenUrlKey = shortenUrl.getShortenUrlKey();
        this.redirectCount = shortenUrl.getRedirectCount();
    }
}

package kr.co.shortenurlservice.presentation.dto;

import kr.co.shortenurlservice.domain.ShortenUrl;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ShortenUrlCreateResponseDto {
    private String originalUrl;
    private String shortenUrlKey;

    public ShortenUrlCreateResponseDto(ShortenUrl shortenUrl) {
        this.originalUrl = shortenUrl.getOriginalUrl();
        this.shortenUrlKey = shortenUrl.getShortenUrlKey();
    }
}

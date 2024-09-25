package kr.co.shortenurlservice.application;

import kr.co.shortenurlservice.domain.Exception.NotFoundShortenUrlException;
import kr.co.shortenurlservice.presentation.dto.ShortenUrlCreateRequestDto;
import kr.co.shortenurlservice.presentation.dto.ShortenUrlCreateResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SimpleShortenUrlServiceTest {
    @Autowired
    private SimpleShortenUrlService shortenUrlService;

    @Test
    @DisplayName("URL 단축한 후 단축된 URL 키로 조회하면 원래 URL이 조회되어야 한다.")
    void shortenUrlAddTest() {
        String expectedOriginalUrl = "https://www.google.com";
        ShortenUrlCreateRequestDto requestDto = new ShortenUrlCreateRequestDto(expectedOriginalUrl);

        ShortenUrlCreateResponseDto responseDto = shortenUrlService.generateShortenUrl(requestDto);
        String shortenUrlKey = responseDto.getShortenUrlKey();

        String originalUrl = shortenUrlService.getOriginalUrlByShortenUrlKey(shortenUrlKey);
        assertTrue(originalUrl.equals(expectedOriginalUrl));
    }

    @Test
    @DisplayName("존재하지 않는 URL을 조회하는 경우, NotFoundShortenUrlException 을 반환한다.")
    void throwNotFoundShortenUrlExceptionTest() {
        String requestedUrlKey = "NBsd2Pqs";
        assertThrows(
                NotFoundShortenUrlException.class,
                () -> shortenUrlService.getShortenUrlInformationByShortenUrlKey(requestedUrlKey)
        );
        assertThrows(
                NotFoundShortenUrlException.class,
                () -> shortenUrlService.getShortenUrlInformationByShortenUrlKey(requestedUrlKey)
        );
    }

}
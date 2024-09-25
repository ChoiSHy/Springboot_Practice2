package kr.co.shortenurlservice.application;

import kr.co.shortenurlservice.domain.Exception.LackOfShortenUrlKeyException;
import kr.co.shortenurlservice.domain.Exception.NotFoundShortenUrlException;
import kr.co.shortenurlservice.domain.ShortenUrl;
import kr.co.shortenurlservice.domain.ShortenUrlRepository;
import kr.co.shortenurlservice.presentation.dto.ShortenUrlCreateRequestDto;
import kr.co.shortenurlservice.presentation.dto.ShortenUrlCreateResponseDto;
import kr.co.shortenurlservice.presentation.dto.ShortenUrlInformationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleShortenUrlService {
    private ShortenUrlRepository shortenUrlRepository;

    @Autowired
    public SimpleShortenUrlService(ShortenUrlRepository shortenUrlRepository) {
        this.shortenUrlRepository = shortenUrlRepository;
    }

    public ShortenUrlCreateResponseDto generateShortenUrl(ShortenUrlCreateRequestDto shortenUrlCreateRequestDto) {
        String originalUrl = shortenUrlCreateRequestDto.getOriginalUrl();
        String shortenUrlKey = getUniqueShortenUrlKey();


        ShortenUrl shortenUrl = new ShortenUrl(originalUrl, shortenUrlKey);
        shortenUrlRepository.saveShortenUrl(shortenUrl);

        ShortenUrlCreateResponseDto createdResponseDto = new ShortenUrlCreateResponseDto(shortenUrl);
        return createdResponseDto;
    }

    private String getUniqueShortenUrlKey() {
        final int MAX_RETRY_COUNT = 5;
        int count = 0;

        while (count++ < MAX_RETRY_COUNT) {
            String shortenUrlKey = ShortenUrl.generateShortenUrlKey();
            ShortenUrl shortenUrl = shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlKey);

            if (null == shortenUrl)
                return shortenUrlKey;
        }
        throw new LackOfShortenUrlKeyException();
    }

    public String getOriginalUrlByShortenUrlKey(String shortenUrlKey) {
        ShortenUrl shortenUrl = shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlKey);

        if (null == shortenUrl)
            throw new NotFoundShortenUrlException();

        shortenUrl.increaseRedirectCount();
        shortenUrlRepository.saveShortenUrl(shortenUrl);

        String originalUrl = shortenUrl.getOriginalUrl();
        return originalUrl;
    }

    public ShortenUrlInformationDto getShortenUrlInformationByShortenUrlKey(String shortenUrlKey) {
        ShortenUrl shortenUrl = shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlKey);

        if (null == shortenUrl)
            throw new NotFoundShortenUrlException();

        ShortenUrlInformationDto responseInfoDto = new ShortenUrlInformationDto(shortenUrl);
        return responseInfoDto;
    }

}

package kr.co.shortenurlservice.presentation;

import jakarta.validation.Valid;
import kr.co.shortenurlservice.application.SimpleShortenUrlService;
import kr.co.shortenurlservice.domain.ShortenUrl;
import kr.co.shortenurlservice.presentation.dto.ShortenUrlCreateRequestDto;
import kr.co.shortenurlservice.presentation.dto.ShortenUrlCreateResponseDto;
import kr.co.shortenurlservice.presentation.dto.ShortenUrlInformationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ShortenUrlController {
    private SimpleShortenUrlService simpleShortenUrlService;
    @Autowired
    public ShortenUrlController(SimpleShortenUrlService simpleShortenUrlService) {
        this.simpleShortenUrlService = simpleShortenUrlService;
    }

    /* 단축 URL 생성 */
    @RequestMapping(value = "/shortenUrl", method = RequestMethod.POST)
    public ResponseEntity<ShortenUrlCreateResponseDto> createShortenUrl(@Valid @RequestBody ShortenUrlCreateRequestDto shortenUrlRequestDto){
        ShortenUrlCreateResponseDto createdResponseDto = simpleShortenUrlService.generateShortenUrl(shortenUrlRequestDto);

        return ResponseEntity.ok().body(createdResponseDto);
    }

    /* 단축 URL 리다이렉트 */
    @RequestMapping(value = "/{shortenUrl}", method = RequestMethod.GET)
    public ResponseEntity<?> redirectShortenUrl(@PathVariable String shortenUrl) throws URISyntaxException {
        String originalUrl = simpleShortenUrlService.getOriginalUrlByShortenUrlKey(shortenUrl);

        URI redirectUri = new URI(originalUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(redirectUri);

        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }
    
    /* 단축 URL 정보 요청 */
    @RequestMapping(value = "/shortenUrl/{shortenUrl}", method = RequestMethod.GET)
    public ResponseEntity<ShortenUrlInformationDto> getShortenUrlInformation(@PathVariable String shortenUrl){
        ShortenUrlInformationDto responseInfoDto = simpleShortenUrlService.getShortenUrlInformationByShortenUrlKey(shortenUrl);

        return ResponseEntity.ok().body(responseInfoDto);
    }
}

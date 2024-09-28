package kr.co.shortenurlservice.presentation;

import kr.co.shortenurlservice.application.SimpleShortenUrlService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@WebMvcTest(controllers = ShortenUrlController.class)   // 컨트롤러 단위 테스트 시작(테스트할 컨트롤러 지정)
                                                        // 컨트롤러 빈을 실제로 생성 -> 단위 테스트지만,
                                                        // 통합 테스트처럼 애플리케이션이 실제로 실행되는 것과 동일한 효과
class ShortenUrlControllerTest {

    @MockBean
    // 스프링 컨테이너에서 빈을 생성
    // @Mock은 컨테이너와 무관하게@InjectMocks 애너테이션이 붙은 곳으로 의존성을 넣음
    private SimpleShortenUrlService simpleShortenUrlService;
    @Autowired
    private MockMvc mockMvc;    // 컨트롤러를 테스트하기 위한 기능

    @Test
    @DisplayName("원래의 URL로 리다이렉트 되어야한다.")
    void redirectTest() throws Exception{
        String expectedOriginalUrl = "https://www.google.com";

        when(simpleShortenUrlService.getOriginalUrlByShortenUrlKey(any()))
                .thenReturn(expectedOriginalUrl);       // 서비스 파트의 동작을 지정


        mockMvc.perform(get("/any-key"))        // MockMvc에게 '/any-key' 경로로 GET메서드 요청
                .andExpect(status().isMovedPermanently()) // 검증 -> 상태
                .andExpect(header().string("Location",expectedOriginalUrl)); // 검증 -> 헤더(Location)파트
    }
}
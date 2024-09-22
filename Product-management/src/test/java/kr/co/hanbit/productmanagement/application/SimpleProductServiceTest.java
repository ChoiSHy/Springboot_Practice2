package kr.co.hanbit.productmanagement.application;

import kr.co.hanbit.productmanagement.domain.Exception.EntityNotFoundException;
import kr.co.hanbit.productmanagement.domain.Product;
import kr.co.hanbit.productmanagement.presentation.DTO.ProductDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest     // 스프링 컨테이너 통합 테스트
@ActiveProfiles("prod") // Profile 지정 -> test
class SimpleProductServiceTest {
    @Autowired  // 테스트 상황에선 생성자에서 주입하지 않고, 바로 의존성 주입을 해도 무관
    SimpleProductService simpleProductService;

    @Transactional  // 테스트 시 데이터베이스에 테스트 내용이 반영되지 않도록 트랜젝션 처리 표시. 테스트 후 커밋 아닌 롤백됨.
    @Test   // 테스트 코드 의미
    @DisplayName("상품을 추가한 후 id로 조회하면 해당 상품이 조회되어야 한다.") // 테스트 코드 이름
    void productAddAndFindByIdTest(){
        ProductDto productDto = new ProductDto("연필", 300, 20);

        ProductDto savedProductDto = simpleProductService.add(productDto);      // create 기능
        Long savedProductId = savedProductDto.getId();

        ProductDto foundProductDto = simpleProductService.findById(savedProductId); // read 기능

        /* 두 기능의 결과를 비교 하면서 이상 여부 확인 */
        assertTrue(savedProductDto.getId().equals( foundProductDto.getId()));
        assertTrue(savedProductDto.getName().equals( foundProductDto.getName()));
        assertTrue(savedProductDto.getPrice().equals( foundProductDto.getPrice()));
        assertTrue(savedProductDto.getAmount().equals( foundProductDto.getAmount()));
    }

    @Test
    @DisplayName("존재하지 않는 상품 id로 조회하면 EntityNotFoundException이 발생해야한다. ")
    void findProductNotExistIdTest(){
        Long notExistId = -1L;

        assertThrows(EntityNotFoundException.class, ()->{
            simpleProductService.findById(notExistId);
        });
    }
}
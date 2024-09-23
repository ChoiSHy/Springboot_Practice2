package kr.co.hanbit.productmanagement.application;

import kr.co.hanbit.productmanagement.domain.Product;
import kr.co.hanbit.productmanagement.domain.ProductRepository;
import kr.co.hanbit.productmanagement.presentation.DTO.ProductDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // 단위 테스트인 경우에 사용하는 어노테이션
                                    // -> 현재 어노테이션은 애플리케이션을 실행시키지 않고도 테스트 코드를 실행
class SimpleProductServiceUnitTest {

    @Mock   // 해당 의존성에 '목 객체(Mock Object)'를 주입 -> 해당 객체가 어떤 동작을 하는지는 직접 정의해야 함.
    private ProductRepository productRepository;

    @Mock
    private ValidationService validationService;

    @InjectMocks    // 앞서 정의한 (@Mock)들을 해당 객체 안에 주입. 아래 객체는 실제 인스턴스를 생성하여 로직으로 활용
    private SimpleProductService simpleProductService;

    @Test
    @DisplayName("상품 추가 후에는 추가된 상품이 반환되어야한다.")
    void productAddTest(){
        ProductDto productDto = new ProductDto("연필", 300,20);
        Long PRODUCT_ID= 1L;

        Product product = ProductDto.toEntity(productDto);
        product.setId(PRODUCT_ID);
        when(productRepository.add(any())).thenReturn(product);
        
        // when() : 목 객체가 해당 동작을 수행할 떄
        // thenReturn() : 다음 값을 반환
        // any() : 아무 값이나 넣는 것

        ProductDto savedProduct = simpleProductService.add(productDto);

        assertTrue(savedProduct.getId().equals(PRODUCT_ID));
        assertTrue(savedProduct.getName().equals(savedProduct.getName()));
        assertTrue(savedProduct.getPrice().equals(savedProduct.getPrice()));
        assertTrue(savedProduct.getAmount().equals(savedProduct.getAmount()));

    }

}
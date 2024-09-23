package kr.co.hanbit.productmanagement.application;

import kr.co.hanbit.productmanagement.domain.Product;
import kr.co.hanbit.productmanagement.domain.ProductRepository;
import kr.co.hanbit.productmanagement.presentation.DTO.ProductDto;
//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleProductService {
    private ValidationService validationService;
    private ProductRepository productRepository;

    @Autowired
    public SimpleProductService(ProductRepository productRepository, ValidationService validationService) {
        this.productRepository = productRepository;
        this.validationService = validationService;
    }

    public ProductDto add(ProductDto productDto) {
        // 1. ProductDto -> Product 변환
        Product product = ProductDto.toEntity(productDto);

        // 1.5 유효성 검사
        validationService.checkValid(product);

        // 2. repository 호출
        Product savedProduct = productRepository.add(product);

        // 3. Product -> ProductDto 변환
        ProductDto savedProductDto = ProductDto.toDto(savedProduct);
        // 4. DTO return
        return savedProductDto;
    }

    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id);                   // repository 에서 id에 해당하는 객체 호출
        ProductDto productDto = ProductDto.toDto(product); // Product -> ProductDto
        return productDto;
        //return new ProductDto();
    }

    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = products.stream()
                .map(product -> ProductDto.toDto(product))// products의 각 항목을 ProductDto로 변환
                .toList();  // List로 반환
        return productDtos;
    }
    public List<ProductDto> findByNameContaining(String name){
        List<Product> products = productRepository.findByNameContaining(name);
        List<ProductDto> productDtos = products.stream()
                .map(product -> ProductDto.toDto(product))// Product -> ProductDto
                .toList();  // List return
        return productDtos;
    }
    public ProductDto update(ProductDto productDto){
        Product product = ProductDto.toEntity(productDto);
        Product updatedProduct = productRepository.update(product);
        ProductDto updatedProductDto = ProductDto.toDto(updatedProduct);
        return updatedProductDto;
    }
    public void delete(Long id){
        productRepository.delete(id);
    }


}

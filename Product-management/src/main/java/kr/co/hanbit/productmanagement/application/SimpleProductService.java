package kr.co.hanbit.productmanagement.application;

import kr.co.hanbit.productmanagement.domain.Product;
import kr.co.hanbit.productmanagement.infrastructure.ProductRepository;
import kr.co.hanbit.productmanagement.presentation.DTO.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleProductService {
    private ProductRepository productRepository;
    private ModelMapper modelMapper;

    @Autowired
    SimpleProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    public ProductDto add(ProductDto productDto) {
        // 1. ProductDto -> Product 변환
        Product product = modelMapper.map(productDto, Product.class);

        // 2. repository 호출
        Product savedProduct = productRepository.add(product);

        // 3. Product -> ProductDto 변환
        ProductDto savedProductDto = modelMapper.map(savedProduct, ProductDto.class);

        // 4. DTO return
        return savedProductDto;
    }

    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id);                   // repository 에서 id에 해당하는 객체 호출
        ProductDto productDto = modelMapper.map(product, ProductDto.class); // Product -> ProductDto
        return productDto;
    }

    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))// products의 각 항목을 ProductDto로 변환
                .toList();  // List로 반환
        return productDtos;
    }
    public List<ProductDto> findByName(String name){
        List<Product> products = productRepository.findByName(name);
        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))// Product -> ProductDto
                .toList();  // List return
        return productDtos;
    }
    public ProductDto update(ProductDto productDto){
        Product product = modelMapper.map(productDto, Product.class);
        Product updatedProduct = productRepository.update(product);
        ProductDto updatedProductDto = modelMapper.map(updatedProduct, ProductDto.class);
        return updatedProductDto;
    }
    public void delete(Long id){
        productRepository.delete(id);
    }


}

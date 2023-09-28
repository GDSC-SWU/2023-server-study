package com.project.shopping.service.products;

import com.project.shopping.domain.products.Products;
import com.project.shopping.domain.products.ProductsRepository;
import com.project.shopping.web.dto.ProductsResponseDto;
import com.project.shopping.web.dto.ProductsSaveRequestDto;
import com.project.shopping.web.dto.ProductsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    @Transactional
    public Long save(ProductsSaveRequestDto requestDto){
        return productsRepository.save(requestDto.toEntity()).getId();
    }

//    @Transactional
//    public List<ProductsReponseDto> getAllProducts(){
//        List<Products> entity = productsRepository.findAll();
//        return entity;
//    }


    public ProductsResponseDto findById(Long id){
        Products entity=productsRepository.findById(id)
                .orElseThrow(()-> new
                        IllegalArgumentException("해당 상품이 없습니다. id= "+id));

        return new ProductsResponseDto(entity);
    }

    @Transactional
    public Long update(Long id, ProductsUpdateRequestDto requestDto){
        Products products=productsRepository.findById(id)
                .orElseThrow(()-> new
                        IllegalArgumentException("해당 상품이 없습니다. id= "+id));
        products.update(requestDto.getProduct_name(), requestDto.getProduct_number(),requestDto.getStock(),requestDto.getCategory(),requestDto.getPrice());
        return id ;

    }

    @Transactional
    public void delete(Long id){
        Products products = productsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 상품이 없습니다. id= " + id));

        productsRepository.delete(products);
    }

}

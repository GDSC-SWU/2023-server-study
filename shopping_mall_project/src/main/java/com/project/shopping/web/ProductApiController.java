package com.project.shopping.web;

import com.project.shopping.service.products.ProductsService;
import com.project.shopping.web.dto.ProductsResponseDto;
import com.project.shopping.web.dto.ProductsSaveRequestDto;
import com.project.shopping.web.dto.ProductsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ProductApiController {

    private final ProductsService productsService;

    @PostMapping("/api/v1/products")
    public Long register(@RequestBody ProductsSaveRequestDto requestDto){
        return productsService.save(requestDto);
    }

    @GetMapping("/api/v1/products/{id}")
    public ProductsResponseDto findById(@PathVariable Long id){
        return productsService.findById(id);
    }

    @PutMapping("/api/v1/products/{id}")
    public Long update(@PathVariable Long id,@RequestBody ProductsUpdateRequestDto requestDto){
        return productsService.update(id,requestDto);
    }

    @DeleteMapping("/api/v1/products/{id}")
    public void delete(@PathVariable Long id){
        productsService.delete(id);
    }




}

package com.rdi.menu.service;

import com.rdi.menu.dto.ProductComponentsDTO;
import com.rdi.menu.dto.ProductDTO;
import com.rdi.menu.enums.Status;
import com.rdi.menu.exception.ProductNotFoundException;
import com.rdi.menu.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static com.rdi.menu.utils.ProductUtils.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    private MockMvc mockMvc;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

//    @Test
//    void whenListProductIsCalledThenReturnAListOfProducts() {
//        // given
//        List<ProductDTO> productDTOList = createFakeProductDTOList();
//        ProductDTO expectedProductDTO = createFakeProductDTO();
//        // when
//        when(productService.listAll()).thenReturn((productDTOList));
//        //then
//        List<ProductDTO> foundProductDTO = productService.listAll();
//        assertThat(foundProductDTO, is(not(empty())));
//        assertThat(foundProductDTO.get(0).getId(), is(equalTo(expectedProductDTO.getId())));
//    }
//
//    @Test
//    void whenListProductComponentsIsCalledThenReturnAListOfProductComponents() {
//        // given
//        List<ProductComponentsDTO> productComponentDTOList = createFakeProductComponentsDTOList();
//        ProductComponentsDTO expectedProductComponentsDTO = createFakeProductComponentDTOTypeChoice();
//        // when
//        when(productService.listAllComponents()).thenReturn((productComponentDTOList));
//        //then
//        List<ProductComponentsDTO> foundProductComponentsDTO = productService.listAllComponents();
//        assertThat(foundProductComponentsDTO, is(not(empty())));
//        assertThat(foundProductComponentsDTO.get(0).getId(), is(equalTo(expectedProductComponentsDTO.getId())));
//    }

}

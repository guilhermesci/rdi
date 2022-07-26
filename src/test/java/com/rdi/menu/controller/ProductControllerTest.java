package com.rdi.menu.controller;

import com.rdi.menu.dto.ProductComponentsDTO;
import com.rdi.menu.dto.ProductDTO;
import com.rdi.menu.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.List;

import static com.rdi.menu.utils.ProductUtils.createFakeProductComponentsDTOList;
import static com.rdi.menu.utils.ProductUtils.createFakeProductDTOList;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    private static final String PRODUCT_API_URL_PATH = "/api/v1/products";
    private static final String PRODUCT_COMPONENTS_API_URL_PATH = "/api/v1/products/components";

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    public void whenGETIsCalledThenOkStatusIsReturned() throws Exception
    {
        // given
        List<ProductDTO> productDTOList = createFakeProductDTOList();
        // when
        when(productService.listAll()).thenReturn((productDTOList));
        // then
        mockMvc.perform(MockMvcRequestBuilders.get(PRODUCT_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(productDTOList.get(0).getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(productDTOList.get(0).getName())))
                .andExpect(jsonPath("$[0].type", is(productDTOList.get(0).getType().toString())));
    }

    @Test
    public void whenGETComponentsIsCalledThenOkStatusIsReturned() throws Exception
    {
        // given
        List<ProductComponentsDTO> productComponentsDTOList = createFakeProductComponentsDTOList();
        // when
        when(productService.listAllComponents()).thenReturn((productComponentsDTOList));
        // then
        mockMvc.perform(MockMvcRequestBuilders.get(PRODUCT_COMPONENTS_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(productComponentsDTOList.get(0).getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(productComponentsDTOList.get(0).getName())))
                .andExpect(jsonPath("$[0].type", is(productComponentsDTOList.get(0).getType().toString())));
    }

}

package com.rdi.menu.controller;

import com.rdi.menu.dto.ProductStatusDTO;
import com.rdi.menu.enums.Status;
import com.rdi.menu.exception.ProductNotAllowedToUpdateStatusException;
import com.rdi.menu.exception.ProductNotFoundException;
import com.rdi.menu.service.ProductStatusService;
import org.hamcrest.core.Is;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.List;

import static com.rdi.menu.utils.ProductStatusUtils.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.core.Is.is;

@ExtendWith(MockitoExtension.class)
public class ProductStatusControllerTest {

    private static final String PRODUCT_STATUS_API_URL_PATH = "/api/v1/products/status";
    private static final Long INVALID_PRODUCT_ID = 9999L;
    private static final Status NEW_STATUS = Status.INACTIVE;

    private MockMvc mockMvc;

    @Mock
    private ProductStatusService productStatusService;

    @InjectMocks
    private ProductStatusController productStatusController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productStatusController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    public void whenGETIsCalledThenOkStatusIsReturned() throws Exception
    {
        // given
        List<ProductStatusDTO> productStatusDTOList = createFakeProductStatusDTOList();
        // when
        when(productStatusService.listAll()).thenReturn((productStatusDTOList));
        // then
        mockMvc.perform(MockMvcRequestBuilders.get(PRODUCT_STATUS_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id",
                        is(productStatusDTOList.get(0).getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].status",
                        is(productStatusDTOList.get(0).getStatus().toString())));
    }

    @Test
    void whenPATCHIsCalledWithAnAllowedIdThenOkStatusIsReturned() throws Exception {
        // given
        ProductStatusDTO productStatusDTO = createFakeProductStatusDTO();
        productStatusDTO.setStatus(NEW_STATUS);
        // when
        when(productStatusService.updateProductStatus(productStatusDTO)).thenReturn(productStatusDTO);
        // then
        mockMvc.perform(MockMvcRequestBuilders.patch(PRODUCT_STATUS_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(productStatusDTO)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Is.is(NEW_STATUS.toString())));
    }

    @Test
    void whenPATCHIsCalledWithAInvalidIdThenNotFoundIsReturned() throws Exception {
        // given
        ProductStatusDTO productStatusDTO = createFakeProductStatusDTO();
        productStatusDTO.setId(INVALID_PRODUCT_ID);
        // when
        when(productStatusService.updateProductStatus(productStatusDTO)).thenThrow(ProductNotFoundException.class);
        // then
        mockMvc.perform(MockMvcRequestBuilders.patch(PRODUCT_STATUS_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(productStatusDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenPATCHIsCalledWithANotAllowedIdThenBadRequestIsReturned() throws Exception {
        // given
        ProductStatusDTO productStatusDTO = createFakeProductStatusDTO();
        // when
        when(productStatusService.updateProductStatus(productStatusDTO)).thenThrow(ProductNotAllowedToUpdateStatusException.class);
        // then
        mockMvc.perform(MockMvcRequestBuilders.patch(PRODUCT_STATUS_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(productStatusDTO)))
                .andExpect(status().isBadRequest());
    }

//    @Test
//    void whenPATCHIsCalledWithoutRequiredFieldThenBadRequestIsReturned() throws Exception {
//        // given
//        ProductStatusDTO productStatusDTO = createInvalidFakeProductStatusDTO();
//
//        // then
//        mockMvc.perform(MockMvcRequestBuilders.patch(PRODUCT_STATUS_API_URL_PATH)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(productStatusDTO)))
//                .andExpect(status().isBadRequest());
//    }

}

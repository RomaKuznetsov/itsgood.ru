package com.itsgood.ru.controller.rest.spring;

import com.itsgood.ru.controller.request.item.ItemRequestCreate;
import com.itsgood.ru.controller.request.item.ItemRequestSearch;
import com.itsgood.ru.controller.request.item.ItemRequestUpdate;
import com.itsgood.ru.domain.hibernate.BucketDTO;
import com.itsgood.ru.domain.hibernate.ItemDTO;
import com.itsgood.ru.service.spring.ItemDataService;
import com.itsgood.ru.exceptions.IllegalRequestException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/rest/spring/item")
@RequiredArgsConstructor
public class ItemDataController {
//                    @Parameter(name = "gender",
//                            required = true,
//                            in = ParameterIn.QUERY,
//                            schema = @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
//                            example = "NOT_SELECTED", type = "Gender", implementation = Gender.class,
//                            description = "user gender"))
    private final ItemDataService itemDataService;

    @Operation(summary = "Spring data item find all search",
            description = "find all item without limitation",
            responses = {@ApiResponse(responseCode = "OK",
                    description = "successfully loaded items",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ItemDTO.class))))})
    @GetMapping(value = "/findAllItem", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<ItemDTO>> findAllItem() {
        return new ResponseEntity<>(itemDataService.findAllItem(), HttpStatus.OK);
    }
    @Operation(summary = "Spring data item find all search",
            description = "find all item without limitation",
            responses = {@ApiResponse(responseCode = "OK",
                    description = "successfully loaded items",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PageImpl.class))))})
    @GetMapping(value = "/page/{page}", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> testEndPoint(@Parameter(name = "page", example = "1", required = true)
                                                   @PathVariable int page) {
        return new ResponseEntity<>(Collections.singletonMap("result",
                itemDataService.findNumberPageItem(PageRequest.of(page, 4))), HttpStatus.OK);
    }

    @GetMapping(value = "/findItemById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<ItemDTO> findItemById(@Validated @RequestBody ItemRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(itemDataService.findItemById(request.getId()), HttpStatus.OK);
    }
    @Operation(
            summary = "Spring Data Item Search According to search request",
            description = "Item criteria for search id or title",
            parameters = {
                    @Parameter(name = "id",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "400000",
                                    type = "int", description = "id item")),
                    @Parameter(name = "title",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "title",
                                    type = "string", description = "title item"))
            },
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded Items",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ItemDTO.class)
                            )
                    )
            }
    )
    @GetMapping(value = "/findItemByIdOrTitle", consumes = {"application/xml", "application/json"})
    public ResponseEntity<ItemDTO> findItemByIdOrTitle(@Parameter(hidden = true) @Validated @RequestBody ItemRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(itemDataService.findItemByIdOrTitle(request), HttpStatus.OK);
    }

    @PostMapping(value = "/createItem", consumes = {"application/xml", "application/json"})
    public ResponseEntity<ItemDTO> createItem(@Validated @RequestBody ItemRequestCreate request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(itemDataService.createItem(request), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/updateItem", consumes = {"application/xml", "application/json"})
    public ResponseEntity<ItemDTO> updateItem(@Validated @RequestBody ItemRequestUpdate request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(itemDataService.updateItem(request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteItem", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteItem(@Validated @RequestBody ItemRequestUpdate request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        itemDataService.deleteItem(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/deleteItemById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Object> deleteItemById(@Validated @RequestBody ItemRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        itemDataService.deleteItemById(request.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/findSetBucketById", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Set<BucketDTO>> findSetBucketById(@Validated @RequestBody ItemRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(itemDataService.findSetBucketById(request.getId()), HttpStatus.OK);
    }

    @GetMapping(value = "/findItemByTitleAndPriceAfterOrFirm", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<ItemDTO>> findByTitleAndPriceAfterOrFirm(@Validated @RequestBody ItemRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(itemDataService.findItemByTitleAndPriceAfterOrFirm(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findItemByTitleAndPriceBeforeOrFirm", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<ItemDTO>> findItemByTitleAndPriceBeforeOrFirm(@Validated @RequestBody ItemRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(itemDataService.findItemByTitleAndPriceBeforeOrFirm(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findItemByTitleAndDescription", consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<ItemDTO>> findItemByTitleAndDescription(@Validated @RequestBody ItemRequestSearch request, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }
        return new ResponseEntity<>(itemDataService.findItemByTitleAndDescription(request), HttpStatus.OK);
    }
}

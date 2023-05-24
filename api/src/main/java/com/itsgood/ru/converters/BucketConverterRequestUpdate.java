package com.itsgood.ru.converters;


import com.itsgood.ru.controller.request.bucket.BucketRequestUpdate;
import com.itsgood.ru.domain.BucketDTO;
import org.springframework.core.convert.converter.Converter;

import java.util.Set;

public interface BucketConverterRequestUpdate extends Converter<BucketRequestUpdate, Set<BucketDTO>> {
}

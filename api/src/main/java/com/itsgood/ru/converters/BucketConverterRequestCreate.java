package com.itsgood.ru.converters;

import com.itsgood.ru.controller.request.bucket.BucketRequestCreate;
import com.itsgood.ru.domain.hibernate.BucketDTO;
import org.springframework.core.convert.converter.Converter;

public interface BucketConverterRequestCreate extends Converter<BucketRequestCreate, BucketDTO> {
}

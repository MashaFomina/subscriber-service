package com.subscriber.utils;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Helper {
    /**
     * Execute given function with object as parameter and convert result
     *
     * @param function          function to apply
     * @param obj               initial object, which used as parameter of the function
     * @param conversionService conversion service
     * @param classTo           class of output object
     * @return converted object
     */
    public static <T, V, O> Optional<V> execFunctionAndConvert(Function<O, Optional<T>> function, O obj,
                                                                ConversionService conversionService,
                                                                Class<V> classTo) {
        if (obj == null || (obj instanceof String && ((String) obj).trim().isEmpty())) {
            return Optional.empty();
        }
        Optional<T> optionalT = function.apply(obj);
        if (!optionalT.isPresent()) {
            return Optional.empty();
        }
        return Optional.ofNullable(conversionService.convert(optionalT.get(), classTo));
    }

    /**
     * Convert a list of objects using conversion service
     *
     * @param list              list to convert
     * @param conversionService conversion service
     * @param classFrom         class of objects in list
     * @param classTo           class of objects in converted list
     * @return converted list
     */
    public static <I, C> List<C> convertList(List<I> list, ConversionService conversionService,
                                             Class<I> classFrom, Class<C> classTo) {
        if (list.isEmpty()) {
            return Collections.emptyList();
        }
        List<C> convertedList = list.stream().map(e -> (C) conversionService.convert(
                e, TypeDescriptor.valueOf(classFrom), TypeDescriptor.valueOf(classTo)))
                .collect(Collectors.toList());
        return convertedList;
    }
}

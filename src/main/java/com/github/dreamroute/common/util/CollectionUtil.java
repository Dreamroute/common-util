package com.github.dreamroute.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;

/**
 * 描述：{@link java.util.Optional}辅助类
 *
 * @author w.dehi.2022-05-10
 */
public class CollectionUtil {
    private CollectionUtil() {}

    public static <T> boolean isEmpty(Collection<T> source) {
        return source == null || source.isEmpty();
    }

    public static <T> boolean isNotEmpty(Collection<T> source) {
        return !isEmpty(source);
    }

    public static <K, V> boolean isEmpty(Map<K, V> source) {
        return source == null || source.isEmpty();
    }

    public static <K, V> boolean isNotEmpty(Map<K, V> source) {
        return !isEmpty(source);
    }

    public static <T> List<T> nonNull(List<T> source) {
        return ofNullable(source).orElseGet(ArrayList::new);
    }

    public static <T> Stream<T> nonNullStream(List<T> source) {
        return nonNull(source).stream();
    }

    public static <T> Set<T> nonNull(Set<T> source) {
        return ofNullable(source).orElseGet(HashSet::new);
    }

    public static <T> Stream<T> nonNullStream(Set<T> source) {
        return nonNull(source).stream();
    }

    public static <T> T[] nonNull(T[] source) {

    }

}

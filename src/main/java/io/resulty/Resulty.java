package io.resulty;

import java.util.function.Function;

public sealed interface Resulty<T> permits OptionalResult, Result {

    <N> Resulty<N> map(Function<? super T, ? extends N> function);

    <N> Result<N> flatMap(ResultFunction<T, N> function);
    <N> OptionalResult<N> flatMap(OptionalResultFunction<T, N> function);


    @FunctionalInterface
    interface ResultFunction<T, R> {
        Result<R> apply(T t);
    }

    @FunctionalInterface
    interface OptionalResultFunction<T, R> {
        OptionalResult<R> apply(T t);
    }
}

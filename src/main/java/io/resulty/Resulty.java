package io.resulty;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public sealed interface Resulty<T> permits OptionalResult, Result {

    <N> Resulty<N> map(Function<? super T, ? extends N> function);
    <N, R extends Resulty<N>> R flatMap(Function<? super T, R> function);

}

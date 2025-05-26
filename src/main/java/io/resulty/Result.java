package io.resulty;

import java.util.Objects;
import java.util.function.Function;

public sealed interface Result<T> extends Resulty<T> {

    static <T> Result<T> success(T value) {
        return new Success<>(Objects.requireNonNull(value));
    }

    static <T> Result<T> error(Exception error) {
        return new Error<>(Objects.requireNonNull(error));
    }

    record Success<S>(S value) implements Result<S> {

        @Override
        public <N> Result<N> map(Function<? super S, ? extends N> function) {
            return success(function.apply(value));
        }

        @Override
        public <N> Result<N> flatMap(ResultFunction<S, N> function) {
            return function.apply(value);
        }

        @Override
        public <N> OptionalResult<N> flatMap(OptionalResultFunction<S, N> function) {
            OptionalResult<N> apply = function.apply(value);
            return apply;
        }
    }

    record Error<S>(Exception error) implements Result<S> {

        @Override
        public <N> Result<N> map(Function<? super S, ? extends N> function) {
            return safeCast();
        }

        @Override
        public <N> Result<N> flatMap(ResultFunction<S, N> function) {
            return safeCast();
        }

        @Override
        public <N> OptionalResult<N> flatMap(OptionalResultFunction<S, N> function) {
            return OptionalResult.error(error);
        }

        @SuppressWarnings("unchecked")
        private <R1> Error<R1> safeCast() {
            return (Error<R1>) this;
        }

    }
}

package io.resulty;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public sealed interface OptionalResult<T> extends Resulty<T> {

    static <T> OptionalResult<T> success(T value) {
        return new OptionalResult.Value<>(Objects.requireNonNull(value));
    }

    static <T> OptionalResult<T> success(Optional<T> value) {
        return value
                .map(OptionalResult::success)
                .orElse(empty());
    }

    static <T> OptionalResult<T> empty() {
        return new OptionalResult.Empty<>();
    }

    static <T> OptionalResult<T> error(Exception error) {
        return new OptionalResult.Error<>(Objects.requireNonNull(error));
    }

    record Error<O>(Exception error) implements OptionalResult<O> {

        @Override
        public <N> OptionalResult<N> map(Function<? super O, ? extends N> function) {
            return (OptionalResult<N>) this;
        }

        @Override
        public <N, R extends Resulty<N>> R flatMap(Function<? super O, R> function) {
            return (R) this;
        }
    }

    record Value<O>(O value) implements OptionalResult<O> {

        @Override
        public <N> OptionalResult<N> map(Function<? super O, ? extends N> function) {
            return OptionalResult.success(function.apply(value));
        }

        @Override
        public <N, R extends Resulty<N>> R flatMap(Function<? super O, R> function) {
            return function.apply(value);
        }
    }

    record Empty<O>() implements OptionalResult<O> {

        @Override
        public <N> OptionalResult<N> map(Function<? super O, ? extends N> function) {
            return (OptionalResult<N>) this;
        }

        @Override
        public <N, R extends Resulty<N>> R flatMap(Function<? super O, R> function) {
            return (R) this;
        }

    }
}

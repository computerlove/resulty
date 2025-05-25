package io.resulty;

import java.util.Objects;
import java.util.function.Function;

public sealed interface Resulty<T> {

    static <T> Resulty<T> success(T value) {
        return new Success<>(Objects.requireNonNull(value));
    }

    static <T> Resulty<T> error(Exception error) {
        return new Error<>(Objects.requireNonNull(error));
    }

    <N> Resulty<N> map(Function<? super T, ? extends N> function);
    <N> Resulty<N> flatMap(
            Function<? super T, ? extends Resulty<? extends N>> function);

    record Success<S>(S value) implements Resulty<S> {
        @Override
        public <N> Resulty<N> map(Function<? super S, ? extends N> function) {
            return success(function.apply(value));
        }

        @Override
        public <N> Resulty<N> flatMap(Function<? super S, ? extends Resulty<? extends N>> function) {
            Resulty<? extends N> apply = function.apply(value);
            return (Resulty<N>) apply;
        }


        @SuppressWarnings("unchecked")
        private <E1> Success<S> safeCast() {
            return (Success<S>) this;
        }
    }

    record Error<S>(Exception error) implements Resulty<S> {
        @Override
        public <N> Resulty<N> map(Function<? super S, ? extends N> function) {
            return safeCast();
        }

        @Override
        public <N> Resulty<N> flatMap(Function<? super S, ? extends Resulty<? extends N>> function) {
            return safeCast();
        }

        @SuppressWarnings("unchecked")
        private <R1> Error<R1> safeCast() {
            return (Error<R1>) this;
        }
    }
}

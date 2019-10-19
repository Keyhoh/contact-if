package com.demo.contactif.domain.util;

import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class Validational<T> {
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    /**
     * If non-null, the value; if null, indicates no value is present
     */
    private final T value;

    /**
     * Constructs an instance with the described value.
     *
     * @param value the non-{@code null} value to describe
     * @throws NullPointerException if value is {@code null}
     */
    private Validational(T value) {
        this.value = Objects.requireNonNull(value);
    }

    /**
     * Returns an {@code Validational} describing the given non-{@code null}
     * value.
     *
     * @param value the value to describe, which must be non-{@code null}
     * @param <T>   the type of the value
     * @return an {@code Validational} with the value present
     * @throws NullPointerException if value is {@code null}
     */
    public static <T> Validational<T> of(T value) {
        return new Validational<>(value);
    }

    /**
     * If a value is present, returns the value, otherwise throws
     * {@code NoSuchElementException}.
     *
     * @return the non-{@code null} value described by this {@code Validational}
     * @throws NoSuchElementException if no value is present
     * @apiNote The preferred alternative to this method is {@link #orElseThrow()}.
     */
    public T get() {
        if (isInvalid()) {
            throw new ValidationException();
        }
        return value;
    }

    /**
     * If a value is valid, returns {@code true}, otherwise {@code false}.
     *
     * @return {@code true} if a value is valid, otherwise {@code false}.
     */
    public boolean isValid() {
        return validator.validate(value).isEmpty();
    }

    /**
     * If a value is invalid, returns {@code true}, otherwise {@code false}.
     *
     * @return {@code true} if a value is invalid, otherwise {@code false}.
     */
    public boolean isInvalid() {
        return !isValid();
    }

    /**
     * If a value is present, returns the value, otherwise throws
     * {@code NoSuchElementException}.
     *
     * @return the non-{@code null} value described by this {@code Validational}
     * @throws NoSuchElementException if no value is present
     * @since 10
     */
    public T orElseThrow() {
        if (value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    /**
     * Indicates whether some other object is "equal to" this {@code Validational}.
     * The other object is considered equal if:
     * <ul>
     * <li>it is also an {@code Validational} and;
     * <li>both instances have no value present or;
     * <li>the present values are "equal to" each other via {@code equals()}.
     * </ul>
     *
     * @param obj an object to be tested for equality
     * @return {@code true} if the other object is "equal to" this object
     * otherwise {@code false}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Validational)) {
            return false;
        }

        Validational<?> other = (Validational<?>) obj;
        return Objects.equals(value, other.value);
    }

    /**
     * Returns the hash code of the value, if present, otherwise {@code 0}
     * (zero) if no value is present.
     *
     * @return hash code value of the present value or {@code 0} if no value is
     * present
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    /**
     * Returns a non-empty string representation of this {@code Validational}
     * suitable for debugging.  The exact presentation format is unspecified and
     * may vary between implementations and versions.
     *
     * @return the string representation of this instance
     * @implSpec If a value is present the result must include its string representation
     * in the result.  Empty and present {@code Validational}s must be unambiguously
     * differentiable.
     */
    @Override
    public String toString() {
        return value != null
                ? String.format("Validational[%s]", value)
                : "Validational.empty";
    }
}

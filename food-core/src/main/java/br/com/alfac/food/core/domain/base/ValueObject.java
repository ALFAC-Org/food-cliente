package br.com.alfac.food.core.domain.base;

public abstract class ValueObject<T extends ValueObject<T>> {

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();

    @Override
    public abstract String toString();

    public abstract boolean sameValueAs(T other);

    protected boolean equalsClass(ValueObject<T> other) {
        return other != null && getClass().equals(other.getClass());
    }
}

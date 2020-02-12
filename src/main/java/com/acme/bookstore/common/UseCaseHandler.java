package com.acme.bookstore.common;

public interface UseCaseHandler<T, R> {
    R handle(T useCaseParam);
}

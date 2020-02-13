package com.acme.bookstore.domain;

import com.acme.bookstore.common.Repository;

import java.util.UUID;

public interface InventoryRepository extends Repository<UUID, BookInventoryRecord> {
}

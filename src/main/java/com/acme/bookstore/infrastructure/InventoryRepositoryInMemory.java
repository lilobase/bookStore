package com.acme.bookstore.infrastructure;

import com.acme.bookstore.common.RepositoryInMemory;
import com.acme.bookstore.domain.*;

public class InventoryRepositoryInMemory extends RepositoryInMemory<ISBN, BookInventoryRecord> implements InventoryRepository {
}

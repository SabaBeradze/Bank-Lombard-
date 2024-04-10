package dev.boot.repository;

import dev.boot.domain.PawnItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PawnItemRepository extends CrudRepository<PawnItem,Long> {
}

package org.mounanga.accounting.queires.repository;

import org.mounanga.accounting.queires.entity.Asset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface AssetRepository extends JpaRepository<Asset, String> {

    @Query("select a from Asset a where a.name like :keyword or a.description like :keyword order by a.name asc")
    Page<Asset> findByNameOrDescription(@Param("keyword") String keyword, Pageable pageable);

    @Query("select a from Asset a where a.category =:category order by a.name asc")
    Page<Asset> findByCategory(@Param("category") String category, Pageable pageable);

    @Query("select a from Asset a where a.type =:type order by a.name asc")
    Page<Asset> findByType(@Param("type") String type, Pageable pageable);

    @Query("select a from Asset a where a.category =:category and a.type=:type order by a.name asc")
    Page<Asset> findByCategoryAndType(@Param("category") String category, @Param("type") String type, Pageable pageable);

    @Query("SELECT COALESCE(SUM(a.value), 0) FROM Asset a")
    BigDecimal getTotalAssetBalance();
}

package org.mounanga.accounting.queires.repository;

import org.mounanga.accounting.common.enums.LiabilityCategory;
import org.mounanga.accounting.common.enums.LiabilityType;
import org.mounanga.accounting.queires.entity.Liability;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface LiabilityRepository extends JpaRepository<Liability, String> {

    @Query("select l from Liability l where l.name like :keyword or l.description like :keyword order by l.name asc")
    Page<Liability> findByNameOrDescription(@Param("keyword") String keyword, Pageable pageable);

    @Query("select l from Liability l where l.category = :category order by l.name asc")
    Page<Liability> findByCategory(@Param("category") LiabilityCategory category, Pageable pageable);


    @Query("select l from Liability l where l.type =:type order by l.name asc")
    Page<Liability> findByType(@Param("type") LiabilityType type, Pageable pageable);

    @Query("select l from Liability l where l.category =:category and l.type=:type order by l.name asc")
    Page<Liability> findByCategoryAndType(@Param("category") LiabilityCategory category, @Param("type") LiabilityType type, Pageable pageable);

    @Query("SELECT COALESCE(SUM(l.amount), 0) FROM Liability l")
    BigDecimal getTotalLiabilityBalance();
}

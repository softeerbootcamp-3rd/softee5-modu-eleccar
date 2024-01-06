package com.moduelec.moduelec.repository;

import com.moduelec.moduelec.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample,Long> {
}

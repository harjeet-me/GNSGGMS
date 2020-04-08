package org.gnsg.gms.repository.search;

import org.gnsg.gms.domain.AppliedCharge;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link AppliedCharge} entity.
 */
public interface AppliedChargeSearchRepository extends ElasticsearchRepository<AppliedCharge, Long> {
}

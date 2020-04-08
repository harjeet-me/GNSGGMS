package org.gnsg.gms.repository.search;

import org.gnsg.gms.domain.Charge;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Charge} entity.
 */
public interface ChargeSearchRepository extends ElasticsearchRepository<Charge, Long> {
}

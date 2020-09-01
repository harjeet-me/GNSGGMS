package org.gnsg.gms.repository.search;

import org.gnsg.gms.domain.ASPath;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link ASPath} entity.
 */
public interface ASPathSearchRepository extends ElasticsearchRepository<ASPath, Long> {
}

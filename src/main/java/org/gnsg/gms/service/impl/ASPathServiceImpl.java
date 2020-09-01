package org.gnsg.gms.service.impl;

import org.gnsg.gms.service.ASPathService;
import org.gnsg.gms.domain.ASPath;
import org.gnsg.gms.repository.ASPathRepository;
import org.gnsg.gms.repository.search.ASPathSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link ASPath}.
 */
@Service
@Transactional
public class ASPathServiceImpl implements ASPathService {

    private final Logger log = LoggerFactory.getLogger(ASPathServiceImpl.class);

    private final ASPathRepository aSPathRepository;

    private final ASPathSearchRepository aSPathSearchRepository;

    public ASPathServiceImpl(ASPathRepository aSPathRepository, ASPathSearchRepository aSPathSearchRepository) {
        this.aSPathRepository = aSPathRepository;
        this.aSPathSearchRepository = aSPathSearchRepository;
    }

    /**
     * Save a aSPath.
     *
     * @param aSPath the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ASPath save(ASPath aSPath) {
        log.debug("Request to save ASPath : {}", aSPath);
        ASPath result = aSPathRepository.save(aSPath);
        aSPathSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the aSPaths.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ASPath> findAll() {
        log.debug("Request to get all ASPaths");
        return aSPathRepository.findAll();
    }


    /**
     * Get one aSPath by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ASPath> findOne(Long id) {
        log.debug("Request to get ASPath : {}", id);
        return aSPathRepository.findById(id);
    }

    /**
     * Delete the aSPath by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ASPath : {}", id);

        aSPathRepository.deleteById(id);
        aSPathSearchRepository.deleteById(id);
    }

    /**
     * Search for the aSPath corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ASPath> search(String query) {
        log.debug("Request to search ASPaths for query {}", query);
        return StreamSupport
            .stream(aSPathSearchRepository.search(queryStringQuery(query)).spliterator(), false)
        .collect(Collectors.toList());
    }
}

package org.gnsg.gms.service.impl;

import org.gnsg.gms.service.PRoulService;
import org.gnsg.gms.domain.PRoul;
import org.gnsg.gms.repository.PRoulRepository;
import org.gnsg.gms.repository.search.PRoulSearchRepository;
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
 * Service Implementation for managing {@link PRoul}.
 */
@Service
@Transactional
public class PRoulServiceImpl implements PRoulService {

    private final Logger log = LoggerFactory.getLogger(PRoulServiceImpl.class);

    private final PRoulRepository pRoulRepository;

    private final PRoulSearchRepository pRoulSearchRepository;

    public PRoulServiceImpl(PRoulRepository pRoulRepository, PRoulSearchRepository pRoulSearchRepository) {
        this.pRoulRepository = pRoulRepository;
        this.pRoulSearchRepository = pRoulSearchRepository;
    }

    /**
     * Save a pRoul.
     *
     * @param pRoul the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PRoul save(PRoul pRoul) {
        log.debug("Request to save PRoul : {}", pRoul);
        PRoul result = pRoulRepository.save(pRoul);
        pRoulSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the pRouls.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<PRoul> findAll() {
        log.debug("Request to get all PRouls");
        return pRoulRepository.findAll();
    }


    /**
     * Get one pRoul by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PRoul> findOne(Long id) {
        log.debug("Request to get PRoul : {}", id);
        return pRoulRepository.findById(id);
    }

    /**
     * Delete the pRoul by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PRoul : {}", id);

        pRoulRepository.deleteById(id);
        pRoulSearchRepository.deleteById(id);
    }

    /**
     * Search for the pRoul corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<PRoul> search(String query) {
        log.debug("Request to search PRouls for query {}", query);
        return StreamSupport
            .stream(pRoulSearchRepository.search(queryStringQuery(query)).spliterator(), false)
        .collect(Collectors.toList());
    }
}

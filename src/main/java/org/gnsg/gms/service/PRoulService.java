package org.gnsg.gms.service;

import org.gnsg.gms.domain.PRoul;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link PRoul}.
 */
public interface PRoulService {

    /**
     * Save a pRoul.
     *
     * @param pRoul the entity to save.
     * @return the persisted entity.
     */
    PRoul save(PRoul pRoul);

    /**
     * Get all the pRouls.
     *
     * @return the list of entities.
     */
    List<PRoul> findAll();


    /**
     * Get the "id" pRoul.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PRoul> findOne(Long id);

    /**
     * Delete the "id" pRoul.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the pRoul corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @return the list of entities.
     */
    List<PRoul> search(String query);
}

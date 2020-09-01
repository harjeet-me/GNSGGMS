package org.gnsg.gms.service;

import org.gnsg.gms.domain.ASPath;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ASPath}.
 */
public interface ASPathService {

    /**
     * Save a aSPath.
     *
     * @param aSPath the entity to save.
     * @return the persisted entity.
     */
    ASPath save(ASPath aSPath);

    /**
     * Get all the aSPaths.
     *
     * @return the list of entities.
     */
    List<ASPath> findAll();


    /**
     * Get the "id" aSPath.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ASPath> findOne(Long id);

    /**
     * Delete the "id" aSPath.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the aSPath corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @return the list of entities.
     */
    List<ASPath> search(String query);
}

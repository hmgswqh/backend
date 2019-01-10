package com.youthchina.controller.zhongyang;

import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;
import com.youthchina.util.zhongyang.HasId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by zhongyangwu on 11/21/18.
 */
abstract class DomainCRUDController<T extends HasId<K>, K extends Serializable> {

    /**
     * @return DomainCRUDService to access the domain model
     */
    protected abstract DomainCRUDService<T, K> getService();

    /**
     * @param key key of domain model
     * @return 200 if found
     * @throws NotFoundException cannot find domain model based on the key
     */
    protected ResponseEntity<?> get(K key) throws NotFoundException {
        T t = getService().get(key);
        return ResponseEntity.ok(t);
    }

    /**
     * @param t domain model
     * @return 200 if updated
     * @throws NotFoundException cannot find domain model based on the key
     */
    protected ResponseEntity<?> update(T t) throws NotFoundException {
        T updatedT = getService().update(t);
        return ResponseEntity.ok().build();
    }

    /**
     * @param key primary key of domain model
     * @return 204 if success
     * @throws NotFoundException cannot find domain model based on the key
     */
    protected ResponseEntity<?> delete(K key) throws NotFoundException {
        getService().delete(key);
        return ResponseEntity.noContent().build();
    }

    /**
     * @param t domain model to add
     * @return 201 if added
     */
    protected ResponseEntity<?> add(T t) {
        T created = getService().add(t);
        try {
            return ResponseEntity.created(getUriForNewInstance(t.getId())).build();
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    /**
     * @param id primary key of domain model
     * @return URI which can be used to access the domain model with primary key equals to k
     * */
    abstract protected URI getUriForNewInstance(K id) throws URISyntaxException;
}
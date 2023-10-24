package learn.jpa.demo.dao;

import learn.jpa.demo.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/*
@RepositoryRestResource(path = "spring-rest-rooms") // base endpoints: /rooms
public interface RoomRepository extends JpaRepository<Room, Integer> {
    // spring data JPA will also help expose:
    // POST rooms
    // GET rooms
    // GET rooms/{id}
    // PUT rooms/{id}
    // DELETE rooms/{id}

    // pagination
    // http://localhost: 8080/spring-rest/rooms?page=1

    // sorting
    // http://localhost:8080/spring-rest/rooms?sort=capicity,desc
}
*/

public interface RoomRepository extends JpaRepository<Room, Integer> {
    /*
    Interface CrudRepository<T,ID>:

    long count(); Returns the number of entities available.
    void delete(T entity); Deletes a given entity.
    void deleteAll(); Deletes all entities managed by the repository.
    void deleteAll(Iterable<? extends T> entities); Deletes the given entities.
    void deleteAllById(Iterable<? extends ID> ids); Deletes all instances of the type T with the given IDs.
    void deleteById(ID id); Deletes the entity with the given id.
    boolean existsById(ID id); Returns whether an entity with the given id exists.
    Iterable<T> findAll(); Returns all instances of the type.
    Iterable<T> findAllById(Iterable<ID> ids); Returns all instances of the type T with the given IDs.
    Optional<T> findById(ID id); Retrieves an entity by its id.
    <S extends T>S save(S entity); Saves a given entity.
    <S extends T>Iterable<S> saveAll(Iterable<S> entities); Saves all given entities.
     */
}

package org.mt.business.control.repository;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mt.business.api.domain.IdentifiableObject;
import org.mt.business.api.domain.SortCriteria;
import org.mt.business.api.domain.SortCriteria.SortDirection;

import com.mongodb.WriteResult;

/**
 * Abstract DAO class for base CRUD operations on entities. <br>
 *
 * @param <EC> Entity concrete class (annotated with Morphia annotations) and
 *             implements BaseEntity.
 */
public abstract class AbstractRepository<EC extends IdentifiableObject> {

    @Inject
    protected Datastore ds;

    /**
     * Transformer function for SortCriteria to string appropriate for Morphia
     * order condition
     */
    protected static final Function<SortCriteria, String> ORDER_TRANSFORMER = sc -> {

        final List<String> fields = sc.getCriteria().entrySet().stream()
                .map(e -> SortDirection.desc.equals(e.getValue()) ? "-" + e.getKey() : e.getKey())
                .collect(Collectors.toList());

        return fields != null && !fields.isEmpty() ? String.join(",", fields) : "";
    };

    public abstract Class<EC> getEntityClazz();

    public void save(@Valid EC entity) {

        Objects.requireNonNull(entity);

        ds.save(entity);
    }

    public EC findById(String id) {

        Objects.requireNonNull(id);

        return ds.get(getEntityClazz(), new ObjectId(id));
    }

    public long getCount() {

        return ds.getCount(getEntityClazz());
    }

    public boolean deleteById(String id) {

        Objects.requireNonNull(id);

        final WriteResult wr = ds.delete(getEntityClazz(), new ObjectId(id));

        if (wr.getN() > 0) {

            return true;
        }

        return false;
    }
}

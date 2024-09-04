package ru.itmo.filter;

import org.springframework.data.jpa.domain.Specification;
import ru.itmo.models.Cat;

import java.util.List;

public class CatSpecification {
    public static Specification<Cat> createSpecification(CatFilter catFilter) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(String.valueOf(catFilter.getCatFields())),
        CastToType.castToType(catFilter.getValue(), root.get(String.valueOf(catFilter.getCatFields())).getJavaType()));
    }

    public static Specification<Cat> getSpecification(List<CatFilter> catFilter) {
        if (catFilter == null || catFilter.isEmpty()) {
            return null;
        }
        Specification<Cat> specification = createSpecification(catFilter.getFirst());
        catFilter.remove(catFilter.getFirst());
        for (CatFilter catFilter1 : catFilter) {
            specification = specification.and(createSpecification(catFilter1));
        }
        return specification;
    }
}

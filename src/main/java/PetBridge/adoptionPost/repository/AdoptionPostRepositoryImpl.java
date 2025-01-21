package PetBridge.adoptionPost.repository;

import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.adoptionPost.model.entity.QAdoptionPost;
import PetBridge.animal.model.entity.Breed;
import PetBridge.animal.model.entity.Tag;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static PetBridge.adoptionPost.model.entity.QAdoptionPost.*;
import static PetBridge.adoptionPost.model.entity.QTagAdoptionPostMapping.tagAdoptionPostMapping;
import static PetBridge.animal.model.entity.QBreed.breed;

@RequiredArgsConstructor
public class AdoptionPostRepositoryImpl implements AdoptionPostCustom {

    private static final String CLICK_COUNT = "clickCount";
    private static final String WISH_COUNT = "wishCount";
    private final JPAQueryFactory queryFactory;

    @Override
    public List<AdoptionPost> findAllFilteredAdoptionPost(String sortBy, String keyword, List<Tag> tagList, List<Breed> breedList) {
        List<AdoptionPost> result = queryFactory
                .selectFrom(adoptionPost)
                .where(hasKeyword(keyword),
                        hasTagList(tagList),
                        hasBreedList(breedList))
                .orderBy(getSortOrder(sortBy))
                .fetch();
        
        return result;
    }

    private OrderSpecifier<?> getSortOrder(String sortBy) {
        if(sortBy.equals(CLICK_COUNT))
            return adoptionPost.clickCount.desc();
        if (sortBy.equals(WISH_COUNT))
            return adoptionPost.wishCount.desc();
        return adoptionPost.id.desc();
    }

    private BooleanExpression hasBreedList(List<Breed> breedList) {
        return breedList != null ? breed.in(breedList) : null;
    }

    private BooleanExpression hasTagList(List<Tag> tagList) {
        return tagList == null ? null :
                tagAdoptionPostMapping.adoptionPost.in(
                        JPAExpressions
                                .select(tagAdoptionPostMapping.adoptionPost)
                                .from(tagAdoptionPostMapping)
                                .where(tagAdoptionPostMapping.tag.in(tagList))
                                .groupBy(tagAdoptionPostMapping.adoptionPost)
                                .having(tagAdoptionPostMapping.adoptionPost.count().eq((long) tagList.size()))
                                .fetch()
                );
    }

    private BooleanExpression hasKeyword(String keyword) {
        return keyword == null || keyword.isBlank() ?  null : adoptionPost.title.containsIgnoreCase(keyword);
    }

}

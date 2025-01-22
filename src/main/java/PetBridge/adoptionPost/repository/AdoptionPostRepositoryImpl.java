package PetBridge.adoptionPost.repository;

import PetBridge.adoptionPost.model.entity.AdoptionPost;
import PetBridge.animal.model.entity.Breed;
import PetBridge.animal.model.entity.Tag;
import PetBridge.search.model.entity.SearchCondition;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static PetBridge.adoptionPost.model.entity.QAdoptionPost.adoptionPost;
import static PetBridge.adoptionPost.model.entity.QTagAdoptionPostMapping.tagAdoptionPostMapping;
import static PetBridge.animal.model.entity.QBreed.breed;

@RequiredArgsConstructor
public class AdoptionPostRepositoryImpl implements AdoptionPostCustom {

    private static final String CLICK_COUNT = "clickCount";
    private static final String WISH_COUNT = "wishCount";
    private final JPAQueryFactory queryFactory;

    @Override
    public List<AdoptionPost> findAllFilteredAdoptionPost(SearchCondition searchCondition, List<Tag> tagList, List<Breed> breedList) {
        String keyword  = searchCondition.getKeyword();
        String sortBy = searchCondition.getSortBy();
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
                adoptionPost.id.in(
                        JPAExpressions
                                .select(tagAdoptionPostMapping.adoptionPost.id)
                                .from(tagAdoptionPostMapping)
                                .where(tagAdoptionPostMapping.tag.in(tagList))
                                .groupBy(tagAdoptionPostMapping.adoptionPost.id)
                                .having(tagAdoptionPostMapping.adoptionPost.count().eq((long) tagList.size()))
                );
    }

    private BooleanExpression hasKeyword(String keyword) {
        return keyword == null ?  null : adoptionPost.title.containsIgnoreCase(keyword);
    }

}

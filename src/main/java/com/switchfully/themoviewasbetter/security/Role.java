package com.switchfully.themoviewasbetter.security;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.switchfully.themoviewasbetter.security.Feature.*;

public enum Role {
    ADMIN(newArrayList(REGISTER_ADMIN, GET_ALL_USERS)),
    LIBRARIAN(newArrayList(GET_ALL_RENTAL_BY_MEMBER, GET_ALL_DUE_RENTALS, GET_ALL_RENTALS)),
    MEMBER(newArrayList());

    private final List<Feature> featureList;

    Role(List<Feature> featureList) {
        this.featureList = featureList;
    }

    public boolean containsFeature(Feature feature){
        return featureList.contains(feature);
    }
}

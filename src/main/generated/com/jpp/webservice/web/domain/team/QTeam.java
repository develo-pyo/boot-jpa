package com.jpp.webservice.web.domain.team;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTeam is a Querydsl query type for Team
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTeam extends EntityPathBase<Team> {

    private static final long serialVersionUID = -289783526L;

    public static final QTeam team = new QTeam("team");

    public final StringPath teamNm = createString("teamNm");

    public final NumberPath<Long> tid = createNumber("tid", Long.class);

    public final ListPath<com.jpp.webservice.web.domain.user.User, com.jpp.webservice.web.domain.user.QUser> users = this.<com.jpp.webservice.web.domain.user.User, com.jpp.webservice.web.domain.user.QUser>createList("users", com.jpp.webservice.web.domain.user.User.class, com.jpp.webservice.web.domain.user.QUser.class, PathInits.DIRECT2);

    public QTeam(String variable) {
        super(Team.class, forVariable(variable));
    }

    public QTeam(Path<? extends Team> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTeam(PathMetadata metadata) {
        super(Team.class, metadata);
    }

}


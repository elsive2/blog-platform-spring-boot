CREATE TABLE communities (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    avatar VARCHAR(255) NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_id UUID NOT NULL
);
ALTER TABLE communities ADD CONSTRAINT communities_users_fk FOREIGN KEY (user_id) REFERENCES users(id);
CREATE TABLE community_post (
    community_id UUID NOT NULL,
    post_id int8 NOT NULL
);
ALTER TABLE community_post ADD CONSTRAINT community_post_pk PRIMARY KEY (community_id, post_id);
ALTER TABLE community_post ADD CONSTRAINT community_post_community_fk FOREIGN KEY (community_id) REFERENCES communities(id);
ALTER TABLE community_post ADD CONSTRAINT community_post_posts_fk FOREIGN KEY (post_id) REFERENCES posts(id);

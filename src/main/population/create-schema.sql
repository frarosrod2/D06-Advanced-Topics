
    create table `accounting_record` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `creation_moment` datetime(6),
        `status` varchar(255),
        `title` varchar(255),
        `bookkeeper_id` integer not null,
        `investment_round_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `activity` (
       `id` integer not null,
        `version` integer not null,
        `budget_amount` double precision,
        `budget_currency` varchar(255),
        `end` datetime(6),
        `start` datetime(6),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `administrator` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `anonymous` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `application` (
       `id` integer not null,
        `version` integer not null,
        `creation_moment` datetime(6),
        `investment_offer_amount` double precision,
        `investment_offer_currency` varchar(255),
        `justification` varchar(255),
        `statement` varchar(255),
        `status` varchar(255),
        `ticker` varchar(255),
        `update_status_moment` datetime(6),
        `investment_round_id` integer not null,
        `investor_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `authenticated` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `banner` (
       `id` integer not null,
        `version` integer not null,
        `picture` varchar(255),
        `slogan` varchar(255),
        `targeturl` varchar(255),
        `credit_card_id` integer,
        `patron_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `bookkeeper` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `name` varchar(255),
        `responsability_statement` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `bookkeeper_request` (
       `id` integer not null,
        `version` integer not null,
        `name` varchar(255),
        `responsability_statement` varchar(255),
        `state` varchar(255),
        `authenticated_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `challenge` (
       `id` integer not null,
        `version` integer not null,
        `average_goal_amount` double precision,
        `average_goal_currency` varchar(255),
        `average_reward_amount` double precision,
        `average_reward_currency` varchar(255),
        `deadline` datetime(6),
        `description` varchar(255),
        `expert_goal_amount` double precision,
        `expert_goal_currency` varchar(255),
        `expert_reward_amount` double precision,
        `expert_reward_currency` varchar(255),
        `rookie_goal_amount` double precision,
        `rookie_goal_currency` varchar(255),
        `rookie_reward_amount` double precision,
        `rookie_reward_currency` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `consumer` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `credit_card` (
       `id` integer not null,
        `version` integer not null,
        `brand` varchar(255),
        `cvv` integer,
        `exp_month` integer,
        `exp_year` integer,
        `holder_name` varchar(255),
        `number` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `customisation` (
       `id` integer not null,
        `version` integer not null,
        `activity_sectors` varchar(255),
        `spam_threshold` double precision,
        `spam_words` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `entrepreneur` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `activity_sector` varchar(255),
        `name` varchar(255),
        `qualification_record` varchar(255),
        `skills_record` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `forum` (
       `id` integer not null,
        `version` integer not null,
        `moment` datetime(6),
        `title` varchar(255),
        `creator_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `forum_message` (
       `forum_id` integer not null,
        `messages_id` integer not null
    ) engine=InnoDB;

    create table `inquiry` (
       `id` integer not null,
        `version` integer not null,
        `creation` datetime(6),
        `deadline` datetime(6),
        `description` varchar(255),
        `email` varchar(255),
        `max_money_amount` double precision,
        `max_money_currency` varchar(255),
        `min_money_amount` double precision,
        `min_money_currency` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `investment_round` (
       `id` integer not null,
        `version` integer not null,
        `creation_moment` datetime(6),
        `description` varchar(255),
        `final_mode` bit not null,
        `link` varchar(255),
        `money_amount` double precision,
        `money_currency` varchar(255),
        `round` varchar(255),
        `ticker` varchar(255),
        `title` varchar(255),
        `entrepreneur_id` integer not null,
        `forum_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `investment_round_activity` (
       `investment_round_id` integer not null,
        `work_programme_id` integer not null
    ) engine=InnoDB;

    create table `investor` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `activity_sector` varchar(255),
        `name` varchar(255),
        `profile` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `involved_user` (
       `id` integer not null,
        `version` integer not null,
        `search_user` varchar(255),
        `authenticated_id` integer not null,
        `forum_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `message` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `moment` datetime(6),
        `tags` varchar(255),
        `title` varchar(255),
        `creator_id` integer not null,
        `forum_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `murillo_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `author` varchar(255),
        `moment` datetime(6),
        `number` integer,
        `text` varchar(255),
        `title` varchar(255),
        `volumen` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `notice` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `creation` datetime(6),
        `deadline` datetime(6),
        `optional_links` varchar(255),
        `picture` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `overture` (
       `id` integer not null,
        `version` integer not null,
        `creation` datetime(6),
        `deadline` datetime(6),
        `description` varchar(255),
        `email` varchar(255),
        `max_money_amount` double precision,
        `max_money_currency` varchar(255),
        `min_money_amount` double precision,
        `min_money_currency` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `patron` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `name` varchar(255),
        `credit_card_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `pazos_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `author` varchar(255),
        `contact` varchar(255),
        `experience` bit,
        `food_handler` bit,
        `moment` datetime(6),
        `text` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `provider` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `rosa_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `author` varchar(255),
        `description` varchar(255),
        `moment` datetime(6),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `technology_record` (
       `id` integer not null,
        `version` integer not null,
        `activity_sector` varchar(255),
        `description` varchar(255),
        `email` varchar(255),
        `inventor` varchar(255),
        `open_source` bit,
        `stars` integer,
        `title` varchar(255),
        `website` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `tool_record` (
       `id` integer not null,
        `version` integer not null,
        `activity_sector` varchar(255),
        `description` varchar(255),
        `email` varchar(255),
        `inventor` varchar(255),
        `open_source` bit,
        `stars` integer,
        `title` varchar(255),
        `website` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `user_account` (
       `id` integer not null,
        `version` integer not null,
        `enabled` bit not null,
        `identity_email` varchar(255),
        `identity_name` varchar(255),
        `identity_surname` varchar(255),
        `password` varchar(255),
        `username` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `hibernate_sequence` (
       `next_val` bigint
    ) engine=InnoDB;

    insert into `hibernate_sequence` values ( 1 );

    alter table `forum_message` 
       add constraint UK_c0q529r106roshilrmgdn5mq7 unique (`messages_id`);
create index IDX3qtg1fe48q71u218rdyieeurl on `investment_round` (`creation_moment`);

    alter table `investment_round` 
       add constraint UK_ti0p3dolmlo4vxkj8f5d6v8po unique (`forum_id`);

    alter table `investment_round_activity` 
       add constraint UK_r7iyfis9wnm52dspjcv1qpqh5 unique (`work_programme_id`);

    alter table `user_account` 
       add constraint UK_castjbvpeeus0r8lbpehiu0e4 unique (`username`);

    alter table `accounting_record` 
       add constraint `FK41jm4vk7runvmg5tderffrele` 
       foreign key (`bookkeeper_id`) 
       references `bookkeeper` (`id`);

    alter table `accounting_record` 
       add constraint `FKk1pmfnppwk0kav7xloy8u71uq` 
       foreign key (`investment_round_id`) 
       references `investment_round` (`id`);

    alter table `administrator` 
       add constraint FK_2a5vcjo3stlfcwadosjfq49l1 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `anonymous` 
       add constraint FK_6lnbc6fo3om54vugoh8icg78m 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `application` 
       add constraint `FKk5ibe41quxsif8im882xv4afo` 
       foreign key (`investment_round_id`) 
       references `investment_round` (`id`);

    alter table `application` 
       add constraint `FKl4fp0cd8c008ma79n6w58xhk9` 
       foreign key (`investor_id`) 
       references `investor` (`id`);

    alter table `authenticated` 
       add constraint FK_h52w0f3wjoi68b63wv9vwon57 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `banner` 
       add constraint `FKr19baq0bri0akndc7ruwhngy4` 
       foreign key (`credit_card_id`) 
       references `credit_card` (`id`);

    alter table `banner` 
       add constraint `FKdocr1jjfgwx9ef5jbf675l360` 
       foreign key (`patron_id`) 
       references `patron` (`id`);

    alter table `bookkeeper` 
       add constraint FK_krvjp9eaqyapewl2igugbo9o8 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `bookkeeper_request` 
       add constraint `FKhdducua8c58xhfrls8oiih3j0` 
       foreign key (`authenticated_id`) 
       references `authenticated` (`id`);

    alter table `consumer` 
       add constraint FK_6cyha9f1wpj0dpbxrrjddrqed 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `entrepreneur` 
       add constraint FK_r6tqltqvrlh1cyy8rsj5pev1q 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `forum` 
       add constraint `FK49evxvl11kdqxjybm2sn41x6f` 
       foreign key (`creator_id`) 
       references `authenticated` (`id`);

    alter table `forum_message` 
       add constraint `FK1sp7ne3hl23g01ggrp329i71c` 
       foreign key (`messages_id`) 
       references `message` (`id`);

    alter table `forum_message` 
       add constraint `FKsrtj8k65l4o01scnduc07muo5` 
       foreign key (`forum_id`) 
       references `forum` (`id`);

    alter table `investment_round` 
       add constraint `FKkj1l8c2ftn9c65y061me6t37j` 
       foreign key (`entrepreneur_id`) 
       references `entrepreneur` (`id`);

    alter table `investment_round` 
       add constraint `FKidufrenbfe15mdi0cx80oci4v` 
       foreign key (`forum_id`) 
       references `forum` (`id`);

    alter table `investment_round_activity` 
       add constraint `FKo2rpduv8cnisos31hcrmo99w6` 
       foreign key (`work_programme_id`) 
       references `activity` (`id`);

    alter table `investment_round_activity` 
       add constraint `FKfvuiqaq17g9wrgm95fabomjin` 
       foreign key (`investment_round_id`) 
       references `investment_round` (`id`);

    alter table `investor` 
       add constraint FK_dcek5rr514s3rww0yy57vvnpq 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `involved_user` 
       add constraint `FKtdjfvr1t34oywmymvqnq56f84` 
       foreign key (`authenticated_id`) 
       references `authenticated` (`id`);

    alter table `involved_user` 
       add constraint `FK83poh8m3ta2omwcedsvh8jxtf` 
       foreign key (`forum_id`) 
       references `forum` (`id`);

    alter table `message` 
       add constraint `FKd8wmf6nghttk2h9gq7v8p9lqo` 
       foreign key (`creator_id`) 
       references `authenticated` (`id`);

    alter table `message` 
       add constraint `FKfwwpivgx5j4vw4594dgrw884q` 
       foreign key (`forum_id`) 
       references `forum` (`id`);

    alter table `patron` 
       add constraint `FKpj4cod0bcxwxg4nqv4f2xkikg` 
       foreign key (`credit_card_id`) 
       references `credit_card` (`id`);

    alter table `patron` 
       add constraint FK_8xx5nujhuio3advxc2freyu65 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `provider` 
       add constraint FK_b1gwnjqm6ggy9yuiqm0o4rlmd 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

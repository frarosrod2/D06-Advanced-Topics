
    alter table `accounting_record` 
       drop 
       foreign key `FK41jm4vk7runvmg5tderffrele`;

    alter table `accounting_record` 
       drop 
       foreign key `FKk1pmfnppwk0kav7xloy8u71uq`;

    alter table `administrator` 
       drop 
       foreign key FK_2a5vcjo3stlfcwadosjfq49l1;

    alter table `anonymous` 
       drop 
       foreign key FK_6lnbc6fo3om54vugoh8icg78m;

    alter table `application` 
       drop 
       foreign key `FKk5ibe41quxsif8im882xv4afo`;

    alter table `application` 
       drop 
       foreign key `FKl4fp0cd8c008ma79n6w58xhk9`;

    alter table `authenticated` 
       drop 
       foreign key FK_h52w0f3wjoi68b63wv9vwon57;

    alter table `banner` 
       drop 
       foreign key `FKr19baq0bri0akndc7ruwhngy4`;

    alter table `banner` 
       drop 
       foreign key `FKdocr1jjfgwx9ef5jbf675l360`;

    alter table `bookkeeper` 
       drop 
       foreign key FK_krvjp9eaqyapewl2igugbo9o8;

    alter table `bookkeeper_request` 
       drop 
       foreign key `FKhdducua8c58xhfrls8oiih3j0`;

    alter table `entrepreneur` 
       drop 
       foreign key FK_r6tqltqvrlh1cyy8rsj5pev1q;

    alter table `forum` 
       drop 
       foreign key `FK49evxvl11kdqxjybm2sn41x6f`;

    alter table `forum_message` 
       drop 
       foreign key `FK1sp7ne3hl23g01ggrp329i71c`;

    alter table `forum_message` 
       drop 
       foreign key `FKsrtj8k65l4o01scnduc07muo5`;

    alter table `investment_round` 
       drop 
       foreign key `FKkj1l8c2ftn9c65y061me6t37j`;

    alter table `investment_round` 
       drop 
       foreign key `FKidufrenbfe15mdi0cx80oci4v`;

    alter table `investment_round_activity` 
       drop 
       foreign key `FKo2rpduv8cnisos31hcrmo99w6`;

    alter table `investment_round_activity` 
       drop 
       foreign key `FKfvuiqaq17g9wrgm95fabomjin`;

    alter table `investor` 
       drop 
       foreign key FK_dcek5rr514s3rww0yy57vvnpq;

    alter table `involved_user` 
       drop 
       foreign key `FKtdjfvr1t34oywmymvqnq56f84`;

    alter table `involved_user` 
       drop 
       foreign key `FK83poh8m3ta2omwcedsvh8jxtf`;

    alter table `message` 
       drop 
       foreign key `FKd8wmf6nghttk2h9gq7v8p9lqo`;

    alter table `message` 
       drop 
       foreign key `FKfwwpivgx5j4vw4594dgrw884q`;

    alter table `patron` 
       drop 
       foreign key `FKpj4cod0bcxwxg4nqv4f2xkikg`;

    alter table `patron` 
       drop 
       foreign key FK_8xx5nujhuio3advxc2freyu65;

    alter table `provider` 
       drop 
       foreign key FK_b1gwnjqm6ggy9yuiqm0o4rlmd;

    drop table if exists `accounting_record`;

    drop table if exists `activity`;

    drop table if exists `administrator`;

    drop table if exists `anonymous`;

    drop table if exists `application`;

    drop table if exists `authenticated`;

    drop table if exists `banner`;

    drop table if exists `bookkeeper`;

    drop table if exists `bookkeeper_request`;

    drop table if exists `challenge`;

    drop table if exists `credit_card`;

    drop table if exists `customisation`;

    drop table if exists `entrepreneur`;

    drop table if exists `forum`;

    drop table if exists `forum_message`;

    drop table if exists `inquiry`;

    drop table if exists `investment_round`;

    drop table if exists `investment_round_activity`;

    drop table if exists `investor`;

    drop table if exists `involved_user`;

    drop table if exists `message`;

    drop table if exists `murillo_bulletin`;

    drop table if exists `notice`;

    drop table if exists `overture`;

    drop table if exists `patron`;

    drop table if exists `pazos_bulletin`;

    drop table if exists `provider`;

    drop table if exists `rosa_bulletin`;

    drop table if exists `technology_record`;

    drop table if exists `tool_record`;

    drop table if exists `user_account`;

    drop table if exists `hibernate_sequence`;

create table if not exists TB_WALLETS(
    WALLET_ID serial primary key,
    WALLET_FULL_NAME varchar(120),
    WALLET_CPF varchar(11),
    WALLET_EMAIL varchar(50),
    WALLET_PASSWORD varchar(50),
    WALLET_TYPE_WALLET int,
    WALLET_BALANCE decimal(10,2),
    WALLET_VERSION bigint
);

create table if not exists TB_TRANSACTIONS(
    TRANSACTION_ID serial primary key,
    TRANSACTION_VALUE decimal(10,2),
    TRANSACTION_CREATED_AT timestamp,
    TRANSACTION_PAYEE bigint,
    TRANSACTION_PAYER bigint,
    foreign key (TRANSACTION_PAYEE) references  TB_WALLETS (WALLET_ID),
    foreign key (TRANSACTION_PAYER) references TB_WALLETS (WALLET_ID)
);

create unique index if not exists CPF_IDX on TB_WALLETS (WALLET_CPF);
create unique index if not exists EMAIL_IDX on TB_WALLETS (WALLET_EMAIL);
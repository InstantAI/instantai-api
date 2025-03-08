-- Table: public.api_route
-- DROP TABLE public.api_route;
CREATE TABLE public.api_route
(
    id bigserial NOT NULL,  -- 使用 bigserial 自动生成自增值
    path character varying COLLATE pg_catalog."default" NOT NULL,
    method character varying(10) COLLATE pg_catalog."default",
    uri character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT api_route_pkey PRIMARY KEY (id)
)
TABLESPACE pg_default;

ALTER TABLE public.api_route
    OWNER to postgres;

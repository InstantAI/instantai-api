-- V1__init.sql
-- 1. workspaces 表
CREATE TABLE public.workspaces (
    name           VARCHAR(63)    PRIMARY KEY,
    cpu_limit      VARCHAR(20)    NOT NULL,
    memory_limit   VARCHAR(20)    NOT NULL,
    gpu_limit      INT            NOT NULL DEFAULT 0
);

-- 2. workspace_permissions
CREATE TABLE public.workspace_permissions (
    id bigserial PRIMARY KEY,
    workspace_name VARCHAR(255)   NOT NULL,
    username       VARCHAR(255)   NOT NULL,
    role           VARCHAR(16)    NOT NULL CHECK (role IN ('view', 'edit')),

    -- 复合主键：workspace_name + username
    UNIQUE (workspace_name, username),

    -- 外键引用 workspaces，并在删除 workspace 时自动级联删除其权限
    CONSTRAINT fk_workspace_permissions_workspace
        FOREIGN KEY (workspace_name)
        REFERENCES public.workspaces(name)
        ON DELETE CASCADE
);

-- 3. api_route
CREATE TABLE public.api_route
(
    id bigserial PRIMARY KEY,
    path character varying COLLATE pg_catalog."default" NOT NULL,
    method character varying(10) COLLATE pg_catalog."default",
    uri character varying COLLATE pg_catalog."default" NOT NULL
)
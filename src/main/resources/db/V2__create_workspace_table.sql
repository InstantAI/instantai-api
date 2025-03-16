-- v2__create_workspace_table.sql
CREATE TABLE public.workspaces (
    name VARCHAR(63) PRIMARY KEY,
    cpu_limit VARCHAR(20) NOT NULL,
    memory_limit VARCHAR(20) NOT NULL,
    gpu_limit INT NOT NULL DEFAULT 0
);
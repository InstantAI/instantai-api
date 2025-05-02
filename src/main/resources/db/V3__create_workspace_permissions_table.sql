CREATE TABLE public.workspace_permissions (
    workspace_name VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    role VARCHAR(16) NOT NULL CHECK (role IN ('view', 'edit')),
    PRIMARY KEY (workspace_name, username),
    FOREIGN KEY (workspace_name) REFERENCES public.workspaces(name)
);
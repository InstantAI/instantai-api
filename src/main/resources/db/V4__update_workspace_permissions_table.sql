-- 1. 添加 id 列
ALTER TABLE public.workspace_permissions
ADD COLUMN id BIGSERIAL;

-- 2. 删除旧主键
ALTER TABLE public.workspace_permissions
DROP CONSTRAINT workspace_permissions_pkey;

-- 3. 添加新的主键（id）
ALTER TABLE public.workspace_permissions
ADD PRIMARY KEY (id);

-- 4. 添加唯一约束用于避免重复 workspaceName + username
ALTER TABLE public.workspace_permissions
ADD CONSTRAINT uq_workspace_username UNIQUE (workspace_name, username);
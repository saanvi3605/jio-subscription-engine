-- LiteLLM Schema: pre-created so Prisma migrations are never needed

CREATE TYPE IF NOT EXISTS "JobStatus" AS ENUM ('ACTIVE', 'INACTIVE');

CREATE TABLE IF NOT EXISTS "LiteLLM_BudgetTable" (
    "budget_id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "max_budget" FLOAT, "soft_budget" FLOAT, "max_parallel_requests" INTEGER,
    "tpm_limit" BIGINT, "rpm_limit" BIGINT, "model_max_budget" JSONB,
    "budget_duration" TEXT, "budget_reset_at" TIMESTAMP(3),
    "allowed_models" TEXT[] DEFAULT '{}',
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "created_by" TEXT NOT NULL DEFAULT '',
    "updated_at" TIMESTAMP(3) DEFAULT NOW(), "updated_by" TEXT NOT NULL DEFAULT ''
);

CREATE TABLE IF NOT EXISTS "LiteLLM_ObjectPermissionTable" (
    "object_permission_id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "mcp_servers" TEXT[] DEFAULT '{}', "mcp_access_groups" TEXT[] DEFAULT '{}',
    "mcp_tool_permissions" JSONB, "vector_stores" TEXT[] DEFAULT '{}',
    "agents" TEXT[] DEFAULT '{}', "agent_access_groups" TEXT[] DEFAULT '{}',
    "models" TEXT[] DEFAULT '{}', "blocked_tools" TEXT[] DEFAULT '{}',
    "mcp_toolsets" TEXT[] DEFAULT '{}', "search_tools" TEXT[] DEFAULT '{}'
);

CREATE TABLE IF NOT EXISTS "LiteLLM_ModelTable" (
    "id" SERIAL PRIMARY KEY, "aliases" JSONB,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "created_by" TEXT NOT NULL DEFAULT '',
    "updated_at" TIMESTAMP(3) DEFAULT NOW(), "updated_by" TEXT NOT NULL DEFAULT ''
);

CREATE TABLE IF NOT EXISTS "LiteLLM_OrganizationTable" (
    "organization_id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "organization_alias" TEXT NOT NULL DEFAULT '', "budget_id" TEXT,
    "metadata" JSONB DEFAULT '{}', "models" TEXT[] DEFAULT '{}',
    "spend" FLOAT DEFAULT 0.0, "model_spend" JSONB DEFAULT '{}',
    "object_permission_id" TEXT,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "created_by" TEXT NOT NULL DEFAULT '',
    "updated_at" TIMESTAMP(3) DEFAULT NOW(), "updated_by" TEXT NOT NULL DEFAULT ''
);

CREATE TABLE IF NOT EXISTS "LiteLLM_TeamTable" (
    "team_id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "team_alias" TEXT, "organization_id" TEXT, "object_permission_id" TEXT,
    "admins" TEXT[] DEFAULT '{}', "members" TEXT[] DEFAULT '{}',
    "members_with_roles" JSONB DEFAULT '{}', "metadata" JSONB DEFAULT '{}',
    "max_budget" FLOAT, "soft_budget" FLOAT, "spend" FLOAT DEFAULT 0.0,
    "models" TEXT[] DEFAULT '{}', "max_parallel_requests" INTEGER,
    "tpm_limit" BIGINT, "rpm_limit" BIGINT, "budget_duration" TEXT,
    "budget_reset_at" TIMESTAMP(3), "blocked" BOOLEAN DEFAULT false,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "updated_at" TIMESTAMP(3) DEFAULT NOW(),
    "model_spend" JSONB DEFAULT '{}', "model_max_budget" JSONB DEFAULT '{}',
    "router_settings" JSONB DEFAULT '{}', "team_member_permissions" TEXT[] DEFAULT '{}',
    "access_group_ids" TEXT[] DEFAULT '{}', "policies" TEXT[] DEFAULT '{}',
    "default_team_member_models" TEXT[] DEFAULT '{}', "budget_limits" JSONB,
    "model_id" INTEGER UNIQUE, "allow_team_guardrail_config" BOOLEAN DEFAULT false
);

CREATE TABLE IF NOT EXISTS "LiteLLM_ProjectTable" (
    "project_id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "project_alias" TEXT, "description" TEXT, "team_id" TEXT, "budget_id" TEXT,
    "metadata" JSONB DEFAULT '{}', "models" TEXT[] DEFAULT '{}',
    "spend" FLOAT DEFAULT 0.0, "model_spend" JSONB DEFAULT '{}',
    "model_rpm_limit" JSONB DEFAULT '{}', "model_tpm_limit" JSONB DEFAULT '{}',
    "blocked" BOOLEAN DEFAULT false, "object_permission_id" TEXT,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "created_by" TEXT NOT NULL DEFAULT '',
    "updated_at" TIMESTAMP(3) DEFAULT NOW(), "updated_by" TEXT NOT NULL DEFAULT ''
);

CREATE TABLE IF NOT EXISTS "LiteLLM_UserTable" (
    "user_id" TEXT PRIMARY KEY, "user_alias" TEXT, "team_id" TEXT,
    "sso_user_id" TEXT UNIQUE, "organization_id" TEXT, "object_permission_id" TEXT,
    "password" TEXT, "teams" TEXT[] DEFAULT '{}', "user_role" TEXT,
    "max_budget" FLOAT, "spend" FLOAT DEFAULT 0.0, "user_email" TEXT,
    "models" TEXT[] DEFAULT '{}', "metadata" JSONB DEFAULT '{}',
    "max_parallel_requests" INTEGER, "tpm_limit" BIGINT, "rpm_limit" BIGINT,
    "budget_duration" TEXT, "budget_reset_at" TIMESTAMP(3),
    "allowed_cache_controls" TEXT[] DEFAULT '{}', "policies" TEXT[] DEFAULT '{}',
    "model_spend" JSONB DEFAULT '{}', "model_max_budget" JSONB DEFAULT '{}',
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "updated_at" TIMESTAMP(3) DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS "LiteLLM_EndUserTable" (
    "user_id" TEXT PRIMARY KEY, "alias" TEXT, "spend" FLOAT DEFAULT 0.0,
    "allowed_model_region" TEXT, "default_model" TEXT, "budget_id" TEXT,
    "object_permission_id" TEXT, "blocked" BOOLEAN DEFAULT false
);

CREATE TABLE IF NOT EXISTS "LiteLLM_TagTable" (
    "tag_name" TEXT PRIMARY KEY, "description" TEXT, "models" TEXT[] DEFAULT '{}',
    "model_info" JSONB, "spend" FLOAT DEFAULT 0.0, "budget_id" TEXT,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "updated_at" TIMESTAMP(3) DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS "LiteLLM_VerificationToken" (
    "token" TEXT PRIMARY KEY, "key_name" TEXT, "key_alias" TEXT,
    "soft_budget_cooldown" BOOLEAN DEFAULT false, "spend" FLOAT DEFAULT 0.0,
    "expires" TIMESTAMP(3), "models" TEXT[] DEFAULT '{}',
    "aliases" JSONB DEFAULT '{}', "config" JSONB DEFAULT '{}',
    "router_settings" JSONB DEFAULT '{}', "user_id" TEXT, "team_id" TEXT,
    "agent_id" TEXT, "project_id" TEXT, "permissions" JSONB DEFAULT '{}',
    "max_parallel_requests" INTEGER, "metadata" JSONB DEFAULT '{}', "blocked" BOOLEAN,
    "tpm_limit" BIGINT, "rpm_limit" BIGINT, "max_budget" FLOAT,
    "budget_duration" TEXT, "budget_reset_at" TIMESTAMP(3),
    "allowed_cache_controls" TEXT[] DEFAULT '{}', "allowed_routes" TEXT[] DEFAULT '{}',
    "policies" TEXT[] DEFAULT '{}', "access_group_ids" TEXT[] DEFAULT '{}',
    "model_spend" JSONB DEFAULT '{}', "model_max_budget" JSONB DEFAULT '{}',
    "budget_id" TEXT, "organization_id" TEXT, "object_permission_id" TEXT,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "created_by" TEXT,
    "updated_at" TIMESTAMP(3) DEFAULT NOW(), "updated_by" TEXT,
    "last_active" TIMESTAMP(3), "rotation_count" INTEGER DEFAULT 0,
    "auto_rotate" BOOLEAN DEFAULT false, "rotation_interval" TEXT,
    "last_rotation_at" TIMESTAMP(3), "key_rotation_at" TIMESTAMP(3), "budget_limits" JSONB
);

CREATE INDEX IF NOT EXISTS "LiteLLM_VT_user_team_idx" ON "LiteLLM_VerificationToken"("user_id","team_id");
CREATE INDEX IF NOT EXISTS "LiteLLM_VT_team_idx" ON "LiteLLM_VerificationToken"("team_id");

CREATE TABLE IF NOT EXISTS "LiteLLM_JWTKeyMapping" (
    "id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "jwt_claim_name" TEXT NOT NULL, "jwt_claim_value" TEXT NOT NULL,
    "token" TEXT NOT NULL, "description" TEXT, "is_active" BOOLEAN DEFAULT true,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "created_by" TEXT,
    "updated_at" TIMESTAMP(3) DEFAULT NOW(), "updated_by" TEXT,
    UNIQUE ("jwt_claim_name","jwt_claim_value")
);

CREATE TABLE IF NOT EXISTS "LiteLLM_DeprecatedVerificationToken" (
    "id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "token" TEXT UNIQUE NOT NULL, "active_token_id" TEXT NOT NULL,
    "revoke_at" TIMESTAMP(3) NOT NULL, "created_at" TIMESTAMP(3) DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS "LiteLLM_DeletedVerificationToken" (
    "id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT, "token" TEXT NOT NULL,
    "key_name" TEXT, "key_alias" TEXT, "soft_budget_cooldown" BOOLEAN DEFAULT false,
    "spend" FLOAT DEFAULT 0.0, "expires" TIMESTAMP(3), "models" TEXT[] DEFAULT '{}',
    "aliases" JSONB DEFAULT '{}', "config" JSONB DEFAULT '{}',
    "user_id" TEXT, "team_id" TEXT, "agent_id" TEXT, "project_id" TEXT,
    "permissions" JSONB DEFAULT '{}', "max_parallel_requests" INTEGER,
    "metadata" JSONB DEFAULT '{}', "blocked" BOOLEAN, "tpm_limit" BIGINT,
    "rpm_limit" BIGINT, "max_budget" FLOAT, "budget_duration" TEXT,
    "budget_reset_at" TIMESTAMP(3), "allowed_cache_controls" TEXT[] DEFAULT '{}',
    "allowed_routes" TEXT[] DEFAULT '{}', "policies" TEXT[] DEFAULT '{}',
    "access_group_ids" TEXT[] DEFAULT '{}', "model_spend" JSONB DEFAULT '{}',
    "model_max_budget" JSONB DEFAULT '{}', "router_settings" JSONB,
    "budget_id" TEXT, "organization_id" TEXT, "object_permission_id" TEXT,
    "created_at" TIMESTAMP(3), "created_by" TEXT, "updated_at" TIMESTAMP(3),
    "updated_by" TEXT, "last_active" TIMESTAMP(3), "rotation_count" INTEGER DEFAULT 0,
    "auto_rotate" BOOLEAN DEFAULT false, "rotation_interval" TEXT,
    "last_rotation_at" TIMESTAMP(3), "key_rotation_at" TIMESTAMP(3),
    "deleted_at" TIMESTAMP(3) DEFAULT NOW(), "deleted_by" TEXT,
    "deleted_by_api_key" TEXT, "litellm_changed_by" TEXT
);

CREATE TABLE IF NOT EXISTS "LiteLLM_AgentsTable" (
    "agent_id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "agent_name" TEXT UNIQUE NOT NULL, "litellm_params" JSONB,
    "agent_card_params" JSONB NOT NULL DEFAULT '{}',
    "static_headers" JSONB DEFAULT '{}', "extra_headers" TEXT[] DEFAULT '{}',
    "agent_access_groups" TEXT[] DEFAULT '{}', "object_permission_id" TEXT,
    "spend" FLOAT DEFAULT 0.0, "tpm_limit" INTEGER, "rpm_limit" INTEGER,
    "session_tpm_limit" INTEGER, "session_rpm_limit" INTEGER,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "created_by" TEXT NOT NULL DEFAULT '',
    "updated_at" TIMESTAMP(3) DEFAULT NOW(), "updated_by" TEXT NOT NULL DEFAULT ''
);

CREATE TABLE IF NOT EXISTS "LiteLLM_CredentialsTable" (
    "credential_id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "credential_name" TEXT UNIQUE NOT NULL, "credential_values" JSONB NOT NULL,
    "credential_info" JSONB,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "created_by" TEXT NOT NULL DEFAULT '',
    "updated_at" TIMESTAMP(3) DEFAULT NOW(), "updated_by" TEXT NOT NULL DEFAULT ''
);

CREATE TABLE IF NOT EXISTS "LiteLLM_ProxyModelTable" (
    "model_id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "model_name" TEXT NOT NULL, "litellm_params" JSONB NOT NULL,
    "model_info" JSONB, "blocked" BOOLEAN DEFAULT false,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "created_by" TEXT NOT NULL DEFAULT '',
    "updated_at" TIMESTAMP(3) DEFAULT NOW(), "updated_by" TEXT NOT NULL DEFAULT ''
);

CREATE TABLE IF NOT EXISTS "LiteLLM_Config" (
    "param_name" TEXT PRIMARY KEY, "param_value" JSONB
);

CREATE TABLE IF NOT EXISTS "LiteLLM_SpendLogs" (
    "request_id" TEXT PRIMARY KEY, "call_type" TEXT NOT NULL,
    "api_key" TEXT DEFAULT '', "spend" FLOAT DEFAULT 0.0,
    "total_tokens" INTEGER DEFAULT 0, "prompt_tokens" INTEGER DEFAULT 0,
    "completion_tokens" INTEGER DEFAULT 0,
    "startTime" TIMESTAMP(3) NOT NULL, "endTime" TIMESTAMP(3) NOT NULL,
    "request_duration_ms" INTEGER, "completionStartTime" TIMESTAMP(3),
    "model" TEXT DEFAULT '', "model_id" TEXT DEFAULT '',
    "model_group" TEXT DEFAULT '', "custom_llm_provider" TEXT DEFAULT '',
    "api_base" TEXT DEFAULT '', "user" TEXT DEFAULT '',
    "metadata" JSONB DEFAULT '{}', "cache_hit" TEXT DEFAULT '',
    "cache_key" TEXT DEFAULT '', "request_tags" JSONB DEFAULT '[]',
    "team_id" TEXT, "organization_id" TEXT, "end_user" TEXT,
    "requester_ip_address" TEXT, "messages" JSONB DEFAULT '{}',
    "response" JSONB DEFAULT '{}', "session_id" TEXT, "status" TEXT,
    "mcp_namespaced_tool_name" TEXT, "agent_id" TEXT,
    "proxy_server_request" JSONB DEFAULT '{}'
);

CREATE INDEX IF NOT EXISTS "LiteLLM_SpendLogs_startTime_idx" ON "LiteLLM_SpendLogs"("startTime");
CREATE INDEX IF NOT EXISTS "LiteLLM_SpendLogs_end_user_idx" ON "LiteLLM_SpendLogs"("end_user");
CREATE INDEX IF NOT EXISTS "LiteLLM_SpendLogs_session_id_idx" ON "LiteLLM_SpendLogs"("session_id");

CREATE TABLE IF NOT EXISTS "LiteLLM_ErrorLogs" (
    "request_id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "startTime" TIMESTAMP(3) NOT NULL, "endTime" TIMESTAMP(3) NOT NULL,
    "api_base" TEXT DEFAULT '', "model_group" TEXT DEFAULT '',
    "litellm_model_name" TEXT DEFAULT '', "model_id" TEXT DEFAULT '',
    "request_kwargs" JSONB DEFAULT '{}', "exception_type" TEXT DEFAULT '',
    "exception_string" TEXT DEFAULT '', "status_code" TEXT DEFAULT ''
);

CREATE TABLE IF NOT EXISTS "LiteLLM_UserNotifications" (
    "request_id" TEXT PRIMARY KEY, "user_id" TEXT NOT NULL,
    "models" TEXT[] DEFAULT '{}', "justification" TEXT NOT NULL DEFAULT '',
    "status" TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS "LiteLLM_TeamMembership" (
    "user_id" TEXT NOT NULL, "team_id" TEXT NOT NULL,
    "spend" FLOAT DEFAULT 0.0, "total_spend" FLOAT DEFAULT 0.0, "budget_id" TEXT,
    PRIMARY KEY ("user_id","team_id")
);

CREATE TABLE IF NOT EXISTS "LiteLLM_OrganizationMembership" (
    "user_id" TEXT NOT NULL, "organization_id" TEXT NOT NULL,
    "user_role" TEXT, "spend" FLOAT DEFAULT 0.0, "budget_id" TEXT,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "updated_at" TIMESTAMP(3) DEFAULT NOW(),
    PRIMARY KEY ("user_id","organization_id"), UNIQUE ("user_id","organization_id")
);

CREATE TABLE IF NOT EXISTS "LiteLLM_InvitationLink" (
    "id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "user_id" TEXT NOT NULL, "is_accepted" BOOLEAN DEFAULT false,
    "accepted_at" TIMESTAMP(3), "expires_at" TIMESTAMP(3) NOT NULL,
    "created_at" TIMESTAMP(3) NOT NULL, "created_by" TEXT NOT NULL,
    "updated_at" TIMESTAMP(3) NOT NULL, "updated_by" TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS "LiteLLM_AuditLog" (
    "id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "updated_at" TIMESTAMP(3) DEFAULT NOW(),
    "changed_by" TEXT DEFAULT '', "changed_by_api_key" TEXT DEFAULT '',
    "action" TEXT NOT NULL, "table_name" TEXT NOT NULL, "object_id" TEXT NOT NULL,
    "before_value" JSONB, "updated_values" JSONB
);

CREATE TABLE IF NOT EXISTS "LiteLLM_DailyUserSpend" (
    "id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "user_id" TEXT, "date" TEXT NOT NULL, "api_key" TEXT NOT NULL,
    "model" TEXT, "model_group" TEXT, "custom_llm_provider" TEXT,
    "mcp_namespaced_tool_name" TEXT, "endpoint" TEXT,
    "prompt_tokens" BIGINT DEFAULT 0, "completion_tokens" BIGINT DEFAULT 0,
    "cache_read_input_tokens" BIGINT DEFAULT 0, "cache_creation_input_tokens" BIGINT DEFAULT 0,
    "spend" FLOAT DEFAULT 0.0, "api_requests" BIGINT DEFAULT 0,
    "successful_requests" BIGINT DEFAULT 0, "failed_requests" BIGINT DEFAULT 0,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "updated_at" TIMESTAMP(3) DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS "LiteLLM_DailyTeamSpend" (
    "id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "team_id" TEXT, "date" TEXT NOT NULL, "api_key" TEXT NOT NULL,
    "model" TEXT, "model_group" TEXT, "custom_llm_provider" TEXT,
    "mcp_namespaced_tool_name" TEXT, "endpoint" TEXT,
    "prompt_tokens" BIGINT DEFAULT 0, "completion_tokens" BIGINT DEFAULT 0,
    "cache_read_input_tokens" BIGINT DEFAULT 0, "cache_creation_input_tokens" BIGINT DEFAULT 0,
    "spend" FLOAT DEFAULT 0.0, "api_requests" BIGINT DEFAULT 0,
    "successful_requests" BIGINT DEFAULT 0, "failed_requests" BIGINT DEFAULT 0,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "updated_at" TIMESTAMP(3) DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS "LiteLLM_DailyOrganizationSpend" (
    "id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "organization_id" TEXT, "date" TEXT NOT NULL, "api_key" TEXT NOT NULL,
    "model" TEXT, "model_group" TEXT, "custom_llm_provider" TEXT,
    "mcp_namespaced_tool_name" TEXT, "endpoint" TEXT,
    "prompt_tokens" BIGINT DEFAULT 0, "completion_tokens" BIGINT DEFAULT 0,
    "cache_read_input_tokens" BIGINT DEFAULT 0, "cache_creation_input_tokens" BIGINT DEFAULT 0,
    "spend" FLOAT DEFAULT 0.0, "api_requests" BIGINT DEFAULT 0,
    "successful_requests" BIGINT DEFAULT 0, "failed_requests" BIGINT DEFAULT 0,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "updated_at" TIMESTAMP(3) DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS "LiteLLM_DailyEndUserSpend" (
    "id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "end_user_id" TEXT, "date" TEXT NOT NULL, "api_key" TEXT NOT NULL,
    "model" TEXT, "model_group" TEXT, "custom_llm_provider" TEXT,
    "mcp_namespaced_tool_name" TEXT, "endpoint" TEXT,
    "prompt_tokens" BIGINT DEFAULT 0, "completion_tokens" BIGINT DEFAULT 0,
    "cache_read_input_tokens" BIGINT DEFAULT 0, "cache_creation_input_tokens" BIGINT DEFAULT 0,
    "spend" FLOAT DEFAULT 0.0, "api_requests" BIGINT DEFAULT 0,
    "successful_requests" BIGINT DEFAULT 0, "failed_requests" BIGINT DEFAULT 0,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "updated_at" TIMESTAMP(3) DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS "LiteLLM_DailyAgentSpend" (
    "id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "agent_id" TEXT, "date" TEXT NOT NULL, "api_key" TEXT NOT NULL,
    "model" TEXT, "model_group" TEXT, "custom_llm_provider" TEXT,
    "mcp_namespaced_tool_name" TEXT, "endpoint" TEXT,
    "prompt_tokens" BIGINT DEFAULT 0, "completion_tokens" BIGINT DEFAULT 0,
    "cache_read_input_tokens" BIGINT DEFAULT 0, "cache_creation_input_tokens" BIGINT DEFAULT 0,
    "spend" FLOAT DEFAULT 0.0, "api_requests" BIGINT DEFAULT 0,
    "successful_requests" BIGINT DEFAULT 0, "failed_requests" BIGINT DEFAULT 0,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "updated_at" TIMESTAMP(3) DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS "LiteLLM_DailyTagSpend" (
    "id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "request_id" TEXT, "tag" TEXT, "date" TEXT NOT NULL, "api_key" TEXT NOT NULL,
    "model" TEXT, "model_group" TEXT, "custom_llm_provider" TEXT,
    "mcp_namespaced_tool_name" TEXT, "endpoint" TEXT,
    "prompt_tokens" BIGINT DEFAULT 0, "completion_tokens" BIGINT DEFAULT 0,
    "cache_read_input_tokens" BIGINT DEFAULT 0, "cache_creation_input_tokens" BIGINT DEFAULT 0,
    "spend" FLOAT DEFAULT 0.0, "api_requests" BIGINT DEFAULT 0,
    "successful_requests" BIGINT DEFAULT 0, "failed_requests" BIGINT DEFAULT 0,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "updated_at" TIMESTAMP(3) DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS "LiteLLM_CronJob" (
    "cronjob_id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "pod_id" TEXT NOT NULL, "status" "JobStatus" DEFAULT 'INACTIVE',
    "last_updated" TIMESTAMP(3) DEFAULT NOW(), "ttl" TIMESTAMP(3) NOT NULL
);

CREATE TABLE IF NOT EXISTS "LiteLLM_GuardrailsTable" (
    "guardrail_id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "guardrail_name" TEXT UNIQUE NOT NULL, "litellm_params" JSONB NOT NULL,
    "guardrail_info" JSONB, "team_id" TEXT,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "updated_at" TIMESTAMP(3) DEFAULT NOW(),
    "status" TEXT DEFAULT 'active', "submitted_at" TIMESTAMP(3), "reviewed_at" TIMESTAMP(3)
);

CREATE TABLE IF NOT EXISTS "LiteLLM_DailyGuardrailMetrics" (
    "guardrail_id" TEXT NOT NULL, "date" TEXT NOT NULL,
    "requests_evaluated" BIGINT DEFAULT 0, "passed_count" BIGINT DEFAULT 0,
    "blocked_count" BIGINT DEFAULT 0, "flagged_count" BIGINT DEFAULT 0,
    "avg_score" FLOAT, "avg_latency_ms" FLOAT,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "updated_at" TIMESTAMP(3) DEFAULT NOW(),
    PRIMARY KEY ("guardrail_id","date")
);

CREATE TABLE IF NOT EXISTS "LiteLLM_DailyPolicyMetrics" (
    "policy_id" TEXT NOT NULL, "date" TEXT NOT NULL,
    "requests_evaluated" BIGINT DEFAULT 0, "passed_count" BIGINT DEFAULT 0,
    "blocked_count" BIGINT DEFAULT 0, "flagged_count" BIGINT DEFAULT 0,
    "avg_score" FLOAT, "avg_latency_ms" FLOAT,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "updated_at" TIMESTAMP(3) DEFAULT NOW(),
    PRIMARY KEY ("policy_id","date")
);

CREATE TABLE IF NOT EXISTS "LiteLLM_SpendLogGuardrailIndex" (
    "request_id" TEXT NOT NULL, "guardrail_id" TEXT NOT NULL,
    "policy_id" TEXT, "start_time" TIMESTAMP(3) NOT NULL,
    PRIMARY KEY ("request_id","guardrail_id")
);

CREATE TABLE IF NOT EXISTS "LiteLLM_SpendLogToolIndex" (
    "request_id" TEXT NOT NULL, "tool_name" TEXT NOT NULL,
    "start_time" TIMESTAMP(3) NOT NULL, PRIMARY KEY ("request_id","tool_name")
);

CREATE TABLE IF NOT EXISTS "LiteLLM_PromptTable" (
    "id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "prompt_id" TEXT NOT NULL, "version" INTEGER DEFAULT 1,
    "environment" TEXT DEFAULT 'development', "created_by" TEXT,
    "litellm_params" JSONB NOT NULL, "prompt_info" JSONB,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "updated_at" TIMESTAMP(3) DEFAULT NOW(),
    UNIQUE ("prompt_id","version","environment")
);

CREATE TABLE IF NOT EXISTS "LiteLLM_HealthCheckTable" (
    "health_check_id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "model_name" TEXT NOT NULL, "model_id" TEXT, "status" TEXT NOT NULL,
    "healthy_count" INTEGER DEFAULT 0, "unhealthy_count" INTEGER DEFAULT 0,
    "error_message" TEXT, "response_time_ms" FLOAT, "details" JSONB,
    "checked_by" TEXT, "checked_at" TIMESTAMP(3) DEFAULT NOW(),
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "updated_at" TIMESTAMP(3) DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS "LiteLLM_SearchToolsTable" (
    "search_tool_id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "search_tool_name" TEXT UNIQUE NOT NULL, "litellm_params" JSONB NOT NULL,
    "search_tool_info" JSONB,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "updated_at" TIMESTAMP(3) DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS "LiteLLM_SSOConfig" (
    "id" TEXT PRIMARY KEY DEFAULT 'sso_config',
    "sso_settings" JSONB NOT NULL,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "updated_at" TIMESTAMP(3) DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS "LiteLLM_CacheConfig" (
    "id" TEXT PRIMARY KEY DEFAULT 'cache_config',
    "cache_settings" JSONB NOT NULL,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "updated_at" TIMESTAMP(3) DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS "LiteLLM_UISettings" (
    "id" TEXT PRIMARY KEY DEFAULT 'ui_settings',
    "ui_settings" JSONB NOT NULL,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "updated_at" TIMESTAMP(3) DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS "LiteLLM_ConfigOverrides" (
    "config_type" TEXT PRIMARY KEY, "config_value" JSONB NOT NULL,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "updated_at" TIMESTAMP(3) DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS "LiteLLM_SkillsTable" (
    "skill_id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "display_title" TEXT, "description" TEXT, "instructions" TEXT,
    "source" TEXT DEFAULT 'custom', "latest_version" TEXT,
    "file_content" BYTEA, "file_name" TEXT, "file_type" TEXT,
    "metadata" JSONB DEFAULT '{}',
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "created_by" TEXT,
    "updated_at" TIMESTAMP(3) DEFAULT NOW(), "updated_by" TEXT
);

CREATE TABLE IF NOT EXISTS "LiteLLM_PolicyTable" (
    "policy_id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "policy_name" TEXT NOT NULL, "version_number" INTEGER DEFAULT 1,
    "version_status" TEXT DEFAULT 'production', "parent_version_id" TEXT,
    "is_latest" BOOLEAN DEFAULT true, "published_at" TIMESTAMP(3),
    "production_at" TIMESTAMP(3), "inherit" TEXT, "description" TEXT,
    "guardrails_add" TEXT[] DEFAULT '{}', "guardrails_remove" TEXT[] DEFAULT '{}',
    "condition" JSONB DEFAULT '{}', "pipeline" JSONB,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "created_by" TEXT,
    "updated_at" TIMESTAMP(3) DEFAULT NOW(), "updated_by" TEXT,
    UNIQUE ("policy_name","version_number")
);

CREATE TABLE IF NOT EXISTS "LiteLLM_PolicyAttachmentTable" (
    "attachment_id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "policy_name" TEXT NOT NULL, "scope" TEXT,
    "teams" TEXT[] DEFAULT '{}', "keys" TEXT[] DEFAULT '{}',
    "models" TEXT[] DEFAULT '{}', "tags" TEXT[] DEFAULT '{}',
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "created_by" TEXT,
    "updated_at" TIMESTAMP(3) DEFAULT NOW(), "updated_by" TEXT
);

CREATE TABLE IF NOT EXISTS "LiteLLM_ToolTable" (
    "tool_id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "tool_name" TEXT UNIQUE NOT NULL, "origin" TEXT,
    "input_policy" TEXT DEFAULT 'untrusted', "output_policy" TEXT DEFAULT 'untrusted',
    "call_count" INTEGER DEFAULT 0, "assignments" JSONB DEFAULT '{}',
    "key_hash" TEXT, "team_id" TEXT, "key_alias" TEXT, "user_agent" TEXT,
    "last_used_at" TIMESTAMP(3),
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "created_by" TEXT,
    "updated_at" TIMESTAMP(3) DEFAULT NOW(), "updated_by" TEXT
);

CREATE TABLE IF NOT EXISTS "LiteLLM_AccessGroupTable" (
    "access_group_id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "access_group_name" TEXT UNIQUE NOT NULL, "description" TEXT,
    "access_model_names" TEXT[] DEFAULT '{}',
    "access_mcp_server_ids" TEXT[] DEFAULT '{}',
    "access_agent_ids" TEXT[] DEFAULT '{}',
    "assigned_team_ids" TEXT[] DEFAULT '{}', "assigned_key_ids" TEXT[] DEFAULT '{}',
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "created_by" TEXT,
    "updated_at" TIMESTAMP(3) DEFAULT NOW(), "updated_by" TEXT
);

CREATE TABLE IF NOT EXISTS "LiteLLM_MCPServerTable" (
    "server_id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "server_name" TEXT, "alias" TEXT, "description" TEXT, "instructions" TEXT,
    "url" TEXT, "spec_path" TEXT, "transport" TEXT DEFAULT 'sse',
    "auth_type" TEXT, "credentials" JSONB DEFAULT '{}',
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "created_by" TEXT,
    "updated_at" TIMESTAMP(3) DEFAULT NOW(), "updated_by" TEXT,
    "mcp_info" JSONB DEFAULT '{}', "mcp_access_groups" TEXT[] DEFAULT '{}',
    "allowed_tools" TEXT[] DEFAULT '{}',
    "tool_name_to_display_name" JSONB DEFAULT '{}',
    "tool_name_to_description" JSONB DEFAULT '{}',
    "extra_headers" TEXT[] DEFAULT '{}', "static_headers" JSONB DEFAULT '{}',
    "env_vars" JSONB DEFAULT '[]', "status" TEXT DEFAULT 'unknown',
    "last_health_check" TIMESTAMP(3), "health_check_error" TEXT,
    "command" TEXT, "args" TEXT[] DEFAULT '{}', "env" JSONB DEFAULT '{}',
    "authorization_url" TEXT, "token_url" TEXT, "registration_url" TEXT,
    "oauth2_flow" TEXT, "allow_all_keys" BOOLEAN DEFAULT false,
    "available_on_public_internet" BOOLEAN DEFAULT true,
    "delegate_auth_to_upstream" BOOLEAN DEFAULT false,
    "oauth_passthrough" BOOLEAN DEFAULT false, "is_byok" BOOLEAN DEFAULT false,
    "byok_description" TEXT[] DEFAULT '{}', "byok_api_key_help_url" TEXT,
    "source_url" TEXT, "timeout" FLOAT, "approval_status" TEXT DEFAULT 'active',
    "submitted_by" TEXT, "submitted_at" TIMESTAMP(3), "reviewed_at" TIMESTAMP(3),
    "review_notes" TEXT
);

CREATE TABLE IF NOT EXISTS "LiteLLM_MCPToolsetTable" (
    "toolset_id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "toolset_name" TEXT UNIQUE NOT NULL, "description" TEXT,
    "tools" JSONB DEFAULT '[]',
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "created_by" TEXT,
    "updated_at" TIMESTAMP(3) DEFAULT NOW(), "updated_by" TEXT
);

CREATE TABLE IF NOT EXISTS "LiteLLM_MCPUserCredentials" (
    "id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "user_id" TEXT NOT NULL, "server_id" TEXT NOT NULL,
    "credential_b64" TEXT NOT NULL,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "updated_at" TIMESTAMP(3) DEFAULT NOW(),
    UNIQUE ("user_id","server_id")
);

CREATE TABLE IF NOT EXISTS "LiteLLM_MCPUserEnvVars" (
    "id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "user_id" TEXT NOT NULL, "server_id" TEXT NOT NULL, "values_b64" TEXT NOT NULL,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "updated_at" TIMESTAMP(3) DEFAULT NOW(),
    UNIQUE ("user_id","server_id")
);

CREATE TABLE IF NOT EXISTS "LiteLLM_ManagedVectorStoreIndexTable" (
    "id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "index_name" TEXT UNIQUE NOT NULL, "litellm_params" JSONB NOT NULL,
    "index_info" JSONB,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "created_by" TEXT,
    "updated_at" TIMESTAMP(3) DEFAULT NOW(), "updated_by" TEXT
);

CREATE TABLE IF NOT EXISTS "LiteLLM_ManagedFileTable" (
    "id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "unified_file_id" TEXT UNIQUE NOT NULL, "file_object" JSONB,
    "model_mappings" JSONB NOT NULL, "flat_model_file_ids" TEXT[] DEFAULT '{}',
    "storage_backend" TEXT, "storage_url" TEXT,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "created_by" TEXT, "team_id" TEXT,
    "updated_at" TIMESTAMP(3) DEFAULT NOW(), "updated_by" TEXT
);

CREATE TABLE IF NOT EXISTS "LiteLLM_ManagedObjectTable" (
    "id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "unified_object_id" TEXT UNIQUE NOT NULL, "model_object_id" TEXT UNIQUE NOT NULL,
    "file_object" JSONB NOT NULL, "file_purpose" TEXT NOT NULL,
    "status" TEXT, "batch_processed" BOOLEAN DEFAULT false,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "created_by" TEXT, "team_id" TEXT,
    "updated_at" TIMESTAMP(3) DEFAULT NOW(), "updated_by" TEXT
);

CREATE TABLE IF NOT EXISTS "LiteLLM_ManagedVectorStoreTable" (
    "id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "unified_resource_id" TEXT UNIQUE NOT NULL, "resource_object" JSONB,
    "model_mappings" JSONB NOT NULL, "flat_model_resource_ids" TEXT[] DEFAULT '{}',
    "storage_backend" TEXT, "storage_url" TEXT,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "created_by" TEXT, "team_id" TEXT,
    "updated_at" TIMESTAMP(3) DEFAULT NOW(), "updated_by" TEXT
);

CREATE TABLE IF NOT EXISTS "LiteLLM_ManagedVectorStoresTable" (
    "vector_store_id" TEXT PRIMARY KEY, "custom_llm_provider" TEXT NOT NULL,
    "vector_store_name" TEXT, "vector_store_description" TEXT,
    "vector_store_metadata" JSONB,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "updated_at" TIMESTAMP(3) DEFAULT NOW(),
    "litellm_credential_name" TEXT, "litellm_params" JSONB,
    "team_id" TEXT, "user_id" TEXT
);

CREATE TABLE IF NOT EXISTS "LiteLLM_ClaudeCodePluginTable" (
    "id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "name" TEXT UNIQUE NOT NULL, "version" TEXT, "description" TEXT,
    "manifest_json" TEXT, "files_json" TEXT DEFAULT '{}',
    "enabled" BOOLEAN DEFAULT true,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "updated_at" TIMESTAMP(3) DEFAULT NOW(),
    "created_by" TEXT
);

CREATE TABLE IF NOT EXISTS "LiteLLM_MemoryTable" (
    "memory_id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "key" TEXT UNIQUE NOT NULL, "value" TEXT NOT NULL, "metadata" JSONB,
    "user_id" TEXT, "team_id" TEXT,
    "created_at" TIMESTAMP(3) DEFAULT NOW(), "created_by" TEXT,
    "updated_at" TIMESTAMP(3) DEFAULT NOW(), "updated_by" TEXT
);

CREATE TABLE IF NOT EXISTS "LiteLLM_AdaptiveRouterState" (
    "router_name" TEXT NOT NULL, "request_type" TEXT NOT NULL,
    "model_name" TEXT NOT NULL, "alpha" FLOAT NOT NULL, "beta" FLOAT NOT NULL,
    "total_samples" INTEGER DEFAULT 0, "last_updated_at" TIMESTAMP(3) DEFAULT NOW(),
    PRIMARY KEY ("router_name","request_type","model_name")
);

CREATE TABLE IF NOT EXISTS "LiteLLM_AdaptiveRouterSession" (
    "session_id" TEXT NOT NULL, "router_name" TEXT NOT NULL, "model_name" TEXT NOT NULL,
    "classified_type" TEXT NOT NULL, "misalignment_count" INTEGER DEFAULT 0,
    "stagnation_count" INTEGER DEFAULT 0, "disengagement_count" INTEGER DEFAULT 0,
    "satisfaction_count" INTEGER DEFAULT 0, "failure_count" INTEGER DEFAULT 0,
    "loop_count" INTEGER DEFAULT 0, "exhaustion_count" INTEGER DEFAULT 0,
    "last_user_content" TEXT, "last_assistant_content" TEXT,
    "tool_call_history" JSONB DEFAULT '[]', "pending_tool_calls" JSONB DEFAULT '{}',
    "turn_count" INTEGER DEFAULT 0, "last_processed_turn" INTEGER DEFAULT -1,
    "clean_credit_awarded" BOOLEAN DEFAULT false, "terminal_status" INTEGER,
    "last_activity_at" TIMESTAMP(3) DEFAULT NOW(),
    PRIMARY KEY ("session_id","router_name","model_name")
);

CREATE TABLE IF NOT EXISTS "LiteLLM_WorkflowRun" (
    "run_id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "session_id" TEXT UNIQUE DEFAULT gen_random_uuid()::TEXT,
    "workflow_type" TEXT NOT NULL, "status" TEXT DEFAULT 'pending',
    "created_by" TEXT, "created_at" TIMESTAMP(3) DEFAULT NOW(),
    "updated_at" TIMESTAMP(3) DEFAULT NOW(),
    "input" JSONB, "output" JSONB, "metadata" JSONB
);

CREATE TABLE IF NOT EXISTS "LiteLLM_WorkflowEvent" (
    "event_id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "run_id" TEXT NOT NULL, "event_type" TEXT NOT NULL, "step_name" TEXT NOT NULL,
    "sequence_number" INTEGER NOT NULL, "data" JSONB,
    "created_at" TIMESTAMP(3) DEFAULT NOW(),
    UNIQUE ("run_id","sequence_number")
);

CREATE TABLE IF NOT EXISTS "LiteLLM_WorkflowMessage" (
    "message_id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "run_id" TEXT NOT NULL, "role" TEXT NOT NULL, "content" TEXT NOT NULL,
    "sequence_number" INTEGER NOT NULL, "session_id" TEXT,
    "created_at" TIMESTAMP(3) DEFAULT NOW(),
    UNIQUE ("run_id","sequence_number")
);

CREATE TABLE IF NOT EXISTS "LiteLLM_DeletedTeamTable" (
    "id" TEXT PRIMARY KEY DEFAULT gen_random_uuid()::TEXT,
    "team_id" TEXT NOT NULL, "team_alias" TEXT, "organization_id" TEXT,
    "object_permission_id" TEXT, "admins" TEXT[] DEFAULT '{}',
    "members" TEXT[] DEFAULT '{}', "members_with_roles" JSONB DEFAULT '{}',
    "metadata" JSONB DEFAULT '{}', "max_budget" FLOAT, "soft_budget" FLOAT,
    "spend" FLOAT DEFAULT 0.0, "models" TEXT[] DEFAULT '{}',
    "max_parallel_requests" INTEGER, "tpm_limit" BIGINT, "rpm_limit" BIGINT,
    "budget_duration" TEXT, "budget_reset_at" TIMESTAMP(3),
    "blocked" BOOLEAN DEFAULT false, "model_spend" JSONB DEFAULT '{}',
    "model_max_budget" JSONB DEFAULT '{}', "router_settings" JSONB,
    "team_member_permissions" TEXT[] DEFAULT '{}',
    "access_group_ids" TEXT[] DEFAULT '{}', "policies" TEXT[] DEFAULT '{}',
    "model_id" INTEGER, "allow_team_guardrail_config" BOOLEAN DEFAULT false,
    "created_at" TIMESTAMP(3), "updated_at" TIMESTAMP(3),
    "deleted_at" TIMESTAMP(3) DEFAULT NOW(), "deleted_by" TEXT,
    "deleted_by_api_key" TEXT, "litellm_changed_by" TEXT
);

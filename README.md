# ymh-ai-coder-mother

AI 驱动的智能代码生成平台——输入自然语言描述，自动生成完整前端项目代码，支持流式预览、多文件输出、截图对比和可视化编辑。

## 技术栈

| 层级 | 技术 |
|------|------|
| **后端框架** | Spring Boot 3.5 + Java 21 |
| **AI 集成** | LangChain4j + DeepSeek / 通义千问 |
| **数据库** | MySQL 8.0 + MyBatis-Plus |
| **缓存/Session** | Redis |
| **前端** | Vue 3 + TypeScript + Vite + Ant Design Vue |
| **监控** | Prometheus + Grafana + Actuator |
| **API 文档** | SpringDoc OpenAPI + Knife4j |
| **构建工具** | Maven Wrapper |

## 项目结构

```
ymh-ai-coder-mother/
├── src/                              # 主应用（Spring Boot 入口）
├── ymh-ai-code-mother-frontend/      # Vue 3 前端
├── ymh-ai-code-mother-microservice/  # 微服务模块
│   ├── ymh-ai-code-ai/               # AI 代码生成（流式对话、工具管理）
│   ├── ymh-ai-code-app/              # 应用管理服务
│   ├── ymh-ai-code-client/           # 微服务客户端（Feign）
│   ├── ymh-ai-code-common/           # 公共模块
│   ├── ymh-ai-code-model/            # 数据模型、DTO、枚举
│   ├── ymh-ai-code-screenshot/       # 网页截图服务
│   └── ymh-ai-code-user/            # 用户管理服务
├── sql/                              # 数据库初始化脚本
├── grafana/                          # Grafana 监控面板配置
├── prometheus.yml                    # Prometheus 配置
└── pom.xml                           # Maven 父 POM
```

## 环境要求

- **JDK** 21+
- **Maven** 3.8+
- **Node.js** 18+ / npm 9+
- **MySQL** 8.0+
- **Redis** 6.0+

## 快速启动

### 1. 初始化数据库

执行 `sql/create_table.sql` 创建数据库表：

```bash
mysql -u root -p < sql/create_table.sql
```

### 2. 配置环境

复制并编辑本地配置文件（不会被 Git 追踪）：

```bash
cp src/main/resources/application-local.yml.example src/main/resources/application-local.yml
```

填写你的 AI API Key（DeepSeek / 通义千问）和数据库连接信息。

### 3. 启动后端

```bash
./mvnw spring-boot:run
```

后端运行在 `http://localhost:8123/api`，API 文档地址 `http://localhost:8123/api/doc.html`

### 4. 启动前端

```bash
cd ymh-ai-code-mother-frontend
npm install
npm run dev
```

前端运行在 `http://localhost:5173`

## 核心功能

- **AI 流式代码生成**：支持 DeepSeek 和通义千问双模型，实时流式输出
- **多文件项目生成**：一键生成完整的 Vue + TypeScript 前端项目
- **智能路由分发**：简单任务路由至轻量模型，复杂任务使用推理模型
- **工具调用链**：AI 自主调用文件读写、修改、删除工具完成代码生成
- **网页截图预览**：Playwright 驱动的服务端截图，实时预览生成效果
- **用户管理**：注册、登录、Session 管理
- **AI 模型监控**：Prometheus + Grafana 实时监控 AI 调用指标
- **安全防护**：Prompt 安全输入护栏 + 重试输出护栏

## 监控

项目内置 Prometheus 指标采集，Metrics 端点：`http://localhost:8123/api/actuator/prometheus`

Grafana 面板配置位于 `grafana/ai_model_grafana_config.json`，导入后即可查看 AI 模型调用次数、响应时间等指标。

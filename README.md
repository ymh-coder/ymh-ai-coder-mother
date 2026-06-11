# ymh-ai-coder-mother

AI 驱动的智能代码生成平台——输入自然语言描述，自动生成完整前端项目代码，支持流式预览、多文件输出、截图对比和可视化编辑。

## 技术栈

### 后端

| 类别 | 技术 | 说明 |
|------|------|------|
| **核心框架** | Spring Boot 3.5 | 主框架，提供 Web、AOP、Actuator 等开箱即用的能力 |
| **语言** | Java 21 | 利用虚拟线程、Record 等新特性 |
| **AI 框架** | LangChain4j | Java 生态的 LLM 集成框架，提供 Chat Model、Tool、Guardrail、Streaming 等统一抽象 |
| **AI 模型** | DeepSeek（chat / reasoner）+ 通义千问（qwen-turbo） | 双模型架构，chat 负责生成，reasoner 负责复杂推理，qwen-turbo 负责意图路由 |
| **流式响应** | LangChain4j Reactor + SSE | 基于 Project Reactor 的 `Flux<String>` 实现 SSE 流式输出 |
| **ORM** | MyBatis Flex + MyBatis Flex CodeGen | 轻量 ORM，支持链式查询和代码生成器 |
| **连接池** | HikariCP | Spring Boot 默认高性能连接池 |
| **数据库** | MySQL 8.0 | 关系型数据库，存储用户、应用、聊天历史 |
| **缓存** | Redis + Caffeine + Redisson | Redis 负责 Session 和聊天记忆持久化；Caffeine 提供本地缓存加速；Redisson 提供分布式锁 |
| **会话管理** | Spring Session + Redis | 分布式 Session 管理，支持 30 天免登录 |
| **API 文档** | SpringDoc OpenAPI + Knife4j | 自动生成 Swagger 文档，Knife4j 提供增强 UI |
| **监控** | Micrometer + Prometheus + Grafana | Micrometer 采集指标，Prometheus 拉取存储，Grafana 可视化 |
| **截图** | Selenium + WebDriverManager | 自动化浏览器截图，WebDriverManager 自动管理 ChromeDriver 版本 |
| **对象存储** | 腾讯云 COS | 截图文件云端存储 |
| **工具库** | Lombok + Hutool | Lombok 减少样板代码，Hutool 提供通用工具方法 |
| **构建** | Maven Wrapper | 无需预装 Maven，`mvnw` 自动下载匹配版本 |

### 前端

| 类别 | 技术 | 说明 |
|------|------|------|
| **核心框架** | Vue 3.5（Composition API） | 渐进式前端框架，`<script setup>` 语法 |
| **语言** | TypeScript 5.8 | 类型安全，配合 Vue 3 获得完整的 IDE 提示 |
| **构建工具** | Vite 7 | 极速 HMR 热更新和构建 |
| **UI 组件库** | Ant Design Vue 4 | 企业级 UI 组件，提供表单、表格、布局等丰富组件 |
| **状态管理** | Pinia 3 | Vue 官方推荐的状态管理库，轻量且 TypeScript 友好 |
| **路由** | Vue Router 4 | SPA 路由管理 |
| **HTTP 客户端** | Axios | 请求拦截、响应拦截统一处理 |
| **代码渲染** | Markdown-it + highlight.js | AI 生成的 Markdown 代码块语法高亮渲染 |
| **API 类型生成** | @umijs/openapi | 从后端 OpenAPI 文档自动生成 TypeScript 类型和请求函数 |
| **代码质量** | ESLint 9 + Prettier 3 | 代码规范和格式化 |

### 运维 & 基础设施

| 类别 | 技术 | 说明 |
|------|------|------|
| **指标采集** | Prometheus | 定时拉取 Actuator 暴露的 `/actuator/prometheus` 端点 |
| **可视化** | Grafana | 预置 JSON 面板配置，展示 AI 调用次数、成功率、响应时间等 |
| **健康检查** | Spring Boot Actuator | 提供 `/health`、`/info`、`/prometheus` 等运维端点 |

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

### AI 流式代码生成

基于 LangChain4j 集成了 DeepSeek（deepseek-chat / deepseek-reasoner）和通义千问（qwen-turbo）双模型架构。支持两种代码生成模式：

- **HTML 单文件模式**：适用于快速生成单个页面，AI 直接输出完整的 HTML + CSS + JS
- **多文件项目模式**：AI 自动规划项目结构，依次生成多个文件，适合完整的 Vue + TypeScript 前端项目

两种模式均支持 **SSE 流式输出**，用户可实时看到 AI 逐字生成代码的过程，体验流畅。流式响应基于 Project Reactor 的 `Flux<String>` 实现，前端通过 EventSource 接收并渲染。

### 智能路由分发

系统在用户提交需求后，首先通过 `AiCodeGenTypeRoutingService` 调用轻量路由模型（qwen-turbo）进行意图分类，判断任务是简单的 HTML 页面生成还是复杂的多文件项目：

- **简单任务**（如单个页面）→ 路由至 deepseek-chat，快速响应
- **复杂任务**（如完整项目）→ 路由至 deepseek-reasoner，利用推理模型深度思考项目架构、文件拆分和依赖关系

路由决策由结构化 System Prompt 驱动，直接输出枚举类型，无需二次解析。

### AI 工具调用链

AI 在生成多文件项目时，不是一次性输出所有代码，而是像一个真实开发者一样**逐步操作文件系统**。系统注册了 6 个工具供 AI 自主调用：

| 工具 | 功能 | 说明 |
|------|------|------|
| `file_read` | 读取文件 | 允许 AI 查看已生成的文件内容作为上下文 |
| `file_write` | 写入文件 | AI 指定路径和内容，创建新文件 |
| `file_modify` | 修改文件 | 局部修改已有文件，减少重复输出 |
| `file_delete` | 删除文件 | 清理不需要的临时文件 |
| `file_dir_read` | 读取目录 | 查看项目文件结构 |
| `exit` | 结束生成 | AI 判断项目完成后主动退出 |

ToolManager 统一管理所有工具实例，通过 `@Resource` 自动注入并建立名称映射，AI 根据工具描述自主决定调用顺序和参数，形成完整的**工具调用链**。

### 网页截图预览

代码生成完成后，系统通过 Playwright 驱动的 `WebScreenShotUtils` 对生成的网页进行服务端截图，并自动上传至 **腾讯云 COS** 对象存储，返回可访问的 URL。用户可以：

- 在生成结果页直接预览网页渲染效果
- 对比生成前后的视觉差异
- 分享截图链接给团队成员

截图服务已拆分为独立微服务 `ymh-ai-code-screenshot`，通过 Feign 客户端被主应用调用。

### 安全防护

系统内置两层护栏（Guardrail）确保 AI 对话安全：

**输入护栏 `PromptSafetyInputGuardrail`**：
- 检测输入长度（限制 1000 字符以内）
- 过滤空输入
- 拦截敏感词（越狱、绕过、hack、jailbreak 等中英文关键词）
- 正则匹配注入攻击模式（ignore previous instructions、pretend as、system: you are 等）

**输出护栏 `RetryOutputGuardrail`**：
- 检测空响应或过短响应（< 10 字符），自动触发重试
- 过滤输出中的敏感内容，检测到则重新生成

两层护栏在 LangChain4j 的 `@Service` 接口上声明式配置，不侵入业务逻辑。

### 用户管理

基于 Redis Session 的完整用户体系：

- 注册 / 登录 / 登出
- Cookie Session 管理，支持 30 天免登录
- 角色权限枚举（UserRoleEnum：普通用户 / 管理员）
- AuthInterceptor 拦截器统一鉴权
- 用户服务已拆分为独立微服务 `ymh-ai-code-user`

### AI 模型监控

基于 Prometheus + Grafana 的全链路监控，追踪每一次 AI 调用的关键指标：

- **自定义指标采集**：`AiModelMetricsCollector` 记录调用次数、成功/失败率、响应时长
- **请求生命周期监听**：`AiModelMonitorListener` 在 AI 调用的前后埋点，自动计算耗时
- **线程隔离上下文**：`MonitorContextHolder`（基于 ThreadLocal）确保并发场景下指标不串扰
- **Grafana 面板**：预置 JSON 配置（`grafana/ai_model_grafana_config.json`），一键导入即可展示仪表盘

Metrics 端点：`http://localhost:8123/api/actuator/prometheus`

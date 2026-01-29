# 技能管理系统 (Skills Management System)

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.1-brightgreen)
![Java](https://img.shields.io/badge/Java-17-orange)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1-blue)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3-purple)
![License](https://img.shields.io/badge/License-MIT-yellow)

一个基于 Spring Boot + Thymeleaf 构建的完整技能管理系统，用于记录和管理个人技能清单。

## 📋 目录

- [功能特性](#-功能特性)
- [技术栈](#-技术栈)
- [项目结构](#-项目结构)
- [快速开始](#-快速开始)
- [使用说明](#-使用说明)
- [API 端点](#-api-端点)
- [数据库](#-数据库)
- [测试](#-测试)
- [截图](#-截图)
- [贡献](#-贡献)

## ✨ 功能特性

### 核心功能
- ✅ **技能管理 CRUD**：创建、查看、编辑、删除技能
- 🔍 **搜索功能**：通过关键字搜索技能名称、描述或分类
- 🏷️ **分类筛选**：按技能分类（编程语言、框架、工具、数据库、软技能）进行筛选
- 📊 **熟练度等级**：记录技能的熟练程度（初学者、中级、高级、专家）
- 📅 **经验年限**：追踪每项技能的学习时长
- 📄 **分页显示**：支持大量数据的分页浏览
- 🎨 **响应式设计**：适配各种屏幕尺寸

### 技术特性
- ✅ **表单验证**：前后端完整的输入验证
- 🎯 **异常处理**：全局异常处理机制
- 📝 **日志记录**：详细的应用日志
- 🗄️ **H2 数据库**：内存数据库，开发便捷
- 🔄 **数据初始化**：自动加载示例数据
- 🧪 **单元测试**：完整的测试覆盖

## 🛠 技术栈

### 后端
- **Spring Boot 3.2.1** - 应用框架
- **Spring Data JPA** - 数据持久化
- **Spring MVC** - Web 框架
- **Bean Validation** - 数据验证
- **H2 Database** - 内存数据库
- **Lombok** - 简化代码

### 前端
- **Thymeleaf** - 模板引擎
- **Bootstrap 5.3** - UI 框架
- **Bootstrap Icons** - 图标库
- **JavaScript** - 客户端交互

### 开发工具
- **Maven** - 构建工具
- **Spring Boot DevTools** - 热重载
- **JUnit 5** - 单元测试
- **Mockito** - Mock 框架

## 📁 项目结构

```
spingskilldemo/
├── src/
│   ├── main/
│   │   ├── java/com/example/skillsdemo/
│   │   │   ├── SkillsDemoApplication.java       # 主程序入口
│   │   │   ├── controller/
│   │   │   │   └── SkillController.java         # 控制器
│   │   │   ├── model/
│   │   │   │   ├── Skill.java                   # 技能实体
│   │   │   │   └── ProficiencyLevel.java        # 熟练度枚举
│   │   │   ├── repository/
│   │   │   │   └── SkillRepository.java         # 数据访问层
│   │   │   ├── service/
│   │   │   │   ├── SkillService.java            # 服务接口
│   │   │   │   └── SkillServiceImpl.java        # 服务实现
│   │   │   ├── config/
│   │   │   │   └── DataInitializer.java         # 数据初始化
│   │   │   └── exception/
│   │   │       └── GlobalExceptionHandler.java  # 全局异常处理
│   │   └── resources/
│   │       ├── application.properties            # 主配置
│   │       ├── application-dev.properties        # 开发环境配置
│   │       ├── static/
│   │       │   ├── css/
│   │       │   │   └── style.css                 # 自定义样式
│   │       │   └── js/
│   │       │       └── main.js                   # 自定义脚本
│   │       └── templates/
│   │           ├── index.html                    # 首页
│   │           ├── skills/
│   │           │   ├── list.html                 # 技能列表
│   │           │   ├── create.html               # 创建技能
│   │           │   ├── edit.html                 # 编辑技能
│   │           │   └── view.html                 # 查看技能
│   │           └── fragments/
│   │               ├── header.html               # 页头片段
│   │               ├── footer.html               # 页脚片段
│   │               └── navigation.html           # 导航栏片段
│   └── test/
│       └── java/com/example/skillsdemo/
│           ├── SkillsDemoApplicationTests.java
│           ├── controller/
│           │   └── SkillControllerTest.java      # 控制器测试
│           └── service/
│               └── SkillServiceTest.java         # 服务层测试
├── pom.xml                                       # Maven 配置
├── README.md                                     # 项目文档
└── .gitignore                                    # Git 忽略配置
```

## 🚀 快速开始

### 前置要求

- Java 17 或更高版本
- Maven 3.6 或更高版本
- Git（可选）

### 安装步骤

1. **克隆仓库**
```bash
git clone https://github.com/sanecat/spingskilldemo.git
cd spingskilldemo
```

2. **构建项目**
```bash
mvn clean install
```

3. **运行应用**
```bash
mvn spring-boot:run
```

4. **访问应用**
- 应用地址: http://localhost:8080
- H2 控制台: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:skillsdb`
  - 用户名: `sa`
  - 密码: (留空)

### Docker 运行（可选）

```bash
# 构建镜像
docker build -t skills-management-system .

# 运行容器
docker run -p 8080:8080 skills-management-system
```

## 📖 使用说明

### 1. 查看首页
访问 http://localhost:8080 查看系统介绍和功能概览。

### 2. 浏览技能列表
- 点击"技能列表"导航链接
- 使用搜索框输入关键字搜索技能
- 使用分类下拉框筛选特定分类的技能
- 支持分页浏览，每页显示 10 个技能

### 3. 添加新技能
1. 点击"添加技能"按钮
2. 填写技能信息：
   - **名称**（必填）：技能的名称
   - **描述**：技能的详细描述
   - **分类**（必填）：如编程语言、框架、工具等
   - **熟练度等级**（必填）：初学者、中级、高级、专家
   - **经验年限**（必填）：0-50 年
3. 点击"保存"提交表单

### 4. 查看技能详情
- 在技能列表中点击"查看"按钮
- 显示完整的技能信息和时间戳

### 5. 编辑技能
- 点击"编辑"按钮修改技能信息
- 支持更新所有字段
- 自动记录更新时间

### 6. 删除技能
- 点击"删除"按钮
- 确认删除操作
- 技能将从数据库中永久删除

## 🔌 API 端点

| 方法 | 端点 | 描述 |
|------|------|------|
| GET | `/` | 首页 |
| GET | `/skills` | 技能列表（支持分页、搜索、筛选） |
| GET | `/skills/new` | 显示创建技能表单 |
| POST | `/skills` | 创建新技能 |
| GET | `/skills/{id}` | 查看技能详情 |
| GET | `/skills/{id}/edit` | 显示编辑技能表单 |
| POST | `/skills/{id}` | 更新技能 |
| POST | `/skills/{id}/delete` | 删除技能 |

### 请求参数

**GET /skills**
- `page` (int): 页码，默认 0
- `size` (int): 每页大小，默认 10
- `keyword` (String): 搜索关键字（可选）
- `category` (String): 分类筛选（可选）
- `sortBy` (String): 排序字段，默认 createdAt
- `sortDir` (String): 排序方向，默认 desc

## 🗄️ 数据库

### 技能实体 (Skill)

| 字段 | 类型 | 说明 |
|------|------|------|
| id | Long | 主键，自增 |
| name | String | 技能名称（必填，2-100字符） |
| description | String | 技能描述（最多500字符） |
| category | String | 分类（必填） |
| proficiencyLevel | Enum | 熟练度等级（必填） |
| yearsOfExperience | Integer | 经验年限（0-50） |
| createdAt | LocalDateTime | 创建时间 |
| updatedAt | LocalDateTime | 更新时间 |

### 熟练度等级 (ProficiencyLevel)

- `BEGINNER` - 初学者
- `INTERMEDIATE` - 中级
- `ADVANCED` - 高级
- `EXPERT` - 专家

### 示例数据

应用启动时会自动初始化以下示例技能：
1. Java - 编程语言（专家，8年）
2. Spring Boot - 框架（高级，5年）
3. Python - 编程语言（中级，3年）
4. React - 框架（中级，2年）
5. Docker - 工具（高级，4年）
6. MySQL - 数据库（高级，6年）
7. Git - 工具（专家，7年）
8. 沟通协作 - 软技能（高级，8年）
9. Kubernetes - 工具（初学者，1年）
10. TypeScript - 编程语言（中级，2年）

## 🧪 测试

### 运行所有测试
```bash
mvn test
```

### 运行特定测试类
```bash
mvn test -Dtest=SkillServiceTest
mvn test -Dtest=SkillControllerTest
```

### 测试覆盖
- 单元测试：`SkillServiceTest`
- 集成测试：`SkillControllerTest`
- 应用上下文测试：`SkillsDemoApplicationTests`

## 📸 截图

### 首页
![首页](screenshots/home.png)

### 技能列表
![技能列表](screenshots/skills-list.png)

### 添加技能
![添加技能](screenshots/create-skill.png)

### 查看技能详情
![技能详情](screenshots/view-skill.png)

### 编辑技能
![编辑技能](screenshots/edit-skill.png)

### H2 控制台
![H2控制台](screenshots/h2-console.png)

## 🔧 配置

### 端口配置
修改 `application.properties` 中的端口：
```properties
server.port=8080
```

### 数据库配置
默认使用 H2 内存数据库。如需使用 MySQL：

1. 添加 MySQL 依赖到 `pom.xml`：
```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```

2. 修改 `application-dev.properties`：
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/skillsdb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```

### 日志级别
修改 `application.properties` 中的日志配置：
```properties
logging.level.com.example.skillsdemo=DEBUG
```

## 🤝 贡献

欢迎贡献！请遵循以下步骤：

1. Fork 本仓库
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启 Pull Request

## 📝 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情。

## 👨‍💻 作者

- **Your Name** - [GitHub Profile](https://github.com/sanecat)

## 🙏 致谢

- Spring Boot 团队
- Thymeleaf 社区
- Bootstrap 开发者
- 所有开源贡献者

## 📞 联系方式

如有问题或建议，请通过以下方式联系：

- 提交 Issue: https://github.com/sanecat/spingskilldemo/issues
- Email: your.email@example.com

---

⭐ 如果这个项目对你有帮助，请给一个星标！
# SpringMVC 重点学习指南

> 依据《SpringMVC 讲义》（动力节点，共 14 章 + 源码剖析，约 8100 行，`document-md文件请安装typora工具打开/` 目录——这是资料包原装文件夹名，不用真装 Typora，任意 md 编辑器可读）
> 制定时间：2026-10-19 ｜ 目标：**10-20~23 四天学完**（Day 1 上午先回 Spring6 第 18 章整合），10-24 进 Spring Boot
>
> **核心策略：这套课是"原理教学版"——web.xml、XML 配置、Thymeleaf 视图渲染都教，但你后面用 Spring Boot，这些配置全被自动化。**
> 该花时间的是：**注解与请求处理（3/6/7）、拦截器、异常处理、执行流程、SSM 整合**——注解是 Boot 项目的日常，执行流程是面试最高频，SSM 整合是黑马点评前的最后合龙。
> 该省时间的是：**视图渲染、三个域对象深究、XML 配置细节**——前后端分离 + Boot 之后都用不上。
>
> 环境：课程 JDK21 + Tomcat 10 + Spring/SpringMVC 6.1.4（jakarta 命名空间，与你的 JDK17/Spring6 完全兼容，可直接跟做）。`tools/` 里三个 Tomcat **只用 10.1.19**；`repository.zip` 是离线 Maven 仓库，依赖下载慢时解压再用。

---

## 〇、优先级总览

| 优先级 | 含义 | 章节清单 |
|--------|------|----------|
| 🔥🔥🔥 必须精通 | 动手写 + 能给面试官讲明白 | 1 入门(跑通) / 2 RequestMapping / 3 接收数据 / 6 RESTFul / 7 HttpMessageConverter / 9 异常处理器 / 10 拦截器 / 14 SSM整合 |
| 🔥🔥 重点掌握 | 理解 + 会用 | 4 三个域对象(只到 Model) / 8 文件上传下载 / 11 执行流程 |
| 🔥 快速过 | 知道有这回事 | 5 视图(只看转发重定向+静态资源) / 13 全注解开发 / 2 的 params/headers 属性 |
| ⏭ 选做亮点 | 时间富余再做，最多半天 | 12 手写Spring MVC(至少读"参与者"一节) / 源码剖析文件夹 |

**如果时间只剩 3 天的最短路径：**
1 → 2 → 3 → 6 → 7 → 9 → 10 → 11 → 14

---

## 一、分章重点明细

### 第 1 章 初识SpringMVC 🔥🔥🔥（半天，必动手）
- MVC 概念快过，记住定位：**DispatcherServlet 是整个框架的心脏（前端控制器）**——后面执行流程、手写、Boot 自动配置全绕着它
- 第一个程序完整跟做：web 模块 → web.xml 配 DispatcherServlet → springmvc-servlet.xml → 控制器返回视图名 → Tomcat 部署跑通
- ⚠️ 这套"web.xml + XML 配置 + 返回视图"是**教学用传统姿势**，Boot 里全部自动化——跟做的目的是看懂 DispatcherServlet 在中间干了什么，不是背配置
- 第二个程序（免 XML 配置版）知道有这回事即可

### 第 2 章 RequestMapping注解 🔥🔥🔥（2~3 小时）
- **类上 + 方法上结合**（`/user` + `/list` = `/user/list`）：项目标准姿势，必会
- value 属性：Ant 风格（`?` `*` `**`）了解；**占位符 `{id}` + @PathVariable 是重点**——RESTFul 的地基
- method 属性 + **衍生注解 @GetMapping/@PostMapping**：以后项目里基本只写这俩
- GET vs POST 区别：面试高频，3 分钟总结收下
- params/headers 属性：快速过，实际项目很少用

### 第 3 章 获取请求数据 🔥🔥🔥（半天，接口层的日常）
- 四种接收方式，按实际使用频率排：**POJO 自动封装 > @RequestParam（required/defaultValue）> @PathVariable > 原生 Servlet API（知道即可）**
- @RequestHeader / @CookieValue：会用即可
- **POST 中文乱码 → CharacterEncodingFilter**：SSM 整合章要用，记住它
- 自测标准：给一个注册表单，不假思索选对接收方式

### 第 4 章 三个域对象 🔥（1 小时，只到"会用 Model"）
- request 域：**实际开发唯一姿势是 Model 接口（addAttribute）**，原生 API / Map / ModelMap / ModelAndView 知道即可
- session / application 域：**快速过**——你的项目登录态走 Redis + token（黑马点评正是如此），传统 session 知道 SessionAttributes 存在即可

### 第 5 章 视图View 🔥（只看两小节，40 分钟）
- **Thymeleaf / JSP 视图渲染整段跳过**——前后端分离后端只出 JSON，不渲染页面
- **转发 forward / 重定向 redirect 区别必看**（面试高频：请求次数、地址栏、能否共享 request 域）
- 静态资源两种处理方式 + `<mvc:annotation-driven/>` 存在的意义：过一遍，Boot 里全是自动的

### 第 6 章 RESTFul编程风格 🔥🔥🔥（半天，含一个大实战）
- **RESTFul：URL 只表资源、HTTP 动词表操作**（GET 查 / POST 增 / PUT 改 / DELETE 删）——11 月项目的接口全按这个风格写
- **HiddenHttpMethodFilter**：HTML 表单只支持 GET/POST，怎么发 PUT/DELETE——理解机制即可
- 用户管理系统实战：**建议完整跟做**——第一个"完整 CRUD 项目"，把增删改查 + 跳转全串起来，是 SSM 整合的预演；前端页面用资料配套的，别自己写

### 第 7 章 HttpMessageConverter 🔥🔥🔥（2~3 小时）——前后端分离的命脉章
- **@ResponseBody：对象 → JSON**（MappingJackson2HttpMessageConverter 干的）
- **@RequestBody：JSON → 对象**
- **@RestController = @Controller + @ResponseBody**：以后写接口直接用它，黑马点评全项目都是 @RestController
- RequestEntity / ResponseEntity：快速过（文件下载、统一响应时还会遇到）
- 自测标准：axios 发 JSON → 后端收对象 → 返回 JSON，这个闭环通了，前后端分离就通了

### 第 8 章 文件上传与下载 🔥🔥（1~2 小时）
- 上传：multipart 依赖 + **MultipartFile.transferTo**；下载：ResponseEntity 写流
- 项目里头像/图片上传常见，跟做一遍即可；Boot 里配置更简单，思想不变

### 第 9 章 异常处理器 🔥🔥🔥（40 分钟，小而关键）
- 默认的 DefaultHandlerExceptionResolver 知道即可
- **@ControllerAdvice + @ExceptionHandler 必会**——黑马点评"统一异常处理"改造点就是它（全局兜底 + 自定义异常 + 统一响应格式）

### 第 10 章 拦截器 🔥🔥🔥（2 小时）
- HandlerInterceptor 三方法 + 基本配置 + 高级配置（excludePathPatterns 排除路径）
- **执行顺序 preHandle → Controller → postHandle → afterCompletion 必能画出来**；源码分析小节可跳，记结论
- 你的项目登录校验就靠它（拦截器 + token），是项目骨架件
- 面试连带：**拦截器 vs 过滤器区别**（归属规范 / 拦截粒度 / 是否进 Spring 容器）

### 第 11 章 Spring MVC执行流程 🔥🔥🔥（1~2 小时）——面试第一高频
- **白板能画完整流程**：请求 → DispatcherServlet → HandlerMapping（找处理器执行链）→ HandlerAdapter（执行）→ Controller → ModelAndView → ViewResolver → 渲染响应
- 前后端分离视角也要会讲：@RestController 时没有视图那半截，直接 HttpMessageConverter 写回 JSON
- "WEB 服务器启动时做了什么"看一遍：DispatcherServlet 启动即初始化容器
- ⏭ 源码剖析文件夹 9 个文件：读完本章有兴趣再对号入座，别上来就啃

### 第 12 章 手写Spring MVC ⏭（选做亮点，最多半天）
- 和手写 GodBatis、mini-Spring 同一定位：看穿本质 + 面试差异化
- **时间不够保底：只读开头"整个完整系统的参与者"一节**（几页纸，讲清 Servlet 容器 / 框架 / 应用三层关系）
- 1467 行的大章，排期紧就跳过，Boot 项目经验比它值钱

### 第 13 章 全注解开发 🔥（1 小时）
- Servlet 3.0：WebAppInitializer 替代 web.xml，配置类替代 springmvc.xml
- **定位：通往 Spring Boot 的桥**——看懂"这些配置要手写"之后，Boot 自动配置帮你省了什么就秒懂（Boot 3 天的伏笔）

### 第 14 章 SSM整合 🔥🔥🔥（半天，最终章 + 合龙章）
- 依赖 → 包结构 → **Spring 整合 MyBatis（SqlSessionFactoryBean + MapperScannerConfigurer，即 Spring6 第 18 章）** → Spring 整合 MVC（注解方式配 DispatcherServlet）→ **DataSourceTransactionManager 事务**
- ajax 测试：ch7 的 @ResponseBody + ch3 的接收参数 + MyBatis 的 CRUD 全用上，前后端分离雏形
- **这个 demo 就是你 11 月黑马点评的骨架雏形，做完留好**——至此 Spring6 ch18 + MyBatis + SpringMVC 三线正式合龙

---

## 二、面试高频题自测清单

学完后逐条自测，答不上来回看对应章节：

| # | 面试题 | 出处 | 掌握标准 |
|---|--------|------|----------|
| 1 | Spring MVC 执行流程？ | 11 | 能白板画图：DispatcherServlet→HandlerMapping→HandlerAdapter→Controller→视图/JSON |
| 2 | 转发和重定向的区别？ | 5 | 请求次数、地址栏变化、request 域能否共享 |
| 3 | 拦截器和过滤器的区别？ | 10 | Servlet 规范 vs SpringMVC、拦截粒度、能否注入容器 Bean |
| 4 | @Controller 和 @RestController 的区别？ | 7 | 后者 = 前者 + @ResponseBody，方法返回值直接写响应体 |
| 5 | @RequestParam / @RequestBody / @PathVariable 各什么时候用？ | 3/7/6 | 散参 / JSON 体 / URL 路径变量，各举一例 |
| 6 | GET 和 POST 的区别？ | 2 | 语义、参数位置、长度/缓存/幂等性 |
| 7 | Spring MVC 怎么统一处理异常？ | 9 | @ControllerAdvice + @ExceptionHandler 全局兜底 |
| 8 | @ResponseBody 把对象变成 JSON 的原理？ | 7 | HttpMessageConverter，运行时由 Jackson 转换器序列化 |
| 9 | RESTFul 是什么？ | 6 | 资源 + HTTP 动词；HiddenHttpMethodFilter 支持 PUT/DELETE |
| 10 | Spring MVC 和 Spring Boot 的关系？ | 13/总结 | Boot 自动配置了 MVC 全部组件 + 内嵌 Tomcat，省掉 web.xml/配置类 |

---

## 三、时间规划（10-20 ~ 10-23，四天）

| 天 | 内容 | 产出检验 |
|----|------|----------|
| Day 1（10-20） | 上午：**Spring6 第 18 章整合** → 下午：1 → 2 | ch18 的 SqlSessionFactoryBean / MapperScannerConfigurer 跑通；第一个 SpringMVC 程序部署 Tomcat 跑通；@RequestMapping 类+方法组合、@PathVariable 会用 |
| Day 2（10-21） | 3 → 4(快) → 6 → 7 | 四种接收参数都敲过；RESTFul 用户管理实战跟完；**@RestController + @RequestBody 完成 JSON 接口闭环** |
| Day 3（10-22） | 9 → 10 → 8 → 5(两小节) | @ControllerAdvice 全局异常敲过；拦截器执行顺序能画；文件上传跑通；转发重定向能讲 |
| Day 4（10-23） | 11 → 14 → 13(快过) → 12(选做) | 执行流程白板画图；**SSM 整合 demo 完整跑通**（CRUD + 事务 + ajax）——黑马点评的雏形 |

> 每章在 IDEA 新建 Maven web 模块跟做（如 `springmvc-ch01`），Tomcat 用 `tools/` 里的 10.1.19。
> Day 4 结束当晚或次日直接进 Spring Boot——你会发现 ch13 手写的配置类全被 Boot 自动配置替代，正好闭环验证。

---

## 四、学习检验标准（学完 SpringMVC 你应该能做到）

- [ ] 从零建 Maven web 模块 → web.xml + springmvc.xml → 部署 Tomcat，独立跑通一个请求
- [ ] 手写：一个 Controller 里用类+方法 @RequestMapping、@RequestParam(required/defaultValue)、@PathVariable、POJO 接收
- [ ] 手写：@RestController + @RequestBody 完成 JSON 接口，axios 调通（前后端分离最小闭环）
- [ ] 手写：@ControllerAdvice 全局异常处理 + 自定义异常 + 统一响应格式
- [ ] 手写：登录拦截器（含 excludePathPatterns 排除登录/注册路径）
- [ ] SSM 整合 demo 完整跑通（Spring6 + MyBatis + SpringMVC，含声明式事务）
- [ ] 能讲：执行流程画图、拦截器 vs 过滤器、转发 vs 重定向、@RestController 原理（各 1 分钟）

---

## 五、避坑提醒

1. **Tomcat 只用 10.1.19**——`tools/` 里还有 8.5 和 9.0，那俩是 javax 命名空间，配 Spring 6（jakarta）启动直接 ClassNotFoundException
2. **web.xml 版本必须 5.0+**（jakarta），IDEA 新建 web 模块默认可能给 4.0，启动报错先查这个
3. **别陷进视图渲染**（Thymeleaf/JSP）——你的方向后端只出 JSON，第 5 章只留转发/重定向和静态资源
4. **XML 配置不用背**——理解每个文件"为什么存在"即可，Boot 之后全自动化
5. **第 12 章 / 源码剖析是亮点不是必修**——4 天排期放不下就只读 12 章开头"参与者"
6. 乱码分工：POST 用 CharacterEncodingFilter；GET 在 Tomcat 10 默认 UTF-8 已处理
7. ch6 用户管理实战的页面用资料配套的（`resources/`），别在前端上浪费时间
8. 学完立刻进 Spring Boot——ch13 的配置类会被 Boot 自动配置整体替代，两边对着看理解最深

# Kotlin Design Patterns Demo Project

这是一个使用 Kotlin 编写的演示项目，展示了多种设计模式的实际应用。项目基于 Spring Boot 构建，包含了多个业务场景的实现示例。

## 📋 项目概述

本项目通过实际的业务场景演示了以下设计模式：
- 策略模式 (Strategy Pattern)
- 状态模式 (State Pattern)
- 工厂模式 (Factory Pattern)
- 密封类 (Sealed Classes)

## 🛠 技术栈

- **语言**: Kotlin 2.2.21
- **框架**: Spring Boot 4.0.2
- **Java 版本**: Java 17
- **构建工具**: Gradle (Kotlin DSL)
- **测试框架**: JUnit 5

## 📁 项目结构

```
src/
├── main/kotlin/com/mycompany/myproject/demo/
│   ├── DemoApplication.kt          # Spring Boot 启动类
│   ├── fee/                        # 手续费计算模块
│   │   └── FeeStrategy.kt
│   ├── marsrover/                  # 火星车控制模块
│   │   └── RoverState.kt
│   ├── membership/                 # 会员折扣模块
│   │   └── DiscountStrategy.kt
│   ├── order/                      # 订单状态管理模块
│   │   └── OrderState.kt
│   ├── payment/                    # 支付方式模块
│   │   └── PaymentMethodFactory.kt
│   ├── tax/                        # 税务计算模块
│   │   └── TaxStrategy.kt
│   └── vehicle/                    # 车辆类型模块
│       └── VehicleStrategy.kt
└── test/kotlin/com/mycompany/myproject/demo/
    └── # 对应的单元测试文件
```

## 🔧 模块详解

### 1. 手续费计算模块 (`fee`)
实现了不同支付方式的手续费计算策略：
- **信用卡**: 按金额的3%收取手续费
- **银行转账**: 固定收取5元手续费  
- **现金**: 免手续费

### 2. 火星车控制模块 (`marsrover`)
使用状态模式实现火星车的方向控制：
- 支持左转(L)、右转(R)、前进(M)指令
- 四个方向状态：北(N)、东(E)、南(S)、西(W)
- 坐标系统追踪位置变化

### 3. 会员折扣模块 (`membership`)
提供灵活的折扣计算机制：
- **会员等级折扣**: 不同会员级别享受不同百分比折扣
- **满额减免**: 达到指定金额可获得固定减免
- 支持策略链式组合

### 4. 订单状态管理模块 (`order`)
使用状态模式管理订单生命周期：
- **新建状态**: 可添加商品、可取消、需付款后发货
- **已付款状态**: 不可添加商品、可发货或取消
- **已发货状态**: 不可修改、不可取消
- **已取消状态**: 终止状态

### 5. 支付方式模块 (`payment`)
密封类实现支付方式管理：
- 现金支付
- 信用卡支付（包含卡号信息）
- 支持手续费计算

### 6. 税务计算模块 (`tax`)
根据不同地区实现税务计算策略

### 7. 车辆类型模块 (`vehicle`)
基于车辆类型的不同行为策略

## 🚀 快速开始

### 环境要求
- Java 17 或更高版本
- Kotlin 2.2.21

### 构建项目
```bash
./gradlew build
```

### 运行测试
```bash
./gradlew test
```

### 启动应用
```bash
./gradlew bootRun
```

## 🧪 测试说明

每个模块都配有完整的单元测试，覆盖了各种业务场景：
- 正常流程测试
- 边界条件测试
- 异常情况处理
- 状态转换验证

运行所有测试：
```bash
./gradlew test
```

## 📊 设计模式应用总结

| 模块 | 主要设计模式 | 应用场景 |
|------|-------------|----------|
| Fee | 策略模式 + 工厂模式 | 不同支付方式的手续费计算 |
| MarsRover | 状态模式 + 密封类 | 火星车方向和移动控制 |
| Membership | 策略模式 + 工厂模式 | 会员折扣计算 |
| Order | 状态模式 + 密封类 | 订单状态流转管理 |
| Payment | 密封类 + 工厂方法 | 支付方式创建和管理 |

## 🤝 开发规范

- 使用 Kotlin 习惯用法
- 遵循 Spring Boot 最佳实践
- 保持代码简洁和可读性
- 完整的单元测试覆盖率
- 清晰的注释和文档

## 📄 许可证

本项目仅供学习和演示用途。

## 👥 贡献

欢迎提交 Issue 和 Pull Request 来改进这个演示项目。

## 📌 Summary
- State Pattern:
```kotlin
sealed class XxxState {
    abstract fun action1 (): XxxState
    abstract fun action2 (): XxxState
    
    data object State1: XxxState() {
        override fun action1(): XxxState {
            return State2
        }
        override fun action2(): XxxState {
            return State1
        }
    }
    data object State2: XxxState() {
        override fun action1(): XxxState {
            return State1
        }
        override fun action2(): XxxState {
            return State2
        }
    }
}
class Xxx {
    var state: XxxState = XxxState.State1 // initial state when Xxx created
    fun act(command: String) {
        when (command) {
            "cmd1" -> state = state.operation1()
            "cmd2" -> state = state.operation2()
        }
    }
}
```
- Factory Pattern:
```kotlin
sealed class XxxFactory {
    data object Xxx1: XxxFactory()
    data class Xxx2(val param: String): XxxFactory()
    fun everyInstanceCanDoSomethingDifferently() {
        when (this) {
            is Xxx1 -> println("Xxx1")
            is Xxx2 -> println("Xxx2: $param")
            else -> throw IllegalArgumentException("Invalid type")
        }
    }
    companion object {
        fun create(type: String): XxxFactory {
            return when (type) {
                "type1" -> Xxx1
                "type2" -> Xxx2("param")
                else -> throw IllegalArgumentException("Invalid type")
            }
        }
    }
}
```
- Strategy Pattern:

```kotlin
interface XxxStrategy {
    fun doSomething()
}
class XxxStrategy1 : XxxStrategy {
    override fun doSomething() {}
}
class XxxStrategy2 : XxxStrategy {
    override fun doSomething() {}
}
data object XxxStrategiesFactory {
    fun create(params: String): List<XxxStrategy> {
        return listOf(XxxStrategy1(), XxxStrategy2())
    }
}
class Xxx(val strategies: List<XxxStrategy>) {
    fun applyStrategies() {
        for (strategy in strategies) {
            strategy.doSomething()
        }
    }
}
```

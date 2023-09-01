package com.spring.cloud.gateway.sentinel;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayParamFlowItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
@Configuration
public class GatewayConfiguration {

    //==================================================================================================================
    // 默认配置SentinelGatewayBlockExceptionHandler
//    private final List<ViewResolver> viewResolvers;
//    private final ServerCodecConfigurer serverCodecConfigurer;
//
//    public GatewayConfiguration(ObjectProvider<List<ViewResolver>> viewResolversProvider,
//                                ServerCodecConfigurer serverCodecConfigurer) {
//        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
//        this.serverCodecConfigurer = serverCodecConfigurer;
//    }
//
//    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
//        // Register the block exception handler for Spring Cloud Gateway.
//        return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
//    }
    //==================================================================================================================

    @PostConstruct
    public void doInit() {
        initBlockRequestHandler();
        initCustomizedApis();
        initGatewayRules();
    }

    /**
     * 您可以在 GatewayCallbackManager 注册回调进行定制：
     *
     * setBlockHandler：注册函数用于实现自定义的逻辑处理被限流的请求，对应接口为 BlockRequestHandler。默认实现为 DefaultBlockRequestHandler，
     * 当被限流时会返回类似于下面的错误信息：Blocked by Sentinel: FlowException。
     *
     */
    private void initBlockRequestHandler() {
        CustomBlockRequestHandler customBlockRequestHandler = new CustomBlockRequestHandler();
        GatewayCallbackManager.setBlockHandler(customBlockRequestHandler);
    }

    private void initCustomizedApis() {
        Set<ApiDefinition> definitions = new HashSet<>();

        Set<ApiPredicateItem> api1PredicateItems= new HashSet<>();
        api1PredicateItems.add(new ApiPathPredicateItem().setPattern("/ahas"));
        api1PredicateItems.add(new ApiPathPredicateItem().setPattern("/product/**")
                .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
        ApiDefinition api1 = new ApiDefinition("some_customized_api").setPredicateItems(api1PredicateItems);
        definitions.add(api1);

        Set<ApiPredicateItem> api2PredicateItems= new HashSet<>();
        api2PredicateItems.add(new ApiPathPredicateItem().setPattern("/**")
                .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
        ApiDefinition api2 = new ApiDefinition("another_customized_api").setPredicateItems(api2PredicateItems);
        definitions.add(api2);

        GatewayApiDefinitionManager.loadApiDefinitions(definitions);
    }

    /**
     * 从 1.6.0 版本开始，Sentinel 提供了 Spring Cloud Gateway 的适配模块，可以提供两种资源维度的限流：
     *
     * route 维度：即在 Spring 配置文件中配置的路由条目，资源名为对应的 routeId
     * 自定义 API 维度：用户可以利用 Sentinel 提供的 API 来自定义一些 API 分组
     *
     */
    private void initGatewayRules() {
        Set<GatewayFlowRule> rules = new HashSet<>();

        rules.add(new GatewayFlowRule("microservice1_route") // resource：资源名，即限流规则的作用对象
                .setCount(2) // count: 限流阈值
                .setIntervalSec(1) // intervalSec：统计时间窗口，单位是秒，默认是 1 秒。
        );

        rules.add(new GatewayFlowRule("microservice1_route")
                .setCount(2) // count：限流阈值
                .setIntervalSec(2) // intervalSec：统计时间窗口，单位是秒，默认是 1 秒。
                .setBurst(2) // burst：应对突发请求时额外允许的请求数目。
                .setParamItem(new GatewayParamFlowItem()
                        // parseStrategy：从请求中提取参数的策略，目前支持
                        // 提取来源 IP（PARAM_PARSE_STRATEGY_CLIENT_IP）、Host（PARAM_PARSE_STRATEGY_HOST）、
                        // 任意 Header（PARAM_PARSE_STRATEGY_HEADER）和任意 URL 参数（PARAM_PARSE_STRATEGY_URL_PARAM）四种模式。
                        .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_CLIENT_IP)
                )
        );

        rules.add(new GatewayFlowRule("httpbin_route")
                .setCount(10)
                .setIntervalSec(1)
                // controlBehavior：流量整形的控制效果，同限流规则的 controlBehavior 字段，目前支持快速失败和匀速排队两种模式，默认是快速失败。
                .setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER)
                // maxQueueingTimeoutMs：匀速排队模式下的最长排队时间，单位是毫秒，仅在匀速排队模式下生效。
                .setMaxQueueingTimeoutMs(600)
                // paramItem：参数限流配置。若不提供，则代表不针对参数进行限流，该网关规则将会被转换成普通流控规则；否则会转换成热点规则。
                .setParamItem(new GatewayParamFlowItem()
                        .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_HEADER)
                        // fieldName：若提取策略选择 Header 模式或 URL 参数模式，则需要指定对应的 header 名称或 URL 参数名称。
                        .setFieldName("X-Sentinel-Flag")
                )
        );

        rules.add(new GatewayFlowRule("httpbin_route")
                .setCount(1)
                .setIntervalSec(1)
                .setParamItem(new GatewayParamFlowItem()
                        .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_URL_PARAM)
                        .setFieldName("pa")
                )
        );

        rules.add(new GatewayFlowRule("httpbin_route")
                .setCount(2)
                .setIntervalSec(30)
                .setParamItem(new GatewayParamFlowItem()
                        // parseStrategy：从请求中提取参数的策略，目前支持
                        // 提取来源 IP（PARAM_PARSE_STRATEGY_CLIENT_IP）、Host（PARAM_PARSE_STRATEGY_HOST）、
                        // 任意 Header（PARAM_PARSE_STRATEGY_HEADER）和任意 URL 参数（PARAM_PARSE_STRATEGY_URL_PARAM）四种模式。
                        .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_URL_PARAM)
                        // fieldName：若提取策略选择 Header 模式或 URL 参数模式，则需要指定对应的 header 名称或 URL 参数名称。
                        .setFieldName("type")
                        // pattern：参数值的匹配模式，只有匹配该模式的请求属性值会纳入统计和流控；若为空则统计该请求属性的所有值。（1.6.2 版本开始支持）
                        .setPattern("warn")
                        // matchStrategy：参数值的匹配策略，目前支持精确匹配（PARAM_MATCH_STRATEGY_EXACT）、
                        // 子串匹配（PARAM_MATCH_STRATEGY_CONTAINS）和正则匹配（PARAM_MATCH_STRATEGY_REGEX）。（1.6.2 版本开始支持）
                        .setMatchStrategy(SentinelGatewayConstants.PARAM_MATCH_STRATEGY_CONTAINS)
                )
        );

        rules.add(new GatewayFlowRule("some_customized_api")
                .setResourceMode(SentinelGatewayConstants.RESOURCE_MODE_CUSTOM_API_NAME)
                .setCount(5)
                .setIntervalSec(1)
                .setParamItem(new GatewayParamFlowItem()
                        .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_URL_PARAM)
                        .setFieldName("pn")
                )
        );

        // 用户可以通过 GatewayRuleManager.loadRules(rules) 手动加载网关规则，
        // 或通过 GatewayRuleManager.register2Property(property) 注册动态规则源动态推送（推荐方式）。
        GatewayRuleManager.loadRules(rules);
    }
}

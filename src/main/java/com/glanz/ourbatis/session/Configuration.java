package com.glanz.ourbatis.session;

import com.glanz.ourbatis.binding.MapperRegistry;
import com.glanz.ourbatis.cache.Cache;
import com.glanz.ourbatis.cache.decorators.FifoCache;
import com.glanz.ourbatis.cache.impl.PerpetualCache;
import com.glanz.ourbatis.datasource.druid.DruidDataSourceFactory;
import com.glanz.ourbatis.datasource.pooled.PooledDataSourceFactory;
import com.glanz.ourbatis.datasource.unpooled.UnpooledDataSourceFactory;
import com.glanz.ourbatis.executor.CachingExecutor;
import com.glanz.ourbatis.executor.Executor;
import com.glanz.ourbatis.executor.SimpleExecutor;
import com.glanz.ourbatis.executor.keygen.KeyGenerator;
import com.glanz.ourbatis.executor.parameter.ParameterHandler;
import com.glanz.ourbatis.executor.resultset.DefaultResultSetHandler;
import com.glanz.ourbatis.executor.resultset.ResultSetHandler;
import com.glanz.ourbatis.executor.statement.PreparedStatementHandler;
import com.glanz.ourbatis.executor.statement.StatementHandler;
import com.glanz.ourbatis.mapping.BoundSql;
import com.glanz.ourbatis.mapping.Environment;
import com.glanz.ourbatis.mapping.MappedStatement;
import com.glanz.ourbatis.mapping.ResultMap;
import com.glanz.ourbatis.plugin.Interceptor;
import com.glanz.ourbatis.plugin.InterceptorChain;
import com.glanz.ourbatis.reflection.MetaObject;
import com.glanz.ourbatis.reflection.factory.DefaultObjectFactory;
import com.glanz.ourbatis.reflection.factory.ObjectFactory;
import com.glanz.ourbatis.reflection.wrapper.DefaultObjectWrapperFactory;
import com.glanz.ourbatis.reflection.wrapper.ObjectWrapperFactory;
import com.glanz.ourbatis.reflection.wrapper.DefaultObjectWrapperFactory;
import com.glanz.ourbatis.scripting.LanguageDriver;
import com.glanz.ourbatis.scripting.LanguageDriverRegistry;
import com.glanz.ourbatis.scripting.xmltags.XMLLanguageDriver;
import com.glanz.ourbatis.transaction.Transaction;
import com.glanz.ourbatis.transaction.jdbc.JdbcTransactionFactory;
import com.glanz.ourbatis.type.TypeAliasRegistry;
import com.glanz.ourbatis.type.TypeHandlerRegistry;

import java.util.*;

/**
 * @Author GlanzWen
 * @Description 配置项
 * @github
 */
public class Configuration {

    // 环境
    protected Environment environment;
    protected boolean useGeneratedKeys = false;
    // 默认启用缓存，cacheEnabled = true/false
    protected boolean cacheEnabled = true;
    // 缓存机制，默认不配置的情况是 SESSION
    protected LocalCacheScope localCacheScope = LocalCacheScope.SESSION;

    // 映射注册机
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    // 映射的语句，存在Map里
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();
    // 缓存,存在Map里
    protected final Map<String, Cache> caches = new HashMap<>();
    // 结果映射，存在Map里
    protected final Map<String, ResultMap> resultMaps = new HashMap<>();
    protected final Map<String, KeyGenerator> keyGenerators = new HashMap<>();

    // 插件拦截器链
    protected final InterceptorChain interceptorChain = new InterceptorChain();

    // 类型别名注册机
    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();
    protected final LanguageDriverRegistry languageRegistry = new LanguageDriverRegistry();

    // 类型处理器注册机
    protected final TypeHandlerRegistry typeHandlerRegistry = new TypeHandlerRegistry();

    // 对象工厂和对象包装器工厂
    protected ObjectFactory objectFactory = new DefaultObjectFactory();
    protected ObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory();

    protected final Set<String> loadedResources = new HashSet<>();

    protected String databaseId;

    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);

        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
        typeAliasRegistry.registerAlias("UNPOOLED", UnpooledDataSourceFactory.class);
        typeAliasRegistry.registerAlias("POOLED", PooledDataSourceFactory.class);

        typeAliasRegistry.registerAlias("PERPETUAL", PerpetualCache.class);
        typeAliasRegistry.registerAlias("FIFO", FifoCache.class);

        languageRegistry.setDefaultDriverClass(XMLLanguageDriver.class);
    }

    public void addMappers(String packageName) {
        mapperRegistry.addMappers(packageName);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

    public TypeAliasRegistry getTypeAliasRegistry() {
        return typeAliasRegistry;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String getDatabaseId() {
        return databaseId;
    }

    /**
     * 创建结果集处理器
     */
    public ResultSetHandler newResultSetHandler(Executor executor, MappedStatement mappedStatement, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        return new DefaultResultSetHandler(executor, mappedStatement, resultHandler, rowBounds, boundSql);
    }

    /**
     * 生产执行器
     */
    public Executor newExecutor(Transaction transaction) {
        Executor executor = new SimpleExecutor(this, transaction);
        // 配置开启缓存，创建 CachingExecutor(默认就是有缓存)装饰者模式
        if (cacheEnabled) {
            executor = new CachingExecutor(executor);
        }
        return executor;
    }

    /**
     * 创建语句处理器
     */
    public StatementHandler newStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        // 创建语句处理器，Mybatis 这里加了路由 STATEMENT、PREPARED、CALLABLE 我们默认只根据预处理进行实例化
        StatementHandler statementHandler = new PreparedStatementHandler(executor, mappedStatement, parameter, rowBounds, resultHandler, boundSql);
        // 嵌入插件，代理对象
        statementHandler = (StatementHandler) interceptorChain.pluginAll(statementHandler);
        return statementHandler;
    }

    // 创建元对象
    public MetaObject newMetaObject(Object object) {
        return MetaObject.forObject(object, objectFactory, objectWrapperFactory);
    }

    // 类型处理器注册机
    public TypeHandlerRegistry getTypeHandlerRegistry() {
        return typeHandlerRegistry;
    }

    public boolean isResourceLoaded(String resource) {
        return loadedResources.contains(resource);
    }

    public void addLoadedResource(String resource) {
        loadedResources.add(resource);
    }

    public LanguageDriverRegistry getLanguageRegistry() {
        return languageRegistry;
    }

    public ParameterHandler newParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        // 创建参数处理器
        ParameterHandler parameterHandler = mappedStatement.getLang().createParameterHandler(mappedStatement, parameterObject, boundSql);
        // 插件的一些参数，也是在这里处理，暂时不添加这部分内容 interceptorChain.pluginAll(parameterHandler);
        return parameterHandler;
    }

    public LanguageDriver getDefaultScriptingLanguageInstance() {
        return languageRegistry.getDefaultDriver();
    }

    public ObjectFactory getObjectFactory() {
        return objectFactory;
    }

    public ResultMap getResultMap(String id) {
        return resultMaps.get(id);
    }

    public void addResultMap(ResultMap resultMap) {
        resultMaps.put(resultMap.getId(), resultMap);
    }

    public void addKeyGenerator(String id, KeyGenerator keyGenerator) {
        keyGenerators.put(id, keyGenerator);
    }

    public KeyGenerator getKeyGenerator(String id) {
        return keyGenerators.get(id);
    }

    public boolean hasKeyGenerator(String id) {
        return keyGenerators.containsKey(id);
    }

    public boolean isUseGeneratedKeys() {
        return useGeneratedKeys;
    }

    public void setUseGeneratedKeys(boolean useGeneratedKeys) {
        this.useGeneratedKeys = useGeneratedKeys;
    }

    public void addInterceptor(Interceptor interceptorInstance) {
        interceptorChain.addInterceptor(interceptorInstance);
    }

    public LocalCacheScope getLocalCacheScope() {
        return localCacheScope;
    }

    public void setLocalCacheScope(LocalCacheScope localCacheScope) {
        this.localCacheScope = localCacheScope;
    }

    public boolean isCacheEnabled() {
        return cacheEnabled;
    }

    public void setCacheEnabled(boolean cacheEnabled) {
        this.cacheEnabled = cacheEnabled;
    }

    public void addCache(Cache cache) {
        caches.put(cache.getId(), cache);
    }

    public Cache getCache(String id) {
        return caches.get(id);
    }

}

